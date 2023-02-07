package com.sa.sample.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.sample.project.dto.CreditCardDto;
import com.sa.sample.project.dto.ResponseEntityDTO;
import com.sa.sample.project.dto.Room;
import com.sa.sample.project.jwt.JwtUtils;
import com.sa.sample.project.kafka.CookiesInfo;
import com.sa.sample.project.kafka.KafkaPackage;
import com.sa.sample.project.kafka.KafkaSenderService;
import com.sa.sample.project.model.Booking;
import com.sa.sample.project.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.WebUtils;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class BookingHotelService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    KafkaSenderService kafkaSenderService;
    @Autowired
    JwtUtils jwtUtils;

    public BookingHotelService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public ResponseEntity<?> save(Booking booking, HttpServletRequest request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntityDTO responseEntityDTO = new ResponseEntityDTO();
        // String cookieFrontEnd = request.getHeader("Headers");
        // System.out.println("What is this "+ cookieFrontEnd);
        Booking booking1 = new Booking();
        Cookie cookie = WebUtils.getCookie(request, "subo8");
        // Cookie cookie = jwtUtils.getJwtFromCookies(request);
        // System.out.println("Do we get the cookie? " + cookie);
        // HttpHeaders headers = new HttpHeaders();
        // assert cookie != null;
        // headers.add("subo8", cookie.getValue());
        // HttpEntity<?> entity = new HttpEntity<>(headers);

        // Byambad add start
        HttpHeaders headers = new HttpHeaders();
        assert cookie != null;
        headers.add("subo8", cookie.getValue());
        HttpEntity<?> entity = new HttpEntity<>(headers);
        // Byambad add end

        if (cookie != null) {
            // System.out.println(cookie.getValue());
            // String jwt = cookie.getValue();
            String jwt = cookie.getValue();
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            booking.setUserName(username);
            ResponseEntity<Room> room = restTemplate.exchange(
                    "http://room-service.default.svc.cluster.local:8088/room/{roomId}", HttpMethod.GET,
                    entity, Room.class, booking.getRoomId());
            // ResponseEntity<Room> room =
            // restTemplate.exchange("http://localhost:8088/room/{roomId}", HttpMethod.GET,
            // entity, Room.class, booking.getRoomId());

            CreditCardDto creditCardDto = restTemplate.getForObject(
                    "http://credit-service.default.svc.cluster.local:9001/creditcards/{creditCardId}",
                    CreditCardDto.class, booking.getCreditCardId());
            // CreditCardDto creditCardDto =
            // restTemplate.getForObject("http://localhost:9001/creditcards/{creditCardId}",
            // CreditCardDto.class, booking.getCreditCardId());
            assert room != null;
            System.out.println("++++++++++++ Room Before" + room.getBody().isAvailable());
            // assert room != null;
            room.getBody().setAvailable(false);
            assert creditCardDto != null;
            // if (booking.getAmount() > creditCardDto.getBalance() ||
            // !room.getBody().isAvailable()) {
            // System.out.println("You have insufficient amount Or the room is already
            // booked");
            // return null;
            // } else
            System.out.println("Amount: " + booking.getAmount());
            System.out.println("Balance: " + creditCardDto.getBalance());
            System.out.println("Room available: " + room.getBody().isAvailable());
            creditCardDto.setBalance(creditCardDto.getBalance() - booking.getAmount());
            String roomString = objectMapper.writeValueAsString(room.getBody());
            String creditCardString = objectMapper.writeValueAsString(creditCardDto);
            restTemplate.put("http://room-service.default.svc.cluster.local:8088/room/", roomString, String.class);
            // restTemplate.put("http://localhost:8088/room/", roomString, String.class);
            restTemplate.put("http://credit-service.default.svc.cluster.local:9001/creditcards/", creditCardString,
                    String.class);
            // restTemplate.put("http://localhost:9001/creditcards/", creditCardString,
            // String.class);

            responseEntityDTO.setBooking(booking1);
            responseEntityDTO.setRoom(room.getBody());
            responseEntityDTO.setPaymentMethod(creditCardDto);
            System.out.println("++++++++++++ Room After" + room);

            // Samuel's Part
            String fullName = jwtUtils.getFullNameFromJwtToken(jwt);
            String email = jwtUtils.getEmailFromJwtToken(jwt);
            KafkaPackage kafkaPackage = new KafkaPackage();
            kafkaPackage.setBooking(booking);
            kafkaPackage.setRoom(room.getBody());
            CookiesInfo cookiesInfo = new CookiesInfo();
            cookiesInfo.setFullName(fullName);
            cookiesInfo.setEmail(email);
            kafkaPackage.setCookiesInfo(cookiesInfo);
            kafkaSenderService.receiveEvent(kafkaPackage);

            // if (!room.isAvailable()) {
            // // return new ResponseEntity<>("Room already booked",
            // HttpStatus.NOT_ACCEPTABLE);
            // return null;
            // } else
            return new ResponseEntity<>(bookingRepository.save(booking), HttpStatus.CREATED);
        } else
            return new ResponseEntity<String>("Please Login", HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<?> saveBackend(Booking booking) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntityDTO responseEntityDTO = new ResponseEntityDTO();
        Booking booking1 = new Booking();
        // HttpHeaders headers = new HttpHeaders();
        // HttpEntity<?> entity = new HttpEntity<>(headers);
        // String username = jwtUtils.getUserNameFromJwtToken("Godwin");
        booking.setUserName("Godwin");
        Room room = restTemplate.getForObject("http://room-service.default.svc.cluster.local:8088/room/{roomId}",
                Room.class, booking.getRoomId());
        // Room room = restTemplate.getForObject("http://localhost:8088/room/{roomId}",
        // Room.class, booking.getRoomId());
        CreditCardDto creditCardDto = restTemplate.getForObject(
                "http://credit-service.default.svc.cluster.local:9001/creditcards/{creditCardId}",
                CreditCardDto.class, booking.getCreditCardId());
        // CreditCardDto creditCardDto =
        // restTemplate.getForObject("http://localhost:9001/creditcards/{creditCardId}",
        // CreditCardDto.class, booking.getCreditCardId());
        assert room != null;
        System.out.println("++++++++++++ Room Before" + room.isAvailable());
        // assert room != null;
        room.setAvailable(false);
        assert creditCardDto != null;
        if (booking.getAmount() > creditCardDto.getBalance() && !room.isAvailable()) {
            System.out.println("You have  insufficient amount Or the room is already booked");
            return null;
        } else
            creditCardDto.setBalance(creditCardDto.getBalance() - booking.getAmount());
        String roomString = objectMapper.writeValueAsString(room);
        String creditCardString = objectMapper.writeValueAsString(creditCardDto);
        restTemplate.put("http://room-service.default.svc.cluster.local:8088/room/", roomString, String.class);
        // restTemplate.put("http://localhost:8088/room/", roomString, String.class);
        restTemplate.put("http://credit-service.default.svc.cluster.local:9001/creditcards/", creditCardString,
                String.class);
        // restTemplate.put("http://localhost:9001/creditcards/", creditCardString,
        // String.class);
        responseEntityDTO.setBooking(booking1);
        responseEntityDTO.setRoom(room);
        responseEntityDTO.setPaymentMethod(creditCardDto);
        System.out.println("++++++++++++ Room After" + room);
        return new ResponseEntity<>(bookingRepository.save(booking), HttpStatus.CREATED);
    }

    public ResponseEntityDTO findById(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).get();
        Room room = restTemplate.getForObject("http://room-service.default.svc.cluster.local:8088/room/{roomId}",
                Room.class, booking.getRoomId());
        // Room room = restTemplate.getForObject("http://localhost:8088/room/{roomId}",
        // Room.class, booking.getRoomId());
        CreditCardDto creditCardDto = restTemplate.getForObject(
                "http://credit-service.default.svc.cluster.local:9001/creditcards/{creditCardId}",
                CreditCardDto.class, booking.getCreditCardId());
        // CreditCardDto creditCardDto =
        // restTemplate.getForObject("http://localhost:9001/creditcards/{creditCardId}",
        // CreditCardDto.class, booking.getCreditCardId());
        return new ResponseEntityDTO(booking, room, creditCardDto);
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public void deleteById(String bookingId) {
        bookingRepository.deleteById(bookingId);
    }

}

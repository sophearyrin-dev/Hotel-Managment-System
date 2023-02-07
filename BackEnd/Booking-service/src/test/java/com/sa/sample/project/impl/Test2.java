package com.sa.sample.project.impl;

import com.sa.sample.project.dto.CreditCardDto;
import com.sa.sample.project.dto.ResponseEntityDTO;
import com.sa.sample.project.dto.Room;
import com.sa.sample.project.jwt.JwtUtils;
import com.sa.sample.project.model.Booking;
import com.sa.sample.project.repository.BookingRepository;
import com.sa.sample.project.service.BookingHotelService;
// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@SpringBootTest(classes = { Test2.class })
public class Test2 {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    RestTemplate restTemplate;

    @Mock
    JwtUtils jwtUtils;

    @InjectMocks
    BookingHotelService service;

    //
    // public ResponseEntityDTO findById(String bookingId) {
    // Booking booking = bookingRepository.findById(bookingId).get();
    // Room room = restTemplate.getForObject("http://localhost:8088/room/{roomId}" ,
    // Room.class,booking.getRoomId());
    // CreditCardDto creditCardDto =
    // restTemplate.getForObject("http://localhost:9001/{creditCardId}",
    // CreditCardDto.class, booking.getCreditCardId());
    // return new ResponseEntityDTO(booking,room, creditCardDto);
    //
    // }

    @Test
    public void get_booking_byID_test() {
        // ClientHttpRequestFactory requestFactory = new
        // HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());

        // CloseableHttpClient client = HttpClients.createDefault();

        // ClientHttpRequestFactory requestFactory = new
        // HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        //
        restTemplate = new RestTemplate();
        String bookingId = "ab";

        Room room = new Room("62a92e51b35c623b239e6488", 1, "vip", 100.0, "good", 2, 2, false, "good", true);
        Booking booking = new Booking("ab", LocalDate.of(2021, 10, 10), LocalDate.of(2021, 10, 11), 2, "no", "aa",
                "62a92e51b35c623b239e6488", 10.0, "aa123");
        // CreditCardDto creditCardDto = new CreditCardDto("ff","a","a","a",10.0,new
        // Date(2021-10-10),10.0);
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(restTemplate.getForObject("http://localhost:8088/room/{roomId}", Room.class, booking.getRoomId()))
                .thenReturn(room);
        // when(restTemplate.getForObject("http://localhost:9001/{creditId}",
        // CreditCardDto.class,
        // booking.getCreditCardId())).thenReturn(creditCardDto);
        // ResponseEntityDTO responseEntityDTO = new ResponseEntityDTO(booking,
        // room,creditCardDto);
        // assertThat(responseEntityDTO).isEqualTo(service.findById(bookingId));
    }

}

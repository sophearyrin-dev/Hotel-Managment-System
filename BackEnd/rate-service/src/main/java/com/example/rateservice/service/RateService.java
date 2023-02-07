package com.example.rateservice.service;

import com.example.rateservice.DTO.ResponseEntityDTO;
import com.example.rateservice.DTO.Room;
import com.example.rateservice.exception.RateNotExistException;
import com.example.rateservice.model.Rate;
import com.example.rateservice.repository.RateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class RateService {

    @Autowired
    RateRepository rateRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${room.endpoint}")
    private String roomEndpoint;

    // public List<Rate> findAll(){
    // return rateRepository.findAll();
    // }

    public Rate addRate(Rate rate) throws JsonProcessingException {
        ResponseEntityDTO vo = new ResponseEntityDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        Rate rate1 = new Rate();
        Room room = restTemplate.getForObject("http://room-service.default.svc.cluster.local:8088/room/{roomId}",
                Room.class, rate.getRoomId());
        // Room room = restTemplate.getForObject("http://localhost:8088/room/{roomId}",
        // Room.class, rate.getRoomId());
        room.setTotalRatings(rate.getRating());

        if (rate.getRating() >= 5) {
            room.setRoomRating("5 stars");
        } else if (rate.getRating() >= 4) {
            room.setRoomRating("4 stars");
        } else {
            room.setRoomRating("Moderate");
        }

        String roomString = objectMapper.writeValueAsString(room);
        restTemplate.put("http://room-service.default.svc.cluster.local:8088/room/", roomString, String.class);
        // restTemplate.put("http://localhost:8088/room/", roomString, String.class);
        vo.setRate(rate1);
        vo.setRoom(room);
        return rateRepository.save(rate);
    }

    public boolean updateRate(String rateID, Rate rate) {
        Optional<Rate> optionalRate = rateRepository.findById(rateID);
        if (!optionalRate.isPresent())
            throw new RateNotExistException("Rate id is invalid " + rateID);
        Rate rateToBeUpdate = optionalRate.get();
        if (rate.getRating() != null) {
            rateToBeUpdate.setRating(rate.getRating());
        }
        rateRepository.save(rateToBeUpdate);
        return true;
    }

    public boolean deleteRate(String rateID) {
        Optional<Rate> optionalRate = rateRepository.findById(rateID);
        if (!optionalRate.isPresent())
            throw new RateNotExistException("Rate id is invalid " + rateID);
        rateRepository.deleteById(rateID);
        return true;
    }
}

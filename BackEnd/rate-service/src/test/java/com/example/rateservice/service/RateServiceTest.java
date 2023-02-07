package com.example.rateservice.service;


import com.example.rateservice.DTO.Room;
import com.example.rateservice.model.Rate;
import com.example.rateservice.repository.RateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RateServiceTest.class})
@TestPropertySource(properties = "room.endpoint=http://localhost:8088")
public class RateServiceTest {

    @Mock
    RateRepository rateRepository;
    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    RateService rateService;

//    @Value("${room.endpoint}")
@Value("${room.endpoint}")
    String roomEndpoint;

    Rate rate;

//    @Test
//    public void addRateTest() throws JsonProcessingException {
//        System.out.println(roomEndpoint);
//        rate = new Rate("1","1","1",4);
//        Room room1 = new Room("1",1,"vip",100.0,"good",2,2,false,"good",true,"4 star",4);
//        when(restTemplate.getForObject(roomEndpoint.concat("/room/{roomID}"), Room.class, rate.getRoomId())).thenReturn(room1);
////        when(restTemplate.getForObject("http://localhost:8088/room/{roomId}", Room.class, rate.getRoomId())).thenReturn(room1);
//        when(rateRepository.save(rate)).thenReturn(rate);
//        assertThat(rateService.addRate(rate)).isEqualTo(rate);
//    }

    @Test
    public void updateRateTest(){
        Optional<Rate> rate =Optional.of(new Rate("1", "1", "1", 4)) ;
        when(rateRepository.findById("1")).thenReturn(rate);
        Rate rateToChange=Rate.builder().rating(2).build();
        when(rateRepository.save(rate.get())).thenReturn(rate.get());
        Boolean result= rateService.updateRate("1",rateToChange );
        assertThat(result).isEqualTo(true);
    }

    @Test
    void deleteRateTest(){
        Optional<Rate> rate =Optional.of(new Rate("2", "2", "2", 4)) ;
        when(rateRepository.findById("2")).thenReturn(rate);
        Boolean result= rateService.deleteRate("2" );
        assertThat(result).isEqualTo(true);
    }
}

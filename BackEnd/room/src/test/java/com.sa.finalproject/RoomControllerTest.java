package com.sa.finalproject;
import com.sa.finalproject.controller.RoomController;
import com.sa.finalproject.dto.UserDTO;
import com.sa.finalproject.model.Room;
import org.assertj.core.api.ObjectEnumerableAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.net.URISyntaxException;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomControllerTest {

    @Autowired
    RoomController roomController;

    private static String createdRoomId = null;
    private static Room createdRoom = null;

    private static String token = null;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void getToken() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + 8080 + "/user/auth/signin";
        URI uri = new URI(baseUrl);
        UserDTO userDTO = new UserDTO();
        userDTO.username = "susu";
        userDTO.password = "admin123";
        //restTemplate.postForEntity(uri,userDTO, String.class);
        token = "";

    }

    private Room postHttp(Room room, String param) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:" + 8088 + "/room/";
        HttpEntity<Room> requestEntity = new HttpEntity<Room>(room, headers);
        ResponseEntity<Room> responseEntity = null;
        if(param!=null) {
            baseUrl += param;
            responseEntity = restTemplate.exchange(new URI(baseUrl), HttpMethod.PUT, requestEntity, Room.class);
        }else{
            responseEntity = restTemplate.postForEntity(new URI(baseUrl), requestEntity, Room.class);
        }
        return responseEntity.getBody();
    }

    private Room getHttp(String roomId) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + 8088 + "/room/"+roomId;
        ResponseEntity<Room> responseEntity = restTemplate.getForEntity(new URI(baseUrl), Room.class);
        return responseEntity.getBody();
    }

    @Test
    public void createRoom() throws URISyntaxException {
        Room room = new Room();
        room.setRoomNumber(903);
        room.setType("Double");
        room.setPrice(120.0);
        room.setNumberOfBeds(2);
        room.setMaxNumberOfGuests(2);
        room.setSmoking(true);
        room.setDescription("City View");
        room.setAvailable(true);
        room.setRoomRating("4 stars");
        room.setTotalRatings(10);


        createdRoom =  postHttp(room,null);
        createdRoomId = createdRoom.getRoomId();

        // createdRoom = roomController.createRoom(room);
        createdRoomId = createdRoom.getRoomId();
        Assert.assertEquals(true,createdRoom.getRoomId()!=null);
        Assert.assertEquals(room.getRoomNumber(),createdRoom.getRoomNumber());
        Assert.assertEquals(room.getType(),createdRoom.getType());
        Assert.assertEquals(room.getPrice(),createdRoom.getPrice());
        Assert.assertEquals(room.getNumberOfBeds(),createdRoom.getNumberOfBeds());
        Assert.assertEquals(room.getMaxNumberOfGuests(),createdRoom.getMaxNumberOfGuests());
        Assert.assertEquals(room.isSmoking(),createdRoom.isSmoking());
        Assert.assertEquals(room.getDescription(),createdRoom.getDescription());
        Assert.assertEquals(room.isAvailable(),createdRoom.isAvailable());
        Assert.assertEquals(room.getRoomRating(),createdRoom.getRoomRating());
        Assert.assertEquals(room.getTotalRatings(),createdRoom.getTotalRatings());

    }


    @Test
    public void updateRoom() throws URISyntaxException {
        Room room = new Room();
        room.setRoomId(createdRoomId);
        room.setRoomNumber(607);
        room.setType("Queen");
        room.setPrice(130.0);
        room.setNumberOfBeds(2);
        room.setMaxNumberOfGuests(2);
        room.setSmoking(false);
        room.setDescription("City View");
        room.setAvailable(true);
        room.setRoomRating("4 stars");
        room.setTotalRatings(10);
        createdRoom =  postHttp(room,createdRoomId);
        Assert.assertEquals(createdRoom.getRoomId(),room.getRoomId());
        Assert.assertEquals(createdRoom.getRoomNumber(),room.getRoomNumber());
        Assert.assertEquals(createdRoom.getType(),room.getType());
        Assert.assertEquals(createdRoom.getPrice(),room.getPrice());
        Assert.assertEquals(createdRoom.getNumberOfBeds(),room.getNumberOfBeds());
        Assert.assertEquals(createdRoom.getMaxNumberOfGuests(),room.getMaxNumberOfGuests());
        Assert.assertEquals(createdRoom.isSmoking(),room.isSmoking());
        Assert.assertEquals(createdRoom.getDescription(),room.getDescription());
        Assert.assertEquals(createdRoom.isAvailable(),room.isAvailable());
        Assert.assertEquals(createdRoom.getRoomRating(),room.getRoomRating());
        Assert.assertEquals(createdRoom.getTotalRatings(),room.getTotalRatings());

    }

    @Test
    public void findRoom() throws URISyntaxException {

        Room room =  getHttp(createdRoomId);

        Assert.assertEquals(createdRoom,room);
        Assert.assertEquals(createdRoom.getRoomId(),room.getRoomId());
        Assert.assertEquals(createdRoom.getRoomNumber(),room.getRoomNumber());
        Assert.assertEquals(createdRoom.getType(),room.getType());
        Assert.assertEquals(createdRoom.getPrice(),room.getPrice());
        Assert.assertEquals(createdRoom.getNumberOfBeds(),room.getNumberOfBeds());
        Assert.assertEquals(createdRoom.getMaxNumberOfGuests(),room.getMaxNumberOfGuests());
        Assert.assertEquals(createdRoom.isSmoking(),room.isSmoking());
        Assert.assertEquals(createdRoom.getDescription(),room.getDescription());
        Assert.assertEquals(createdRoom.isAvailable(),room.isAvailable());
        Assert.assertEquals(createdRoom.getRoomRating(),room.getRoomRating());
        Assert.assertEquals(createdRoom.getTotalRatings(),room.getTotalRatings());
    }
}

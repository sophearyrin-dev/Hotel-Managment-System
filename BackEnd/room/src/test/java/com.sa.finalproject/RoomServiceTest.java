package com.sa.finalproject;


import com.sa.finalproject.model.Room;
import com.sa.finalproject.service.RoomService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoomServiceTest{

    @Autowired
    RoomService roomService;

    private static String createdRoomId = null;
    private static Room createdRoom = null;


    @Test
    public void createRoom() {
        Room room = new Room();
        room.setRoomNumber(901);
        room.setType("Double");
        room.setPrice(120.0);
        room.setNumberOfBeds(2);
        room.setMaxNumberOfGuests(2);
        room.setSmoking(true);
        room.setDescription("City View");
        room.setAvailable(true);
        room.setRoomRating("4 stars");
        room.setTotalRatings(10);

        createdRoom = roomService.createRoom(room);
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
    public void updateRoom(){
        Room room = new Room();
        room.setRoomId(createdRoomId);
        room.setRoomNumber(802);
        room.setType("Queen");
        room.setPrice(130.0);
        room.setNumberOfBeds(2);
        room.setMaxNumberOfGuests(2);
        room.setSmoking(false);
        room.setDescription("City View");
        room.setAvailable(true);
        room.setRoomRating("4 stars");
        room.setTotalRatings(10);
        createdRoom =  roomService.updateRoom(room.getRoomId(),room);
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
    public void findRoom(){

        Room room =  roomService.findById(createdRoomId);
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

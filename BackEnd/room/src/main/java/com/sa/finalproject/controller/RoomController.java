package com.sa.finalproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.finalproject.model.Room;
import com.sa.finalproject.security.jwt.JwtUtils;
import com.sa.finalproject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/room")
public class RoomController {

    @Value("${subo8.app.jwtCookieName}")
    private String jwtCookie;

    @Autowired
    private JwtUtils jwtUtils;

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    @CrossOrigin("http://localhost:3000")
    public List<Room> getRooms(HttpServletRequest request) throws AuthenticationException {
        return roomService.getRooms();
    }

    @GetMapping("/{roomId}")
    @CrossOrigin("http://localhost:3000")
    public Room getRoomById(HttpServletRequest request,@PathVariable String roomId) throws AuthenticationException {
        return roomService.findById(roomId);
    }

    @PostMapping("/")
    public Room createRoom(HttpServletRequest request,@RequestBody Room room) throws AuthenticationException
    {
//        Cookie cookie = WebUtils.getCookie(request, "subo8");
//        String jwt = cookie.getValue();
//        String userRole = jwtUtils.getUserRoleFromJwtToken(jwt);
//        //System.out.println("User Role "+userRole);
//        if(!userRole.equals("ROLE_ADMIN"))
//            throw new AuthenticationException();

        return roomService.createRoom(room);
    }

    @DeleteMapping("/{roomId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void deleteRoom(HttpServletRequest request,@PathVariable String roomId) throws AuthenticationException {
        // Cookie cookie = WebUtils.getCookie(request, "subo8");
        // String jwt = cookie.getValue();
        // String userRole = jwtUtils.getUserRoleFromJwtToken(jwt);
        // //System.out.println("User Role "+userRole);
        // if(!userRole.equals("ROLE_ADMIN"))
        //     throw new AuthenticationException();
        roomService.deleteRoom(roomId);

    }
    @PutMapping("/{roomId}")
    @CrossOrigin("http://localhost:3000")
    public Room updateRoom(HttpServletRequest request,@PathVariable String roomId, @RequestBody Room room) throws AuthenticationException {
        // Cookie cookie = WebUtils.getCookie(request, "subo8");
        // String jwt = cookie.getValue();
        // String userRole = jwtUtils.getUserRoleFromJwtToken(jwt);
        // //System.out.println("User Role "+userRole);
        // if(!userRole.equals("ROLE_ADMIN"))
        //     throw new AuthenticationException();
        return roomService.updateRoom(roomId, room);
    }

    //for Room Rating Service
    @GetMapping("/available")
    public List<Room> availableRooms(){

        return roomService.availableRooms();
    }

    //for Room Rating Service
    @GetMapping("/not-available")
    public List<Room> noAvailableRooms(){

        return roomService.notAvailableRooms();
    }

    //for Room Booking
    @PutMapping("/")
    public Room updateRoomServiceLevel(@RequestBody String room) {
        ObjectMapper objectMapper = new ObjectMapper();
        Room room1 = null;
        try {
            room1 = objectMapper.readValue(room, Room.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return roomService.updateRoomServiceCommunication(room1);

    }

    @GetMapping("/checkout/{roomId}")
    public ResponseEntity<Object> releaseRoomById(HttpServletRequest request,@PathVariable String roomId) throws AuthenticationException
    {
        // Cookie cookie = WebUtils.getCookie(request, "subo8");
        // String jwt = cookie.getValue();
        // String userRole = jwtUtils.getUserRoleFromJwtToken(jwt);
        // //System.out.println("User Role "+userRole);
        // if(!userRole.equals("ROLE_ADMIN") && !userRole.equals("ROLE_USER"))
        //     throw new AuthenticationException();
        Room room = null;

        try {
            room = roomService.roomCheckout(roomId);
        }catch (IllegalStateException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

}

package com.sa.finalproject.repository;

import com.sa.finalproject.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room,String> {

    Room findByRoomNumber(Integer roomNumber);

}

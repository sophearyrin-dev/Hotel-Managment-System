package com.example.rateservice.DTO;
import com.example.rateservice.model.Rate;

public class ResponseEntityDTO {
    private Rate rate;
    private Room room;
    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

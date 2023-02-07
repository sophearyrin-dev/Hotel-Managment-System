package com.miu.edu.cs590.project.producer.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationInfo {

    private String id;
    private String bookingId;
    private String roomId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfArrival;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfDeparture;
    private String email;
    private int numberOfRooms;
    private String otherReservations;
    private String userName;
    private Integer roomNumber;
    private String type;
    private Double price;
    private String bedType;
    private Integer numberOfBeds;
    private Integer maxNumberOfGuests;
    private boolean smoking;
    private String description;

    public boolean getSmoking() {
        return this.smoking;
    }
}

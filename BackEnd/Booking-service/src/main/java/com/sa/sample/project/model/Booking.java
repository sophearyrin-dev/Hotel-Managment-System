package com.sa.sample.project.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@ToString
public class Booking {
//    @Transient
//    public static final String SEQUENCE_NAME = "booking_sequence";
    @Id
    private String bookingId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfArrival;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfDeparture;
    private int numberOfRooms;
    private String otherReservations;
    private String userName;
    private String roomId;
    private Double amount;
   private String creditCardId;


    public Booking(LocalDate of, LocalDate of1, int i, String any_other_reservation) {
        this.dateOfArrival = of;
        this.dateOfDeparture = of1;
        this.numberOfRooms = i;
        this.otherReservations = any_other_reservation;
    }

//    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
//    @JoinTable(name = "users_bookings", joinColumns = @JoinColumn(name = "bookingId"), inverseJoinColumns = @JoinColumn(name = "id"))
//    private Set<User> userDetails = new HashSet<>();
//
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bookHotel")
//    private List<Room> rooms = new ArrayList<>();

}

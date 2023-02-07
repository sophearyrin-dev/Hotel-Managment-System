package com.sa.finalproject.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("room")

public class Room {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
   private String roomId;
    private Integer roomNumber;
    private String type;
    private Double price;
    private String bedType;
    private Integer numberOfBeds;
    private Integer maxNumberOfGuests;
    private boolean smoking;
    private String description;
    private boolean available;
    private String roomRating;
    private  Integer totalRatings;
}

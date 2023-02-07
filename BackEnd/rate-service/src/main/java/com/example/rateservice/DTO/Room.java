package com.example.rateservice.DTO;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;

public class Room {

    @Id
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

    public Room(){}
    public Room(String roomId, Integer roomNumber, String type, Double price, String bedType, Integer numberOfBeds, Integer maxNumberOfGuests, boolean smoking, String description, boolean available, String roomRating, Integer totalRatings) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.bedType = bedType;
        this.numberOfBeds = numberOfBeds;
        this.maxNumberOfGuests = maxNumberOfGuests;
        this.smoking = smoking;
        this.description = description;
        this.available = available;
        this.roomRating = roomRating;
        this.totalRatings = totalRatings;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Integer getMaxNumberOfGuests() {
        return maxNumberOfGuests;
    }

    public void setMaxNumberOfGuests(Integer maxNumberOfGuests) {
        this.maxNumberOfGuests = maxNumberOfGuests;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getRoomRating() {
        return roomRating;
    }

    public void setRoomRating(String roomRating) {
        this.roomRating = roomRating;
    }

    public Integer getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(Integer totalRatings) {
        this.totalRatings = totalRatings;
    }

}

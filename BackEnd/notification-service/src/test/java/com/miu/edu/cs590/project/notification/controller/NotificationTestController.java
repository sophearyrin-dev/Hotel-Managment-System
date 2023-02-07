package com.miu.edu.cs590.project.notification.controller;


import com.miu.edu.cs590.project.notification.model.NotificationInfo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class NotificationTestController {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8110);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetOneNotification() {

        NotificationInfo notificationInfo = new NotificationInfo(null, "1","1", LocalDate.of(2022,6,16),
                LocalDate.of(2022,6,22),"sbartolome@gmail.com",2,"Rental Car","Samuel Bartolome",
                304,500.00,"Double Room",34.50, "Queen Size", 2, 3,false,"New Customers");
        // Adding Objects to the Database
        given()
                .contentType("application/json")
                .body(notificationInfo)
                .when().post("/notifications").then()
                .statusCode(200);

        // Test getting the InformationTest Object
        given()
                .when()
                .get("/notifications/sbartolome@gmail.com")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("bookingId", hasItems("1"))
                .body("roomId", hasItems("1"))
                .body("email", hasItems("sbartolome@gmail.com"))
                .body("numberOfRooms", hasItems(2))
                .body("otherReservations", hasItems("Rental Car"))
                .body("fullName", hasItems("Samuel Bartolome"))
                .body("roomNumber", hasItems(304))
                .body("amount", hasItems(500.00f))
                .body("type", hasItems("Double Room"))
                .body("price", hasItems(34.50f))
                .body("bedType", hasItems("Queen Size"))
                .body("numberOfBeds", hasItems(2))
                .body("maxNumberOfGuests", hasItems(3))
                .body("smoking", hasItems(false))
                .body("description", hasItems("New Customers"));


        // Cleaning up
        given()
                .when()
                .delete("/notifications/sbartolome@gmail.com");
    }

    @Test
    public void testGetAllNotification() {
        NotificationInfo notificationInfo = new NotificationInfo(null, "1","1", LocalDate.of(2022,6,16),
                LocalDate.of(2022,6,22),"sbartolome@gmail.com",2,"Rental Car","Samuel Bartolome",
                304,500.00,"Double Room",34.50, "Queen Size", 2, 3,false,"New Customers");
        NotificationInfo notificationInfo2 = new NotificationInfo(null, "3","3", LocalDate.of(2022,6,19),
                LocalDate.of(2022,6,25),"jorge@gmail.com",1,"Motorbike","Jorge Hernandez",
                310,450.00,"Single Room",60.95, "King Size", 3, 4,true,"VIP Customer");

        // Adding Objects to the Database
        given()
                .contentType("application/json")
                .body(notificationInfo)
                .when().post("/notifications").then()
                .statusCode(200);
        given()
                .contentType("application/json")
                .body(notificationInfo2)
                .when().post("/notifications").then()
                .statusCode(200);

        // Test getting the NotificationInfo List
        given()
                .when()
                .get("/notifications")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("bookingId", hasItems("1","3"))
                .body("roomId", hasItems("1","3"))
                .body("email", hasItems("sbartolome@gmail.com","jorge@gmail.com"))
                .body("numberOfRooms", hasItems(2,1))
                .body("otherReservations", hasItems("Rental Car","Motorbike"))
                .body("fullName", hasItems("Samuel Bartolome","Jorge Hernandez"))
                .body("roomNumber", hasItems(304,310))
                .body("amount", hasItems(500.00f,450.00f))
                .body("type", hasItems("Double Room","Single Room"))
                .body("price", hasItems(34.50f,60.95f))
                .body("bedType", hasItems("Queen Size","King Size"))
                .body("numberOfBeds", hasItems(2,3))
                .body("maxNumberOfGuests", hasItems(3,4))
                .body("smoking", hasItems(false,true))
                .body("description", hasItems("New Customers","VIP Customer"));

        // Cleaning up
        given()
                .when()
                .delete("/notifications/sbartolome@gmail.com");
        given()
                .when()
                .delete("/notifications/jorge@gmail.com");
    }

}

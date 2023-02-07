package com.miu.edu.cs590.project.notification.messaging;

import com.miu.edu.cs590.project.notification.model.NotificationInfo;

public class EmailTemplate {

    public static String createCustomerEmail(NotificationInfo notificationInfo) {

        String smoking;

        if(notificationInfo.getSmoking()) {
            smoking = "For Smokers";
        } else {
            smoking = "No Smokers";
        }

        String html = "<!doctype html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
                "      xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\"\n" +
                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>Email</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div><p>Mr/Miss <b>" + notificationInfo.getFullName() + "</b></p></div>\n" +
                "\n" +
                "<div><p> Your reservation has been made successfully. Here we leave the information of your room: </p></div>\n" +
                "<div><p> Here you have the information of your reservation: </p></div>\n" +
                "<div>"+
                "   <ul>" +
                "       <li> Number of rooms Booked: " + notificationInfo.getNumberOfRooms() + "</li> \n" +
                "       <li> Room number: " + notificationInfo.getRoomNumber() + "</li> \n" +
                "       <li> Room type: " + notificationInfo.getType() + " " + smoking + "</li> \n" +
                "       <li> Type of bed: " + notificationInfo.getBedType() + "</li> \n" +
                "       <li> Number of beds: " + notificationInfo.getNumberOfBeds() +"</li> \n" +
                "       <li> Number of Guests: " + notificationInfo.getMaxNumberOfGuests() + "</li> \n" +
                "       <li> Number of rooms Booked: " + notificationInfo.getNumberOfRooms() + "</li> \n" +
                "       <li> Price of the rooms Booked: $" + notificationInfo.getPrice() + "</li> \n" +
                "       <li> Total Amount: $" + notificationInfo.getPrice() + "</li> \n" +
                "       <li> Arrival Date: " + notificationInfo.getDateOfArrival() + "</li> \n" +
                "       <li> Departure Date: " + notificationInfo.getDateOfDeparture() + "</li> \n" +
                "       <li> Other reservations: " + notificationInfo.getOtherReservations() + "</li> \n" +
                "       <li> Description: " + notificationInfo.getDescription() + "</li> \n" +
                "   </ul>" +
                "</div>" +
                "<div><p> It is a pleasure for us to have you as a guest in out Hotel and we hope everything is to your liking.</p></div> \n" +
                "<div><p> If you have questions, please do not hesitate to contact us through our customer service at 641-2345-1231.</p></div> \n" +
                "<div><br><img src=\"https://www.freepnglogos.com/uploads/hotel-logo-png/download-building-hotel-clipart-png-33.png\" width=\"200\" alt=\"download building hotel clipart png\" /></a></div>" +
                "<br><b>Best Regards</b>" +
                "</body>\n" +
                "</html>\n";

        return html;
    }

    public static String createOwnerEmail(NotificationInfo notificationInfo) {

        String smoking;

        if(notificationInfo.getSmoking()) {
            smoking = "For Smokers";
        } else {
            smoking = "No Smokers";
        }

        String html = "<!doctype html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
                "      xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\"\n" +
                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>Email</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div><p>A new customer has been registered in the Hotel</p></div>\n" +
                "\n" +
                "<div><p> Here is the customer information: </p></div>\n" +
                "<div>"+
                "   <ul>" +
                "       <li> Customer UserName: " + notificationInfo.getFullName() + "</li> \n" +
                "       <li> Customer email: " + notificationInfo.getEmail() + "</li> \n" +
                "       <li> Number of rooms Booked: " + notificationInfo.getNumberOfRooms() + "</li> \n" +
                "       <li> Room number: " + notificationInfo.getRoomNumber() + "</li> \n" +
                "       <li> Room type: " + notificationInfo.getType() + " " + smoking + "</li> \n" +
                "       <li> Type of bed: " + notificationInfo.getBedType() + "</li> \n" +
                "       <li> Number of beds: " + notificationInfo.getNumberOfBeds() +"</li> \n" +
                "       <li> Number of Guests: " + notificationInfo.getMaxNumberOfGuests() + "</li> \n" +
                "       <li> Number of rooms Booked: " + notificationInfo.getNumberOfRooms() + "</li> \n" +
                "       <li> Price of the rooms Booked: $" + notificationInfo.getPrice() + "</li> \n" +
                "       <li> Total Amount: $" + notificationInfo.getPrice() + "</li> \n" +
                "       <li> Arrival Date: " + notificationInfo.getDateOfArrival() + "</li> \n" +
                "       <li> Departure Date: " + notificationInfo.getDateOfDeparture() + "</li> \n" +
                "       <li> Other reservations: " + notificationInfo.getOtherReservations() + "</li> \n" +
                "       <li> Description: " + notificationInfo.getDescription() + "</li> \n" +
                "   </ul>" +
                "</div>" +
                "</body>\n" +
                "</html>\n";

        return html;
    }


}

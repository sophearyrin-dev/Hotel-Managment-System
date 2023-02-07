package com.miu.edu.cs590.project.notification.repository;

import com.miu.edu.cs590.project.notification.model.NotificationInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@DataMongoTest
public class NotificationsRepositoryTest {

    @Autowired
    private NotificationInfoRepository notificationInfoRepository;

    @Test
    public void whenFindByEmail_thenReturnInformationTest() {

        NotificationInfo notificationInfo = new NotificationInfo(null, "1","1", LocalDate.of(2022,6,16),
                LocalDate.of(2022,6,22),"sbartolome@gmail.com",2,"Rental Car","Samuel Bartolome",
                304,500.00,"Double Room",34.50, "Queen Size", 2, 3,false,"New Customers");

        notificationInfoRepository.save(notificationInfo);

        NotificationInfo found = notificationInfoRepository.findByFullName("Samuel Bartolome");

        assertThat(found.getBookingId()).isEqualTo("1");
        assertThat(found.getRoomId()).isEqualTo("1");
        assertThat(found.getEmail()).isEqualTo("sbartolome@gmail.com");
        assertThat(found.getNumberOfRooms()).isEqualTo(2);
        assertThat(found.getOtherReservations()).isEqualTo("Rental Car");
        assertThat(found.getFullName()).isEqualTo("Samuel Bartolome");
        assertThat(found.getRoomNumber()).isEqualTo(304);
        assertThat(found.getAmount()).isEqualTo(500.00f);
        assertThat(found.getType()).isEqualTo("Double Room");
        assertThat(found.getPrice()).isEqualTo(34.50f);
        assertThat(found.getBedType()).isEqualTo("Queen Size");
        assertThat(found.getNumberOfBeds()).isEqualTo(2);
        assertThat(found.getMaxNumberOfGuests()).isEqualTo(3);
        assertThat(found.getSmoking()).isEqualTo(false);
        assertThat(found.getDescription()).isEqualTo("New Customers");

        notificationInfoRepository.deleteByEmail("sbartolome@gmail.com");

    }

}

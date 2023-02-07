package com.sa.sample.project.controller;


import com.sa.sample.project.model.Booking;
import com.sa.sample.project.service.BookingHotelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BookingController.class)
public class BookControllerTest {
    @Autowired
    @MockBean
    private BookingHotelService bookingHotelService;
    @Before
    public void setUp() {
        List<Booking> bookings = Arrays.asList(
                new Booking("62a59e285e30d468b7223032", LocalDate.of(2022,2,22),
                        LocalDate.of(2022,2,22),3,"Any other reservation","User name","roomId",909.0 ,"payment method"),
                new Booking("62a59e285e30d468b7223032",LocalDate.of(2022,2,22),
                        LocalDate.of(2022,2,22),3,"Any other reservation", "User name","roomId",676.0,"payment")
        );
        Mockito.when(bookingHotelService.findAll()).thenReturn(bookings);
    }

//    @Test
//    public void testListBooks() throws Exception {
//        mockMvc.perform(get("/api/v1/booking/"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("/api/v1/booking/"))
//                .andExpect(model().attributeExists("bookings"))
//                .andExpect(model().attribute("bookings", iterableWithSize(2)));
//    }
}

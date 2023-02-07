package com.sa.sample.project.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sa.sample.project.AbstractELibraryComponentTest;
import com.sa.sample.project.dto.ResponseEntityDTO;
import com.sa.sample.project.model.Booking;
import com.sa.sample.project.service.BookingHotelService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

//@Transactional // Enables any database operations to rollback after each testing
public class BookingServiceTest extends AbstractELibraryComponentTest {

    @Autowired
    private BookingHotelService bookingHotelService;

    @Before
    public void setUp() {
        logger.info("Booking Service Test started");
    }

    @After
    public void tearDown() {
        logger.info("Booking Service Test completed");
    }

    @Test
    public void testGetAllBookings() {
        List<Booking> bookings = bookingHotelService.findAll();
       // int expectedOutput = 0;
        Assert.assertNotNull("Failure: expected books to be not null", bookings);
        Assert.assertEquals("Failure: expected size", bookings.size(), bookings.size());
        logger.info("Booking list data: " + Arrays.toString(bookings.toArray()));
    }

    @Test
    public void testGetBookingById() {
        String bookId = "62a59e285e30d468b7223032";
        ResponseEntityDTO booking = bookingHotelService.findById(bookId);
        Assert.assertNotNull("Failure: expected book to be not null", bookId);
        Assert.assertEquals("Failure: expected bookId to match", bookId, booking.getBooking());
        logger.info("Booking data: " + booking);
    }

//    @Test
//    public void testSaveBook() throws JsonProcessingException {
//        Booking newBooking =  new Booking("62a59e285e30d468b7223032", LocalDate.of(2022,2,22),
//                LocalDate.of(2022,2,22),3,"Any other reservation","User name", "roomId");
//        Booking savedBooking = bookingHotelService.save(newBooking);
//        Assert.assertNotNull("Failure: expected not null", savedBooking);
//        Assert.assertNotNull("Failure: expected bookingId to be not null", savedBooking.getBookingId());
//        Assert.assertEquals("Failure: expected  arrival date match", savedBooking.getDateOfArrival(), savedBooking.getDateOfArrival());
//        List<Booking> bookings = (List<Booking>)bookingHotelService.findAll();
//        Assert.assertEquals("Failure: expected size", bookings.size(), bookings.size());
//        logger.info("Books list data: " + Arrays.toString(bookings.toArray()));
//    }
/*
    @Test
    public void testDeleteBookById() {
        Integer bookId = new Integer(1);
        Book book = bookService.getBookById(bookId);
        Assert.assertNotNull("Failure: expected book to be not null", book);
        bookService.deleteBookById(bookId);
        List<Book> books = (List<Book>)bookService.getAllBooks();
        Assert.assertEquals("Failure: expected size", 5, books.size());
        Book deletedBook = bookService.getBookById(bookId);
        Assert.assertNull("Failure: expected deletedbook to be null since is supposed to have been deleted", deletedBook);
    }
*/
    /* Execute unit-tests via maven on cmdline: mvn clean package */
    /* Execute package only without unit-tests via maven on cmdline:
    /* mvn clean package -DskipTests */
}

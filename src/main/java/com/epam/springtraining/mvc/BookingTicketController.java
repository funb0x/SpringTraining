package com.epam.springtraining.mvc;

import beans.models.Ticket;
import beans.models.User;
import beans.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/ticket")
public class BookingTicketController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/price", method = POST)
    public double getTicketPrice(String eventName, String auditoriumName, @DateTimeFormat(pattern="yyyy-MM-dd") Date dateTime, int[] seats, User user) {
        return bookingService.getTicketPrice(eventName, auditoriumName, dateTime, Arrays.stream(seats).boxed().collect(Collectors.toList()), user);
    }

    @RequestMapping(value = "/book", method = POST)
    public Ticket bookTicket(User user, @DateTimeFormat(pattern="yyyy-MM-dd") Date date, Ticket ticket) {
        Random random = new Random();
        ticket.setPrice(100.0 + 110.* random.nextDouble());
        return bookingService.bookTicket(user, ticket);
    }

    @RequestMapping(value = "/event", method = GET)
    public List<Ticket> getTicketsForEvent(String event, String auditorium, LocalDateTime date) {
        return bookingService.getTicketsForEvent(event, auditorium, date);
    }

    @RequestMapping(value = "/all", method = GET)
    public List<Ticket> getAllTickets() {
        return bookingService.getAllTickets();
    }

}

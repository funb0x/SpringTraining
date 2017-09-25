package com.epam.springtraining.mvc;

import beans.models.Event;
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

    @RequestMapping(value = "/price/{eventName}/{auditoriumName}/{dateTime}/{seats}", method = GET)
    @ResponseBody
    public double getTicketPrice(String eventName, String auditoriumName, @DateTimeFormat(pattern="yyyy-MM-dd") Date dateTime, int[] seats) {
        return bookingService.getTicketPrice(eventName, auditoriumName, dateTime, Arrays.stream(seats).boxed().collect(Collectors.toList()));
    }

    @RequestMapping(value = "/book", method = POST)
    @ResponseBody
    public Ticket bookTicket(User user, @DateTimeFormat(pattern="yyyy-MM-dd") Date date, Ticket ticket) {
        Random random = new Random();
        ticket.setPrice(100.0 + 110.* random.nextDouble());
        return bookingService.bookTicket(user, ticket);
    }

    @RequestMapping(value = "/event", method = GET)
    @ResponseBody
    public List<Ticket> getTicketsForEvent(String event, String auditorium, LocalDateTime date) {
        return bookingService.getTicketsForEvent(event, auditorium, date);
    }

    @RequestMapping(value = "/all", method = GET)
    @ResponseBody
    public List<Ticket> getAllTickets() {

        List<Ticket> tickets = new ArrayList<>();

        tickets.add(new Ticket(new Event("football"), new Date(), Arrays.asList(1, 2, 3), null, 100.1));
        tickets.add(new Ticket(new Event("cinema"), new Date(), Arrays.asList(1, 2, 3), null, 99.9));

        //return bookingService.getAllTickets();
        return tickets;
    }

}

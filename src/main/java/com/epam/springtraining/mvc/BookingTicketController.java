package com.epam.springtraining.mvc;

import beans.dto.BookingTicketDTO;
import beans.models.Event;
import beans.models.Ticket;
import beans.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;


import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/ticket")
public class BookingTicketController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/price/{eventName}/{auditoriumName}/{date}/{seats}", method = GET)
    @ResponseBody
    public double getTicketPrice(@PathVariable String eventName, @PathVariable String auditoriumName, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date, @PathVariable int[] seats) {
        //return bookingService.getTicketPrice(eventName, auditoriumName, date, Arrays.stream(seats).boxed().collect(Collectors.toList()));
        return 999.99;
    }

    @RequestMapping(value = "/book", method = POST)
    @ResponseBody
    public Ticket bookTicket(@RequestBody BookingTicketDTO dto) {
        Random random = new Random();
        dto.getTicket().setPrice(100.0 + 110.* random.nextDouble());
        //return bookingService.bookTicket(dto.getUser(), dto.getTicket());
        return new Ticket(new Event("football"), new Date(), Arrays.asList(1, 2, 3), null, 100.1);
    }

    @RequestMapping(value = "/event/{eventNme}/{auditoriumName}/{date}", method = GET)
    @ResponseBody
    public List<Ticket> getTicketsForEvent(@PathVariable String eventNme, @PathVariable String auditoriumName, @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(new Event(eventNme), date, Arrays.asList(1, 2, 3), null, 100.1));
        tickets.add(new Ticket(new Event(eventNme), date, Arrays.asList(1, 2, 3), null, 99.9));
        return tickets;
        //return bookingService.getTicketsForEvent(eventNme, auditoriumName, date);
    }

    @RequestMapping(value = "/all", method = GET)
    @ResponseBody
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(new Event("football"), new Date(), Arrays.asList(1, 2, 3), null, 100.1));
        tickets.add(new Ticket(new Event("cinema"), new Date(), Arrays.asList(1, 2, 3), null, 99.9));
        return tickets;
        //return bookingService.getAllTickets();
    }

}

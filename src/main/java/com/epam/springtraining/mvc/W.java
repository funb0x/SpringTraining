package com.epam.springtraining.mvc;

import beans.models.Ticket;
import beans.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class W {

    @RequestMapping(value = "/", method = GET)
    public String home(@ModelAttribute("model") ModelMap model) {
        model.put("numbers", new int[]{1,2, 3,4,5,6});
        return "index";
    }


    public void getTicketPrice(String eventName, String auditoriumName, LocalDateTime dateTime, List<Integer> seats, User user) {
        System.out.println(eventName);
        System.out.println(auditoriumName);
        System.out.println(dateTime);
        System.out.println(seats);
        System.out.println(user);
    }

    public Ticket bookTicket(User user, Ticket ticket) {
        return null;
    }

    public List<Ticket> getTicketsForEvent(String event, String auditoriumName, LocalDateTime date) {
        return null;
    }

    public User getUserByEmail(String email) {
        return null;
    }

    public List<User> getUsersByName(String name) {
        return null;
    }

    public List<Ticket> getBookedTickets() {
        return null;
    }

}

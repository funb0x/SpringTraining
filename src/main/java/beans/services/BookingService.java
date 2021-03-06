package beans.services;

import beans.models.Ticket;
import beans.models.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/3/2016
 * Time: 11:22 AM
 */
public interface BookingService {

    double getTicketPrice(String event, String auditorium, Date dateTime, List<Integer> seats);

    Ticket bookTicket(User user, Ticket ticket);

    List<Ticket> getTicketsForEvent(String event, String auditorium, LocalDateTime date);
    
    List<Ticket> getAllTickets();
}

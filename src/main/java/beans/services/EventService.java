package beans.services;

import beans.models.Auditorium;
import beans.models.Event;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/3/2016
 * Time: 11:02 AM
 */
public interface EventService {

    public Event create(Event event);

    public void remove(Event event);

    Event getEvent(String name, Auditorium auditorium, Date dateTime);

    public List<Event> getByName(String name);

    public List<Event> getAll();

    public List<Event> getForDateRange(Date from, Date to);

    public List<Event> getNextEvents(Date to);

    public Event assignAuditorium(Event event, Auditorium auditorium, Date date);
}

package beans.services.impl;

import beans.daos.EventDAO;
import beans.models.Auditorium;
import beans.models.Event;
import beans.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 2/2/2016
 * Time: 12:29 PM
 */
@Service("eventServiceImpl")
@Transactional
public class EventServiceImpl implements EventService {

    private final EventDAO eventDAO;

    @Autowired
    public EventServiceImpl(@Qualifier("eventDAO") EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public Event create(Event event) {
        return eventDAO.create(event);
    }

    public void remove(Event event) {
        eventDAO.delete(event);
    }

    public List<Event> getByName(String name) {
        return eventDAO.getByName(name);
    }

    public Event getEvent(String name, Auditorium auditorium, Date dateTime) {
        return eventDAO.get(name, auditorium, dateTime);
    }

    public List<Event> getAll() {
        return eventDAO.getAll();
    }

    public List<Event> getForDateRange(Date from, Date to) {
        return eventDAO.getForDateRange(from, to);
    }

    public List<Event> getNextEvents(Date to) {
        return eventDAO.getNext(to);
    }

    public Event assignAuditorium(Event event, Auditorium auditorium, Date date) {
        final Event updatedEvent = new Event(event.getId(), event.getName(), event.getRate(), event.getBasePrice(), date, auditorium);
        return eventDAO.update(updatedEvent);
    }
}

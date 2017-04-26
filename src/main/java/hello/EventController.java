package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @RequestMapping(value = "/createEventIfTimeIsAvailableOnly", method = RequestMethod.PUT, produces = "application/json")
    @Transactional
    @ResponseBody
    public Event createEventIfTimeIsAvailableOnly(@RequestBody Event event, HttpServletRequest request, HttpServletResponse response) {
        //
        // Find existing events on the same time and create an event if nothing is found
        //
        List<Event> events = eventRepository.findByStartedBetween(event.getStarted(), event.getEnded());

        if (events.isEmpty()) {
            //
            // TODO: Add notification
            //
            return eventRepository.save(event);
        }

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        return null;
    }

    @RequestMapping(value = "/updateEventIfTimeIsAvailableOnly", method = RequestMethod.PUT, produces = "application/json")
    @Transactional
    @ResponseBody
    public Event updateEventIfTimeIsAvailableOnly(@RequestBody Event event, HttpServletRequest request, HttpServletResponse response) {
        List<Event> existing = eventRepository.findById(event.getId());

        if (!existing.isEmpty()) {
            //
            // Find existing events on the same time and update an event if nothing is found
            //
            List<Event> available = eventRepository.findByStartedBetween(event.getStarted(), event.getEnded());

            if (available.isEmpty()) {
                //
                // TODO: Add notification
                //
                return eventRepository.save(event);
            }
        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        return null;
    }

}

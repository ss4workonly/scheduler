package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @RequestMapping(value = "/createEventIfTimeIsAvailableOnly", method = RequestMethod.PUT)
    @Transactional
    @ResponseBody
    public Event createEventIfTimeIsAvailableOnly(@RequestBody Event event) {
        //
        // Find existing events on the same time and create an event if nothing is found
        //
        List<Event> events = eventRepository.findByStartedBetween(event.getStarted(), event.getEnded());

        if (events.isEmpty()) {
            return eventRepository.save(event);
        }

        return null;
    }

}

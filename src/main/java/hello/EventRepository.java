package hello;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "events", path = "events")
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    List<Event> findByStartedBetween(@Param("started") Long started, @Param("ended") Long ended);
    List<Event> findById(@Param("id") Long id);

}

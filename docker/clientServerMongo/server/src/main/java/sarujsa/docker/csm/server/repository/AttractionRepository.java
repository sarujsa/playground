package sarujsa.docker.csm.server.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import sarujsa.docker.csm.server.model.Attraction;

@Repository
public interface AttractionRepository extends ReactiveCrudRepository<Attraction, String> {

  Mono<Attraction> findByName(String name);

}

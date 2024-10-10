package sarujsa.docker.csm.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sarujsa.docker.csm.dto.AttractionDto;
import sarujsa.docker.csm.server.config.Mapper;
import sarujsa.docker.csm.server.model.Attraction;
import sarujsa.docker.csm.server.repository.AttractionRepository;

@Service
public class AttractionService {

  private final AttractionRepository attractionRepository;
  private final Mapper mapper;

  @Autowired
  public AttractionService(AttractionRepository attractionRepository, Mapper mapper) {
    this.attractionRepository = attractionRepository;
    this.mapper = mapper;
  }

  public Mono<Attraction> insertAttraction(AttractionDto attractionDto) {
    Attraction attraction = mapper.fromAttractionDto(attractionDto);
    return attractionRepository.save(attraction);
  }

  public Mono<AttractionDto> getAttractionByName(String name) {
    return attractionRepository.findByName(name)
        .map(mapper::fromAttraction);
  }
}

package sarujsa.docker.csm.server.config;

import org.springframework.stereotype.Component;
import sarujsa.docker.csm.dto.AttractionDto;
import sarujsa.docker.csm.server.model.Attraction;

@Component
public class Mapper {

  public Attraction fromAttractionDto(AttractionDto dto) {
    Attraction attraction = new Attraction();
    attraction.setName(dto.getName());
    attraction.setDescription(dto.getDescription());
    attraction.setLocation(dto.getLocation().getName());
    return attraction;
  }
}

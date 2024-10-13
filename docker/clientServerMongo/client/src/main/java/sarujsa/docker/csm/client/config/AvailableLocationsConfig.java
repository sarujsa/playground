package sarujsa.docker.csm.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sarujsa.docker.csm.dto.LocationDto;
import sarujsa.docker.csm.dto.LocationType;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AvailableLocationsConfig {

  @Bean("locationMap")
  public Map<String, LocationDto> locationMap() {
    Map<String, LocationDto> map = new HashMap<>();

    map.put("USA", new LocationDto("USA", "US", LocationType.COUNTRY));
    map.put("Nile", new LocationDto("Nile", "EG", LocationType.RIVER));
    map.put("Paris", new LocationDto("Paris", "FR", LocationType.CITY));
    map.put("New York", new LocationDto("New York", "US", LocationType.CITY));
    map.put("Alps", new LocationDto("Alps", "AT", LocationType.MOUNTAIN));
    map.put("Sidney", new LocationDto("Sidney", "AU", LocationType.CITY));

    return map;
  }

}

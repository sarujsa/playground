package sarujsa.docker.csm.client.core;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sarujsa.docker.csm.client.exceptions.LocationNotFoundException;
import sarujsa.docker.csm.dto.AttractionDto;
import sarujsa.docker.csm.dto.LocationDto;

import java.util.Map;
import java.util.regex.Matcher;

@Component
public class RequestGenerator {

  private static final String POST_URL = "/add";
  private static final String GET_URL = "/getOne";

  private final RestTemplate restTemplate;
  private final Map<String,LocationDto> locationMap;

  public RequestGenerator(
      RestTemplate restTemplate, @Qualifier("locationMap") Map<String, LocationDto> locationMap) {
    this.restTemplate = restTemplate;
    this.locationMap = locationMap;
  }

  public ResponseEntity<?> generatePostRequst(Matcher matcher) {
    String attractionName = matcher.group(1).trim();
    String locationName = matcher.group(2).trim();
    String description = matcher.group(3).trim();

    LocationDto locationDto = locationMap.get(locationName);
    if(locationDto == null) {
      throw new LocationNotFoundException();
    }
    AttractionDto dto = new AttractionDto(attractionName, locationMap.get(locationName), description);

    return restTemplate.postForEntity(POST_URL, dto, String.class);
  }

  public ResponseEntity<AttractionDto> generateGetRequst(Matcher matcher) {
    String attractionName = matcher.group(1).trim();
    String url = UriComponentsBuilder.fromUriString(GET_URL)
        .queryParam("name", attractionName)
        .encode()
        .toUriString();
    System.out.println(url);
    return restTemplate.getForEntity(url, AttractionDto.class);
  }

}

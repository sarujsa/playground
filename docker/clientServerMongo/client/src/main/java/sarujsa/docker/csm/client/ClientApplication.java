package sarujsa.docker.csm.client;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sarujsa.docker.csm.dto.Attraction;
import sarujsa.docker.csm.dto.Location;
import sarujsa.docker.csm.dto.LocationType;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ClientApplication {

  private static final String BASE_URL = "http://localhost:8181";
  private static final String API = "/api/v1/attractions/";
  private static final String ADD_URL = BASE_URL + API + "add";
  private static final String GET_ONE_URL = BASE_URL + API + "getOne";

  private static final Map<String,Location> locationMap = new HashMap<>();

  @Bean
  public RestTemplateBuilder restTemplateBuilder() {
    return new RestTemplateBuilder();
  }

  public static void main(String[] args) {
    var context =
        new SpringApplicationBuilder(ClientApplication.class).web(WebApplicationType.NONE).run();

    initLocations();

    RestTemplate restTemplate = initRestTemplate(context);

    postAttractions(restTemplate);
    getAttractions(restTemplate);

  }

  private static void getAttractions(RestTemplate restTemplate) {
    ResponseEntity<Attraction> response = restTemplate.getForEntity(GET_ONE_URL, Attraction.class);
    System.out.println(response.getBody());
  }

  private static RestTemplate initRestTemplate(ConfigurableApplicationContext context) {
    RestTemplateBuilder builder = context.getBean(RestTemplateBuilder.class);
    return builder.build();
  }

  private static void postAttractions(RestTemplate restTemplate) {
    Attraction attraction =
        new Attraction(
            "Empire State Building",
            locationMap.get("New York"),
            "A famous skyscraper in New York");
    ResponseEntity<String> response = restTemplate.postForEntity(ADD_URL, attraction, String.class);
    System.out.println(response.getBody());
  }

  private static void initLocations() {
    Location nile = new Location("Nile", "EG", LocationType.RIVER);
    Location paris = new Location("Paris", "FR", LocationType.CITY);
    Location newYork = new Location("New York", "US", LocationType.CITY);
    Location alps = new Location("Alps", "AT", LocationType.MOUNTAIN);
    Location sidney = new Location("Sidney", "AU", LocationType.CITY);

    locationMap.put("Nile", nile);
    locationMap.put("Paris", paris);
    locationMap.put("New York", newYork);
    locationMap.put("Alps", alps);
    locationMap.put("Sidney", sidney);
  }
}

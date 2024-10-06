package sarujsa.docker.csm.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sarujsa.docker.csm.dto.Attraction;

public class ClientApplication {

  private static final String BASE_URL = "http://localhost:8181";
  private static final String API = "/api/v1/attractions/";
  private static final String ADD_URL = BASE_URL + API + "add";
  private static final String GET_ONE_URL = BASE_URL + API + "getOne";

  public static void main(String[] args) {
    RestTemplateBuilder builder = new RestTemplateBuilder();
    RestTemplate restTemplate = builder.build();
    Attraction attraction = new Attraction("", null, null);
    ResponseEntity<String> response = restTemplate.postForEntity(ADD_URL, attraction, String.class);
    System.out.println(response.getBody());
  }

}

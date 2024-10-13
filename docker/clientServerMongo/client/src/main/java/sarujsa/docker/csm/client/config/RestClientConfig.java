package sarujsa.docker.csm.client.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

  private static final String BASE_URL = "http://csm_server:8181";
  private static final String API = "/api/v1/attractions/";

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplateBuilder().rootUri(BASE_URL + API).build();
  }
}

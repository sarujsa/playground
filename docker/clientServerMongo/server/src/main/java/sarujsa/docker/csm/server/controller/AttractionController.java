package sarujsa.docker.csm.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sarujsa.docker.csm.dto.Attraction;

@RestController("/api/v1/attractions")
public class AttractionController {

  @PostMapping("/add")
  public Mono<ResponseEntity<?>> addAttraction(@RequestBody Attraction attraction) {
    System.out.println("add invodked with param: " + attraction);
    return Mono.empty();
  }

  @GetMapping("/getOne")
  public Mono<ResponseEntity<Attraction>> getAttractionByName(@RequestParam String name) {
    System.out.println("getOne invoked with param: " + name);
    return Mono.empty();
  }

}

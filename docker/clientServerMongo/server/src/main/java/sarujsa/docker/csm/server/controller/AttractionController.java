package sarujsa.docker.csm.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sarujsa.docker.csm.dto.AttractionDto;

@RestController
@RequestMapping("/api/v1/attractions")
public class AttractionController {

  @PostMapping("/add")
  public Mono<ResponseEntity<?>> addAttraction(@RequestBody AttractionDto attractionDto) {
    System.out.println("add invoked with param: " + attractionDto);
    return Mono.empty();
  }

  @GetMapping("/getOne")
  public Mono<ResponseEntity<AttractionDto>> getAttractionByName(@RequestParam String name) {
    System.out.println("getOne invoked with param: " + name);
    return Mono.empty();
  }

}

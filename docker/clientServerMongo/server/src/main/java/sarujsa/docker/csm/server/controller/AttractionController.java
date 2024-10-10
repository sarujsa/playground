package sarujsa.docker.csm.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sarujsa.docker.csm.dto.AttractionDto;
import sarujsa.docker.csm.server.service.AttractionService;

@RestController
@RequestMapping("/api/v1/attractions")
public class AttractionController {

  private AttractionService attractionService;

  @Autowired
  public AttractionController(AttractionService attractionService) {
    this.attractionService = attractionService;
  }

  @PostMapping("/add")
  public Mono<ResponseEntity<?>> addAttraction(@RequestBody AttractionDto attractionDto) {
    System.out.println("add invoked with param: " + attractionDto);
    return attractionService.insertAttraction(attractionDto).map(a -> ResponseEntity.ok().build());
  }

  @GetMapping("/getOne")
  public Mono<ResponseEntity<AttractionDto>> getAttractionByName(@RequestParam String name) {
    System.out.println("getOne invoked with param: " + name);
    return Mono.empty();
  }
}

package sarujsa.docker.csm.server.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sarujsa.docker.csm.dto.AttractionDto;
import sarujsa.docker.csm.server.service.AttractionService;

@RestController
@RequestMapping("/api/v1/attractions")
public class AttractionController {

  private final AttractionService attractionService;

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
  public Mono<ResponseEntity<AttractionDto>> getAttractionByName(@RequestParam String name)
      throws UnsupportedEncodingException {
    System.out.println("getOne invoked with param: " + name);
    return attractionService
        .getAttractionByName(URLDecoder.decode(name, StandardCharsets.UTF_8))
        .map(ResponseEntity::ok)
        .switchIfEmpty(
            Mono.just(
                new ResponseEntity<AttractionDto>((AttractionDto) null, HttpStatus.NOT_FOUND)));
  }
}

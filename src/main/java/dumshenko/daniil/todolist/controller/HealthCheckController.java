package dumshenko.daniil.todolist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

  @GetMapping("/health-check")
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity.status(HttpStatus.OK).body("The server is up and running");
  }
}

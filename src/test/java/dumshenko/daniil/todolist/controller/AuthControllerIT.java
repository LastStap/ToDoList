package dumshenko.daniil.todolist.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dumshenko.daniil.todolist.controller.dto.AuthTokenDto;
import dumshenko.daniil.todolist.controller.dto.ErrorDto;
import dumshenko.daniil.todolist.controller.dto.LoginDto;
import dumshenko.daniil.todolist.controller.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerIT {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private String getBaseUrl() {
    return "http://localhost:" + port;
  }

  @BeforeEach
  void setUpTestData() {
    restTemplate.postForEntity(
        getBaseUrl() + "/sign-up",
        new HttpEntity<>(new UserDto(null, "Dan", "dan5@gmail.com", "1234", Instant.now())),
        Void.class);
  }

  @Test
  void shouldSignUpSuccessfully() {
    // given
    UserDto userDto = new UserDto(null, "Dan", "dan5@gmail.com", "1234", Instant.now());

    // when
    ResponseEntity<Void> response =
        restTemplate.postForEntity(
            getBaseUrl() + "/sign-up", new HttpEntity<>(userDto), Void.class);

    // then
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  void shouldLoginSuccessfully() {
    // given
    LoginDto loginDto = new LoginDto("dan5@gmail.com", "1234");

    // when
    ResponseEntity<AuthTokenDto> response =
            restTemplate.postForEntity(
                    getBaseUrl() + "/login", new HttpEntity<>(loginDto), AuthTokenDto.class);

    // then
    assertEquals(HttpStatus.OK, response.getStatusCode());
    AuthTokenDto authTokenDto = response.getBody();
    assertNotNull(authTokenDto, "The response body should not be null");
    assertNotNull(authTokenDto.getToken(), "The token should not be null");
  }

  @Test
  void shouldNotLoginBecauseOfWrongEmail() {
    // given
    LoginDto loginDto = new LoginDto("notDan5@gmail.com", "1234");

    // when
    ResponseEntity<ErrorDto> response =
        restTemplate.postForEntity(
            getBaseUrl() + "/login", new HttpEntity<>(loginDto), ErrorDto.class);

    // then
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    ErrorDto errorDto = response.getBody();
    assertEquals("Wrong credentials", errorDto.getMessage());
  }

  @Test
  void shouldNotLoginBecauseOfWrongPassword() {
    // given
    LoginDto loginDto = new LoginDto("dan5@gmail.com", "not1234");

    // when
    ResponseEntity<ErrorDto> response =
            restTemplate.postForEntity(
                    getBaseUrl() + "/login", new HttpEntity<>(loginDto), ErrorDto.class);

    // then
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    ErrorDto errorDto = response.getBody();
    assertEquals("Wrong credentials", errorDto.getMessage());
  }

}

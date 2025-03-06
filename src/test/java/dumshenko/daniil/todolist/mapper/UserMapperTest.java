package dumshenko.daniil.todolist.mapper;

import static org.junit.jupiter.api.Assertions.*;

import dumshenko.daniil.todolist.controller.dto.UserDto;
import dumshenko.daniil.todolist.domain.model.User;
import dumshenko.daniil.todolist.repository.entity.UserEntity;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserMapperTest {

  private final UserMapper userMapper = new UserMapper();

  @Test
  void shouldMapDomainToDto() {
    // given
    User user =
        new User(
            UUID.fromString("4542d77c-e970-4c99-9c97-76eb0dd07969"),
            "okroch0",
            "lgalvan0@cloudflare.com",
            "$2a$04$ts.5mJGxqup5GQHEs5OUqurWM.rS9iFWABZx8gnvxKuFhP4Fd2b8q",
            Instant.now());

    // when
    UserDto userDto = userMapper.toDto(user);

    // then
    assertEquals(user.getId(), userDto.getId());
    assertEquals(user.getUsername(), userDto.getUsername());
    assertEquals(user.getEmail(), userDto.getEmail());
    assertEquals(user.getPassword(), userDto.getPassword());
  }

  @Test
  void shouldMapDtoToDomain() {
    // given
    UserDto userDto =
        new UserDto(
            null,
            "okroch0",
            "lgalvan0@cloudflare.com",
            "$2a$04$ts.5mJGxqup5GQHEs5OUqurWM.rS9iFWABZx8gnvxKuFhP4Fd2b8q",
            Instant.parse("2025-01-06T15:54:32.000000Z"));
    // when
    User user = userMapper.toDomain(userDto);
    // then
    assertEquals(userDto.getId(), user.getId());
    assertEquals(userDto.getUsername(), user.getUsername());
    assertEquals(userDto.getEmail(), user.getEmail());
    assertEquals(userDto.getPassword(), user.getPassword());
  }

  @Test
  void shouldMapEntityToDomain() {
    // given
    UserEntity userEntity =
        new UserEntity(
            UUID.fromString("4542d77c-e970-4c99-9c97-76eb0dd07969"),
            "okroch0",
            "lgalvan0@cloudflare.com",
            "$2a$04$ts.5mJGxqup5GQHEs5OUqurWM.rS9iFWABZx8gnvxKuFhP4Fd2b8q",
            Instant.parse("2025-01-06T15:54:32.000000Z"));
    // when
    User user = userMapper.toDomain(userEntity);
    // then
    assertEquals(userEntity.getId(), user.getId());
    assertEquals(userEntity.getUsername(), user.getUsername());
    assertEquals(userEntity.getEmail(), user.getEmail());
    assertEquals(userEntity.getPassword(), user.getPassword());
  }

  @Test
  void shouldMapDomainToEntity() {
    // given
    User user =
        new User(
            UUID.fromString("4542d77c-e970-4c99-9c97-76eb0dd07969"),
            "okroch0",
            "lgalvan0@cloudflare.com",
            "$2a$04$ts.5mJGxqup5GQHEs5OUqurWM.rS9iFWABZx8gnvxKuFhP4Fd2b8q",
            Instant.parse("2025-01-06T15:54:32.000000Z"));
    // when
    UserEntity userEntity = userMapper.toEntity(user);
    // then
    assertEquals(user.getId(), userEntity.getId());
    assertEquals(user.getUsername(), userEntity.getUsername());
    assertEquals(user.getEmail(), userEntity.getEmail());
    assertEquals(user.getPassword(), userEntity.getPassword());
  }
}

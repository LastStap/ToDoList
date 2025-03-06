package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.domain.model.User;
import dumshenko.daniil.todolist.domain.repository.UserRepository;
import dumshenko.daniil.todolist.exception.PasswordIncorrectException;
import dumshenko.daniil.todolist.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;

    @Mock
    private LoginServiceImpl loginServiceImpl;

    @InjectMocks
    private LoginServiceImpl loginService;

    @Test
    void shouldReturnTokenWhenCredentialsAreValid() {
        // given
        String email = "dan@gmail.com";
        String rawPassword = "password";
        String encodedPassword = "encodedPassword";
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);

        when(userRepository.findByEmailIgnoreCase(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);
        when(tokenService.createToken(user)).thenReturn("token123");

        // when
        String token = loginService.login(email, rawPassword);

        // then
        assertEquals("token123", token);

        verify(userRepository).findByEmailIgnoreCase(email);
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
        verify(tokenService).createToken(user);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {
        // given
        String email = "notDan@gmail.com";
        String rawPassword = "password";

        when(userRepository.findByEmailIgnoreCase(email)).thenReturn(Optional.empty());

        // when & then
        assertThrows(UserNotFoundException.class, () -> loginService.login(email, rawPassword));

        verify(userRepository).findByEmailIgnoreCase(email);
        verifyNoMoreInteractions(passwordEncoder, tokenService);
    }

    @Test
    void shouldThrowPasswordIncorrectExceptionWhenPasswordDoesNotMatch() {
        // given
        String email = "dan@gmail.com";
        String rawPassword = "wrongPassword";
        String encodedPassword = "encodedPassword";
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);

        when(userRepository.findByEmailIgnoreCase(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        // when & then
        assertThrows(PasswordIncorrectException.class, () -> loginService.login(email, rawPassword));

        verify(userRepository).findByEmailIgnoreCase(email);
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
        verifyNoInteractions(tokenService);
    }
}

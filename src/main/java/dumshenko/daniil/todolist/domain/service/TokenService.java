package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.domain.model.User;

public interface TokenService {

    String createToken(User user);

    boolean isValidToken(String token);

    String getUserId(String token);
}

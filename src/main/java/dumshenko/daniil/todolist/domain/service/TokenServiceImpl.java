package dumshenko.daniil.todolist.domain.service;

import dumshenko.daniil.todolist.domain.model.User;
import dumshenko.daniil.todolist.property.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenServiceImpl implements TokenService {

    private final JwtProperties jwtProperties;

    @Autowired
    public TokenServiceImpl(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    @Override
    public String createToken(User user) {


        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtProperties.ttlMillis());

        ClaimsBuilder claimsBuilder =
                Jwts.claims().issuedAt(now).expiration(expiration).subject(user.getId().toString());

        Claims claims = claimsBuilder.build();

        return Jwts.builder().claims(claims).signWith(getSecretKey()).compact();
    }

    @Override
    public boolean isValidToken(String token) {

        try {
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public String getUserId(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private SecretKey getSecretKey() {

        return Keys.hmacShaKeyFor(jwtProperties.secret().getBytes(StandardCharsets.UTF_8));
    }
}

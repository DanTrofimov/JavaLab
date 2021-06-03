package ru.itis.trofimoff.task.utils;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWT;

import org.springframework.stereotype.Component;
import ru.itis.trofimoff.task.models.User;

import java.util.Calendar;
import java.util.Date;

// access token handling
@Component
public class TokenGenerator {

    private final String SECRET_KEY = "secret_key";

    public Long getCurrentExpiringDate() {
        // getting token expiring time
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 1);
        return cal.getTimeInMillis();
    }

    // creating access token by user data
    public String createAccessToken(User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("role", user.getRole().toString())
                .withClaim("state", user.getState().toString())
                .withClaim("email", user.getEmail())
                .withClaim("expiringTime", getCurrentExpiringDate())
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public User verifyToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token);

            System.out.println(decodedJWT.getClaim("expiringTime"));

            if (isExpired(new Date(Long.parseLong(String.valueOf(decodedJWT.getClaim("expiringTime")))))) {
                System.out.println("expiringTime!");
                throw new JWTVerificationException("Token is expired");
            }

            // created current user from JWT
            return User.builder()
                    .email(decodedJWT.getClaim("email").toString().substring(1, decodedJWT.getClaim("email").toString().length() - 1))
                    .role(roleConverter(decodedJWT.getClaim("role").toString()))
                    .state(stateConverter(decodedJWT.getClaim("state").toString()))
                    .build();

        } catch (JWTVerificationException e) {
            System.out.println("verification!");
            System.err.println(e.getMessage());
        }

        return null;
    }

    public User.Role roleConverter(String role) {
        switch (role) {
            case "USER":
                return User.Role.USER;
            case "ADMIN":
                return User.Role.ADMIN;
            default:
                return User.Role.USER;
        }
    }

    public User.State stateConverter(String state) {
        switch (state) {
            case "ACTIVE":
                return User.State.ACTIVE;
            case "BANNED":
                return User.State.BANNED;
            default:
                return User.State.BANNED;
        }
    }

    public boolean isExpired(Date tokenDate) {
        return tokenDate.before(new Date());
    }
}

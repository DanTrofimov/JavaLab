package ru.itis.trofimoff.task.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import ru.itis.trofimoff.task.models.User;

public class MainCreateToken {
    public static void main(String[] args) {
        User user = User.builder()
                .id(10L)
                .firstName("Александр")
                .lastName("Ференец")
                .email("aferenetz@kpfu.ru")
                .role(User.Role.ADMIN)
                .state(User.State.ACTIVE)
                .build();

        String token = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("role", user.getRole().toString())
                .withClaim("state", user.getState().toString())
                .withClaim("email", user.getEmail())
                .sign(Algorithm.HMAC256("seckret_key"));

        System.out.println(token);

    }
}

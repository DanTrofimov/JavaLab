package ru.itis.trofimoff.task.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Scanner;

public class MainVerifyToken {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String token = scanner.nextLine();

        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("seckret_key"))
                    .build()
                    .verify(token);

            System.out.println(decodedJWT.getClaim("role"));
            System.out.println(decodedJWT.getClaim("state"));
            System.out.println(decodedJWT.getClaim("email"));
        } catch (JWTVerificationException e) {
            System.err.println(e.getMessage());
        }
    }
}

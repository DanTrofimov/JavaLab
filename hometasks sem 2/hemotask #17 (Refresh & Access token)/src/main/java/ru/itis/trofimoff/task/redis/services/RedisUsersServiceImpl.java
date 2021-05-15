package ru.itis.trofimoff.task.redis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.trofimoff.task.models.User;
import ru.itis.trofimoff.task.redis.models.RedisUser;
import ru.itis.trofimoff.task.redis.repository.RedisUserRepository;
import ru.itis.trofimoff.task.repository.UserRepository;
import ru.itis.trofimoff.task.services.jwt.JwtBlacklistService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RedisUsersServiceImpl implements RedisUsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtBlacklistService blacklistService;

    @Autowired
    private RedisUserRepository redisUserRepository;

    @Override
    public void addTokenToUser(User user, String token) {
        String redisId = user.getRedisId();

        RedisUser redisUser;
        if (redisId != null) {
            redisUser = redisUserRepository.findById(redisId).orElseThrow(IllegalArgumentException::new);
            if (redisUser.getTokens() == null) {
                redisUser.setTokens(new ArrayList<>());
            }
            redisUser.getTokens().add(token);
        } else {
            redisUser = RedisUser.builder()
                    .userId(user.getId())
                    .tokens(Collections.singletonList(token))
                    .build();
        }
        redisUserRepository.save(redisUser);
        user.setRedisId(redisUser.getId());
        userRepository.save(user);
    }

    @Override
    public void addAllTokensToBlackList(User user) {
        if (user.getRedisId() != null) {
            RedisUser redisUser = redisUserRepository.findById(user.getRedisId())
                    .orElseThrow(IllegalArgumentException::new);

            List<String> tokens = redisUser.getTokens();
            for (String token : tokens) {
                blacklistService.add(token);
            }
            redisUser.getTokens().clear();
            redisUserRepository.save(redisUser);
        }
    }
}

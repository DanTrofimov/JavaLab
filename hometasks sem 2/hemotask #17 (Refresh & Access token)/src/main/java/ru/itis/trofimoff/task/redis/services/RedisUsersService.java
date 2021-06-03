package ru.itis.trofimoff.task.redis.services;

import ru.itis.trofimoff.task.models.User;

public interface RedisUsersService {
    void addTokenToUser(User user, String token);

    void addAllTokensToBlackList(User user);
}
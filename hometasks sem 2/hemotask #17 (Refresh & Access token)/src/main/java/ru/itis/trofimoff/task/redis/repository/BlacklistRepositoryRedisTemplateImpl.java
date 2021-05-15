package ru.itis.trofimoff.task.redis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.trofimoff.task.repository.BlacklistRepository;

@Repository
public class BlacklistRepositoryRedisTemplateImpl implements BlacklistRepository {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void save(String token) {
        redisTemplate.opsForValue().set(token, "");
    }

    @Override
    public boolean exists(String token) {
        Boolean hasToken = redisTemplate.hasKey(token);
        System.out.println("has token in the blacklist: " + hasToken);
        return hasToken != null && hasToken;
    }
}

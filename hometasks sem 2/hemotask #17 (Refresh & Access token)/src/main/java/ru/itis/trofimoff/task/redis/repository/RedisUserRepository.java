package ru.itis.trofimoff.task.redis.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.itis.trofimoff.task.redis.models.RedisUser;

public interface RedisUserRepository extends KeyValueRepository<RedisUser, String> {
}
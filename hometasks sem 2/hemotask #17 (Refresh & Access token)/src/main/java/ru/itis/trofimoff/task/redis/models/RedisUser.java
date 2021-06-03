package ru.itis.trofimoff.task.redis.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@RedisHash("user")
public class RedisUser {
    @Id
    private String id;
    private List<String> tokens;
    private Integer userId;
}

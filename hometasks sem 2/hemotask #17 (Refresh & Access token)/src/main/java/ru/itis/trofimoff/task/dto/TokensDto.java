package ru.itis.trofimoff.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokensDto {
    private String accessToken = "empty";
    private String refreshToken = "empty";
}

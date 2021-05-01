package ru.itis.trofimoff.task.services.login;

import ru.itis.trofimoff.task.dto.EmailPasswordDto;
import ru.itis.trofimoff.task.dto.TokensDto;

public interface LoginService {
    TokensDto login(EmailPasswordDto emailPassword);
}

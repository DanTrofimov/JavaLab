package ru.itis.trofimoff.crf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.itis.trofimoff.crf.models.User;
import ru.itis.trofimoff.crf.repositories.UsersRepository;

import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private EmailsService emailsService;

    @Autowired
    @Qualifier("usersRepositoryJdbcImpl")
    private UsersRepository usersRepository;

    @Override
    public void signUp(User user) {
        String confirmCode = UUID.randomUUID().toString();

        user.setConfirmCode(confirmCode);

        String url = "https://itdrive.pro/confirm/" + confirmCode;

        usersRepository.save(user);
        emailsService.sendMail("Подтверждение регистрации", url, user.getEmail());
    }
}

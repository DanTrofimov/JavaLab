package ru.itis.trofimoff.crf.services;

import org.graalvm.compiler.lir.CompositeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.trofimoff.crf.models.User;
import ru.itis.trofimoff.crf.repositories.UsersRepository;


import java.util.Optional;

@Component
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public boolean authenticate(String email, String password) {
        Optional<User> userOptional = usersRepository.findOneByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }
        return false;
    }
}
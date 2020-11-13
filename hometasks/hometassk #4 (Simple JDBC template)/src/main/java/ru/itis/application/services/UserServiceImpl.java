package ru.itis.application.services;

import ru.itis.application.models.User;
import ru.itis.application.repository.UsersRepository;

import java.util.List;

public class UserServiceImpl implements UsersService {
    private UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

}

package ru.itis.trofimoff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.trofimoff.model.Method;
import ru.itis.trofimoff.repository.MethodRepository;

@Service
public class MethodServiceImpl implements MethodService {

    @Autowired
    private MethodRepository methodRepository;

    @Override
    public void incrementMethod(String name) {
        if (methodRepository.findByName(name) != null) {
            methodRepository.incrementMethodAmount(name);
        } else {
            Method method = new Method(name);
            methodRepository.save(method);
            methodRepository.incrementMethodAmount(name);
        }
    }
}

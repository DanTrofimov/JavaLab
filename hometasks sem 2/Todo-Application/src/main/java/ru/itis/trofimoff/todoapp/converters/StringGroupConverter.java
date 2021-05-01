package ru.itis.trofimoff.todoapp.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.trofimoff.todoapp.exceptions.UnknownGroupException;
import ru.itis.trofimoff.todoapp.models.Group;
import ru.itis.trofimoff.todoapp.repositories.jpa.GroupRepository;

import java.util.Optional;

@Component
public class StringGroupConverter implements Converter<String, Group> {

    @Autowired
    public GroupRepository groupRepository;

    @Override
    public Group convert(String str) throws UnknownGroupException {
        Optional<Group> currentGroup = groupRepository.findByName(str);
        if (currentGroup.isPresent()) {
            return currentGroup.get();
        } else {
            throw new UnknownGroupException();
        }
    }
}

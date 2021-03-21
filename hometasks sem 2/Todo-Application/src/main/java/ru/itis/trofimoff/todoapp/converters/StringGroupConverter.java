package ru.itis.trofimoff.todoapp.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.trofimoff.todoapp.models.Group;
import ru.itis.trofimoff.todoapp.repositories.jpa.GroupRepository;

import java.util.Optional;

@Component
public class StringGroupConverter implements Converter<String, Group> {

    @Autowired
    public GroupRepository groupRepository;

    @Override
    public Group convert(String str) {
        Optional<Group> currentGroup = groupRepository.findByName(str);
        return currentGroup.orElse(null);
    }
}

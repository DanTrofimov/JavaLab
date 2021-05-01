package ru.itis.trofimoff.todoapp.services.group;

import ru.itis.trofimoff.todoapp.models.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    List<Group> getAllGroups();
    Optional<Group> getGroupById(int id);
}

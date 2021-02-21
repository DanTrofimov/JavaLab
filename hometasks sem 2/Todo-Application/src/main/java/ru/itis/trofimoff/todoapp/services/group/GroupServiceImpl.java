package ru.itis.trofimoff.todoapp.services.group;

import ru.itis.trofimoff.todoapp.models.Group;
import ru.itis.trofimoff.todoapp.repositories.GroupRepository;

import java.util.List;

public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllGroups() {
        return this.groupRepository.findAll();
    }
}

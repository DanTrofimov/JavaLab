package ru.itis.trofimoff.todoapp.services.group;

import org.springframework.stereotype.Service;
import ru.itis.trofimoff.todoapp.models.Group;
import ru.itis.trofimoff.todoapp.repositories.group.GroupRepository;
import ru.itis.trofimoff.todoapp.repositories.group.GroupRepositoryImpl;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepositoryImpl groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllGroups() {
        return this.groupRepository.findAll();
    }
}

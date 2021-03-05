package ru.itis.trofimoff.todoapp.services.group;

import org.springframework.stereotype.Service;
import ru.itis.trofimoff.todoapp.models.Group;
import ru.itis.trofimoff.todoapp.repositories.jpa.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllGroups() {
        return this.groupRepository.findAll();
    }

    @Override
    public Optional<Group> getGroupById(int id) {
        return groupRepository.findById(id);
    }
}

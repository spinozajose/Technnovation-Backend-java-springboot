package com.technnovation.technnovation.Service;

import com.technnovation.technnovation.Model.Group;
import com.technnovation.technnovation.Repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> obtenerTodos() {
        return groupRepository.findAll();
    }

    public Group crearGrupo(Group group) {
        return groupRepository.save(group);
    }
}
package com.technnovation.technnovation.Controller;

import com.technnovation.technnovation.Model.Group;
import com.technnovation.technnovation.Service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
@CrossOrigin("*")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> obtenerTodos() {
        return groupService.obtenerTodos();
    }

    @PostMapping
    public Group crear(@RequestBody Group group) {
        return groupService.crearGrupo(group);
    }
}
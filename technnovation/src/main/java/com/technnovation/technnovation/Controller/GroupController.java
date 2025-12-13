package com.technnovation.technnovation.Controller;

import com.technnovation.technnovation.Model.Group;
import com.technnovation.technnovation.Service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
@CrossOrigin("*")
@Tag(name = "Grupos de Chat", description = "Gestión de canales y comunidades de usuarios")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    @Operation(summary = "Listar grupos", description = "Obtiene todos los grupos de chat disponibles (Encriptados y Abiertos)")
    public List<Group> obtenerTodos() {
        return groupService.obtenerTodos();
    }

    @PostMapping
    @Operation(summary = "Crear grupo", description = "Inicia un nuevo canal de comunicación")
    public Group crear(@RequestBody Group group) {
        return groupService.crearGrupo(group);
    }
}
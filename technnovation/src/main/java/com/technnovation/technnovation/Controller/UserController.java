package com.technnovation.technnovation.Controller;

import com.technnovation.technnovation.DTO.UserRequestDTO;
import com.technnovation.technnovation.DTO.UserResponseDTO;
import com.technnovation.technnovation.DTO.UserUpdateDTO;
import com.technnovation.technnovation.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
@Tag(name = "Usuarios", description = "Gestión de usuarios, registro y perfiles")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Listar usuarios", description = "Obtiene la lista completa de todos los usuarios registrados en el sistema")
    public List<UserResponseDTO> obtenerTodos() {
        return userService.obtenerTodos();
    }

    @PostMapping
    @Operation(summary = "Registrar usuario", description = "Crea una nueva cuenta de usuario con los datos proporcionados")
    public UserResponseDTO crear(@RequestBody UserRequestDTO dto) {
        return userService.crearUsuario(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar perfil", description = "Modifica los datos de un usuario existente por su ID")
    public UserResponseDTO actualizar(
            @PathVariable Long id,
            @RequestBody UserUpdateDTO dto) {

        return userService.actualizarUsuario(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario", description = "Borra permanentemente un usuario del sistema")
    public String eliminar(@PathVariable Long id) {
        userService.eliminarUsuario(id);
        return "Usuario eliminado correctamente.";
    }
}
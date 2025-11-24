package com.technnovation.technnovation.Controller;


import com.technnovation.technnovation.DTO.UserRequestDTO;
import com.technnovation.technnovation.DTO.UserResponseDTO;
import com.technnovation.technnovation.DTO.UserUpdateDTO;
import com.technnovation.technnovation.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO> obtenerTodos() {
        return userService.obtenerTodos();
    }

    @PostMapping
    public UserResponseDTO crear(@RequestBody UserRequestDTO dto) {
        return userService.crearUsuario(dto);
    }

    @PutMapping("/{id}")
    public UserResponseDTO actualizar(
            @PathVariable Long id,
            @RequestBody UserUpdateDTO dto) {

        return userService.actualizarUsuario(id, dto);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        userService.eliminarUsuario(id);
        return "Usuario eliminado correctamente.";
    }

}

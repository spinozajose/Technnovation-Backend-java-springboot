package com.technnovation.technnovation.Service;

import com.technnovation.technnovation.DTO.UserRequestDTO;
import com.technnovation.technnovation.DTO.UserResponseDTO;
import com.technnovation.technnovation.DTO.UserUpdateDTO;
import com.technnovation.technnovation.Model.User;
import com.technnovation.technnovation.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> obtenerTodos() {
        return userRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO crearUsuario(UserRequestDTO dto) {

        User user = User.builder()
                .nombre(dto.getNombre())
                .correo(dto.getCorreo())
                .contrasenaHash(dto.getContrasena())
                .avatarUrl(dto.getAvatarUrl())
                .build();

        User guardado = userRepository.save(user);
        return toResponseDTO(guardado);
    }

    private UserResponseDTO toResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .nombre(user.getNombre())
                .correo(user.getCorreo())
                .avatarUrl(user.getAvatarUrl())
                .rol(user.getRol())
                .build();
    }

    public UserResponseDTO actualizarUsuario(Long id, UserUpdateDTO dto) {

        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (dto.getNombre() != null) usuario.setNombre(dto.getNombre());
        if (dto.getCorreo() != null) usuario.setCorreo(dto.getCorreo());
        if (dto.getAvatarUrl() != null) usuario.setAvatarUrl(dto.getAvatarUrl());

        User actualizado = userRepository.save(usuario);

        return toResponseDTO(actualizado);
    }

    public void eliminarUsuario(Long id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }

        userRepository.deleteById(id);
    }

}


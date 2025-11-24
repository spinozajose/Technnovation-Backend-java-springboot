package com.technnovation.technnovation.DTO;
import lombok.Data;

@Data
public class UserRequestDTO {

    private String nombre;
    private String correo;
    private String contrasena;
    private String avatarUrl;
}

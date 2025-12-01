package com.technnovation.technnovation.DTO;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String nombre;
    private String correo;
    private String contrasena;
    private String avatarUrl;
    // Agregados:
    private String ubicacion;
    private String pais;
}
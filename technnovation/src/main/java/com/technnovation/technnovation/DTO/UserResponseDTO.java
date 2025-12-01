package com.technnovation.technnovation.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String avatarUrl;
    private String rol;
    // Agregados para el perfil:
    private String ubicacion;
    private String pais;
    private String nivelAcceso;
    private Float reputacion;
    private Float seguridad;
}
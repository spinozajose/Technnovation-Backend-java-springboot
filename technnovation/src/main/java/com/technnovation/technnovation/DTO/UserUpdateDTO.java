package com.technnovation.technnovation.DTO;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String nombre;
    private String correo;
    private String avatarUrl;

    // --- AGREGAR ESTOS DOS ---
    private String ubicacion;
    private String pais;
}
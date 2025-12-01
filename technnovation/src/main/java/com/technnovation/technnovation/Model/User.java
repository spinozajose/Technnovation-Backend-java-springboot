package com.technnovation.technnovation.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    private String contrasenaHash;
    private String avatarUrl;

    // --- NUEVOS CAMPOS PARA MATCH CON FRONTEND ---
    private String ubicacion;
    private String pais;

    // Datos "Cyberpunk" (Valores por defecto al crear)
    @Builder.Default
    private String nivelAcceso = "NIVEL 1: OPERADOR";

    @Builder.Default
    private Float reputacion = 0.5f; // 50%

    @Builder.Default
    private Float seguridad = 0.8f;  // 80%
    // ---------------------------------------------

    @Builder.Default
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Builder.Default
    private String rol = "USER";
}
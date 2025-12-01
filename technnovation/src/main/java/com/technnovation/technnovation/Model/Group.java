package com.technnovation.technnovation.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "grupos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @ElementCollection // Crea una tabla secundaria simple para guardar los IDs/Nombres de miembros
    private List<String> miembros;

    @Builder.Default
    private String estado = "ENCRIPTADO";
}
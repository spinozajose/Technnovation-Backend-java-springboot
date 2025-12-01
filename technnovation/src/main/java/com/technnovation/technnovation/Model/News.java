package com.technnovation.technnovation.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "noticias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    @Column(length = 1000) // Permitir descripciones largas
    private String descripcion;
    private String fuente;     // Ej: "OPEN_AI"
    private String imageUrl;   // URL de la imagen
    private String urlInfo;    // Link externo

    @Builder.Default
    private LocalDateTime fechaPublicacion = LocalDateTime.now();
}
package com.technnovation.technnovation.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;

    @Enumerated(EnumType.STRING)
    private NotificationType tipo;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    // Opcional: Para relacionar con un usuario específico si deseas filtrar después
    // private Long userId;
}
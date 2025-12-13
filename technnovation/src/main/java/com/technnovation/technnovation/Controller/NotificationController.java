package com.technnovation.technnovation.Controller;

import com.technnovation.technnovation.Model.Notification;
import com.technnovation.technnovation.Service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin("*")
@Tag(name = "Notificaciones", description = "Bandeja de entrada de alertas y avisos del usuario")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    @Operation(summary = "Obtener inbox", description = "Lista todas las notificaciones recientes para la bandeja de entrada")
    public List<Notification> obtenerTodas() {
        return notificationService.obtenerTodas();
    }

    @PostMapping
    @Operation(summary = "Enviar notificación", description = "Crea y envía una nueva notificación (Simulación Push)")
    public Notification crear(@RequestBody Notification notification) {
        return notificationService.crearNotificacion(notification);
    }
}
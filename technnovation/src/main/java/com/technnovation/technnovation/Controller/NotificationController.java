package com.technnovation.technnovation.Controller;

import com.technnovation.technnovation.Model.Notification;
import com.technnovation.technnovation.Service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<Notification> obtenerTodas() {
        return notificationService.obtenerTodas();
    }

    @PostMapping
    public Notification crear(@RequestBody Notification notification) {
        return notificationService.crearNotificacion(notification);
    }
}
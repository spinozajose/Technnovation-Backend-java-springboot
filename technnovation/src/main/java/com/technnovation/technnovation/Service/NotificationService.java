package com.technnovation.technnovation.Service;

import com.technnovation.technnovation.Model.Notification;
import com.technnovation.technnovation.Repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> obtenerTodas() {
        return notificationRepository.findAll();
    }

    public Notification crearNotificacion(Notification notification) {
        return notificationRepository.save(notification);
    }
}
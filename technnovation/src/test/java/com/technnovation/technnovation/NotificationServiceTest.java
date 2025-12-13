package com.technnovation.technnovation; // AJUSTA ESTE PAQUETE

import com.technnovation.technnovation.Model.Notification; // Tu Modelo
import com.technnovation.technnovation.Model.NotificationType; // Tu Enum
import com.technnovation.technnovation.Repository.NotificationRepository; // Tu Repositorio
import com.technnovation.technnovation.Service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    private Notification notificationMock;
    private final Long NOTIFICATION_ID = 50L;

    @BeforeEach
    void setUp() {
        // Entidad de prueba
        notificationMock = Notification.builder()
                .id(NOTIFICATION_ID)
                .titulo("Nuevo Comentario")
                .mensaje("Alguien respondió a tu post.")
                .tipo(NotificationType.REPLY)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // =========================================================================
    //                            PRUEBAS DE CREACIÓN (crearNotificacion)
    // =========================================================================

    @Test
    void testCrearNotificacion_GuardaExitosamenteYVerificaTipo() {
        // Arrange: Simula que el repositorio guarda correctamente
        when(notificationRepository.save(any(Notification.class))).thenReturn(notificationMock);

        // Act
        Notification resultado = notificationService.crearNotificacion(notificationMock);

        // Assert:
        verify(notificationRepository, times(1)).save(notificationMock);
        assertNotNull(resultado);

        // Verifica que los campos sensibles al negocio son correctos
        assertEquals(NotificationType.REPLY, resultado.getTipo());
        assertEquals(NOTIFICATION_ID, resultado.getId());
    }

    // =========================================================================
    //                            PRUEBAS DE LECTURA (obtenerTodas)
    // =========================================================================

    @Test
    void testObtenerTodas_RetornaListaCompleta() {
        // Arrange: Simula una lista de notificaciones
        Notification notif2 = Notification.builder().id(51L).tipo(NotificationType.NEWS).build();
        List<Notification> notificationList = Arrays.asList(notificationMock, notif2);
        when(notificationRepository.findAll()).thenReturn(notificationList);

        // Act
        List<Notification> resultado = notificationService.obtenerTodas();

        // Assert:
        verify(notificationRepository, times(1)).findAll();
        assertEquals(2, resultado.size());
        assertEquals(NotificationType.REPLY, resultado.get(0).getTipo());
    }
}
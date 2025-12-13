package com.technnovation.technnovation;

import com.technnovation.technnovation.DTO.UserRequestDTO;
import com.technnovation.technnovation.DTO.UserResponseDTO;
import com.technnovation.technnovation.DTO.UserUpdateDTO;
import com.technnovation.technnovation.Model.User;
import com.technnovation.technnovation.Repository.UserRepository;
import com.technnovation.technnovation.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Habilita las extensiones de Mockito
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    // Se inyecta la implementación real del servicio
    @InjectMocks
    private UserService userService;

    private User userMock;
    private UserRequestDTO requestDtoMock;
    private UserUpdateDTO updateDtoMock;
    private final Long USER_ID = 1L;
    private final String EXISTING_EMAIL = "neo@cyber.com";

    @BeforeEach
    void setUp() {
        // --- 1. Entidad Mockeada (Simula el objeto guardado en DB) ---
        userMock = User.builder()
                .id(USER_ID)
                .nombre("Neo Smith")
                .correo(EXISTING_EMAIL)
                .contrasenaHash("matrixhash")
                .ubicacion("Cloud City")
                .pais("The Matrix")
                .rol("USER")
                .nivelAcceso("NIVEL 1: OPERADOR")
                .reputacion(0.5f)
                .seguridad(0.8f)
                .build();

        // --- 2. DTO de Request Mockeado (Simula la entrada del Controller) ---
        requestDtoMock = new UserRequestDTO();
        requestDtoMock.setNombre("Trinity Green");
        requestDtoMock.setCorreo("trinity@cyber.com");
        requestDtoMock.setContrasena("trinitypass");
        requestDtoMock.setUbicacion("Zion");
        requestDtoMock.setPais("Earth");

        // --- 3. DTO de Update Mockeado (Simula la actualización) ---
        updateDtoMock = new UserUpdateDTO();
        updateDtoMock.setNombre("Neo Renombrado");
        updateDtoMock.setUbicacion("Nueva Ubicacion");
    }

    // =========================================================================
    //                            PRUEBAS DE CREACIÓN (crearUsuario)
    // =========================================================================

    @Test
    void testCrearUsuario_ExitosoYVerificaMapeo() {
        // Arrange: Captura la Entidad User que se pasa al repositorio para verificar el mapeo.
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        // Simula que el repositorio guarda correctamente y devuelve el objeto mockeado.
        when(userRepository.save(Mockito.any(User.class))).thenReturn(userMock);

        // Act
        UserResponseDTO resultado = userService.crearUsuario(requestDtoMock);

        // Assert:
        // 1. Verifica que el save() fue llamado.
        verify(userRepository, times(1)).save(userCaptor.capture());

        // 2. Verifica el mapeo DTO -> Entidad (Lógica del Servicio)
        User userGuardado = userCaptor.getValue();
        assertEquals(requestDtoMock.getCorreo(), userGuardado.getCorreo());
        assertEquals(requestDtoMock.getContrasena(), userGuardado.getContrasenaHash()); // Verifica que contrasena se mapea a contrasenaHash
        assertEquals("USER", userGuardado.getRol()); // Verifica que el campo por defecto se aplica

        // 3. Verifica el DTO de respuesta (Mapeo Entidad -> ResponseDTO)
        assertNotNull(resultado);
        assertEquals(userMock.getPais(), resultado.getPais());
        assertEquals(userMock.getNivelAcceso(), resultado.getNivelAcceso());
    }

    // =========================================================================
    //                            PRUEBAS DE LECTURA (obtenerTodos)
    // =========================================================================

    @Test
    void testObtenerTodos_RetornaListaDTOVacia() {
        // Arrange: Simula que la base de datos no tiene usuarios
        when(userRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<UserResponseDTO> resultado = userService.obtenerTodos();

        // Assert
        assertTrue(resultado.isEmpty());
        verify(userRepository, times(1)).findAll();
    }

    // =========================================================================
    //                            PRUEBAS DE ACTUALIZACIÓN (actualizarUsuario)
    // =========================================================================

    @Test
    void testActualizarUsuario_ActualizaCamposPermitidos() {
        // Arrange
        Long idAActualizar = 1L;

        // Simula la búsqueda: encuentra el usuario
        when(userRepository.findById(idAActualizar)).thenReturn(Optional.of(userMock));
        // Simula el guardado: retorna el objeto modificado
        when(userRepository.save(userMock)).thenReturn(userMock);

        // Act
        UserResponseDTO resultado = userService.actualizarUsuario(idAActualizar, updateDtoMock);

        // Assert
        // 1. Verifica que los campos se modificaron en la ENTIDAD MOCKEADA
        assertEquals(updateDtoMock.getNombre(), userMock.getNombre());
        assertEquals(updateDtoMock.getUbicacion(), userMock.getUbicacion());

        // 2. Verifica que los métodos del repositorio se usaron
        verify(userRepository, times(1)).findById(idAActualizar);
        verify(userRepository, times(1)).save(userMock);

        // 3. Verifica el DTO de respuesta
        assertEquals(updateDtoMock.getNombre(), resultado.getNombre());
    }

    @Test
    void testActualizarUsuario_FalloNoEncontrado() {
        // Arrange: Simula que findById no encuentra nada
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert: Debe lanzar la RuntimeException del servicio
        assertThrows(RuntimeException.class, () -> {
            userService.actualizarUsuario(99L, new UserUpdateDTO());
        });

        // Verifica que el save() nunca fue llamado
        verify(userRepository, never()).save(Mockito.any(User.class));
    }

    // =========================================================================
    //                            PRUEBAS DE ELIMINACIÓN (eliminarUsuario)
    // =========================================================================

    @Test
    void testEliminarUsuario_Exitoso() {
        // Arrange: Simula que el usuario existe
        when(userRepository.existsById(USER_ID)).thenReturn(true);

        // Act
        userService.eliminarUsuario(USER_ID);

        // Assert: Verifica que se llamó al método deleteById()
        verify(userRepository, times(1)).existsById(USER_ID);
        verify(userRepository, times(1)).deleteById(USER_ID);
    }

    @Test
    void testEliminarUsuario_FalloNoEncontrado() {
        // Arrange: Simula que el usuario NO existe
        when(userRepository.existsById(99L)).thenReturn(false);

        // Act & Assert: Debe lanzar la RuntimeException
        assertThrows(RuntimeException.class, () -> {
            userService.eliminarUsuario(99L);
        });

        // Verifica que deleteById() NUNCA fue llamado
        verify(userRepository, never()).deleteById(anyLong());
    }
}
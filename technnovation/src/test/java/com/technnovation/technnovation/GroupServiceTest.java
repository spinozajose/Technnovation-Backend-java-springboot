package com.technnovation.technnovation; // AJUSTA ESTE PAQUETE

import com.technnovation.technnovation.Model.Group; // Tu Modelo
import com.technnovation.technnovation.Repository.GroupRepository; // Tu Repositorio
import com.technnovation.technnovation.Service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    private Group groupMock;
    private final Long GROUP_ID = 5L;

    @BeforeEach
    void setUp() {
        // Entidad de prueba
        groupMock = Group.builder()
                .id(GROUP_ID)
                .nombre("DevOps Central")
                .descripcion("Grupo de CI/CD")
                .estado("ABIERTO")
                .miembros(new ArrayList<>(Arrays.asList("UserA", "UserB")))
                .build();
    }

    // =========================================================================
    //                            PRUEBAS DE CREACIÓN (crearGrupo)
    // =========================================================================

    @Test
    void testCrearGrupo_GuardaExitosamenteYVerificaEstado() {
        // Arrange: Simula que el repositorio guarda correctamente
        when(groupRepository.save(any(Group.class))).thenReturn(groupMock);

        // Act
        Group resultado = groupService.crearGrupo(groupMock);

        // Assert:
        verify(groupRepository, times(1)).save(groupMock);
        assertNotNull(resultado);
        assertEquals(GROUP_ID, resultado.getId());

        // Verifica que la lógica de negocio se mantuvo (ej: el estado es correcto)
        assertEquals("ABIERTO", resultado.getEstado());
        assertEquals(2, resultado.getMiembros().size());
    }

    // =========================================================================
    //                            PRUEBAS DE LECTURA (obtenerTodos)
    // =========================================================================

    @Test
    void testObtenerTodos_RetornaListaConGrupos() {
        // Arrange: Simula una lista
        Group group2 = Group.builder().id(6L).nombre("Kotlin Devs").build();
        List<Group> groupList = Arrays.asList(groupMock, group2);
        when(groupRepository.findAll()).thenReturn(groupList);

        // Act
        List<Group> resultado = groupService.obtenerTodos();

        // Assert:
        verify(groupRepository, times(1)).findAll();
        assertEquals(2, resultado.size());
        assertEquals("DevOps Central", resultado.get(0).getNombre());
    }
}
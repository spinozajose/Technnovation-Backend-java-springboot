package com.technnovation.technnovation; // AJUSTA ESTE PAQUETE

import com.technnovation.technnovation.Model.News; // Tu Modelo
import com.technnovation.technnovation.Repository.NewsRepository; // Tu Repositorio
import com.technnovation.technnovation.Service.NewsService;
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
public class NewsServiceTest {

    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private NewsService newsService;

    private News newsMock;
    private final Long NEWS_ID = 101L;

    @BeforeEach
    void setUp() {
        // Entidad de prueba (simula un objeto guardado en DB)
        newsMock = News.builder()
                .id(NEWS_ID)
                .titulo("Noticia de Prueba")
                .descripcion("Descripción de prueba para el test unitario.")
                .fuente("TEST_UNIT")
                .fechaPublicacion(LocalDateTime.now())
                .build();
    }

    // =========================================================================
    //                            PRUEBAS DE CREACIÓN (crearNoticia)
    // =========================================================================

    @Test
    void testCrearNoticia_AsignaFechaYGuardaExitosamente() {
        // Arrange: Simula que el repositorio guarda correctamente
        when(newsRepository.save(any(News.class))).thenReturn(newsMock);

        // Act
        News resultado = newsService.crearNoticia(newsMock);

        // Assert:
        // 1. Verifica que el método save() del repositorio fue llamado
        verify(newsRepository, times(1)).save(newsMock);

        // 2. Verifica que el objeto retornado no es nulo y tiene el ID.
        assertNotNull(resultado);
        assertEquals(NEWS_ID, resultado.getId());
    }

    // =========================================================================
    //                            PRUEBAS DE LECTURA (obtenerTodas)
    // =========================================================================

    @Test
    void testObtenerTodas_RetornaListaConDosNoticias() {
        // Arrange: Simula una lista de noticias
        News news2 = News.builder().id(102L).titulo("Segunda Noticia").build();
        List<News> newsList = Arrays.asList(newsMock, news2);
        when(newsRepository.findAll()).thenReturn(newsList);

        // Act
        List<News> resultado = newsService.obtenerTodas();

        // Assert:
        // 1. Verifica la interacción
        verify(newsRepository, times(1)).findAll();

        // 2. Verifica el contenido de la lista
        assertEquals(2, resultado.size());
        assertEquals("Noticia de Prueba", resultado.get(0).getTitulo());
    }

    @Test
    void testObtenerTodas_RetornaListaVacia() {
        // Arrange: Simula una lista vacía
        when(newsRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<News> resultado = newsService.obtenerTodas();

        // Assert:
        verify(newsRepository, times(1)).findAll();
        assertTrue(resultado.isEmpty());
    }
}
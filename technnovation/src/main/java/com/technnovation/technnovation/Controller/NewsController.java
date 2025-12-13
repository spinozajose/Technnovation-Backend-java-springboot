package com.technnovation.technnovation.Controller;

import com.technnovation.technnovation.Model.News;
import com.technnovation.technnovation.Service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/noticias")
@CrossOrigin("*")
@Tag(name = "Noticias", description = "Gestión del Feed de noticias tecnológicas (CyberNews)")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    @Operation(summary = "Obtener feed", description = "Retorna todas las noticias tecnológicas para el feed principal")
    public List<News> obtenerTodas() {
        return newsService.obtenerTodas();
    }

    @PostMapping
    @Operation(summary = "Publicar noticia", description = "Crea una nueva noticia en el sistema (Uso administrativo)")
    public News crear(@RequestBody News news) {
        return newsService.crearNoticia(news);
    }
}
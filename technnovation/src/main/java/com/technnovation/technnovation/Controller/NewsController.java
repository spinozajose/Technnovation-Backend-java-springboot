package com.technnovation.technnovation.Controller;

import com.technnovation.technnovation.Model.News;
import com.technnovation.technnovation.Service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/noticias")
@CrossOrigin("*")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public List<News> obtenerTodas() {
        return newsService.obtenerTodas();
    }

    @PostMapping
    public News crear(@RequestBody News news) {
        return newsService.crearNoticia(news);
    }
}
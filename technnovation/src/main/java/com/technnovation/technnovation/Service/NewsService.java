package com.technnovation.technnovation.Service;

import com.technnovation.technnovation.Model.News;
import com.technnovation.technnovation.Repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> obtenerTodas() {
        return newsRepository.findAll();
    }

    public News crearNoticia(News news) {
        return newsRepository.save(news);
    }
}
package com.example.firstproject.api;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ArticleApiController {
    
    @Autowired
    private ArticleRepository articleRepository;


    // GET
    @GetMapping("/api/articles")
    public List<Article> indes(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){

        return articleRepository.findById(id).orElse(null);
    }
    

    // POST


    // PUT/PATCH


    // DELETE
}

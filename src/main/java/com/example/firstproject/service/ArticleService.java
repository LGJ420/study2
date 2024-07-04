package com.example.firstproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;

@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;

    
    public List<Article> index(){

        return articleRepository.findAll();
    }


    public Article show(Long id){

        return articleRepository.findById(id).orElse(null);
    }

}

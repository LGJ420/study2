package com.example.firstproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.firstproject.repository.ArticleRepository;

@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;

}

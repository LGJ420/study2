package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.firstproject.dto.ArticleForm;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ArticleController {
    
    @GetMapping("/articles/new")
    public String newArticleForm() {

        return "articles/new";
    }
    

    @PostMapping("articles/create")
    public String createArticle(ArticleForm form) {

        log.info(form.toString());
        return "";
    }
}

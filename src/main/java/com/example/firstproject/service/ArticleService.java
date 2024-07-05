package com.example.firstproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;

    
    public List<Article> index(){

        return articleRepository.findAll();
    }


    public Article show(Long id){

        return articleRepository.findById(id).orElse(null);
    }


    public Article create(ArticleForm dto){

        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }


    public Article update(Long id, ArticleForm dto){

         // 1. 수정용 엔티티 생성하기
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());


        // 2. DB에 대상 엔티티가 있는지 조회하기
        Article target = articleRepository.findById(id).orElse(null);


        // 3. 대상 엔티티가 없거나 수정하려는 id가 잘못됐을 경우 처리하기
        if (target == null ||  id != article.getId()){

            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }


        // 4. 대상 엔티티가 있으면 수정 내용으로 업데이트하고 정상 응답(200) 보내기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }
}

package com.example.firstproject.api;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Log4j2
@RestController
public class ArticleApiController {
    
    @Autowired
    private ArticleService articleService;


    // GET
    @GetMapping("/api/articles")
    public List<Article> index(){

        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){

        return articleService.show(id);
    }
    

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){

        Article created = articleService.create(dto);

        return (created != null) ?
            ResponseEntity.status(HttpStatus.OK).body(created) : 
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    // // PUT/PATCH
    // @PatchMapping("/api/articles/{id}")
    // public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){

    //     // 1. 수정용 엔티티 생성하기
    //     Article article = dto.toEntity();
    //     log.info("id: {}, article: {}", id, article.toString());


    //     // 2. DB에 대상 엔티티가 있는지 조회하기
    //     Article target = articleRepository.findById(id).orElse(null);


    //     // 3. 대상 엔티티가 없거나 수정하려는 id가 잘못됐을 경우 처리하기
    //     if (target == null ||  id != article.getId()){

    //         log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //     }


    //     // 4. 대상 엔티티가 있으면 수정 내용으로 업데이트하고 정상 응답(200) 보내기
    //     target.patch(article);
    //     Article updated = articleRepository.save(target);
    //     return ResponseEntity.status(HttpStatus.OK).body(updated);

    // }


    // // DELETE
    // @DeleteMapping("/api/articles/{id}")
    // public ResponseEntity<Article> delete(@PathVariable Long id){

    //     // 1. DB에서 대상 엔티티가 있는지 조회하기
    //     Article target = articleRepository.findById(id).orElse(null);
        

    //     // 2. 대상 엔티티가 없어서 요청 자체가 잘못됐을 경우 처리하기
    //     if (target == null){

    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //     }


    //     // 3. 대상 엔티티가 있으면 삭제하고 정상 응답(200) 반환하기
    //     articleRepository.delete(target);
    //     return ResponseEntity.status(HttpStatus.OK).build();

    // }
}

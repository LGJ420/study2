package com.example.firstproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;

import jakarta.transaction.Transactional;
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


    public Article delete(Long id){

        // 1. DB에서 대상 엔티티가 있는지 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        

        // 2. 대상 엔티티가 없어서 요청 자체가 잘못됐을 경우 처리하기
        if (target == null){

            return null;
        }


        // 3. 대상 엔티티가 있으면 삭제하고 정상 응답(200) 반환하기
        articleRepository.delete(target);
        return target;
    }


    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos){

        // 1. dto 묶음(리스트)을 엔티티 묶음(리시트)으로 변환하기
        List<Article> articleList = dtos.stream()   // 스트림 화 한다
            .map(dto -> dto.toEntity())             // map으로 dto하나하나 toEntity()수행
            .collect(Collectors.toList());          // 매핑한것을 리스트로 묶는다


        // 2. 엔티티 묶음(리스트)을 DB에 저장하기
        articleList.stream()    // 스트림 화 한다
            .forEach(article -> articleRepository.save(article));   // 각각 article이 올때마다 DB에 저장


        // 3. 강제로 에러를 발생시키기
        articleRepository.findById(-1L)
            .orElseThrow(()->new IllegalArgumentException("결제 실패!"));


        // 4. 결과 값 반환하기
        return articleList;
    }
}

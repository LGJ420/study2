package com.example.firstproject.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.firstproject.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
    @Query(value = "SELECT * FROM study2.comment WHERE article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    List<Comment> findByNickname(String nickname);
}
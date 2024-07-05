package com.example.firstproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;

@RestController
public class CommentApiController {
    
    @Autowired
    private CommentService commentService;

    // 1. 댓글 조회
    @GetMapping("api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comment(@PathVariable Long articleId){

        // 서비스에 명령
        List<CommentDto> dtos = commentService.comments(articleId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 2. 댓글 생성



    // 3. 댓글 수정



    // 4. 댓글 삭제
}

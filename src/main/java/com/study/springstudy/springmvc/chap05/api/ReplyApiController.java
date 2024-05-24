package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap05.dto.response.ReplyDetailDto;
import com.study.springstudy.springmvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/replies")
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyService replyService;

    // 댓글 목록 조회 요청
    // URL : /api/v1/replies/원본글번호 - GET -> 목록조회
    // http://localhost:8383/api/v1/replies/17
    @GetMapping("/{bno}") // 변칙적으로 들어가야 할 내용은 {파라미터}으로 만들어주기
    // @PathVariable : URL에 붙어있는 변수 값을 읽는 아노테이션
    public ResponseEntity<?> list (@PathVariable long bno){

        List<ReplyDetailDto> replies = replyService.getReplies(bno);

        return ResponseEntity.ok().body(replies);

        // https://www.postman.com/downloads/ 포스트맨 다운로드
        // 브라우저가 아닌 모바일 등 환경일 때 브라우저를 사용하지 않고 테스트 가능.
        // get -> http://localhost:8383/api/v1/replies/1
    }
}

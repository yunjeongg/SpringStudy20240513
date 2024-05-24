package com.study.springstudy.springmvc.chap05.dto.request;

import lombok.*;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
// 댓글 등록 시 클라이언트로부터 받는 데이터 객체
public class ReplyPostDto {

    private String text; // 댓글 내용
    private String author; // 댓글 작성자
    private Long bno; // 원본 글번호

}

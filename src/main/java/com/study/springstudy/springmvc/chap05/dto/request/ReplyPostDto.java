package com.study.springstudy.springmvc.chap05.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
// 댓글 등록 시 클라이언트로부터 받는 데이터 객체
public class ReplyPostDto {

    // 클라이언트에서 서버로 오는 데이터를 받고, 검증

    @NotBlank // - null, 빈문자 둘 다 안됨
    // @NotNull - null 안됨 // @NotEmpty - null은 되는데 빈문자는 안됨
    @Size(min = 1, max = 300) // - 최소 1글자에서 300글자까지 작성
    private String text; // 댓글 내용
    @NotBlank
    @Size(min = 2, max = 8)
    private String author; // 댓글 작성자
    @NotNull
    private Long bno; // 원본 글번호

}

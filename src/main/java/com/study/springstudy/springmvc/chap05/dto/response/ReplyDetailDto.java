package com.study.springstudy.springmvc.chap05.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ReplyDetailDto {

    // 서버에서 클라이언트로 보내는 데이터를 클라이언트의 입맛에 맞게 변환

    // 클라이언트가 rno를 reply_no 로 바꿔달라고 했을 때
    // rno를 클라이언트의 요구에 맞게 reply_no 라고 바꿔줄 수도 있지만
    // 그럼 수정해야 할 부분이 있기 때문에 @JsonProperty("reply_no")로 해줘도 된다.
    private long rno;
    private String text;
    private String writer;
    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm")
    private LocalDateTime createAt;

    // 엔터티를 DTO 로 변환하는 생성자
    public ReplyDetailDto (Reply r) {
        this.rno = r.getReplyNo();
        this.text = r.getReplyText();
        this.writer = r.getReplyWriter();
        this.createAt = r.getReplyDate();
    }
}

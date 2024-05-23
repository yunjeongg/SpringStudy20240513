package com.study.springstudy.springmvc.chap05.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @Setter 만들지 않은 이유는 entity 데이터는 DB의 데이터를 직접적으로 가져와
// 가공하지 않은 상태 그대로 놔두는게 좋다.
public class Reply {

    private long id; // 실무에서는 long
    private String replyText;
    private String replyWriter;
    private LocalDateTime replyDate;
    private long boardNo;
}

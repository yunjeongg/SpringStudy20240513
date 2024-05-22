package com.study.springstudy.springmvc.chap04.common;

import lombok.*;

@Getter
//@Setter
@ToString
@EqualsAndHashCode
//@NoArgsConstructor // 기본생성자
@AllArgsConstructor
public class Page {

    private int pageNo; // 클라이언트가 요청한 페이지 번호
    private int amount; // 클라이언트가 요청한 한 페이지당 게시물 목록 수

    public Page() {
        // 웹페이지 들어왔을 때 기본적으로 보여줄 페이지 1, 게시물 6개
        this.pageNo = 1;
        this.amount = 6;
    }

    public void setPageNo(int pageNo) {
        // 페이지를 1 이하, 또는 최대숫자 이상일 경우 1페이지로 돌아간다.
        if (pageNo < 1 || pageNo > Integer.MAX_VALUE) {
            this.pageNo = 1;
            return;
        }
        this.pageNo = pageNo;
    }

    public void setAmount(int amount) {
        if (amount < 6 || amount > 60) {
            this.amount = 6;
            return;
        }
        this.amount = amount;
    }



        public int getApple (){
        // Apple 이란 필드를 만들지 않아도
        // 메소드 명을 getㅇㅇ으로 지으면 마이바티스가
        // 자동으로 필드의 getter로 인식해서 적용시킴.
        return 5;
    }

    /**
        만약 한 페이지에 게시물을 10개씩 렌더링한다면
        1페이지 -> LIMIT 0, 10
        2페이지 -> LIMIT 10, 10
        3페이지 -> LIMIT 20, 10

        만약 한 페이지에 게시물을 6개씩 렌더링한다면
        1페이지 -> LIMIT 0, 6
        2페이지 -> LIMIT 6, 6
        3페이지 -> LIMIT 12, 6

        만약 한 페이지에 게시물을 N개씩 렌더링한다면
        1페이지 -> LIMIT 0, N
        2페이지 -> LIMIT 6, N
        3페이지 -> LIMIT 12, N
        N페이지 -> LIMIT (M - 1) * N, N

        http://localhost:8383/board/list?pageNo=1&amount=9
     */
    public int getPageStart (){
        return (this.pageNo - 1) * this.amount;
    }


}

package com.study.springstudy.springmvc.chap04.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

// 페이지 화면 렌더링에 필요한 정보들을 계산
@Getter @ToString
@EqualsAndHashCode
public class PageMaker {

    // 한 화면에 페이지를 몇 개씩 배치할 것인지??
    private static final int PAGE_COUNT = 10;
    // 페이지 시작번호와 끝번호
    private int begin, end;

    // 현재 페이지 정보
    private Page pageInfo; // 내가 지금 몇 페이지를 보고 있는가?

    public PageMaker(Page page) {
        this.pageInfo = page;
        makePageInfo();
    }

    // 페이지 생성에 필요한 데이터를 만드는 알고리즘
    private void makePageInfo () {
        // 1. end 값 계산
        /*
            지금 사용자가 7페이지를 보고 있다면
            페이지 구간은 :1 -10 구간

            지금 사용자가 24페이지를 보고 있다면
            페이지 구간은 :21 -30 구간

            // 5개씩 페이지를 배치하는 경우는
            7page : 6-10 구간
            24page : 21~25 구간

            // 공식: (올림 (현재 사용자가 위치한 페이지넘버 / 한 화면에 보여줄 페이지 수)) * 한 화면에 보여줄 페이지 수
         */

        // int 타입끼리 연산은 소수점이 나올 수 없어 ceil 사용불가, 이 경우 한쪽을 소수점타입으로 변경
        // this.end 는 int 타입이기 때문에 결과를 int 타입으로 변경
        this.end = (int)Math.ceil(pageInfo.getPageNo() / (double)PAGE_COUNT ) * PAGE_COUNT;

        // 2. begin
        //          총 페이지 - 한 페이지에 나타낼 페이지 + 1
        this.begin = this.end - PAGE_COUNT + 1;
    }
}

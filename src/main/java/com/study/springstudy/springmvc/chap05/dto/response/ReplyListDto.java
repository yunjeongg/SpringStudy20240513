package com.study.springstudy.springmvc.chap05.dto.response;

import com.study.springstudy.springmvc.chap04.common.PageMaker;
import lombok.*;

import java.util.List;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ReplyListDto {
    // http://localhost:8383/api/v1/replies/100/page/3

    // 원래 배열 형태로 JSON으로 변환되었는데
    /*
        [
        List<ReplyDetailDto>
            {}, {}, {}
        ]
     */

    // replies객체안에 배열을 넣은 상태로 JSON으로 변환한다.
    /*
        [
            replies:[
            {}, {}, {}
            ]
        ]
     */

    private PageMaker pageInfo; // 페이지를 계산해서 구조를 만들어놓은것
    private List<ReplyDetailDto> replies;

}

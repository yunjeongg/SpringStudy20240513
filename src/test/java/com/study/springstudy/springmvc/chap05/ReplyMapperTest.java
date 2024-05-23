package com.study.springstudy.springmvc.chap05;

import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReplyMapperTest {

    @Autowired BoardMapper boardMapper;
    @Autowired ReplyMapper replyMapper;

//    @Test
//    @DisplayName("게시물 100개와 댓글 5000개를 랜덤으로 등록")
//    void bulkInsert() {
//        for (int i = 1; i <= 100; i++) {
//            Board b = Board.builder()
//                    .title("재미있는 글" + i)
//                    .content("응 개노잼" + i)
//                    .writer("산리오친구들" + i)
//                    .build();
//        }
//        for (int i = 1; i <= 5000; i++) {
//            Reply reply = Reply.builder()
//                    .replyText("하하호호 댓글" + i)
//                    .replyWriter("꾸러기친구들" + i)
//                    .boardNo((long) (Math.random() * 100 + 1))
//                    .build();
//
//            replyMapper.save(reply);
//        }
//    }
}
package com.study.springstudy.springmvc.chap05.service;

import com.study.springstudy.springmvc.chap05.dto.request.ReplyPostDto;
import com.study.springstudy.springmvc.chap05.dto.response.ReplyDetailDto;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import com.study.springstudy.springmvc.chap05.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {

    // 컨트롤러에게 명령받은 내용의 중간처리

    private final ReplyMapper replyMapper;

    // 댓글 목록 전체 조회
    public List<ReplyDetailDto> getReplies(long boardNo){
        List<Reply> replies = replyMapper.findAll(boardNo);
        return replies.stream().map(r-> new ReplyDetailDto(r))
                .collect(Collectors.toList());
    }

    // 댓글 입력
    public boolean register(ReplyPostDto dto) {
        Reply reply = Reply.builder()
                .replyText(dto.getText())
                .replyWriter(dto.getAuthor())
                .boardNo(dto.getBno())
                .build();


        boolean flag = replyMapper.save(reply);
        if (flag) log.info("댓글 등록 성공!! - {}", dto);
        else log.warn("댓글 등록 실패");

        return flag;
    }

    // 댓글 수정
    public void modify(){}

    // 댓글 삭제
    @Transactional // 트랜잭션으로 묶기
    public List<ReplyDetailDto> remove(long rno){
        // 댓글번호로 원본 글번호 찾기
        long bno = replyMapper.findBno(rno);

        // DB에 댓글 삭제명령
        boolean flag = replyMapper.delete(rno);

        // 삭제 후 삭제된 목록을 리턴
        return flag ? getReplies(bno) : Collections.emptyList();
    }

}

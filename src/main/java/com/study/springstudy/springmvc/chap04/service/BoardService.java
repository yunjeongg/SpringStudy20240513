package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardFindAlldto;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import com.study.springstudy.springmvc.chap05.mapper.ReplyMapper;
import com.study.springstudy.springmvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    // Service 는 여러개의 매퍼를 사용할 수 있다.
    private final BoardMapper boardMapper;
    private final ReplyMapper replyMapper;

    // 목록 조회 요청 중간처리
    public List<BoardListResponseDto> findList(Page page) {
        List<BoardFindAlldto> boardList = boardMapper.findAll(page);

        // 조회해온 게시물 리스트에서 각 게시물들의 조회수를 확인하여
        // 조회수가 5이상인 게시물에 특정 마킹
        List<BoardListResponseDto> dtoList = boardList.stream()
                .map(b -> new BoardListResponseDto(b))
                .collect(Collectors.toList());

        return dtoList;
    }

    // 등록 요청 중간처리
    public boolean insert(BoardWriteRequestDto dto, HttpSession session) {
        Board b = dto.toEntity();

        // 계정명을 엔터티에 추가 - 세션에서 계정명 가져오기
        b.setAccount(LoginUtil.getLoggedInUserAccount(session));

        return boardMapper.save(b);
    }

    // 삭제 요청 중간처리
    public boolean remove(int boardNo) {
        return boardMapper.delete(boardNo);
    }

    // 상세 조회 요청 중간처리
    public BoardDetailResponseDto detail(int bno) {
        Board b = boardMapper.findOne(bno);
        // 조회 할 때 마다 조회 수 올리기
        if (b != null) boardMapper.upViewCount(bno);

        // 댓글 목록 조회
//        List<Reply> replies = replyMapper.findAll(bno);

        BoardDetailResponseDto responseDto = new BoardDetailResponseDto(b);
//        responseDto.setReplies(replies);

        return responseDto;
    }

    public int getCount(Search search) {
        return boardMapper.count(search);
    }
}

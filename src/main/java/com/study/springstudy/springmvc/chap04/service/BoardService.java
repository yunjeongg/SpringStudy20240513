package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardDetailResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardFindAllDto;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDto;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.study.springstudy.springmvc.util.LoginUtil.*;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardMapper boardMapper;

    // 목록 조회 요청 중간처리
    public List<BoardListResponseDto> findList(Search page) {

        List<BoardFindAllDto> boardList = boardMapper.findAll(page);

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
        b.setAccount(getLoggedInUserAccount(session));

        return boardMapper.save(b);
    }

    // 삭제 요청 중간처리
    public boolean remove(int boardNo) {
        return boardMapper.delete(boardNo);
    }

    // 상세 조회 요청 중간처리
    public BoardDetailResponseDto detail(int bno, HttpServletRequest request, HttpServletResponse response) {

        // 게시물 정보 조회
        Board b = boardMapper.findOne(bno);

        HttpSession session = request.getSession();

        // 비회원이거나 본인 글이면 조회수 증가 방지
        if (!isLoggedIn(session) || isMine(b.getAccount(), getLoggedInUserAccount(session))) {
            return new BoardDetailResponseDto(b);
        }

        // 조회수가 올라가는 조건처리
        if (shouldIncreaseViewCount(bno, request, response)) boardMapper.upViewCount(bno);
        return new BoardDetailResponseDto(b);
    }

    // 조회수 증가 여부를 판단
    /*
        - 만약 내가 처음 조회한 상대방의 게시물이면 해당 게시물 번호로 쿠키를 생성, 쿠키안에는 생성시간을 저장, 수명은 1시간으로 설정
        - 이후 게시물 조회시 반복해서 쿠키를 조회한 후 해당 쿠키가 존재할 시 false를 리턴하여 조회수증가를 방지
        - 쿠키 생성 예시
        Cookie(name = view_101, 2024-06-03 14:11:30) // 이름 = 글번호, 내용 = 조회한 시간
     */

    private boolean shouldIncreaseViewCount(int bno, HttpServletRequest request, HttpServletResponse response) {
        // 쿠키 검사
        String cookieName = "view_" + bno;
        Cookie viewCookie = WebUtils.getCookie(request, cookieName); // 쿠키가 있으면 리턴 쿠키, 없으면 리턴 null

        // 이 게시물에 대한 쿠키가 존재한다면? -> 아까 조회한 게시물이다.
        if (viewCookie != null) {
            return false;
        }
        // 쿠키를 생성하고
        makeViewCookie(cookieName, response);

        return true; // 조회 수 증가
    }

    // 조회수 쿠키를 생성하고 클라이언트에 전송하는 메서드
    private void makeViewCookie(String cookName, HttpServletResponse response) {
        Cookie newCookie = new Cookie(cookName, LocalDateTime.now().toString());
        newCookie.setPath("/"); // 쿠키 사용 범위 (사이트 전체)
        newCookie.setMaxAge(60 * 60); // 쿠키유효시간 1시간

        response.addCookie(newCookie);

        // 조회수를 쿠키로 제어하는게 좋지 않은 이유
        // 쿠키에 대한 지식이 있다면 우회해서 조회수를 올릴 수 있다.
        // 브라우저자체에서 쿠키를 차단했을 때 먹히지 않을 수 있다.
        // 쿠키는 컴퓨터 내부에 저장되기 때문에 같은컴퓨터라면 다른계정이라도 조회수 증가하지 않는다.
        // 쿠키는 개수제한이 있어서 (320개), 글을 그 이상 봤을 경우 먹히지 않는다.
    }


    public int getCount(Search search) {
        return boardMapper.count(search);
    }

}

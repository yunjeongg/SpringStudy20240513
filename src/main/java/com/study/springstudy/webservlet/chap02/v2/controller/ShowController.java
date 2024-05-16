package com.study.springstudy.webservlet.chap02.v2.controller;

import com.study.springstudy.webservlet.MemberMemoryRepo;
import com.study.springstudy.webservlet.View;
import com.study.springstudy.webservlet.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowController implements ControllerV2 {

    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

    @Override
    public View process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 적절한 저장소에서 회원정보들을 가져오기.
        List<Member> memberList = repo.findAll();

        // 2. 해당 회원정보를 JSP파일에 전송하기 위한 세팅을 한다.
        request.setAttribute("memberList", memberList);

        // 3. 적절한 JSP를 찾아 화면 렌더링.
        return new View("v2/m-list");
    }
}

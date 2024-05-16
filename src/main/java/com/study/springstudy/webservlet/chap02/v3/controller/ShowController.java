package com.study.springstudy.webservlet.chap02.v3.controller;

import com.study.springstudy.webservlet.MemberMemoryRepo;
import com.study.springstudy.webservlet.ModelAndView;
import com.study.springstudy.webservlet.entity.Member;

import java.util.List;
import java.util.Map;

public class ShowController implements ControllerV3 {

    private MemberMemoryRepo repo = MemberMemoryRepo.getInstance();

    @Override
    public ModelAndView process(Map<String, String> paramMap) {

        // 1. 적절한 저장소에서 회원정보들을 가져오기.
        List<Member> memberList = repo.findAll();

        // 3. 적절한 JSP를 찾아 화면 렌더링.
        ModelAndView modelAndView = new ModelAndView("v3/m-list");

        // 2. 해당 회원정보를 JSP파일에 전송하기 위한 세팅을 한다.
        modelAndView.addAttribute("memberList", memberList);

        return modelAndView;
    }
}

package com.study.springstudy.springmvc.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ResponseController {

    // 응답처리

    // JSP 파일로 포워딩할 때 데이터 전달하기

    /*
        1. Model 객체 사용
        Model 객체는 Controller 에서 생성된 데이터를 담다 View 로 전달할 때 사용되는 객체이다.
        addAttribute 메서드를 사용해 Model에 데이터를 key, value 형식으로 추가한 후
        return 반환값에 작성된 String 타입 JSP 경로로 JSP Resolver 에 접근해서 찾을 수 있다.
     */
    // URL : http://localhost:8383/hobbies
    @RequestMapping("/hobbies")
    public String hobbies (Model model) {

        model.addAttribute("name", "김철수");
        model.addAttribute("hobbies", List.of("축구", "수영", "영화보기"));

        model.addAttribute("major", "경영학");

        return "mvc/hobbies";
    }

    /*
        2. ModelAndView 객체 사용
        Model 객체와 사용법이 비슷하다. 그러나 Model 이 Model, View 를 각각 처리해야 한다면
        ModelAndView 는 Model, View 를 한번에 처리해줘서 편리하다.
        Return 반환값을 String 타입으로 반환하지 않고
        ModelAndView 객체안에 setViewName 메소드로 JSP 이름을 세팅해
        return 반환값을 ModelAndView 객체로 하고,
        JSP Resolver 에서 그에 해당하는 JSP 파일을 찾는 방식으로 진행한다.

        * JSP Resolver 는 String, ModelAndView 객체만 받고, 그 외의 객체는 null로 처리한다.
     */
    // http://localhost:8383/hobbies2
    @RequestMapping("hobbies2")
    public ModelAndView hobbies2 () {

        ModelAndView mv = new ModelAndView("mvc/hobbies");

        mv.addObject("name", "박영희");
        mv.addObject("hobbies", List.of("멍때리기", "맛집가기"));
        mv.addObject("major", "컴퓨터 과학");

        return mv;
    }
}

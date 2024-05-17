package com.study.springstudy.springmvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coffee/*")
public class CoffeeController {


    /**
     * @requser-url : /coffee/order
     * @forwarding-jsp : /WEB-INF/views/mvc/coffee-form.jsp
     */

    // GET 요청만 받겠다.
    // 파라미터가 URL에 노출되는 방식, SELECT 기능면에서 우수
    // 서버에 데이터를 가져와 보여주고 싶을 때
//    @RequestMapping(value = "/order", method = RequestMethod.GET), 요약한 것이 아랫줄
    @GetMapping("/order")
    public String order () {
        return "mvc/coffee-form";
    }

    // POST 요청만 받겠다.
    // 파라미터가 노출되지 않아 보안에 우수, CREATE, UPDATE, DELETE에 사용
    // 서버의 값, 상태를 바꾸고 싶을 때
//    @RequestMapping(value="/result", method = RequestMethod.POST), 요약한 것이 아랫줄
    @PostMapping("/result")
    public String result (String menu, int price, Model model) {
        // 1. 주문한 데이터(menu, price) 읽어오기
        // 파라미터 (String menu, int price)

        // 2. JSP 에 보내서 렌더링
        // 파라미터 (Model model)
        model.addAttribute("mmm", menu);
        model.addAttribute("ppp", price);
        return "mvc/coffee-result";
    }
}

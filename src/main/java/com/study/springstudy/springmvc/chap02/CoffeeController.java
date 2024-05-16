package com.study.springstudy.springmvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coffee/*")
public class CoffeeController {

    /**
     * @requser-url : /coffee/order
     * @forwarding-jsp : /WEB-INF/views/mvc/coffee-form.jsp
     */

    @RequestMapping("/order")
    public String order () {
        return "mvc/coffee-form";
    }

    @RequestMapping("/result")
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

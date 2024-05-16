package com.study.springstudy.springmvc.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/spring/chap01/*")
public class BasicController {

    // 요청처리

    // 디테일한 요청은 메소드로 작성
    // URL : /spring/chap01/hello
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("hello 요청이 들어옴!!");

        // 최종경로는 /WEB-INF/views/hello.jsp
        return "hello";
    }

    // 요청 파라미터(Query String) 읽기
    // URL에 붙어있거나 form태그에서 전송된 데이터

    // 1. @HttpServletRequest 사용
    // http://localhost:8383/spring/chap01/person?name=kim&age=30
    @RequestMapping("/person")
    public String person (HttpServletRequest request) {
        System.out.println("/person!!");
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        System.out.println("name = " + name);
        System.out.println("age = " + age);

        return "";
    }

    // 2. @RequestParam 사용
    // http://localhost:8383/spring/chap01/major?stu=kim&major=business&grade=3
    @RequestMapping("/major")
    // @RequestParam 생략가능, String에서 해당 타입으로 변환시켜줌
    // @RequestParam 꼭 써야 할 경우는 클라이언트가 보낸 변수이름과 내가 받는 변수이름이 다를 경우 (major)
    public String major (@RequestParam String stu, @RequestParam("major") String mj, @RequestParam int grade) {

        String major = "ddd";

        System.out.println("stu = " + stu);
        System.out.println("major = " + mj);
        System.out.println("grade = " + grade);

        return "";
    }

    // 3. 커맨트 객체 (RequestDTO) 사용하기
    // ex) /spring/chap01/order?orderNum=22&goods=구두&amount=3&price=20000....
    // 받아야 할 파라미터가 많을 경우 함수파라미터에 일일이 쓰지 않고 클래스를 하나 따로 만들어
    // 받을 파라미터를 필드로 설정하고, getter, setter 를 설정해주고
    // 받을 파라미터명을 클래스명으로 설정한다.
    // http://localhost:8383/spring/chap01/order?orderNum=22&goods=aircon&amount=10&price=20000
    @RequestMapping("/order")
    public String order(OrderDTO order) {

        System.out.println("주문번호 = " + order.getOrderNum());
        System.out.println("주문상품명 = " + order.getGoods());
        System.out.println("주문개수 = " + order.getAmount());
        System.out.println("주문가격 = " + order.getPrice());

        return "";
    }
}

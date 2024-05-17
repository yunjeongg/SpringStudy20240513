package com.study.springstudy.springmvc.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/*
    클라이언트의 요청을 받는 역할
    SpringFramework 는 MVC 패턴을 쓰고
    Controller 는 Model(비즈니스로직), View(화면)를 연결하는 역할을 한다.
    RequestMapping 는 요청주소와 실제주소를 연결하는 역할을 한다.
*/
// 1. @Controller, @RequestMapping 을 클래스 명 위에 작성
// 2. @RequestMapping ("이 화면을 띄우고 싶을 때 클라이언트가 작성할 주소를 작성")
// (이 클래스에서 여러 요청처리를 같이 하기 때문에 요청에 대한 공통 주소만 작성하고, 세부주소는 각각의 메소드에서 작성한다)
@Controller
@RequestMapping("/spring/chap01/*")
public class BasicController {

    // 클라이언트의 요청을 받은 후 디테일한 요청처리는 각각의 메소드에서 작성한다.

    // ==================== 클라이언트가 작성한 주소에 대한 처리 ==================== //

    // URL : /spring/chap01/hello
    @RequestMapping("/hello") // 3-1. hello 메소드 요청할 세부주소 작성하기
    // SpringFramework 의 기본 메소드 형식은 public string 메소드명(){}
    public String hello() { // 3-2. 메소드 정의하기
        System.out.println("hello 요청이 들어옴!!");

        // 최종경로는 /WEB-INF/views/hello.jsp
        return "hello"; // 3-3. 메소드 반환값(이 메소드를 통해 연결시킬 페이지 주소) 작성하기

        /*
            이 메소드는 /spring/chap01/hello 주소요청에 대해
            연결할 페이지 /WEB-INF/views/hello.jsp 를 반환한다.
            (앞뒤로 생략된 주소는 resources/application.properties 에 이미 적어놓음)
         */
    }



    // ==================== 클라이언트가 작성한 값에 대한 처리 ==================== //

    /*
        클라이언트가 hello 메소드에서 요청한 내용에 대한
        요청 파라미터(Query String) 읽기
        요청 파라미터는 URL에 붙어있거나 form 태그에서 전송된 데이터
     */

    // 요청 파라미터를 읽고 처리하는 방법

    // 1. @HttpServletRequest 사용
    // URL : http://localhost:8383/spring/chap01/person?name=kim&age=30
    @RequestMapping("/person")
    public String person (HttpServletRequest request) { // 1. 파라미터에 HttpServletRequest 작성하기
        System.out.println("/person!!");
        String name = request.getParameter("name"); // 2-1. 파라미터로 받은 값에 대해 각각의 변수로 저장하기
        String age = request.getParameter("age");

        System.out.println("name = " + name);
        System.out.println("age = " + age);

        return "";
    }

    /*
        2. @RequestParam 사용
        SpringFramework 을 사용하면 요청에 대해 String 타입으로 반환을 하는데
        메소드 파라미터자리에 @RequestParam 를 사용하면 String 타입에서 원하는 타입으로 자동 변환시켜준다.
        (ex. String -> int, ...)
        @RequestParam 는 기본적으로 생략이 가능하지만, 무조건 써야 할 경우가 있는데
        프론트에서 보낸 변수명과 백엔드에서 내가 받고 싶은 변수명에 차이가 있을 경우 생략이 불가능하다.
        @RequestParam("major") String mj (프론트 -> major, 백엔드 -> mj)
     */
    // URL : http://localhost:8383/spring/chap01/major?stu=kim&major=business&grade=3
    @RequestMapping("/major")
    public String major (@RequestParam String stu, @RequestParam("major") String mj, @RequestParam int grade) {

        String major = "ddd";

        System.out.println("stu = " + stu);
        System.out.println("major = " + mj);
        System.out.println("grade = " + grade);

        return "";
    }

    /*
        3. 커맨트 객체 (RequestDTO) 사용
        ex) /spring/chap01/order?orderNum=22&goods=구두&amount=3&price=20000....
        위와 같이 한번에 받아야 할 파라미터가 많을 경우 메소드 파라미터에 일일이 쓸 수도 있지만
        클래스를 따로 만들어 그 클래스의 필드명에 받아야 할 파라미터를 필드로 작성하고
        getter, setter 을 설정해 준 후
        요청파라미터에 클래스 명을 작성해 주면 된다.
     */
    // URL : http://localhost:8383/spring/chap01/order?orderNum=22&goods=aircon&amount=10&price=20000
    @RequestMapping("/order")
    public String order(OrderDTO order) { // OrderDTO 클래스의 필드명으로 저장된 파라미터

        System.out.println("주문번호 = " + order.getOrderNum());
        System.out.println("주문상품명 = " + order.getGoods());
        System.out.println("주문개수 = " + order.getAmount());
        System.out.println("주문가격 = " + order.getPrice());

        return "";
    }
}

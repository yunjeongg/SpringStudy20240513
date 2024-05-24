package com.study.springstudy.springmvc.chap05.rest;

import com.study.springstudy.database.chap01.Person;
import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap04.common.PageMaker;
import com.study.springstudy.springmvc.chap04.common.Search;
import com.study.springstudy.springmvc.chap04.dto.BoardListResponseDto;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/rest")
//@Controller @ResponseBody -- 두개를 합친게 @RestController
@RestController
@RequiredArgsConstructor
public class RestApiController {

    private final BoardService boardService;

    @GetMapping("/hello")
//    @ResponseBody // 서버가 클라이언트에게 순수한 데이터를 전달할 때 사용
    public String hello () {
        // rest API
        // 클라이언트와 SERVER 사이에서 요청과 응답을 한다.
        // 서버가 ui 만드는 것이 아니라 클라이언트에게 재료를 주고 셀프로 ui를 만들게 하는 것
        // 요즘에는 웹, 모바일, 등등 다양하기때문에

        return "안녕안녕 메롱메롱";
    }

    @GetMapping("/hobby")
//    @ResponseBody // 쓰면 return값으로 jsp를 찾지 않고 json으로 바꿔버린다.
    // jsp를 찾지 않고 순수하게 데이터를 보내고 싶을 때 사용
    public List<String> hobby (){
        List<String> hobbies = List.of("태권도","장기","댄스");
        return hobbies;
    }
    @GetMapping("/person")
//    @GetMapping(value = "/person", produces = "application/json") -- 기본값, 자동으로 들어가있음
//    @ResponseBody
    public Person person () {
        Person p = new Person(100, "핑구", 10);
        
        return p; 
    }

    @GetMapping("/board")
    public Map<String, Object> board () {
        List<BoardListResponseDto> list = boardService.findList(new Search());

        HashMap<String, Object> map = new HashMap<>();
        map.put("page", new PageMaker(new Page(), boardService.getCount(new Search())));

        map.put("articles", list);

        return map;
    }

    // 일반 Controller 은 메소드의 타입이 String 으로 고정되어 있었다면
    // @RestController 는 리턴하는 데이터의 타입에 따라 변경할 수 있었다.
    // 그러나..

    /*
         RestController에서 리턴타입을 ResponseEntity를 쓰는 이유

         - 클라이언트에게 응답할 때는 메시지 바디안에 들어 있는 데이터도 중요하지만
         - 상태코드와 헤더정보를 포함해야 한다
         - 근데 일반 리턴타입은 상태코드와 헤더정보를 전송하기 어렵다
     */
    @GetMapping("/people")
    public ResponseEntity<?> people () {
        Person p1 = new Person(111, "딸기겅듀", 30);
        Person p2 = new Person(222, "잔망루피", 40);
        Person p3 = new Person(333, "뽀로로롱", 50);

        List<Person> people = List.of(p1, p2, p3);
        
        // 헤더 정보 전달
        HttpHeaders headers = new HttpHeaders();
        headers.add("my-pet", "cat");
        headers.add("money", "100");
        
//        return ResponseEntity.status(200).body(people); // status(응답코드).body(전달할데이터명)
        // 200 - 잘했음(.ok()) / 400 잘못됨(.badRequest()),403 접근허용거부, 404 찾지못할때, 500 서버잘못
        return ResponseEntity.ok().headers(headers).body(people);
    }

    @GetMapping("/bmi")
    public ResponseEntity<?> bmi (@RequestParam(required = false) Double cm, @RequestParam(required = false) Double kg) {

        // http://localhost:8383/rest/bmi?cm=180&kg=150
        // 파라미터가 없을 땐 400번, 파라미터 주면 200번, if문으로 지정 안해주면 500번
        if (cm == null || kg == null) {
            return ResponseEntity.badRequest().body("키와 몸무게를 전달하세요");
        }

        // 체질량지수 구하기
        double m = cm / 100;
        double bmi = kg / (m * m);

        return ResponseEntity
                .ok()
                .body(bmi);
    }
}

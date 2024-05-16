package com.study.springstudy.springmvc.chap03.controller;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDTO;
import com.study.springstudy.springmvc.chap03.entity.Score;
import com.study.springstudy.springmvc.chap03.repository.ScoreJdbcRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/score")
public class ScoreController {

    // 데이터베이스에 저장을 위임하는 의존객체 설정
    // 구체적인 클래스보다 추상적인 인터페이스에 의존시키는게 좋다.
    private ScoreJdbcRepository repository = new ScoreJdbcRepository();

    /*
    # 요청 URL
    1. 학생 성적정보 등록화면을 보여주고 및 성적정보 목록조회 처리
    - /score/list : GET
    */
    @GetMapping("/list")
    public String list (Model model) {
        System.out.println("/score/list : Get!!");

        List<Score> scoreList = repository.findAll();
        model.addAttribute("sList", scoreList);


        return "score/score-list";
    }

    /*
    2. 성적 정보 등록 처리 요청
    - /score/register : POST
    */
    @PostMapping("/register")
    public String register(ScorePostDTO dto) {
        System.out.println("/score/register : POST!");
        System.out.println("dto = " + dto);

        // 데이터베이스에 저장 (위임)
        // Score에 dto를 넣어줄게 필요한 데이터 조합해줘.
        Score score = new Score(dto);

        repository.save(score);

        // 다시 조회로 돌아가야 저장된 데이터를 볼 수 있음
        // 포워딩이 아닌 리다이렉트로 재요청을 넣어야 새롭게 디비를 조회
        return "redirect:/score/list";
    }

    /*
    3. 성적정보 삭제 요청
    - /score/remove : POST
    */
    @PostMapping("/remove")
    public String remove() {
        System.out.println("/score/remove : POST!");
        return "";
    }

    /*
    4. 성적정보 상세 조회 요청
    - /score/detail : GET
    */
    @GetMapping("/detail")
    public String detail(long stuNum, Model model) {
        System.out.println("/score/detail : GET!");
//        System.out.println("stuNum = " + stuNum);

        // 1. 상세조회를 원하는 학번을 읽기
        // 2. DB에 상세조회 요청
        Score score = repository.fineOne(stuNum);
        // 3. DB에서 조회한 회원정보 JSP에게 전달
        model.addAttribute("s", score);
        // 4. rank 조회
        int[] result = repository.findRankByStuNum(stuNum);
//        System.out.println("rank = " + rank);
        model.addAttribute("rank", result[0]);
        model.addAttribute("count", result[1]);

        return "score/score-detail";
    }


}

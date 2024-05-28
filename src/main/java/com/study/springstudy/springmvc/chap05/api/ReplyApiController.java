package com.study.springstudy.springmvc.chap05.api;

import com.study.springstudy.springmvc.chap05.dto.request.ReplyPostDto;
import com.study.springstudy.springmvc.chap05.dto.response.ReplyDetailDto;
import com.study.springstudy.springmvc.chap05.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/replies")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin // CORS 정책 허용범위 설정
public class ReplyApiController {

    private final ReplyService replyService;

    // 댓글 목록 조회 요청
    // URL : /api/v1/replies/원본글번호   -  GET -> 목록조회
    // @PathVariable : URL에 붙어있는 변수값을 읽는 아노테이션
    @GetMapping("/{bno}")
    public ResponseEntity<?> list(@PathVariable long bno) {

        if (bno == 0) {
            String message = "글 번호는 0번이 될 수 없습니다.";
            log.warn(message);
            return ResponseEntity
                    .badRequest()
                    .body(message);
        }

        log.info("/api/v1/replies/{} : GET", bno);

        List<ReplyDetailDto> replies = replyService.getReplies(bno);
        // 댓글 하나도 없을 때 에러남
//        log.debug("first reply : {}", replies.get(0));

        return ResponseEntity
                .ok()
                .body(replies);
    }

    // 댓글 생성 요청
    // @RequestBody : 클라이언트가 전송한 데이터를 JSON으로 받아서 파싱
    // http://localhost:8383/api/v1/replies
    @PostMapping
    public ResponseEntity<?> posts(@Validated @RequestBody ReplyPostDto dto, BindingResult result) {
        // @Validated 검증됨
        // BindingResult - 입력값 검증 결과 데이터를 갖고 있는 객체

        log.info("/api/v1/replies : POST");
        log.debug("parameter: {}", dto);

        // 에러가 났을 때 400 메세지만 보내는 것이 아닌 정확이 어디서 문제가 났는지까지도 클라이언트에게 전달해줘야 한다.
        if (result.hasErrors()) {

            Map<String, String> errors = makeValidationMessageMap (result);

            return ResponseEntity.badRequest().body(errors);
        };

        boolean flag = replyService.register(dto);

        if (!flag) return ResponseEntity
                .internalServerError()
                .body("댓글 등록 실패!");

        return ResponseEntity
                .ok()
                .body(replyService.getReplies(dto.getBno()));
    }

    private Map<String, String> makeValidationMessageMap(BindingResult result) {

        Map<String, String> errors = new HashMap<>();

        // 에러정보가 모여있는 리스트
        List<FieldError> fieldErrors = result.getFieldErrors();

        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return errors;
    }

    // 삭제 처리 요청
    @DeleteMapping("/{rno}") // 삭제요청(삭제할 글번호 url)
    public ResponseEntity<?> delete(@PathVariable long rno){ // url읽기위해 @PathVariable 붙이기

        List<ReplyDetailDto> dtoList = replyService.remove(rno);

        return ResponseEntity
                .ok()
                .body(dtoList);
    }

}
package com.study.springstudy.springmvc.upload;

import com.study.springstudy.springmvc.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@Slf4j
public class UploadController {

    // 업로드 루트 경로
    private String rootPath = "D:/spring-prj/upload"; // 첨부파일 저장위치

    @GetMapping("/upload/form")
    public String uploadForm() {
        return "upload/upload-form";
    }

    @PostMapping("/upload/file")
    public String uploadFile(@RequestParam("thumbnail") MultipartFile file) { // 파일형식을 받을 때 타입 MultipartFile

        log.info("file-name: {}", file.getOriginalFilename());
        log.info("file-size: {}MB", file.getSize() /1024.0/1024.0);
        log.info("file-type: {}", file.getContentType());

        // 첨부파일 서버에 저장하기
        // 1. 루트 디렉토리를 생성하기
        // 경로에 도달할 수 있게 경로객체 생성하기
        File root = new File(rootPath);
        if (!root.exists()) root.mkdirs(); // 그런폴더없으면 만들기

        FileUtil.uploadFile(rootPath, file);

//        // 2. 첨부파일을 꺼내서 파일 객체로 포장하기
//        File uploadFile = new File(rootPath, file.getOriginalFilename()); // 경로에, 오리지날이름명으로
//
//        // 3. MultipartFile 객체로 저장명령
//        try {
//            file.transferTo(uploadFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 단점. 저장소는 하나인데 유저가 똑같은 이름의 파일을 여러개 전송하면 맨 마지막 파일 딱 하나만 남아있을 것이다 (중복덮어쓰기)
        // 이름을 오리지날명으로 저장하면 안되고, 중복되지 않은 이름으로 저장되게 해야 한다.
        // 첨부파일은 저장기한(5년) 이 있어서 파일을 날짜별로, 종류별로 보관해야 한다.
        // 첨부파일의 메타데이터 (누가, 어디에업로드 등등)를 DB 에 기록해놔야한다.

        // util - FileUtil 새 클래스에 처리

        return "redirect:/upload/form";
    }

}
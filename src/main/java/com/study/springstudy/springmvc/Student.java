package com.study.springstudy.springmvc;

import lombok.*;

/**
 * lom
 */
// 플러그인에서 lombok을 설치하면 아래와 같이 사용가능
@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor // 기본생성자 (빈 생성자)
@AllArgsConstructor // 모든 필드 초기화 생성자

//@RequiredArgsConstructor // 필수 파라미터 (final 필드) 초기화 생성자
// final 이 붙은 필드는 초기화 생성자가 꼭 필요한데 생성자를 굳이 만들지 않고 이것만 붙여줘도 오류가 안남.
// @Autowired 생성자 대신 @RequiredArgsConstructor 하나만 붙여줘도 됨.

public class Student {
    private String name;
    private int age;
    private int grade;


}

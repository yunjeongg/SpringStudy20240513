package com.study.springstudy.springmvc.chap05.dto.response;


import com.study.springstudy.springmvc.chap05.entity.Member;
import lombok.*;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserInfoDto {

    // 클라이언트에 보낼 정보
    private String account;
    private String nickName;
    private String email;
    private String auth; // 권한

    public LoginUserInfoDto(Member member) {
        this.account = member.getAccount();
        this.nickName = member.getName();
        this.email = member.getEmail();
        this.auth = member.getAuth().name();
    }
}

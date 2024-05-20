package com.study.springstudy.springmvc.chap03.dto;

import com.study.springstudy.springmvc.chap03.entity.Score;
import lombok.Getter;

@Getter
public class ScoreListResponseDto {

    private long stuNum;
    private String maskingName; // 첫글자 빼고 모두 * 처리
    private double average;
    private String grade;

    public ScoreListResponseDto(Score s) {
        this.stuNum = s.getStuNum();
        this.maskingName = maskMaskingName(s.getStuName());
        this.average = s.getAverage();
        this.grade = s.getGrade().toString();
    }

    private String maskMaskingName(String stuName) {
        char firstLetter = stuName.charAt(0); // 첫번째 글자 가져오기
        String maskedName = "" + firstLetter;
        for (int i = 0; i < stuName.length() -1; i++) {
            maskedName += "*";
        }
        return maskedName;
    }
}

package com.study.springstudy.springmvc.chap03.mapper;

import com.study.springstudy.springmvc.chap03.dto.RankDto;
import com.study.springstudy.springmvc.chap03.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreMapperTest {

    @Autowired
    ScoreMapper mapper;

    @Test
    @DisplayName("전체조회")
    void findAllTest() {
        //given

        //when
        List<Score> scoreList = mapper.findAll(null);
        //then
        scoreList.forEach(System.out::println);
    }

    @Test
    @DisplayName("개별조회")
    void findOneTest() {
        //given
        long stuNum = 2;
        //when
        Score score = mapper.findOne(stuNum);

        //then
        System.out.println("score = " + score);
    }

    @Test
    @DisplayName("")
    void rankTest() {
        //given
        long stuNum = 3;
        //when
        RankDto rankByStuNum = mapper.findRankByStuNum(stuNum);
        //then
        System.out.println("rankByStuNum = " + rankByStuNum);
    }

}
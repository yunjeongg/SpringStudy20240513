package com.study.springstudy.springmvc.chap03.repository;

import com.study.springstudy.springmvc.chap03.entity.Score;

import java.util.List;

// 역할 : 적당한 저장소에 CRUD 하기
public interface ScoreRepository {

    // 1. 저장소에 데이터 추가하기
    boolean save (Score score);

    // 2. 저장소에서 데이터 전체조회하기
    List<Score> findAll();

    // 3. 저장소에서 데이터 개별조회하기
    Score fineOne(long stuNum);

    // 4. 저장소에서 등수, 전체인원 조회하기
    int[] findRankByStuNum(long stuNum);

    // 5. 저장소에서 데이터 삭제하기
}

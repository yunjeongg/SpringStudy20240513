package com.study.springstudy.springmvc.chap03.repository;

import com.study.springstudy.springmvc.chap03.entity.Score;

import java.util.List;

// 역할 : 적당한 저장소에 CRUD 하기
public interface ScoreRepository {

    // 1. 저장소에 데이터 추가하기
    boolean save (Score score);

    // 2. 저장소에서 데이터 전체조회하기
    List<Score> findAll(String sort);

    // 3. 저장소에서 데이터 개별조회하기
    Score findOne(long stuNum);

    // 4. 저장소에서 등수, 전체인원 조회하기
    int[] findRankByStuNum(long stuNum);

    // 5. 저장소에서 데이터 삭제하기
    // default 인터페이스에서 새 메소드를 만들 때 인터페이스를 가져가는 클래스들에서
    // 강제로 똑같은 메소드를 만들어야 하는데
    // default 는 강제성을 없애줘서 만들어도 되고, 안만들어도 됨.
    default boolean delete(long stuNum) {
        return false;
    };
}

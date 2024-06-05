package com.study.springstudy.springmvc.chap05.mapper;

import com.study.springstudy.springmvc.chap04.common.Page;
import com.study.springstudy.springmvc.chap05.dto.response.ReplyFindAllDto;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    // 댓글 등록
    boolean save (Reply reply);

    // 댓글 수정
    boolean modify (Reply reply);

    // 댓글 삭제
    boolean delete (long replyNo);

    // 특정 게시물에 달린 댓글 목록 조회 (pk를 파라먼트로 받기)
    // 마이바티스는 파라미터 두개 받는것 허용하지 않는다. 해결방법
    // 두개 이상으로 넣어줄 경우 별칭을 지어주고
    List<ReplyFindAllDto> findAll (@Param("bno")long boardNo, @Param("p") Page page);

    // 특정 게시물에 달린 총 댓글 수 조회 (pk를 파라먼트로 받기)
    int count (long boardNo);

    // 댓글번호로 원본글번호 찾기
    long findBno(long rno);
}

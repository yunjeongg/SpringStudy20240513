package com.study.springstudy.database.chap02;

import com.study.springstudy.database.chap01.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PersonMapperTest {

    @Autowired
    PersonMapper mapper;

    @Test
    @DisplayName("마이바티스 매퍼로 사람 정보를 등록한다.")
    void saveTest() {
        //given
        Person p = new Person(9999, "마바맨", 59);
        //when
        boolean flag =mapper.save(p);
        //then
    }

    @Test
    @DisplayName("아이디로 사람 정보 삭제한다.")
    void delTest() {
        //given
        long id = 9999;
        //when
        boolean flag = mapper.delete(id);
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("아이디가 200인 사람의 정보를 수정한다.")
    void updateTest() {
        //given
        Person p = new Person(200, "이백이", 20);
        //when
        boolean flag = mapper.update(p);
        //then
        assertTrue(flag);
    }
    
    @Test
    @DisplayName("전체조회하면 결과건수가 3건이다")
    void findAllTest () {
        //given
        
        //when
        List<Person> people = mapper.findAll();
        
        //then
        people.forEach(System.out::println);

        assertEquals(3, people.size());
    }

    @Test
    @DisplayName("id로 사람 정보를 개별 조회한다")
    void findOneTest() {
        //given
        long id = 100;
        //when
        Person person = mapper.findOne(id);
        //then
        assertEquals("백백이", person.getPersonName());
    }

    @Test
    @DisplayName("사람 수와 이름리스트를 조회한다.")
    void findNamesTest() {
        //given

        //when
        List<String> names = mapper.findNames();
        int count = mapper.count();
        //then
        names.forEach(System.out::println);
        System.out.println();
        System.out.println("count = " + count);
    }
}
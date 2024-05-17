package com.study.springstudy.database.chap01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class SpringJdbcTest {

    @Autowired
    SpringJdbc springJdbc;

    // 각 테스트 전에 공통으로 실행할 코드
    @BeforeEach // 각각 테스트하기 전
    void bulkInsert(){
        for (int i = 0; i < 100; i++) {
            Person p = new Person(i + 2000, "테스트맨" + 1, 10);
            springJdbc.save(p);
        }
    }

    // 단위 테스트 프레임워크 : JUnit5
    // 테스트 == 단언 (Assertion)
    @Test
    @DisplayName("사람의 정보를 입력하면 데이터베이스에 반드시 저장되어야 한다.")
    void saveTest() {
        // gwt 패턴
        // given : 테스트에 주어질 데이터
        Person p = new Person(77, "칠칠이", 7);
//        Person p = new Person(100, "백백이", 10);
//        Person p = new Person(200, "이백이", 20);

        // when : 테스트 상황
        int result = springJdbc.save(p);

        // then : 테스트 결과 단언
        assertEquals(1, result);
        //이 테스트를 돌리면 result 가 1이라고 단언한다.
    }

    @Test
    @DisplayName("아이디가 주어지면 해당 아이디의 사람정보가 데이터베이스로부터 삭제되어야 한다.")
    void deleteTest() {
        //given
        long id = 77;

        //when
        boolean flag = springJdbc.delete(id);

        //then
        assertTrue(flag); // 정상작동해도 내가원하는 결과가 나오지 않으면 실패
        // 내가 원하는 답 true
    }

    @Test
    @DisplayName("새로운 이름과 나이를 전달하면 사람의 정보가 데이터베이스에서 수정된다.")
    void modifyTest() {
        //given
        Person person = new Person(77, "팔팔이", 8);

        //when
        boolean flag = springJdbc.update(person);

        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("사람 정보를 전체조회하면 결과건수는 4건이며, 첫번째 사람의 이름은 \'백백이\' 이다.")
    void findAllTest() {
        //given

        //
        List<Person> people = springJdbc.findAll();

        //then
        people.forEach(System.out::println);

        assertEquals(2, people.size());
        assertEquals("백백이", people.get(0).getPersonName());

    }

    @Test
    @DisplayName("사람 정보를 아이디로 단일조회시 아이디가 200인 " +
            "사람의 이름은 이백이이고 나이는 20이다.")
    void findOneTest() {
        //given
        long id = 200;
        //when
        Person person = springJdbc.findOne(id);
        //then
        System.out.println("person = " + person);

        assertNotNull(person);
        assertEquals("이백이", person.getPersonName());
        assertEquals(20, person.getPersonAge());
    }
}
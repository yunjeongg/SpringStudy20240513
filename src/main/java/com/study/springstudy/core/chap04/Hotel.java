package com.study.springstudy.core.chap04;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/*
 * @solution
 * - 객체 생성의 제어권을 이 클래스에서
 *   다른 클래스로 이전한다.
 *   ex) new 생성자();  -> 이 문법을 담당클래스를 정해서 몰아서 수행시킴
 * - 호텔 객체 생성시 반드시 의존객체를 전달하도록 강요
 *
 *
 *  // 제어의 역전(IoC) : 객체 생성의 제어권을 외부로 넘긴다.
    // 의존성 주입(DI) : 외부에서 생성된 객체를 주입받는 개념
 */
// ctrl + shift + t, 새 테스트 생성
// @Component : 객체생성의 제어권을 스프링에게 넘길 때 사용,
@Component
public class Hotel {

    // 레스토랑
//    @Autowired // 생성자가 없어도 자동으로 객체를 주입해줘 (필드 또는 setter)
    private Restaurant restaurant;

    // 헤드쉐프
//    @Autowired // 생성자가 없어도 자동으로 객체를 주입해줘 (필드 또는 setter)
    private Chef headChef;

    // 만약 해당 클래스의 생성자가 단 한개 뿐이라면 스프링이 자동으로 @Autowired 붙여줌
    // 그러나 생성자가 없다면 필드 또는 setter에 셀프로 붙여야함
    @Autowired
    public Hotel(@Qualifier("western")Restaurant restaurant, Chef headChef) {
        this.restaurant = restaurant;
        this.headChef = headChef;
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Chef getHeadChef() {
        return headChef;
    }

    // 호텔을 소개하는 기능
    public void inform() {
        System.out.printf("우리 호텔의 레스토랑은 %s입니다. " +
                "그리고 헤드쉐프는 %s입니다.\n"
                , restaurant.getClass().getSimpleName()
                , headChef.getClass().getSimpleName());

        restaurant.order();
    }
}

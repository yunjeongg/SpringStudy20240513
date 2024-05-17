package com.study.springstudy.core.chap04;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class HotelSpringDiTest {

    @Test
    void diTest () {

        // @Component 한 객체 자료보관창고
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HotelConfig.class);

        // hotel 에 자료창고에 있는
        Hotel hotel = context.getBean(Hotel.class);

        hotel.inform();
        System.out.println("hotel = " + hotel);
    }

}
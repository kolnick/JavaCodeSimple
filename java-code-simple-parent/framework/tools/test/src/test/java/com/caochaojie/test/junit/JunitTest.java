package com.caochaojie.test.junit;


import com.caochaojie.test.enums.Direction;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;


/**
 * @Author: caochaojie
 * @Date: 2023-12-09 15:50
 */
public class JunitTest {


    @ParameterizedTest
    @EnumSource(value = Direction.class, names = {"UP", "DOWN"})
    public void testDirection(Direction direction) {
        switch (direction) {
            case UP:
                System.out.println("UP");
                break;
            case DOWN:
                System.out.println("DOWN");
                break;
            case LEFT:
                System.out.println("LEFT");
                break;
            case RIGHT:
                System.out.println("RIGHT");
                break;
        }
    }
}

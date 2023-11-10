package com.caochaojie.faker;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import java.util.Locale;

/**
 * @Author: caochaojie
 * @Date: 2023-11-11 0:18
 */
public class FakerTest {


    @Test
    public void name() {
        Faker faker = new Faker();
        System.out.println(faker.name().firstName());
        System.out.println(faker.name().lastName());
        System.out.println(faker.name().fullName());
        System.out.println(faker.name().username());
    }

    @Test
    public void city() {
        Faker faker = new Faker(Locale.CHINA);
        System.out.println(faker.address().city());
     /*   System.out.println(faker.address().citySuffix());
        System.out.println(faker.address().cityPrefix());*/
        System.out.println(faker.address().streetName());
    }

}

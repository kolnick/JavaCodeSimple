package com.caochaojie.serializable.bean;

import lombok.*;

/**
 * @author caochaojie
 * 2022/7/1
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Person {

    private int age;
    private String name;
}

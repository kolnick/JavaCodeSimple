package com.caochaojie.serializable.bean;

import lombok.*;

import java.io.Serializable;

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
public class Person implements Serializable {

    private int age;
    private String name;
}

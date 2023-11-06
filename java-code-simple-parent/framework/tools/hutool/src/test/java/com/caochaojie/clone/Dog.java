package com.caochaojie.clone;

import cn.hutool.core.clone.CloneSupport;
import lombok.Data;

@Data
public class Dog extends CloneSupport<Dog> {

    private int age;

}

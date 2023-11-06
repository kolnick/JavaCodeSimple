package com.caochaojie.mapper.result;

import com.caochaojie.po.Item;
import lombok.Data;

import java.util.List;

/**
 * @author caochaojie
 * 2022/8/12
 * @description
 */
@Data
public class UserItemResult {

    private Integer id;

    private String name;

    private String sex;

    private List<Item> items;
}

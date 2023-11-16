package bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: caochaojie
 * @Date: 2023-11-17 0:36
 */
@Data
public class BigObject {
    private String name;
    private int age;
    private List<Integer> list;
    private List<Map<Integer, Bean>> listMap;

    public BigObject(String name, int age, List<Integer> list, List<Map<Integer, Bean>> listMap) {
        this.name = name;
        this.age = age;
        this.list = list;
        this.listMap = listMap;
    }
}

package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Author: caochaojie
 * @Date: 2023-11-17 0:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bean {
    private String name;
    private int age;
    private List<Integer> list;
    private List<Map<Integer, Bean>> listMap;
}

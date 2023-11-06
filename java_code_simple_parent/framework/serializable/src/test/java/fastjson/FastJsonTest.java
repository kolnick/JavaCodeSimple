package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.caochaojie.serializable.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caochaojie
 * 2022/8/5
 * @description
 */
@Slf4j
public class FastJsonTest {

    @Test
    public void testMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        String s = JSON.toJSONString(map);

        Map<Integer, Integer> interMap = JSON.parseObject(s, new TypeReference<>() {
        });
        log.info("{}", interMap);
    }


    @Test
    public void object() {

        Person dada = Person.builder().age(120).name("DADA").build();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operator", "add");
        jsonObject.put("data", dada);
        String jsonStr = jsonObject.toJSONString();
        log.info("{}", jsonStr);

        JSONObject jsonObject1 = JSON.parseObject(jsonStr);

        Person data = jsonObject1.getObject("data", Person.class);
        log.info("{}", data);

    }

}

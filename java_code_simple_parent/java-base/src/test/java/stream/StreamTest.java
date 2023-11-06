package stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author caochaojie
 * @Date 2022/8/20
 */
@Slf4j
public class StreamTest {

    @Test
    public void testStreamConcat() {
        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> integers2 = new ArrayList<>(Arrays.asList(2, 3, 4, 5));
        Stream<Integer> concat = Stream.concat(integers.stream(), integers2.stream());
        System.out.println(integers);
        System.out.println(integers2);
        List<Integer> collect = concat.collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void concatKeyValueMap() {
        Map<String, String> map = new HashMap<>();

        map.put("first", "1");
        map.put("second", "2");
        map.put("third", "3");
        System.out.println(map);

        map.replaceAll(String::concat);

        System.out.println(map);
    }

    @Test
    public void testMergeCount() {
        Map<String, Integer> map = new HashMap<>();
        map.merge("1", 2, Integer::sum);
        int count = map.merge("1", 3, Integer::sum);
        log.info("{}", count);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class User {
        String name;
        int age;
    }

    @Test
    public void listToMap() {
        User user1 = new User("aa", 1);
        User user2 = new User("aa", 1);
        User user3 = new User("aa2", 3);
        List<User> list = Arrays.asList(user1, user2, user3);
        Map<String, User> collect = list.stream().collect(Collectors.toMap(User::getName, Function.identity(), (key1, key2) -> key1));
        System.out.println(collect.size());
    }
}

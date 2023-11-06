package com.kolnick.demo.util;

import java.util.*;

/**
 * 集合工具类
 *
 * @author caochaojie
 * @create 2018/5/18
 * @since 1.0.0
 */
public class CollectionUtil {

    /**
     * coll1 是否元素和coll2相同
     *
     * @param coll1
     * @param coll2
     * @return
     */
    public static boolean isEqualAll(final Collection<?> coll1, final Collection<?> coll2) {
        if (coll1 == null || coll2 == null || coll1.size() != coll2.size()) {
            return false;
        } else {
            Iterator<?> iterator = coll2.iterator();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                if (!coll1.contains(next)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * coll1 是否包含 coll2
     *
     * @param coll1
     * @param coll2
     * @return
     */
    public static boolean isContainsAll(final Collection<?> coll1, final Collection<?> coll2) {
        if (coll1 == null || coll2 == null) {
            return false;
        } else {
            Iterator<?> iterator = coll2.iterator();
            while (iterator.hasNext()) {
                Object next = iterator.next();
                if (!coll1.contains(next)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 从键值序列的参数列表生成映射表
     *
     * @param args
     * @return
     */
    public static Map<String, Object> createMap(Object... args) {
        if (args == null || args.length <= 0 || args.length % 2 > 0) {
            return null;
        }
        Map<String, Object> map = new HashMap();
        for (int i = 0; i < args.length; i += 2) {
            map.put((String) args[i], args[i + 1]);
        }
        return map;
    }

    public static <T> Map<T, Integer> createMapByIntegerValueList(
            List<T> keyList, List<Integer> valueList) {
        if (keyList == null || valueList == null || keyList.size() != valueList.size()) {
            return null;
        }
        int size = keyList.size();
        Map<T, Integer> map = new HashMap();
        for (int i = 0; i < size; i++) {
            T key = keyList.get(i);
            Integer val = valueList.get(i);
            Integer oldValue = map.getOrDefault(key, 0);
            map.put(key, oldValue + val);
        }
        return map;
    }

    public static <T> Map<T, Long> createMapByLongValueList(List<T> keyList,
                                                            List<Long> valueList) {
        if (keyList == null || valueList == null || keyList.size() != valueList.size()) {
            return null;
        }
        int size = keyList.size();
        Map<T, Long> map = new HashMap();
        for (int i = 0; i < size; i++) {
            T key = keyList.get(i);
            Long val = valueList.get(i);
            Long oldValue = map.getOrDefault(key, 0l);
            map.put(key, oldValue + val);
        }
        return map;
    }

    public static <T> List<T> createList(List<T> args) {
        List<T> list = new ArrayList<T>();
        for (T arg : args) {
            list.add(arg);
        }
        return list;
    }

    public static List<Integer> createList(List<Integer> args, Integer mul) {
        List<Integer> list = new ArrayList<Integer>();
        for (Integer arg : args) {
            list.add(MathUtil.adjustNumberInRange(arg * mul, 0, Integer.MAX_VALUE));
        }
        return list;
    }

    /**
     * 将map2 添加到map1
     *
     * @param map1
     * @param map2
     * @Author cao chaojie
     */
    public static <T> void addMap(Map<T, Integer> map1, Map<T, Integer> map2) {
        if (map1 == null || map2 == null || map2.isEmpty()) {
            return;
        }
        for (Map.Entry<T, Integer> map2Entry : map2.entrySet()) {
            Integer value = map2Entry.getValue();
            T key = map2Entry.getKey();
            Integer oldValue = map1.getOrDefault(key, 0);
            map1.put(key, MathUtil.adjustNumberInRange(oldValue + value, 0, Integer.MAX_VALUE));
        }
    }


    /**
     * 将map2 在map1 中存在的key 设置到map1中
     *
     * @param map1
     * @param map2
     * @param <T>
     */
    public static <T> void setMapExistsKeyValue(Map<T, T> map1, Map<T, T> map2) {
        if (map1 == null || map1.isEmpty() || map2 == null || map2.isEmpty()) {
            return;
        }

        for (Map.Entry<T, T> entry : map2.entrySet()) {
            T key = entry.getKey();
            T value = entry.getValue();

            if (map1.containsKey(key)) {
                map1.put(key, value);
            }
        }
    }

    public static List<Integer> toList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        return list;
    }

}

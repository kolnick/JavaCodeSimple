package com.kolnick.demo.util;


import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author wangguoyun
 * @description 随机工具类
 * @date 2017年11月25日 上午11:12:46
 */
public class RandomUtil {

    private RandomUtil() {

    }

    private final static ThreadLocal<Random> thead_local_random = new ThreadLocal<Random>() {
        @Override
        protected Random initialValue() {
            return new Random(Thread.currentThread().getId());
        }
    };

    private static char[] numbersAndLetters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final static String STRING_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


    /**
     * 获取随机数
     *
     * @param min
     * @param max
     * @return Integer, null: when max < min
     */
    public static final Long rand(long min, long max) {
        int tmp = Math.abs((int) (max - min));
        if (tmp < 0) {
            return null;
        } else if (tmp == 0) {
            return min;
        } else {
            return thead_local_random.get().nextInt(tmp + 1) + min;
        }
    }


    /**
     * 从[0-9] 和 [A-Z] 中随机定长的字符串
     *
     * @param length 生成字符串长度
     * @return
     */
    public static String randomString(int length) {
        if (length <= 0) {
            return null;
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[thead_local_random.get().nextInt(numbersAndLetters.length - 1)];
        }
        return new String(randBuffer);
    }

    public static <T> T randomList(List<T> srcList, List<Integer> probs) {
        int total = MathUtil.sum(probs);
        if (CollectionUtils.isEmpty(srcList) || total <= 0) {
            return null;
        }
        return srcList.get(randomRoundTable(probs, total));
    }

    /**
     * 圆桌概率(存在返回-1）
     *
     * @param probs
     * @param total
     * @return
     */
    public static int randomRoundTable(List<Integer> probs, int total) {
        int r = randomNumber(total);
        int p = 0;
        int idx = -1;
        for (int i = 0; i < probs.size(); ++i) {
            p += probs.get(i);
            if (p >= r) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    public final static int randomNumber(final int max) {
        if (max == 0) {
            return 0;
        }
        return thead_local_random.get().nextInt(max);
    }

    public static <T> List<T> randomList(List<T> srcList, List<Integer> probs,
                                         int count) {
        if (srcList == null || probs == null || srcList.size() != probs.size()) {
            return null;
        }
        List<T> retList = new ArrayList<T>();
        int total = MathUtil.sum(probs);
        if (CollectionUtils.isEmpty(srcList) || count < 1 || total <= 0) {
            return retList;
        }
        for (int i = 0; i < count; ++i) {
            retList.add(srcList.get(randomRoundTable(probs, total)));
        }
        return retList;
    }


    public static Short randomShortSet(Set<Short> set) {
        if (set != null && !set.isEmpty()) {
            Short[] array = set.toArray(new Short[set.size()]);
            return array[randomNumber(set.size())];
        }
        return null;
    }

    /**
     * 随机返回set中的一个
     *
     * @param set
     * @return
     */
    public static Integer randomIntegerSet(Set<Integer> set) {
        if (set != null && !set.isEmpty()) {
            Integer[] array = set.toArray(new Integer[set.size()]);
            return array[randomNumber(set.size())];
        }
        return null;
    }


    public static boolean randomBoolean() {
        return thead_local_random.get().nextBoolean();
    }

    /**
     * 根据给定的字符串 生成指定长度的字符串
     *
     * @param str
     * @param num
     * @return
     * @author kolnick
     */
    public static String randomString(String str, int num) {
        if (str == null || str.length() < num) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int rdNum = randomNumber(str.length());
            char ch = str.charAt(rdNum);
            sb.append(ch);
        }
        return sb.toString();
    }

}

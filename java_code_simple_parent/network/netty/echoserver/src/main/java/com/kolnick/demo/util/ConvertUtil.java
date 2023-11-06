package com.kolnick.demo.util;

import java.util.StringTokenizer;

public class ConvertUtil {

    /**
     * 获取布尔值当参数为Y|y时返回true,N|n时返回false.
     *
     * @param ch
     * @return
     */
    public final static Boolean getBoolean(final String ch) {
        if (ch.equalsIgnoreCase("Y") || ch.equals("1")) {
            return true;
        } else if (ch.equalsIgnoreCase("N") || ch.equals("0")) {
            return false;
        }
        return null;
    }

    /**
     * 获取正浮点型
     *
     * @param str
     * @return
     */
    public final static Float toPositiveFloat(final String str)
            throws NumberFormatException {
        Float i = Float.valueOf(str);
        if (i != null && i.floatValue() >= 0) {
            return i;
        }
        throw new NumberFormatException();
    }

    public final static Double toPositiveDouble(final String str)
            throws NumberFormatException {
        Double i = Double.valueOf(str);
        if (i != null && i.doubleValue() >= 0) {
            return i;
        }
        throw new NumberFormatException();
    }

    /**
     * 获取正整数
     *
     * @param str
     * @return
     * @throws NumberFormatException
     */
    public final static Integer toInteger(final String str)
            throws NumberFormatException {
        Integer i = Integer.valueOf(str);
        if (i != null) {
            return i;
        }
        throw new NumberFormatException();
    }

    /**
     * 获取正长整形
     *
     * @param str
     * @return
     */
    public final static Long toPositiveLong(final String str)
            throws NumberFormatException {
        Long i = Long.valueOf(str);
        if (i != null && i.longValue() >= 0) {
            return i;
        }
        throw new NumberFormatException();
    }

    /**
     * 解析参数
     *
     * @param param
     * @param sep
     * @return
     */
    public final static int[] toIntArray(final String param, String sep) {
        if (param == null || sep == null) {
            return null;
        }

        StringTokenizer st = new StringTokenizer(param, sep);
        int[] items = new int[st.countTokens()];
        int i = 0;
        try {
            while (st.hasMoreTokens()) {
                items[i] = Integer.parseInt(st.nextToken());
                ++i;
            }
        } catch (NumberFormatException e) {
            items = null;
        }
        return items;
    }

    /**
     * 解析参数
     *
     * @param param
     * @param sep
     * @return
     */
    public final static String[] toStringArray(final String param, String sep) {
        if (param == null || sep == null) {
            return null;
        }
        return param.split(sep);
    }

    public final static String toString(String sep, final String... param) {
        if (param == null || sep == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (String str : param) {
            sb.append(str);
            sb.append(sep);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * String数组转成int数组
     *
     * @param numbers
     * @return
     */
    public static int[] toIntArray(String[] numbers) {
        int[] intArr = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            intArr[i] = Integer.parseInt(numbers[i]);
        }
        return intArr;
    }

    /**
     * String数组转成int数组
     *
     * @param numbers
     * @return
     */
    public static Double[] toDoubleArray(String[] numbers) {
        Double[] intArr = new Double[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            intArr[i] = Double.parseDouble(numbers[i]);
        }
        return intArr;
    }


}

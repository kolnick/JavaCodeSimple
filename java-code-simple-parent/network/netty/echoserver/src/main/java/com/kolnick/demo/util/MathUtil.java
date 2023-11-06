package com.kolnick.demo.util;

import java.util.List;
import java.util.Map;

public class MathUtil {

    public static int sum(Integer... args) {

        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }

    public static long sum(Long... args) {
        if (args == null || args.length == 0) {
            return 0L;
        }
        int sum = 0;
        for (long arg : args) {
            sum += arg;
        }
        return sum;
    }

    public static int sum(int... args) {

        int sum = 0;
        for (int arg : args) {
            sum += arg;
        }
        return sum;
    }

    public static long sum(long... args) {
        long sum = 0;
        for (long arg : args) {
            sum += arg;
        }
        return sum;
    }

    public static Integer sum(List<Integer> args) {
        if (args == null || args.isEmpty()) {
            return 0;
        }
        return args.stream().reduce(Integer::sum).get();
    }

    public static Integer sumMapValue(Map<Integer, Integer> map) {
        if (map == null || map.isEmpty()) {
            return 0;
        }
        int total = 0;
        for (Integer val : map.values()) {
            total += val;
        }
        return total;
    }

    public static int reAdjustNumberInRange(int v, int min, int max) {
        int temp = v;
        if (temp > max) {
            temp = temp % max;
        }
        return Math.min(Math.max(temp, min), max);
    }

    public static int adjustNumberInRange(int v, int min, int max) {
        return Math.min(Math.max(v, min), max);
    }

    public static long adjustNumberInRange(long v, long min, long max) {
        return Math.min(Math.max(v, min), max);
    }

    public static float adjustNumberInRange(float v, float min, float max) {
        return Math.min(Math.max(v, min), max);
    }

    public static double adjustNumberInRange(double v, double min, double max) {
        return Math.min(Math.max(v, min), max);
    }

    public static double halfUpScaleToDouble(double val, int scale) {
        if (scale < 0) {
            return val;
        }
        double number = Math.pow(10, scale);
        return (double) (Math.floor(val * number) / (number * 1));
    }

    public static long halfUpScaleToLong(double val) {
        return new Double(halfUpScaleToDouble(val, 0)).longValue();
    }

    public static int halfUpScaleToInt(double val) {
        return new Double(halfUpScaleToDouble(val, 0)).intValue();
    }

    public static float halfUpScaleToFloat(double val, int scale) {
        return new Double(halfUpScaleToDouble(val, scale)).floatValue();
    }

    public static double halfDownScaleToDouble(double val, int scale) {
        if (scale < 0) {
            return val;
        }
        double number = Math.pow(10, scale);
        return (double) (Math.ceil(val * number) / (number * 1));
    }

    public static long halfDownScaleToLong(double val) {
        return new Double(halfDownScaleToDouble(val, 0)).longValue();
    }

    public static int halfDownScaleToInt(double val) {
        return new Double(halfDownScaleToDouble(val, 0)).intValue();
    }

    public static float halfDownScaleToFloat(double val, int scale) {
        return new Double(halfDownScaleToDouble(val, scale)).floatValue();
    }

}

package com.mwy.demo.util;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

/**
 * 时间单位工具类
 */
public class TimeUnitUtils {
    /**
     * 将秒钟时间解析为合适的中文单位
     *
     * @param sourceDuration
     * @return
     * @see TimeUnitUtils#parseTime(long, TimeUnit)
     */
    public static String parseSecond(long sourceDuration) {
        return parseTime(sourceDuration, SECONDS);
    }

    /**
     * 将时间解析为合适的中文单位
     * 比如:130秒将输出2分10秒,120分将输出2时
     *
     * @param sourceDuration 原始时间
     * @param sourceUnit     原始单位
     * @return
     */
    public static String parseTime(long sourceDuration, TimeUnit sourceUnit) {
        String result = null;
        //选择可选的最大单位
        TimeUnit unit = chooseUnit(sourceDuration, sourceUnit);
        //转换成最大时间
        long bigTime = unit.convert(sourceDuration, sourceUnit);
        //计算差值
        long difference = sourceDuration - sourceUnit.convert(bigTime, unit);
        //若有差值则递归
        if (difference == 0) {
            return bigTime + abbreviateCN(unit);
        } else {
            return bigTime + abbreviateCN(unit) + parseTime(difference, sourceUnit);
        }
    }

    /**
     * 选择一个合适的时间单位
     *
     * @param sourceDuration 原始时间
     * @param sourceUnit     原始单位
     * @return
     */
    public static TimeUnit chooseUnit(long sourceDuration, TimeUnit sourceUnit) {
        if (DAYS.convert(sourceDuration, sourceUnit) > 0) {
            return DAYS;
        }
        if (HOURS.convert(sourceDuration, sourceUnit) > 0) {
            return HOURS;
        }
        if (MINUTES.convert(sourceDuration, sourceUnit) > 0) {
            return MINUTES;
        }
        if (SECONDS.convert(sourceDuration, sourceUnit) > 0) {
            return SECONDS;
        }
        if (MILLISECONDS.convert(sourceDuration, sourceUnit) > 0) {
            return MILLISECONDS;
        }
        if (MICROSECONDS.convert(sourceDuration, sourceUnit) > 0) {
            return MICROSECONDS;
        }
        return NANOSECONDS;
    }

    /**
     * 时间单位转单位字符 英文
     *
     * @param unit
     * @return
     */
    public static String abbreviate(TimeUnit unit) {
        switch (unit) {
            case NANOSECONDS:
                return "ns";
            case MICROSECONDS:
                return "\u03bcs"; // μs
            case MILLISECONDS:
                return "ms";
            case SECONDS:
                return "s";
            case MINUTES:
                return "min";
            case HOURS:
                return "h";
            case DAYS:
                return "d";
            default:
                throw new AssertionError();
        }
    }

    /**
     * 时间单位转单位字符 中文
     *
     * @param unit
     * @return
     */
    public static String abbreviateCN(TimeUnit unit) {
        switch (unit) {
            case NANOSECONDS:
                return "纳秒";
            case MICROSECONDS:
                return "微秒";
            case MILLISECONDS:
                return "毫秒";
            case SECONDS:
                return "秒";
            case MINUTES:
                return "分";
            case HOURS:
                return "时";
            case DAYS:
                return "天";
            default:
                throw new AssertionError();
        }
    }
}

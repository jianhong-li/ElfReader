package com.lijianhong.train.util;

/**
 * @author lijianhong Date: 2022/7/17 Time: 9:43 PM
 * @version $Id$
 */
public class Hex {

    public static final char[] hexDic = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    public static String toHex(byte i) {
        return "0x" + toHexInternal(i);
    }


    public static String toHex(short i) {

        return "0x" + toHexInternal((byte) ((i >> 8) & 0x00ff))+ toHexInternal((byte) ((i) & 0x00ff));
    }

    public static String toHex(int item) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        for (int i = 0; i < 4; i++) {
            int tmp = item << (i * 8);
            int t = (0xff000000) & tmp;
            t = (t >> 24) & 0xff;
            sb.append(toHexInternal((byte) t));
        }
        return sb.toString();
    }


    public static String toHex(long item) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        for (int i = 0; i < 8; i++) {

            long temp = item << (i * 8);
            byte t = (byte)((temp >> 56) & 0xffL);
            sb.append(toHexInternal(t));
        }

        return sb.toString();
    }

    private static String toHexInternal(byte i) {
        return String.valueOf(hexDic[(i & 0x00f0)>>4]) + hexDic[i & 0x0f];
    }

    public static void main(String[] args) {
        int in = 0x02345678;
        System.out.println(String.format("0x%x", in));
        System.out.println(toHex(in));
    }
}

package com.lijianhong.train.def;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijianhong Date: 2022/7/17 Time: 4:20 PM
 * @version $Id$
 */
public class Const {

    /**  */
    public static final int EI_NIDENT = 16;

    public static final int ETYPE_REL = 1;      // 可重定位文件,一般为 .o  文件
    public static final int ETYPE_EXEC = 2;     // 可执行文件
    public static final int ET_DYN = 3;         // 共享目标文件,一般为 .so 文件

    public static final int EM_M32 = 1;
    public static final int EM_SPARC = 2;
    public static final int EM_386 = 3;
    public static final int EM_68K = 4;
    public static final int EM_88K = 5;
    public static final int EM_860 = 6;

    public static Map<Integer, String> ETYPE_MAP = new HashMap<>();
    public static Map<Integer, String> EM_MAP = new HashMap<>();

    static {
        ETYPE_MAP.put(1, "ETYPE_REL");
        ETYPE_MAP.put(2, "ETYPE_EXEC");
        ETYPE_MAP.put(3, "ET_DYN");

        EM_MAP.put(1, "AT&T WE 32100");
        EM_MAP.put(2, "SPARC");
        EM_MAP.put(3, "Intel X86");
        EM_MAP.put(4, "Motorola 68000");
        EM_MAP.put(5, "Motorola 88000");
        EM_MAP.put(6, "Motorola 80860");
    }

}

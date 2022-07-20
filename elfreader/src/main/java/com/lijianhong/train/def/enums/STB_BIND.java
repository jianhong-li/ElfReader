package com.lijianhong.train.def.enums;

/**
 * @author lijianhong Date: 2022/7/20 Time: 7:28 PM
 * @version $Id$
 */
public enum STB_BIND {

    // @formatter:off
    STB_LOCAL         (0x00,  "LOCAL",  "局部符号"),
    STB_GLOBAL        (0x01,  "GLOBAL", "全局符号"),
    STB_WEAK          (0x02,  "WEAK"  , "弱引用"),
    STB_LOOS          (0x0A,  "LOOS"  , "Values in this inclusive range are reserved for operating system-specific semantics"),
    ;



    private int code;
    private String name;
    private String desc;

    STB_BIND(int code, String name, String desc) {

        this.code = code;
        this.name = name;
        this.desc = desc;
    }


    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


    public static STB_BIND codeOf(int code) {
        // System.out.println(code);
        for (STB_BIND o : STB_BIND.values()) {
            if (((code >> 4) & 0xf) == o.getCode()) {
                return o;
            }
        }
        return STB_LOCAL;
    }

}

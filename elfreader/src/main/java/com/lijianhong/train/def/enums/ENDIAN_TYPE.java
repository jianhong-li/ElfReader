package com.lijianhong.train.def.enums;

/**
 * @author lijianhong Date: 2022/7/18 Time: 10:49 PM
 * @version $Id$
 */
public enum ENDIAN_TYPE {
    UNKNOWN(0, "未知"),
    LITTLE_ENDIAN(1, "小端"),
    BIG_ENDIAN(2, "大端"),
    ;

    private int code;
    private String desc;

    ENDIAN_TYPE(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ENDIAN_TYPE codeOf(int code) {
        for (ENDIAN_TYPE value : ENDIAN_TYPE.values()) {
            if (value.code == code) {
                return value;
            }
        }

        return UNKNOWN;
    }
}

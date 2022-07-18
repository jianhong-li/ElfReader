package com.lijianhong.train.def.enums;

/**
 * @author lijianhong Date: 2022/7/18 Time: 11:07 AM
 * @version $Id$
 */
public enum SH_TYPE {
    SHT_NULL        (0x00,  "无效段"),
    SHT_PROGBITS    (0x01,  "程序段,代码段,数据段都是这种类型的"),
    SHT_SYMTAB      (0x02,  "表示该段的内容为符号表"),
    SHT_STRTAB      (0x03,  "表示该段的内容为字符串表"),
    SHT_RELA        (0x04,  "重定位表,该段包含了重定位信息"),
    SHT_HASH        (0x05,  "符号表的哈希表"),
    SHT_DYNAMIC     (0x06,  "动态链接信息"),
    SHT_NOTE        (0x07,  "提示信息"),
    SHT_NOBITS      (0x08,  "表示该段在文件中没有内容,比如 .bss 段"),
    SHT_REL         (0x09,  "该段包含了重定位信息,可参考: 静态地址决议和重定位"),
    SHT_SHLIB       (0x0A,  "保留"),
    SHT_DNYSYM      (0x0B,  "动态链接的符号表"),
    ;

    private int code;
    private String desc;

    SH_TYPE(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static SH_TYPE codeOf(int code) {
        for (SH_TYPE value : SH_TYPE.values()) {
            if (value.code == code) {
                return value;
            }
        }

        return null;
    }
}

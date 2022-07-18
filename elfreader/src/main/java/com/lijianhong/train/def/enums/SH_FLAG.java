package com.lijianhong.train.def.enums;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author lijianhong Date: 2022/7/18 Time: 12:01 PM
 * @version $Id$
 */
public enum SH_FLAG {

    SHT_WRITE        (0x01,  "表示该段在进程空间中可写"),
    SHT_ALLOC        (0x02,  "表示该段在进程空间中需要分配空间. 有些包含指示或控制信息的段不需要在进程空间中被分配空间,它们一般不会有这个标志.像代码段和.bss段都会有这个标志位"),
    SHT_EXECINSTR    (0x04,  "表示该段在进程空间中可被执行,一般指代码段"),
    ;


    private int code;
    private String desc;

    SH_FLAG(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static SH_FLAG codeOf(int code) {
        for (SH_FLAG value : SH_FLAG.values()) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }


    public static List<SH_FLAG> codesOf(long codes) {
        List<SH_FLAG> resList = Lists.newArrayList();
        for (SH_FLAG value : SH_FLAG.values()) {
            if ((value.getCode() & codes) > 0) {
                resList.add(value);
            }
        }

        return resList;
    }

}

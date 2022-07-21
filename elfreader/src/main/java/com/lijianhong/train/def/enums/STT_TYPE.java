package com.lijianhong.train.def.enums;

/**
 * <pre>
 *     低四位代表符号类型
 * </pre>
 * @author lijianhong Date: 2022/7/20 Time: 7:28 PM
 * @version $Id$
 */
public enum STT_TYPE {

    // @formatter:off
    STT_NOTYPE        (0x00,  "NOTYPE", "未知符号类型"),
    STT_OBJECT        (0x01,  "OBJECT", "数据对象,如变量,数组等"),
    STT_FUNC          (0x02,  "FUNC"  , "函数或其它可执行代码"),
    STT_SECTION       (0x03,  "SECTION","该符号表示一个段,这种符号必须是STB_LOCAL的"),
    STT_FILE          (0x04,  "FILE"  , "文件名,一般为该目标文件的源文件名,它一定是STB_LOCAL的.并且它的st_shndx一定是SHN_ABS"),
    ;



    private int code;
    private String name;
    private String desc;

    STT_TYPE(int code, String name, String desc) {

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


    public static STT_TYPE codeOf(int code) {
        // 低四位为 符号类型. 因此mask为: 0x0f
        code = code & 0x0000000f;

        for (STT_TYPE o : STT_TYPE.values()) {
            if (o.getCode() == code) {

                return o;
            }
        }
        return STT_NOTYPE;
    }

}

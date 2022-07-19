package com.lijianhong.train.def;

import com.lijianhong.train.reader.ReadUtils;
import com.lijianhong.train.util.Hex;
import com.lijianhong.train.util.Tab;

/**
 * <pre>
 *     符号表段的定义结构 .symtab
 *     文档地址: https://refspecs.linuxfoundation.org/elf/gabi4+/ch4.symtab.html
 *     Linux源码地址: https://github.com/torvalds/linux/blob/master/include/uapi/linux/elf.h
 * </pre>
 * @author lijianhong Date: 2022/7/19 Time: 11:13 AM
 * @version $Id$
 */
public class Elf64_Sym {

    /*

    typedef struct {
        Elf32_Word	st_name;
        Elf32_Addr	st_value;
        Elf32_Word	st_size;
        unsigned char	st_info;
        unsigned char	st_other;
        Elf32_Half	st_shndx;
    } Elf32_Sym;

    typedef struct {
        Elf64_Word	st_name;
        unsigned char	st_info;
        unsigned char	st_other;
        Elf64_Half	st_shndx;
        Elf64_Addr	st_value;
        Elf64_Xword	st_size;
    } Elf64_Sym;

    */

    public static final int _size = 4 + 1 + 1 + 2 + 8 + 8;

    // Note: 由于Java是没有无符号的数据类型. 因此在可能的情况下,用更长一倍的数据类型来存储原来的数据类型.
    public long	    st_name;    // Elf64_Word
    public short	st_info;    // unsigned char
    public short	st_other;   // unsigned char
    public int   	st_shndx;   // Elf64_Half
    public long	    st_value;   // Elf64_Addr
    public long	    st_size;    // Elf64_Xword

    public String   _st_name;
    public int      _index;


    public void init(byte[] fileBytes, int baseOffset) {
        st_name =  ReadUtils.readWord(fileBytes, baseOffset);
        st_info =  ReadUtils.readUByte(fileBytes, baseOffset + 4);
        st_other = ReadUtils.readUByte(fileBytes, baseOffset + 4 + 1);
        st_shndx = ReadUtils.readShort(fileBytes, baseOffset + 4 + 1 + 1);
        st_value = ReadUtils.readLong (fileBytes, baseOffset + 4 + 1 + 1 + 2);
        st_size  = ReadUtils.readLong (fileBytes, baseOffset + 4 + 1 + 1 + 2 + 8);
    }

    /**
     * 获取符号表格式化后的展示信息,如果是编号为0的元素.会添加一个表头
     * @return 格式化好的待展示元素
     */
    public String formatInfo() {
        String[] tabHeader = {"st_name","st_value"};
        StringBuilder sb = new StringBuilder();
        if (_index == 0) {
            sb.append(tabHeader[0]).append(Tab.makeTab(tabHeader[0], 10));
            sb.append(tabHeader[1]).append(Tab.makeTab(tabHeader[1], 12));
            sb.append("\n");
        }

        sb.append(_st_name).append(Tab.makeTab(_st_name, 10));
        sb.append(Hex.toHex(st_value)).append(Tab.makeTab(Hex.toHex(st_value), 12));
        sb.append("\n");
        return sb.toString();
    }


}

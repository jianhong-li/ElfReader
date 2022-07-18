package com.lijianhong.train.def;

import com.lijianhong.train.def.enums.SH_FLAG;
import com.lijianhong.train.def.enums.SH_TYPE;
import com.lijianhong.train.reader.ReadUtils;
import com.lijianhong.train.util.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lijianhong Date: 2022/7/17 Time: 10:32 PM
 * @version $Id$
 */
public class Shdr64 {

    private static Logger logger = LoggerFactory.getLogger(Shdr64.class);

    // typedef struct elf64_shdr {
    //  Elf64_Word sh_name;		/* Section name, index in string tbl */
    //  Elf64_Word sh_type;		/* Type of section */
    //  Elf64_Xword sh_flags;		/* Miscellaneous section attributes */
    //  Elf64_Addr sh_addr;		/* Section virtual addr at execution */
    //  Elf64_Off sh_offset;		/* Section file offset */
    //  Elf64_Xword sh_size;		/* Size of section in bytes */
    //  Elf64_Word sh_link;		/* Index of another section */
    //  Elf64_Word sh_info;		/* Additional section information */
    //  Elf64_Xword sh_addralign;	/* Section alignment */
    //  Elf64_Xword sh_entsize;	/* Entry size if section holds table */
    //} Elf64_Shdr;

    public static final int size = 4 + 4 + 8 + 8 + 8 + 8 + 4 + 4 + 8 + 8;

    public int _index;

    public int sh_name;        /* Section name, index in string tbl */
    public int sh_type;        /* Type of section */
    public long sh_flags;      /* Miscellaneous section attributes */
    public long sh_addr;       /* Section virtual addr at execution */
    public long sh_offset;     /* Section file offset */
    public long sh_size;       /* Size of section in bytes */
    public int sh_link;        /* Index of another section */
    public int sh_info;        /* Additional section information */
    public long sh_addralign;  /* Section alignment */
    public long sh_entsize;    /* Entry size if section holds table */

    public String _name;

    public void init(byte[] fileBytes, int baseOffset) {
            this.sh_name    = ReadUtils.readWord(fileBytes,        baseOffset);
            this.sh_type    = ReadUtils.readWord(fileBytes, baseOffset + 4);
            this.sh_flags   = ReadUtils.readLong(fileBytes, baseOffset + 4 + 4);
            this.sh_addr    = ReadUtils.readLong(fileBytes, baseOffset + 4 + 4 + 8);
            this.sh_offset  = ReadUtils.readLong(fileBytes, baseOffset + 4 + 4 + 8 + 8);
            this.sh_size    = ReadUtils.readLong(fileBytes, baseOffset + 4 + 4 + 8 + 8 + 8);
            this.sh_link    = ReadUtils.readWord(fileBytes, baseOffset + 4 + 4 + 8 + 8 + 8 + 8);
            this.sh_info    = ReadUtils.readWord(fileBytes, baseOffset + 4 + 4 + 8 + 8 + 8 + 8 + 4);
        this.sh_addralign   = ReadUtils.readLong(fileBytes, baseOffset + 4 + 4 + 8 + 8 + 8 + 8 + 4 + 4);
        this.sh_entsize     = ReadUtils.readLong(fileBytes, baseOffset + 4 + 4 + 8 + 8 + 8 + 8 + 4 + 4 + 8);
    }

    public String formatInfo() {
        StringBuilder sb = new StringBuilder();
        if (_index == 0) {
            sb.append("\n");
            sb.append("[Nr]").append("\t\t");
            sb.append("Name\t\t\t\t\t\t");
            sb.append("TYPE\t\t\t\t\t\t\t\t");
            sb.append("Flag\t\t\t\t\t\t\t\t\t\t\t\t\t");
            sb.append("Addr").append(makeTab("Addr",12));
            sb.append("Offset").append(makeTab("Offset",12));
            sb.append("size").append(makeTab("size",4));
            sb.append("Lk").append(makeTab("lk",8));
            sb.append("Inf").append(makeTab("Inf",8));
            sb.append("Al").append(makeTab("Al",4));;
            sb.append("ES\t\t\t");
            sb.append("\n");
        }
        // 编号
        sb.append(String.format("%2d", _index)).append("\t\t\t");

        // 名称
        String __name = _name.isEmpty() ? Hex.toHex(sh_name) : _name;
        sb.append(__name).append(makeTab(String.valueOf(__name), 8));

        // 类型
        sb.append(SH_TYPE.codeOf(sh_type)).append(makeTab(String.valueOf(SH_TYPE.codeOf(sh_type)), 10));

        // 标志:
        sb.append(SH_FLAG.codesOf(sh_flags)).append(makeTab(String.valueOf(SH_FLAG.codesOf(sh_flags)), 15));

        // 段虚拟地址
        sb.append(Hex.toHex(sh_addr)).append("\t\t\t");

        // 段偏移: section offset
        // 如果该段存在于文件中,则表示该段在文件中的偏移;否则无意义,比如 sh_offset 对于 .bss 段就没有意义
        sb.append(Hex.toHex(sh_offset)).append("\t\t\t");

        // 段大小
        sb.append(sh_size).append(makeTab(String.valueOf(sh_size),4));

        // sh_link
        sb.append(Hex.toHex(sh_link)).append("\t\t\t");

        // sh_info
        sb.append(Hex.toHex(sh_info)).append("\t\t\t");

        // 地址对齐信息
        sb.append(sh_addralign).append(makeTab(String.valueOf(sh_addralign),4));

        // section entry size 项的长度
        sb.append(sh_entsize).append(makeTab(String.valueOf(sh_entsize),4));

        //logger.info("{}",sb);
        sb.append("\n");
        return sb.toString();
    }

    private String makeTab(String content,int contentTabs ) {

        int tabSize = 2;
        int length = content.length();
        int tabCnt = 0;
        if (length % tabSize != 0) {
            tabCnt++;
            length = (length + tabSize - 1) / tabSize;
        }else {
            length = length / tabSize;
        }

        if (length < contentTabs) {
            tabCnt = tabCnt + (contentTabs - length);
        }

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tabCnt; i++) {

            s.append("\t");
        }
        return s.toString();

    }

    public void printInfo() {

        logger.info("{}", _index);
        logger.info("sh[{}]::sh_name:{}", _index,Hex.toHex(sh_name));
        logger.info("sh[{}]::sh_type:{} , {}", _index, Hex.toHex(sh_type), SH_TYPE.codeOf(sh_type));
        logger.info("sh[{}]::sh_flags:{}", _index,Hex.toHex(sh_flags));
        logger.info("sh[{}]::sh_addr:{}", _index,Hex.toHex(sh_addr));
        logger.info("sh[{}]::sh_offset:{}", _index,Hex.toHex(sh_offset));
        logger.info("sh[{}]::sh_size:{}", _index,Hex.toHex(sh_size));
        logger.info("sh[{}]::sh_link:\t\t{}", _index,Hex.toHex(sh_link));
        logger.info("sh[{}]::sh_info:\t\t{}", _index,Hex.toHex(sh_info));
        logger.info("sh[{}]::sh_addralign:{}", _index,Hex.toHex(sh_addralign));
        logger.info("sh[{}]::sh_entsize:\t{}", _index,Hex.toHex(sh_entsize));
    }

}

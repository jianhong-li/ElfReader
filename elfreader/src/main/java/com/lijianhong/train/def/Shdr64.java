package com.lijianhong.train.def;

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
    public long sh_flags;        /* Miscellaneous section attributes */
    public long sh_addr;        /* Section virtual addr at execution */
    public long sh_offset;        /* Section file offset */
    public long sh_size;        /* Size of section in bytes */
    public int sh_link;        /* Index of another section */
    public int sh_info;        /* Additional section information */
    public long sh_addralign;    /* Section alignment */
    public long sh_entsize;    /* Entry size if section holds table */

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

    public void printInfo() {

        logger.info("sh[{}]::sh_name:{}", _index,Hex.toHex(sh_name));
        logger.info("sh[{}]::sh_type:{}", _index,Hex.toHex(sh_type));
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

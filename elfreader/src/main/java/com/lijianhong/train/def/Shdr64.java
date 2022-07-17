package com.lijianhong.train.def;

/**
 * @author lijianhong Date: 2022/7/17 Time: 10:32 PM
 * @version $Id$
 */
public class Shdr64 {
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
}

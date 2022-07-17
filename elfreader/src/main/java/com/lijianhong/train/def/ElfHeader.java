package com.lijianhong.train.def;

/**
 * @author lijianhong Date: 2022/7/17 Time: 4:13 PM
 * @version $Id$
 */
public class ElfHeader {

    // typedef struct elf64_hdr {
    //     unsigned char	e_ident[EI_NIDENT];	/* ELF "magic number" */
    //     Elf64_Half e_type;
    //     Elf64_Half e_machine;
    //     Elf64_Word e_version;
    //     Elf64_Addr e_entry;		/* Entry point virtual address */
    //     Elf64_Off e_phoff;		/* Program header table file offset */
    //     Elf64_Off e_shoff;		/* Section header table file offset */
    //     Elf64_Word e_flags;
    //     Elf64_Half e_ehsize;
    //     Elf64_Half e_phentsize;
    //     Elf64_Half e_phnum;
    //     Elf64_Half e_shentsize;
    //     Elf64_Half e_shnum;
    //     Elf64_Half e_shstrndx;
    // } Elf64_Ehdr;


    /** magic number */
    public final byte[] e_ident = new byte[Const.EI_NIDENT];



}

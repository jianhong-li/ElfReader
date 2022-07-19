package com.lijianhong.train.def;

import com.lijianhong.train.def.enums.ENDIAN_TYPE;
import com.lijianhong.train.reader.ReadUtils;
import com.lijianhong.train.util.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lijianhong Date: 2022/7/17 Time: 4:13 PM
 * @version $Id$
 */
public class Elf64_Ehdr {

    private static Logger logger = LoggerFactory.getLogger(Elf64_Ehdr.class);

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

    private static final int Elf64_Half = 2;
    private static final int Elf64_Word = 4;
    private static final int Elf64_Addr = 8;
    private static final int Elf64_Off = 8;

    public static final int e_ident_offset = 0;
    public static final int e_type_offset = 16;
    public static final int e_machine_offset = e_type_offset + Elf64_Half;
    public static final int e_version_offset = e_machine_offset + Elf64_Half;
    public static final int e_entry_offset = e_version_offset + Elf64_Word;
    public static final int e_phoff_offset = e_entry_offset + Elf64_Addr;
    public static final int e_shoff_offset = e_phoff_offset + Elf64_Off;
    public static final int e_flags_offset = e_shoff_offset + Elf64_Off;
    public static final int e_ehsize_offset = e_flags_offset + Elf64_Word;
    public static final int e_phentsize_offset = e_ehsize_offset + Elf64_Half;
    public static final int e_phnum_offset = e_phentsize_offset + Elf64_Half;
    public static final int e_shentsize_offset = e_phnum_offset + Elf64_Half;
    public static final int e_shnum_offset = e_shentsize_offset + Elf64_Half;
    public static final int e_shstrndx_offset = e_shnum_offset + Elf64_Half;


    /** magic number */
    public byte[] e_ident = new byte[Const.EI_NIDENT];

    public short e_type;
    public short e_machine;
    public int e_version;
    public long e_entry;
    public long e_phoff;
    public long e_shoff;
    public int e_flags;
    public short e_ehsize;
    public short e_phentsize;
    public short e_phnum;
    public short e_shentsize;
    public short e_shnum;
    public short e_shstrndx;

    public void init(byte[] fileBytes) {

        ReadUtils.readBytes(fileBytes, 0, 16, this.e_ident);

        logger.info(
            "magicNumber:{}",
            String.format(
                "0x%x,0x%x,0x%x,0x%x",
                this.e_ident[0],
                this.e_ident[1],
                this.e_ident[2],
                this.e_ident[3]
            )
        );

        // 2 字节
        this.e_type = ReadUtils.readShort(fileBytes, e_type_offset);
        this.printEtype();

        // 2 字节
        this.e_machine = ReadUtils.readShort(fileBytes, e_machine_offset);
        this.printMachine();

        // version: 4 字节
        this.e_version = ReadUtils.readWord(fileBytes, e_version_offset);
        this.printVersion();

        // e_entry: 8字节 (64位)
        this.e_entry = ReadUtils.readLong(fileBytes, e_entry_offset);
        this.printEntry();

        this.e_phoff = ReadUtils.readLong(fileBytes, e_phoff_offset);
        this.printPhoff();

        this.e_shoff = ReadUtils.readLong(fileBytes, e_shoff_offset);
        this.printShoff();

        this.e_flags = ReadUtils.readWord(fileBytes, e_flags_offset);

        this.e_ehsize = ReadUtils.readShort(fileBytes, e_ehsize_offset);

        this.e_phentsize = ReadUtils.readShort(fileBytes, e_phentsize_offset);
        this.e_phnum = ReadUtils.readShort(fileBytes, e_phnum_offset);
        this.e_shentsize = ReadUtils.readShort(fileBytes, e_shentsize_offset);
        this.e_shnum = ReadUtils.readShort(fileBytes, e_shnum_offset);
        this.e_shstrndx = ReadUtils.readShort(fileBytes, e_shstrndx_offset);

    }

    private void printShoff() {
        logger.info("e_shoff:\t{}", String.format("0x%x", e_shoff));
    }

    private void printPhoff() {
        logger.info("e_phoff:\t{}", String.format("0x%x", e_phoff));
    }

    public void printEtype() {

        logger.info("e_type:\t\t{}  - {}", e_type, Const.ETYPE_MAP.get((int) e_type));
    }

    public void printMachine() {
        logger.info("e_machine:{} - {}", e_machine, Const.EM_MAP.get((int) e_machine));
    }

    public void printVersion() {
        logger.info("e_version:{} ", e_version);
    }

    public void printEntry() {
        logger.info("e_entry:\t{} , \t 备注: 当没有入口,或者是目标文件时,为0值.即无效", e_entry);
    }

    public void printPhOff() {
        logger.info("e_phoff:\t{} , \t 备注: 当没有入口,或者是目标文件时,为0值.即无效", e_phoff);
    }

    public void printHeader() {

        System.out.println(this);

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("======================================\n");

        // 打印: e_ident
        sb.append("\nMAGIC NUM: ")
          .append(Hex.toHex(e_ident[0])).append(", ")
          .append(Hex.toHex(e_ident[1])).append(", ")
          .append(Hex.toHex(e_ident[2])).append(", ")
          .append(Hex.toHex(e_ident[3])).append("  ")
        ;

        // https://refspecs.linuxfoundation.org/elf/gabi4+/ch4.eheader.html#elfid
        sb.append("\n  platform: ").append(e_ident[4] == 1 ? "32位对象" : "64位对象");
        sb.append("\n    endian: ").append(ENDIAN_TYPE.codeOf(e_ident[5]).getDesc());
        sb.append("\n  main ver: ").append(e_ident[6]);
        sb.append("\n  EI_OSABI: ").append(e_ident[7]);
        sb.append("\nEI_ABIVERSION: ").append(e_ident[8]);

        // 文件类型
        sb.append("\n    e_type: ").append(e_type).append("  - ").append(Const.ETYPE_MAP.get((int) e_type));
        // 机器类型. 指CPU
        sb.append("\n e_machine: ").append(e_machine).append(" - ").append(Const.EM_MAP.get((int) e_machine));
        // 版本
        sb.append("\n e_version: ").append(e_version);

        sb.append("\n   e_entry: ").append(e_entry).append("  - 备注: 当没有入口,或者是目标文件时,为0值.即无效");
        sb.append("\n   e_phoff: ").append(e_phoff);
        sb.append("\n   e_shoff: ").append(Hex.toHex(e_shoff)).append(" , 段表偏移");
        sb.append("\n   e_shnum: ").append(e_shnum).append(" , 段表数量");
        sb.append("\ne_shstrndx: ").append(e_shstrndx).append(" , 段表引用注常量表编号");

        sb.append("\n======================================");
        return sb.toString();
    }
}

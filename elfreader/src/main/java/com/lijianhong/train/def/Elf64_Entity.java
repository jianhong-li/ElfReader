package com.lijianhong.train.def;

import static com.lijianhong.train.def.enums.SH_TYPE.SHT_STRTAB;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lijianhong Date: 2022/7/17 Time: 4:24 PM
 * @version $Id$
 */
public class Elf64_Entity {

    private static Logger logger = LoggerFactory.getLogger(Elf64_Entity.class);

    byte[] fileBytes = null;

    public Elf64_Ehdr elfHeader = new Elf64_Ehdr();

    // 段表
    public List<Elf64_Shdr> shdr64List = new ArrayList<>();

    // 符号表
    public List<Elf64_Sym> elf64SymList = new ArrayList<>();

    // 常量段
    public List<String> sectionNameStrTAB = Lists.newArrayList();
    public List<String> commonStrTAB = Lists.newArrayList();


    public Elf64_Entity(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public void initShdr(byte[] fileBytes) {
        int baseOffset =(int) elfHeader.e_shoff;
        int sectionTableNumber = elfHeader.e_shnum;

        logger.info("段表基础偏移为:{} ,段个数为:{}", baseOffset, sectionTableNumber);

        for (int i = 0; i < sectionTableNumber; i++) {

            // logger.info("start parse section table[{}], from baseoffset:{}",String.format("%2d" ,i),baseOffset);

            Elf64_Shdr shdr64 = new Elf64_Shdr();
            shdr64.init(fileBytes, baseOffset);
            shdr64._index = i;
            shdr64List.add(shdr64);
            baseOffset += Elf64_Shdr._size;
        }
    }

    public void initSymTab(byte[] fileBytes) {

        // 其它字符串表
        Elf64_Shdr strTabShdr = null;
        for (Elf64_Shdr elf64_shdr : shdr64List) {
            if (elf64_shdr.sh_type == SHT_STRTAB.getCode() && ".strtab".equals(elf64_shdr._name)) {
                strTabShdr = elf64_shdr;
                break;
            }
        }

        Preconditions.checkNotNull(strTabShdr);


        Elf64_Shdr symTab_Shdr = null;

        for (Elf64_Shdr elf64_shdr : shdr64List) {
            if (".symtab".equals(elf64_shdr._name)) {
                symTab_Shdr = elf64_shdr;
                break;
            }
        }

        Preconditions.checkNotNull(symTab_Shdr);
        final long sh_offset = symTab_Shdr.sh_offset;   /* Section file offset */
        final long sh_size = symTab_Shdr.sh_size;       /* Size of section in bytes */

        int baseOffset = (int) sh_offset;
        int i=0;
        do {
            Elf64_Sym sym = new Elf64_Sym();
            sym.init(fileBytes, baseOffset);
            sym._index = i++;

            sym._st_name = fetchString(strTabShdr, (int) sym.st_name);

            elf64SymList.add(sym);

            baseOffset += Elf64_Sym._size;

        } while (baseOffset < sh_offset + sh_size);
    }

    public void printSegTabInfo() {
        StringBuilder formatInfoBuffer = new StringBuilder();
        for (Elf64_Shdr shdr64 : shdr64List) {
            formatInfoBuffer.append(shdr64.formatInfo());
        }
        System.out.println(formatInfoBuffer);
    }

    public void printStrTab() {
        int i = 0;
        for (String shdr64 : commonStrTAB) {
            logger.info("[{}]: {}", i++, shdr64);
        }
    }

    public void initStrTab(byte[] fileBytes) {

        // 段名称
        short idx = elfHeader.e_shstrndx;
        Elf64_Shdr secNameSegHdr = shdr64List.get(idx);
        this.sectionNameStrTAB = parseStrTab(secNameSegHdr);

        // 解析完成后,初始化段名
        for (Elf64_Shdr shdr64 : shdr64List) {
            int baseOffset = (int) (secNameSegHdr.sh_offset + shdr64.sh_name);
            int end = (int) (secNameSegHdr.sh_offset + secNameSegHdr.sh_size);
            shdr64._name = new String(
                fileBytes, baseOffset, (int) (findNextZero(fileBytes, baseOffset, end) - baseOffset)
            );

        }

        // 其它字符串表
        Elf64_Shdr strTabShdr = null;
        for (Elf64_Shdr elf64_shdr : shdr64List) {
            if (elf64_shdr.sh_type == SHT_STRTAB.getCode() && ".strtab".equals(elf64_shdr._name)) {
                strTabShdr = elf64_shdr;
                break;
            }
        }
        // start to parse.
        Preconditions.checkNotNull(strTabShdr);
        this.commonStrTAB = parseStrTab(strTabShdr);
    }

    /**
     *
     * @param strTab strTab段表信息
     * @param offset 表内偏移
     * @return 识别后的string
     */
    private String fetchString(Elf64_Shdr strTab, int offset) {

        int fileOffset = (int) strTab.sh_offset;
        int strOffset = fileOffset + offset;
        int strEnd = (int) (fileOffset + strTab.sh_size);
        final int nextZero = findNextZero(fileBytes, strOffset, strEnd);

        return new String(fileBytes, strOffset, nextZero - strOffset);
    }

    private List<String> parseStrTab(Elf64_Shdr secNameSegHdr) {

        List<String> tmp = Lists.newArrayList();
        long offset = secNameSegHdr.sh_offset;

        long end = offset + secNameSegHdr.sh_size;


        int start = (int)offset;
        int strEnd = (int)start;

        do {
            strEnd = findNextZero(fileBytes, (int) strEnd, (int) end);
            if (strEnd > start) {
                String constStr = new String(fileBytes, start, strEnd-start);
                tmp.add(constStr);
            }else{
                tmp.add("");
            }

            start = strEnd + 1;
            strEnd = start;
        } while (strEnd < end);

        // printStrTabInternal(tmp);

        return tmp;
    }

    private void printStrTabInternal(List<String> tmp) {
        int i = 0;
        for (String shdr64 : tmp) {
            logger.info("[{}]: {}", i++, shdr64);
        }
    }

    public static int findNextZero(byte[] bytes, int offset, int end) {
        while (offset <= end && bytes[offset] != 0) {
            offset++;
        }
        if (bytes[offset] == 0) {
            return offset;
        }
        return end;
    }

    public void printShtStrTab() {
        int i = 0;
        for (String shdr64 : sectionNameStrTAB) {
            logger.info("[{}]: {}", i++, shdr64);
        }
    }

    public void printSymSeg() {
        StringBuilder sb = new StringBuilder();
        for (Elf64_Sym sym : elf64SymList) {
            sb.append(sym.formatInfo());
        }

        System.out.println(sb.toString());

    }
}

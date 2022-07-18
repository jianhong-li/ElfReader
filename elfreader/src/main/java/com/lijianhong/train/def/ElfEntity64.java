package com.lijianhong.train.def;

import com.google.common.collect.Lists;
import com.lijianhong.train.def.enums.SH_TYPE;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lijianhong Date: 2022/7/17 Time: 4:24 PM
 * @version $Id$
 */
public class ElfEntity64 {

    private static Logger logger = LoggerFactory.getLogger(ElfEntity64.class);

    byte[] fileBytes = null;

    public ElfHeader64 elfHeader = new ElfHeader64();

    // 段表
    public List<Shdr64> shdr64List = new ArrayList<>();

    // 常量段
    public List<String> sectionNameStrTAB = Lists.newArrayList();


    public ElfEntity64(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public void initShdr(byte[] fileBytes) {
        int baseOffset =(int) elfHeader.e_shoff;
        int sectionTableNumber = elfHeader.e_shnum;

        logger.info("段表基础偏移为:{} ,段个数为:{}", baseOffset, sectionTableNumber);

        for (int i = 0; i < sectionTableNumber; i++) {

            // logger.info("start parse section table[{}], from baseoffset:{}",String.format("%2d" ,i),baseOffset);

            Shdr64 shdr64 = new Shdr64();
            shdr64.init(fileBytes, baseOffset);
            shdr64._index = i;
            shdr64List.add(shdr64);
            baseOffset += Shdr64.size;
        }
    }

    public void printSegTabInfo() {
        StringBuilder formatInfoBuffer = new StringBuilder();
        for (Shdr64 shdr64 : shdr64List) {
            formatInfoBuffer.append(shdr64.formatInfo());
        }
        System.out.println(formatInfoBuffer);
    }

    public void initStrTab(byte[] fileBytes) {

        // 段名称
        short idx = elfHeader.e_shstrndx;
        Shdr64 secNameSegHdr = shdr64List.get(idx);
        this.sectionNameStrTAB = parseStrTab(secNameSegHdr);

        // 解析完成后,初始化段名
        for (Shdr64 shdr64 : shdr64List) {
            int baseOffset = (int) (secNameSegHdr.sh_offset + shdr64.sh_name);
            int end = (int) (secNameSegHdr.sh_offset + secNameSegHdr.sh_size);
            shdr64._name = new String(
                fileBytes, baseOffset, (int) (findNextZero(fileBytes, baseOffset, end) - baseOffset)
            );

        }

        // 其它字符串表
    }

    private List<String> parseStrTab(Shdr64 secNameSegHdr) {

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

        printShtStrTab();
        return tmp;
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

    void printShtStrTab() {
        int i = 0;
        for (String shdr64 : sectionNameStrTAB) {
            logger.info("[{}]: {}", i++, shdr64);
        }
    }
}

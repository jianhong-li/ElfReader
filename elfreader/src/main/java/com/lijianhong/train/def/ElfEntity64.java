package com.lijianhong.train.def;

import com.google.common.collect.Lists;
import com.lijianhong.train.def.enums.SH_TYPE;
import com.lijianhong.train.reader.ReadUtils;
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

    public ElfHeader64 elfHeader = new ElfHeader64();


    public List<Shdr64> shdr64List = new ArrayList<>();

    public List<String> strStaList = Lists.newArrayList();




    public void initShdr(byte[] fileBytes) {
        int baseOffset =(int) elfHeader.e_shoff;
        int sectionTableNumber = elfHeader.e_shnum;

        logger.info("段表基础偏移为:{} ,段个数为:{}", baseOffset, sectionTableNumber);
        StringBuilder formatInfoBuffer = new StringBuilder();
        for (int i = 0; i < sectionTableNumber; i++) {

            logger.info("start parse section table[{}], from baseoffset:{}",String.format("%2d" ,i),baseOffset);

            Shdr64 shdr64 = new Shdr64();
            shdr64.init(fileBytes, baseOffset);
            shdr64._index = i;
            shdr64List.add(shdr64);

            //shdr64.printInfo();
            formatInfoBuffer.append(shdr64.formatInfo());

            baseOffset += Shdr64.size;
        }

        System.out.println(formatInfoBuffer);
    }

    public void initStrTab(byte[] fileBytes) {
        long offset = 0;
        Shdr64 shdr = null;
        for (Shdr64 shdr64 : shdr64List) {
            if (shdr64.sh_type == SH_TYPE.SHT_STRTAB.getCode()) {
                offset = shdr64.sh_offset;
                shdr = shdr64;
                break;
            }
        }
        if (offset == 0) {
            logger.warn("当前ELF文件没有常字符串");
            return;
        }
        long end = offset + shdr.sh_size;


        long start = offset;
        long strEnd = start;
//        do {
//
//        }


//        new String(fileBytes,)
    }
}

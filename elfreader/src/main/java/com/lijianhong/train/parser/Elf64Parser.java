package com.lijianhong.train.parser;

import com.google.common.base.Preconditions;
import com.lijianhong.train.def.ElfEntity64;
import com.lijianhong.train.def.ElfHeader64;
import com.lijianhong.train.reader.ReadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lijianhong Date: 2022/7/17 Time: 4:24 PM
 * @version $Id$
 */
public class Elf64Parser {

    private static Logger logger = LoggerFactory.getLogger(Elf64Parser.class);

    private boolean is64BitPlatform;

    private ElfEntity64 elfEntity;

    public Elf64Parser(byte[] fileBytes) {

        elfEntity = new ElfEntity64(fileBytes);


        byte elfType = fileBytes[4];
        if (elfType == 1) {
            logger.info("ELF平台: 32位");
        } else if (elfType == 2) {
            logger.info("ELF平台: 64位");
            elfEntity.elfHeader.init(fileBytes);

            elfEntity.elfHeader.printHeader();

            elfEntity.initShdr(fileBytes);

            elfEntity.initStrTab(fileBytes);

            //logger.info("elfHeader:\n{}", elfEntity.elfHeader);

            Preconditions.checkState(elfEntity.elfHeader.e_ident[1] == 'E');
            Preconditions.checkState(elfEntity.elfHeader.e_ident[2] == 'L');
            Preconditions.checkState(elfEntity.elfHeader.e_ident[3] == 'F');

            elfEntity.printSegTabInfo();
            // 打印段表偏移.
            logger.info("段表位置偏移:{}", elfEntity.elfHeader.e_shoff);

        } else {
            throw new UnsupportedOperationException("不支持的平台类型:" + elfType);
        }
    }

    public ElfEntity64 parse() {

        return elfEntity;
    }
}

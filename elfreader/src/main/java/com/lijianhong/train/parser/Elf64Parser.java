package com.lijianhong.train.parser;

import com.google.common.base.Preconditions;
import com.lijianhong.train.def.ElfEntity;
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

    private ElfEntity elfEntity = new ElfEntity();

    public Elf64Parser(byte[] fileBytes) {

        ReadUtils.readBytes(fileBytes, 0, 16, elfEntity.elfHeader.e_ident);

        logger.info(
            "magicNumber:{}",
            String.format(
                "0x%x,0x%x,0x%x,0x%x",
                elfEntity.elfHeader.e_ident[0],
                elfEntity.elfHeader.e_ident[1],
                elfEntity.elfHeader.e_ident[2],
                elfEntity.elfHeader.e_ident[3]
                )
        );

        elfEntity.elfHeader.e_type = ReadUtils.readShort(fileBytes, 16);
        elfEntity.elfHeader.printEtype();

        elfEntity.elfHeader.e_machine = ReadUtils.readShort(fileBytes, 18);

        Preconditions.checkState(elfEntity.elfHeader.e_ident[1] == 'E');
        Preconditions.checkState(elfEntity.elfHeader.e_ident[2] == 'L');
        Preconditions.checkState(elfEntity.elfHeader.e_ident[3] == 'F');





    }

    public ElfEntity parse() {





        return elfEntity;
    }
}

package com.lijianhong.train.parser;

import com.google.common.base.Preconditions;
import com.lijianhong.train.def.ElfEntity;
import com.lijianhong.train.reader.ReadUtils;

/**
 * @author lijianhong Date: 2022/7/17 Time: 4:24 PM
 * @version $Id$
 */
public class Elf64Parser {

    public static ElfEntity parse(byte[] fileBytes) {
        ElfEntity elfEntity = new ElfEntity();
        ReadUtils.readBytes(fileBytes, 0, 16, elfEntity.elfHeader.e_ident);

        Preconditions.checkState(elfEntity.elfHeader.e_ident[1] == 'E');
        Preconditions.checkState(elfEntity.elfHeader.e_ident[2] == 'L');
        Preconditions.checkState(elfEntity.elfHeader.e_ident[3] == 'F');

        return elfEntity;
    }
}

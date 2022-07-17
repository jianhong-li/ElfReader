package com.lijianhong.train.def;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijianhong Date: 2022/7/17 Time: 4:24 PM
 * @version $Id$
 */
public class ElfEntity64 {

    public ElfHeader64 elfHeader = new ElfHeader64();


    public List<Shdr64> shdr64List = new ArrayList<>();


    public void initShdr(byte[] fileBytes) {
        long baseOffset = elfHeader.e_shoff;

    }
}

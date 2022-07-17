package com.lijianhong.train;

import com.lijianhong.train.parser.Elf64Parser;
import com.lijianhong.train.reader.ReadUtils;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * ELF文件读取入口:
 * <pre>
 *     ELF 文件定义: https://github.com/torvalds/linux/blob/master/include/uapi/linux/elf.h
 * </pre>
 */
public class App {

    private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(App.class);

    /**
     * 从参数1中读取文件名:
     *
     * @param args 参数列表
     */
    public static void main(String[] args) throws IOException {
       /* if (args == null || args.length < 1) {
            System.out.println("please input the file name of elf file");
            System.exit(1);
        }
        logger.info("start to read file: {}....", args[0]);*/




        String fileName;
        byte[] elfBytes;

        if (args.length > 0) {
            fileName = args[0];
            elfBytes = FileUtils.readFileToByteArray(new File(fileName));
        }else {
            elfBytes = ReadUtils.loadDefaultELF("SimpleSection.o");
        }

        Elf64Parser parser = new Elf64Parser(elfBytes);

    }
}

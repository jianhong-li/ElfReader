package com.lijianhong.train;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * ELF文件读取入口
 */
public class App {

    private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(App.class);

    /**
     * 从参数1中读取文件名:
     *
     * @param args 参数列表
     */
    public static void main(String[] args) throws IOException {
        if (args == null || args.length < 1) {
            System.out.println("please input the file name of elf file");
            System.exit(1);
        }
        logger.info("start to read file: {}....", args[0]);
        String fileName = args[0];
        byte[] elfBytes = FileUtils.readFileToByteArray(new File(fileName));

        System.out.println("Hello World!");
    }
}

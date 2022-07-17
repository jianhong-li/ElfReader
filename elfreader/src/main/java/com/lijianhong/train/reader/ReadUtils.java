package com.lijianhong.train.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lijianhong Date: 2022/7/17 Time: 4:29 PM
 * @version $Id$
 */
public class ReadUtils {

    private static Logger logger = LoggerFactory.getLogger(ReadUtils.class);

    public static void readBytes(byte[] fileBytes, int offset, int len, byte[] output) {
        if (len >= 0) {
            System.arraycopy(fileBytes, offset, output, 0, len);
        }
    }


    public static byte[] loadDefaultELF(String sourcePath) {

        if (StringUtils.isEmpty(sourcePath)) {
            return new byte[0];
        }

        StringBuilder tplBuffer = new StringBuilder();
        try (InputStream resourceAsStream =
            ReadUtils.class.getClassLoader().getResourceAsStream(sourcePath)) {
            return IOUtils.toByteArray(new InputStreamReader(resourceAsStream));

        } catch (IOException e) {
            logger.error("load error", e);
        }
        return new byte[0];
    }

    public static short readShort(byte[] fileBytes, int offset) {
        return (short) (fileBytes[offset] + (fileBytes[offset + 1] << 8));
    }
}

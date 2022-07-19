package com.lijianhong.train.reader;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
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

    public static short readUByte(byte[] fileBytes, int offset) {
        byte item = fileBytes[offset];
        return (short) (item & 0x00ff);
    }

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

            /* return ByteStreams.toByteArray(resourceAsStream);*/
            return IOUtils.toByteArray(resourceAsStream);

        } catch (IOException e) {
            logger.error("load error", e);
        }
        return new byte[0];
    }

    public static short readShort(byte[] fileBytes, int offset) {
        return (short) (fileBytes[offset] + (fileBytes[offset + 1] << 8));
    }

    public static int readWord(byte[] fileBytes, int offset) {
        long ret = 0;
        ret += fileBytes[offset];
        ret += fileBytes[offset + 1] << 8;
        ret += fileBytes[offset + 2] << 16;
        ret += fileBytes[offset + 3] << 24;
        return (int)ret;
    }

    public static long readLong(byte[] fileBytes, int offset) {
        long ret = 0;
        ret += (0xff & fileBytes[offset]);
        ret += (0xff & fileBytes[offset + 1]) << 8;
        ret += (0xff & fileBytes[offset + 2]) << 16;
        ret += (0xff & fileBytes[offset + 3]) << 24;



        long high = 0;

        high +=(0xff &  fileBytes[offset + 4]);
        high +=(0xff &  fileBytes[offset + 5]) << 8;
        high +=(0xff &  fileBytes[offset + 6]) << 16;
        high +=(0xff &  fileBytes[offset + 7]) << 24;
        return (high << 32) + ret;
    }
}

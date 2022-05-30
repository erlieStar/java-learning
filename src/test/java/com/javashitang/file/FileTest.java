package com.javashitang.file;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author lilimin
 * @Date 2022/5/17
 */
public class FileTest {

    @Test
    public void test1() throws IOException {
        FileUtils.copyFile(new File("/Users/li/Desktop/截屏2022-05-10 上午9.23.57.png"), new File("/Users/li/Desktop/test.png"));
    }
}

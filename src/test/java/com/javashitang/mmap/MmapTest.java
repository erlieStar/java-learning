package com.javashitang.mmap;

import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lilimin
 * @since 2021-11-21
 */
public class MmapTest {

    @Test
    public void use() throws Exception {
        File file = new File("/Users/peng/software/rocketmq/test/test.txt");
        FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
        MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        byteBuffer.put("hello mmap".getBytes());
        // 将 pagecache 中的内容强制刷到磁盘
        byteBuffer.force();
    }

    // 映射1g的文件
    private static final long _GB = 1 * 1024 * 1024 * 1024;
    // 操作系统每页大小，默认4k
    private static final long _4kb = 4 * 1024;

    @Test
    public void warm() throws Exception {
        File file = new File("/Users/peng/software/rocketmq/test/test.txt");
        FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
        MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, _GB);
        for (int i = 0; i < _GB; i += _4kb) {
            byteBuffer.put(i, (byte) 0);
        }
    }

}

package com.javashitang.mmap;

import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
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

    @Test
    public void test1() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < 5; i++) {
            buffer.put((byte) i);
        }

        ByteBuffer slice = buffer.slice();
        for (int i = 0; i < slice.capacity(); i++) {
            slice.put((byte) (i * 10));
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }
    }

    @Test
    public void writeCaseOne() throws Exception {
        File file = new File("/Users/peng/software/rocketmq/test/case1.txt");
        FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
        MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 2048);
        byteBuffer.put("hello mmap\n".getBytes());
        // 将 pagecache 中的内容强制刷到磁盘
        byteBuffer.force();
    }

    @Test
    public void writeCaseTwo() throws Exception {
        File file = new File("/Users/peng/software/rocketmq/test/case2.txt");
        FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        byteBuffer.put("hello mmap\n".getBytes());
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            fileChannel.write(byteBuffer);
        }
        // 将 pagecache 中的内容强制刷到磁盘
        fileChannel.force(false);
    }

}

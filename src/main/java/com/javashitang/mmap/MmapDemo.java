package com.javashitang.mmap;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

/**
 * @author lilimin
 * @since 2021-11-28
 */
public class MmapDemo {

    // 映射100mb的文件
    private static final long _100MB = 100 * 1024 * 1024;
    // 操作系统每页大小，默认4k
    private static final long _4kb = 4 * 1024;

    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
        MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, _100MB);
        if (args.length > 1) {
            for (int i = 0; i < _100MB; i += _4kb) {
                byteBuffer.put(i, (byte) 0);
            }
        }
        System.out.println("over");
        TimeUnit.SECONDS.sleep(10000);
    }
}

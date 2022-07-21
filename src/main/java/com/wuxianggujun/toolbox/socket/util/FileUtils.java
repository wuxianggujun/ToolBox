package com.wuxianggujun.toolbox.socket.util;

import com.wuxianggujun.toolbox.socket.po.FileData;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileUtils {

    public static FileData readFile(String path) throws IOException {
        File file = new File(path);
        //随机读取文件
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            long fileLength = randomAccessFile.length();
            randomAccessFile.seek(fileLength);
            byte[] bytes = new byte[1024];
            int readSize = randomAccessFile.read(bytes);
            if (readSize <= 0) {
                randomAccessFile.close();
                return null;
            }
            byte[] copy = new byte[readSize];
            System.arraycopy(bytes, 0, copy, 0, readSize);
            FileData fileData = new FileData();
            fileData.setName(file.getName());
            fileData.setSize(readSize);
            fileData.setData(copy);
            return fileData;
        }
    }
}

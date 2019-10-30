package com.example.library.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    public static void copyFile(File sourceFile, File targetFile)
    throws IOException {
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inputStream = new BufferedInputStream(input);

        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outputStream = new BufferedOutputStream(output);

        //数据缓冲
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inputStream.read(b)) != -1){
            outputStream.write(b, 0, len);
        }
        outputStream.flush();

        inputStream.close();
        outputStream.close();
        output.close();

    }
}

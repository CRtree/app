package com.zuoxiao.app.io.zeroCopy;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 零拷贝技术初探
 *
 * @author zuoxiao
 * @date 2021/5/24 15:59
 */
public class FileCopy {

    public static void main(String[] args){
        //copy();
        //zeroCopy();
        mmap();
    }

    static void copy() {
        long current = System.currentTimeMillis();
        File file = new File("D:\\tmp\\total.zip");
        File file2 = new File("D:\\tmp\\total_bak.zip");
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = new FileOutputStream(file2)) {
            int readNum = 0;
            while (readNum >= 0) {
                byte[] bytes = new byte[2048];
                readNum = inputStream.read(bytes);
                outputStream.write(bytes);
            }
        } catch (Exception e) {

        }
        System.out.println("time cost : " + (System.currentTimeMillis() - current));
    }

    static void zeroCopy(){
        long current = System.currentTimeMillis();
        File file = new File("D:\\tmp\\total.zip");
        File file2 = new File("D:\\tmp\\total_bak.zip");

        try (FileChannel srcChannel = new FileInputStream(file).getChannel();
             FileChannel destChannel = new FileOutputStream(file2).getChannel()) {
             srcChannel.transferTo(0, srcChannel.size(), destChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("time cost : " + (System.currentTimeMillis() - current));
    }

    static void mmap(){
        File file = new File("D:\\tmp\\content.txt");
        try (FileChannel srcChannel = new FileInputStream(file).getChannel()) {
            int len = 179;
            MappedByteBuffer mappedByteBuffer = srcChannel.map(FileChannel.MapMode.READ_ONLY,0, len);
            byte[] bytes = new byte[1024];
            mappedByteBuffer.get(bytes,0,len);
            String s = new String(bytes,0,len);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

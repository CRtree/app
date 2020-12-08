package com.zuoxiao.app.util;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件处理
 *
 * @author zuoxiao
 * @date 2020/12/7 11:03
 */
public class FileUtils {

    public static void splitFile() {
        Path dir =  Paths.get("D:\\tmp\\logs");
        File fileDir = dir.toFile();
        if(fileDir.isDirectory()){
            File[] subFileList = fileDir.listFiles();
            for (int i = 0; i < subFileList.length; i++) {
                String subFileName = subFileList[i].getPath();
                if(subFileName.endsWith(".log")){
                    System.out.println(subFileName);
                    createSubFile(subFileList[i]);
                }
            }
        }
    }

    private static void createSubFile(File file) {
        try {
            long timestamp = System.currentTimeMillis();
            FileReader read = new FileReader(file);
            BufferedReader br = new BufferedReader(read);
            String row;
            int rownum = 1;
            int fileNo = 1;
            String fileName = file.getPath();
            String newFileName = String.format("%s-%s.log", fileName.substring(0,fileName.indexOf(".log")),fileNo);
            FileWriter fw = new FileWriter(newFileName);
            while ((row = br.readLine()) != null) {
                rownum++;
                fw.append(row + "\r\n");
                //拆分成大概768MB大小的文件
                if ((rownum / 6328970) > (fileNo - 1)) {
                    System.out.println(String.format("文件结转完成:%s，耗时%sms",newFileName,System.currentTimeMillis()-timestamp));
                    fw.close();
                    fileNo++;
                    newFileName = String.format("%s-%s.log", fileName.substring(0,fileName.indexOf(".log")),fileNo);
                    fw = new FileWriter(newFileName);
                    System.out.println(String.format("文件结转开始:%s",newFileName));
                    timestamp = System.currentTimeMillis();
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        FileUtils.splitFile();
    }
}

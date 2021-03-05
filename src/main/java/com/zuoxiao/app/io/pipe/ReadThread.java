package com.zuoxiao.app.io.pipe;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/2/19 8:56
 */
public class ReadThread implements Runnable{

    private PipedInputStream pipedInputStream = new PipedInputStream();

    public PipedInputStream getPipedInputStream() {
        return pipedInputStream;
    }
    @Override
    public void run() {
        System.out.println("start to read");
        int count = 0;
        byte[] b = new byte[1024];
        try {
            int len;
            while ((len = pipedInputStream.read(b)) > 0){
                String str = new String(b,0,len);
                System.out.println("read: " + str);
            }
            System.out.println("read close");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

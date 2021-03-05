package com.zuoxiao.app.io.pipe;

import java.io.IOException;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/2/19 9:13
 */
public class PipeTest {
    public static void main(String[] args){
        WriteThread writeThread = new WriteThread();
        ReadThread readThread = new ReadThread();
        try {
            writeThread.getPipedOutputStream().connect(readThread.getPipedInputStream());
            Thread write = new Thread(writeThread);
            Thread read = new Thread(readThread);
            read.start();
            Thread.sleep(2000);
            write.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

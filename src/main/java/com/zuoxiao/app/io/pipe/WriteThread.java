package com.zuoxiao.app.io.pipe;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/2/19 9:08
 */
public class WriteThread implements Runnable {

    private PipedOutputStream pipedOutputStream = new PipedOutputStream();

    public PipedOutputStream getPipedOutputStream() {
        return pipedOutputStream;
    }

    @Override
    public void run() {
        String msg = "good today is a bool - ";
        try {
            int n = 10;
            while (n > 0) {
                String t = msg + n;
                System.out.println("writing: " + t);
                pipedOutputStream.write(t.getBytes());
                Thread.sleep(1000);
                n--;
            }
            System.out.println("write close");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

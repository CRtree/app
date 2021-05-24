package com.zuoxiao.app.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * TODO
 *
 * @author zuoxiao
 * @date 2021/5/24 10:37
 */
public class Server {
    private Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocate(32);//调整缓存的大小可以看到打印输出的变化
    private ByteBuffer sendBuffer = ByteBuffer.allocate(32);//调整缓存的大小可以看到打印输出的变化

    String str;

    public void start() throws IOException {
        // 打开服务器套接字通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 服务器配置为非阻塞
        ssc.configureBlocking(false);
        // 进行服务的绑定
        ssc.bind(new InetSocketAddress("localhost", 8001));

        ServerSocketChannel ssc2 = ServerSocketChannel.open();
        // 服务器配置为非阻塞
        ssc2.configureBlocking(false);
        // 进行服务的绑定
        ssc2.bind(new InetSocketAddress("localhost", 8002));

        // 通过open()方法找到Selector
        selector = Selector.open();
        // 注册到selector，等待连接
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        ssc2.register(selector, SelectionKey.OP_ACCEPT);

        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("select listening...");
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (!key.isValid()) {
                    continue;
                }
                if (key.isAcceptable()) {
                    accept(key);
                } else if (key.isReadable()) {
                    read(key);
                } else if (key.isWritable()) {
                    write(key);
                }
                keyIterator.remove(); //该事件已经处理，可以丢弃
            }
        }
    }

    private void write(SelectionKey key) throws IOException, ClosedChannelException {
        SocketChannel channel = (SocketChannel) key.channel();
        System.out.println("server write:" + str);
        byte[] bytes = str.getBytes();
        int i = 0;
        while (i < bytes.length) {
            sendBuffer.clear();
            byte[] tmp = new byte[32];
            int len = i + 32 > bytes.length ? bytes.length : i + 32;
            for (int z = 0; i < len; i++) {
                tmp[z++] = bytes[i];
            }
            sendBuffer.put(tmp);
            sendBuffer.flip();
            channel.write(sendBuffer);
        }

        channel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        StringBuilder stringBuilder = new StringBuilder();
        // Attempt to read off the channel
        int numRead = -1;
        while (numRead != 0) {
            // Clear out our read buffer so it's ready for new data
            this.readBuffer.clear();
            try {
                numRead = socketChannel.read(this.readBuffer);
                stringBuilder.append(new String(readBuffer.array(), 0, numRead));
            } catch (IOException e) {
                // The remote forcibly closed the connection, cancel
                // the selection key and close the channel.
                key.cancel();
                socketChannel.close();
                return;
            }
        }

        str = stringBuilder.toString();
        System.out.println("server read:" + str);
        socketChannel.register(selector, SelectionKey.OP_WRITE);
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = ssc.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("a new client connected " + clientChannel.getRemoteAddress());
    }

    public static void main(String[] args) throws IOException {
        System.out.println("server started...");
        new Server().start();
    }
}

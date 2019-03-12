package io;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOServer extends Thread {

    public static void main(String[] args) {

    }

    @Override
    public void run() {
        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocket = ServerSocketChannel.open();) {// 创建Selector和Channel
            serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
            serverSocket.configureBlocking(false);
            // 注册到Selector，并说明关注点
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();// 阻塞等待就绪的Channel，这是关键点之一
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    // 生产系统中一般会额外进行就绪状态检查
                    sayHelloWorld((ServerSocketChannel) key.channel());
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * NIO则是利用了单线程轮询事件的机制，通过高效地定位就绪的Channel，来决定做什么
     *
     * @throws IOException IO异常
     */
    private void synIO() throws IOException {
        // 创建selector和channel
        Selector selector = Selector.open(); // 创建selector
        ServerSocketChannel channel = ServerSocketChannel.open(); // 创建channel

        // channel绑定地址和端口号
        SocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), 8888);
        channel.bind(address); // 绑定地址和端口

        // channel向selector注册
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void asynIO() throws IOException {
        AsynchronousServerSocketChannel channel = AsynchronousServerSocketChannel.open();
        SocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), 8888);
        channel.bind(address);

        channel.accept(channel, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
            @Override
            public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel attachment) {
                channel.accept(channel, this);
            }

            @Override
            public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {

            }
        });

    }

    private void sayHelloWorld(ServerSocketChannel server) throws IOException {
        try (SocketChannel client = server.accept()) {
            client.write(Charset.defaultCharset().encode("Hello world!"));
        }
    }
}

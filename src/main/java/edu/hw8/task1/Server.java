package edu.hw8.task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Server {
    private static final short TIME_BEFORE_SHUTTING_DOWN = 800;
    private final ExecutorService service;
    private final Semaphore semaphore;
    private final InetSocketAddress port;
    private ServerSocketChannel channel;

    public Server(int maxConnections, int port) {
        this.service = Executors.newFixedThreadPool(maxConnections);
        this.semaphore = new Semaphore(maxConnections, true);
        this.port = new InetSocketAddress(port);
    }

    public void startServer() {
        try (
            Selector selector = Selector.open();
            ServerSocketChannel serverSocket = ServerSocketChannel.open()
        ) {
            channel = serverSocket;
            serverSocket.bind(port);
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            while (serverSocket.isOpen()) {
                selector.select();
                for (SelectionKey key : selector.selectedKeys()) {
                    if (semaphore.tryAcquire()) {
                        if (key.isAcceptable()) {
                            service.execute(new ServerThread(semaphore, serverSocket.accept()));
                        }
                        selector.selectedKeys().remove(key);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopServer() {
        try {
            channel.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        service.shutdown();
        try {
            if (!service.awaitTermination(TIME_BEFORE_SHUTTING_DOWN, TimeUnit.MILLISECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
        }
    }
}

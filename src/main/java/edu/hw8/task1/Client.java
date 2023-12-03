package edu.hw8.task1;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Client {
    private final InetSocketAddress address;
    private final ByteBuffer buffer = ByteBuffer.allocate(4096);
    //private final SocketChannel client;

    private Client(String host, int port) {
//        try {
//            this.client = SocketChannel.open(new InetSocketAddress(host, port));
//            this.client.configureBlocking(false);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        this.address = new InetSocketAddress(host, port);
    }

    public static Client connect(String host, int port) {
        return new Client(host, port);
    }

//    public void send(String message) {
//        try {
//            client.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String receive() {
//        buffer.clear();
//        try {
//            StringBuilder answer = new StringBuilder();
//            while (client.read(buffer) > 0 || answer.isEmpty()) {
//                buffer.flip();
//                byte[] bytes = new byte[buffer.remaining()];
//                buffer.get(bytes);
//                answer.append(new String(bytes, StandardCharsets.UTF_8));
//                buffer.clear();
//            }
//            return answer.toString();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public void close() {
//        try {
//            client.close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public String sendAndGet(String message) {
        try (SocketChannel client = SocketChannel.open(address)) {
            client.configureBlocking(false);
            send(message, client);
            return receive(client);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void send(String message, SocketChannel client) {
        try {
            client.write(ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String receive(SocketChannel client) {
        buffer.clear();
        try {
            StringBuilder answer = new StringBuilder();
            while (client.read(buffer) > 0 || answer.isEmpty()) {
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                answer.append(new String(bytes, StandardCharsets.UTF_8));
                buffer.clear();
            }
            return answer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

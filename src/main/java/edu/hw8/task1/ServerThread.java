package edu.hw8.task1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class ServerThread implements Runnable {
    private static final short BUFFER_SIZE = 4096;
    private final Map<String, String> phrases = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );
    private final SocketChannel client;
    private final Semaphore semaphore;

    public ServerThread(Semaphore semaphore, SocketChannel client) {
        this.client = client;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try (Selector selector = Selector.open()) {
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            while (client.isConnected()) {
                selector.select();
                for (SelectionKey key : selector.selectedKeys()) {
                    if (key.isReadable()) {
                        String request;
                        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                        try {
                            StringBuilder message = new StringBuilder();
                            int read = client.read(buffer);
                            if (read <= 0) {
                                request = null;
                            }
                            while (read > 0) {
                                buffer.flip();
                                byte[] bytesArray = new byte[buffer.remaining()];
                                buffer.get(bytesArray);
                                message.append(new String(bytesArray, StandardCharsets.UTF_8));
                                buffer.clear();
                                read = client.read(buffer);
                            }
                            request = message.toString();
                        } catch (Exception e) {
                            request = null;
                        }
                        if (request == null) {
                            break;
                        }
                        String response = phrases.getOrDefault(
                            request.trim(),
                            "Некорректный запрос. Повторите попытку"
                        );
                        client.write(ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8)));
                        client.close();
                    }
                    selector.selectedKeys().remove(key);
                }
            }
            //client.close();
            semaphore.release();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

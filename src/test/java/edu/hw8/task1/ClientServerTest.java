package edu.hw8.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientServerTest {
    @Test
    @DisplayName("Тест Client и Server")
    public void clientServer_shouldWorkCorrectly() {
        Server server = new Server(2, 18080);
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.execute(server::startServer);
        CompletableFuture<String>[] futures = new CompletableFuture[4];
        List<String> requests = List.of(
            "личности",
            "оскорбления",
            "глупый",
            "интеллект"
            );
        for (int i = 0; i < 4; i++) {
            int temp = i;
            futures[i] = CompletableFuture.supplyAsync(() -> {
                Client client1 = Client.connect("localhost", 18080);
                return client1.sendAndGet(requests.get(temp));
            }, service);
        }
        List<String> expectedResponses = List.of(
            "Не переходи на личности там, где их нет",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
            "Чем ниже интеллект, тем громче оскорбления"
        );
        List<String> actualResponses = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            try {
                actualResponses.add(futures[i].get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        assertThat(actualResponses).containsExactlyInAnyOrderElementsOf(expectedResponses);
    }
}

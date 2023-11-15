package edu.hw2;

import edu.hw2.task3.Connection;
import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.DefaultConnectionManager;
import edu.hw2.task3.FaultyConnection;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.StableConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PopularCommandExecutorTest {
    @Test
    @DisplayName("Тест FaultyConnection.execute(), в котором должно вернуться исключение ConnectionException")
    public void execute_fromFaultyConnection_shouldThrowConnectionException() {
        Random random = new Random(0);
        assertThrows(ConnectionException.class, () -> {
            FaultyConnection connection = new FaultyConnection(random);
            connection.execute("start");
        });
    }
    @Test
    @DisplayName("Тест FaultyConnectionManager.getConnection(), в котором вернётся класс FaultyConnection")
    public void getConnection_fromFaultyConnectionManager_shouldReturnFaultyConnection() {
        Connection connection = new FaultyConnectionManager().getConnection();
        assertEquals(connection.getClass(), FaultyConnection.class);
    }
    @Test
    @DisplayName("Тест DefaultConnectionManager.getConnection(), в котором вернётся класс FaultyConnection")
    public void getConnection_fromDefaultConnectionManager_shouldReturnFaultyConnection() {
        Random random = new Random(3);
        Connection connection = new DefaultConnectionManager(random).getConnection();
        assertEquals(connection.getClass(), FaultyConnection.class);
    }
    @Test
    @DisplayName("Тест DefaultConnectionManager.getConnection(), в котором вернётся класс StableConnection")
    public void getConnection_fromDefaultConnectionManager_shouldReturnStableConnection() {
        Random random = new Random(0);
        Connection connection = new DefaultConnectionManager(random).getConnection();
        assertEquals(connection.getClass(), StableConnection.class);
    }
    @Test
    @DisplayName("Тест PopularCommandExecutor.tryExecute(), в котором DefaultConnectionManager вернёт FaultyConnection и оно вернёт исключение")
    public void tryExecute_fromPopularCommandExecutor_shouldThrowConnectionException() {
        assertThrows(ConnectionException.class, () -> {
            Random random = new Random(3);
            PopularCommandExecutor executor = new PopularCommandExecutor(new DefaultConnectionManager(random), 1);
            executor.updatePackages();
        });
    }
    @Test
    @DisplayName("Тест PopularCommandExecutor.tryExecute(), в котором DefaultConnectionManager вернёт StableConnection")
    public void tryExecute_fromPopularCommandExecutor_shouldNotThrowAnyExceptions() {
        Random random = new Random(0);
        PopularCommandExecutor executor = new PopularCommandExecutor(new DefaultConnectionManager(random), 1);
        assertDoesNotThrow(executor::updatePackages);
    }
    @Test
    @DisplayName("Тест PopularCommandExecutor.tryExecute(), в котором на вход подается некорректное максимальное число попыток и возвращается исключение")
    public void tryExecute_fromPopularCommandExecutor_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            PopularCommandExecutor executor = new PopularCommandExecutor(new DefaultConnectionManager(new Random(0)), 0);
        });
    }
}

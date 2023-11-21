package edu.hw6.task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class PortScannerTest {
    @Test
    @DisplayName("Тест PortScanner.scanPorts")
    public void scanPorts_shouldReturnCorrectAnswer() {
        try (
            ServerSocket tcpSocket = new ServerSocket(777);
            DatagramSocket udpSocket = new DatagramSocket(777);
            ) {
            List<List<String>> ports = PortScanner.scanPorts();
            assertThat(ports).contains(List.of("TCP", "777", ""), List.of("UDP", "777", ""));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

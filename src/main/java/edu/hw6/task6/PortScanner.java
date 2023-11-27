package edu.hw6.task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public final class PortScanner {
    private static final int LAST_PORT_TO_BE_SCANNED = 49151;

    private PortScanner() {}

    public static List<List<String>> scanPorts() {
        List<List<String>> ports = new ArrayList<>();
        for (int i = 0; i < LAST_PORT_TO_BE_SCANNED; i++) {
            try (ServerSocket serverSocket = new ServerSocket(i)) {
                //socket is free, so move on
            } catch (Exception e) {
                ports.add(List.of("TCP", String.valueOf(i), Ports.PORTS.getOrDefault(i, "")));
            }
            try (DatagramSocket datagramSocket = new DatagramSocket(i)) {
                //socket is free, so move on
            } catch (Exception e) {
                ports.add(List.of("UDP", String.valueOf(i), Ports.PORTS.getOrDefault(i, "")));
            }
        }
        return ports;
    }
}

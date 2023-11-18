package edu.hw6.task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PortScanner {
    private static final int LAST_PORT_TO_BE_SCANNED = 49151;

    private PortScanner() {}

    public static Map<Integer, List<String>> scanPorts() {
        Map<Integer, List<String>> ports = new HashMap<>();
        for (int i = 0; i < LAST_PORT_TO_BE_SCANNED; i++) {
            try (ServerSocket serverSocket = new ServerSocket(i)) {
                //socket is free, so move on
            } catch (Exception e) {
                ports.put(i, List.of(Ports.PORTS.getOrDefault(i, ""), "TCP"));
            }
            try (DatagramSocket datagramSocket = new DatagramSocket(i)) {
                //socket is free, so move on
            } catch (Exception e) {
                ports.put(i, List.of(Ports.PORTS.getOrDefault(i, ""), "UDP"));
            }
        }
        return ports;
    }
}

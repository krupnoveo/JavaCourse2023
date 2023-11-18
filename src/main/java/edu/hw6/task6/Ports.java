package edu.hw6.task6;

import java.util.Map;

public final class Ports {
    private Ports() {}

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public static final Map<Integer, String> PORTS = Map.ofEntries(
        Map.entry(80, "HTTP"),
        Map.entry(443, "HTTPS"),
        Map.entry(21, "FTP"),
        Map.entry(22, "SSH"),
        Map.entry(25, "SMTP"),
        Map.entry(110, "POP3"),
        Map.entry(143, "IMAP"),
        Map.entry(3306, "MySQL"),
        Map.entry(5432, "PostgreSQL"),
        Map.entry(8080, "HTTP alternative"),
        Map.entry(8000, "HTTP alternative"),
        Map.entry(123, "NTP"),
        Map.entry(53, "DNS"),
        Map.entry(137, "NetBIOS"),
        Map.entry(139, "NetBIOS"),
        Map.entry(161, "SNMP"),
        Map.entry(162, "SNMP trap"),
        Map.entry(179, "BGP"),
        Map.entry(465, "SMTPS"),
        Map.entry(587, "Submission (SMTP)"),
        Map.entry(993, "IMAPS"),
        Map.entry(995, "POP3S"),
        Map.entry(1433, "Microsoft SQL Server"),
        Map.entry(1521, "Oracle database"),
        Map.entry(8443, "HTTPS (alternative port)"),
        Map.entry(8888, "HTTP alternative"),
        Map.entry(9090, "HTTP alternative"),
        Map.entry(5000, "Synology DSM (HTTP)"),
        Map.entry(5353, "Multicast DNS (MDNS)"),
        Map.entry(7000, "Azureus BitTorrent")
    );
}

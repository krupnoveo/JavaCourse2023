package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        if (maxAttempts < 1) {
            throw new IllegalArgumentException("Number of max attempts must be more than or equal to 1");
        }
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        ConnectionException exception = null;
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                LOGGER.info("Command " + command + " was successfully performed");
                return;
            } catch (Exception e) {
                if (e instanceof ConnectionException) {
                    exception = (ConnectionException) e;
                }
            }
        }
        if (exception != null) {
            LOGGER.info("Failed to perform the command " + command);
            throw new ConnectionException(exception);
        }
    }
}

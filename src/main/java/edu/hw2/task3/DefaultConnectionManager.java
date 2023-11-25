package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final short MAX_CHANCE_OF_FAULTY_CONNECTION = 3;
    private final Random random;

    public DefaultConnectionManager() {
        this.random = new Random();
    }

    public DefaultConnectionManager(Random random) {
        this.random = random;
    }

    @Override
    public Connection getConnection() {
        if (random.nextInt(MAX_CHANCE_OF_FAULTY_CONNECTION) == 2) {
            return new FaultyConnection(random);
        } else {
            return new StableConnection();
        }
    }
}

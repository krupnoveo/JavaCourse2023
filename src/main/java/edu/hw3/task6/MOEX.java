package edu.hw3.task6;

import java.util.PriorityQueue;
import java.util.Queue;

public class MOEX implements StockMarket {
    private final static Queue<Stock> STONKS = new PriorityQueue<>(new StocksComparator());

    @Override
    public void add(Stock stock) {
        STONKS.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        STONKS.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return STONKS.peek();
    }
}

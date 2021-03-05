package ru.netology;

import java.util.Random;

public class MyThread extends Thread{
    private MapKeeper mapKeeper;
    private int numberOfActions;
    private static Random random = new Random();

    public MyThread(String name, MapKeeper mapKeeper, int numberOfActions) {
        super(name);
        this.mapKeeper = mapKeeper;
        this.numberOfActions = numberOfActions;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfActions; i++) {
            mapKeeper.update(random.nextInt(500000));
        }
    }
}

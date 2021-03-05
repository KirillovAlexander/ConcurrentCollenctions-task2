package ru.netology;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    private static final int NUMBER_OF_ACTIONS_MIN = 500000;
    private static final int NUMBER_OF_ACTIONS_MAX = 50000000;

    public static void main(String[] args) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        Map<Integer, Integer> commonMap = Collections.synchronizedMap(hashMap);
        Map<Integer, Integer> concurrentMap = new ConcurrentHashMap<>();

        MapKeeper commonMapKeeper = new MapKeeper(commonMap);
        MapKeeper concurrentMapKeeper = new MapKeeper(concurrentMap);

        startThreadsAndGetResult(createThreads(NUMBER_OF_ACTIONS_MIN, commonMapKeeper), "милисекунд - время работы synchronizedMap на " + NUMBER_OF_ACTIONS_MIN + " действиях в каждом потоке.");
        startThreadsAndGetResult(createThreads(NUMBER_OF_ACTIONS_MIN, concurrentMapKeeper), "милисекунд - время работы ConcurrentHashMap на " + NUMBER_OF_ACTIONS_MIN + " действиях в каждом потоке.");

        startThreadsAndGetResult(createThreads(NUMBER_OF_ACTIONS_MAX, commonMapKeeper), "милисекунд - время работы synchronizedMap на " + NUMBER_OF_ACTIONS_MAX + " действиях в каждом потоке.");
        startThreadsAndGetResult(createThreads(NUMBER_OF_ACTIONS_MAX, concurrentMapKeeper), "милисекунд - время работы ConcurrentHashMap на " + NUMBER_OF_ACTIONS_MAX + " действиях в каждом потоке.");
    }

    private static boolean groupIsAlive(List<Thread> threadList) {
        for (Thread thread :
                threadList) {
            if (thread.isAlive()) return true;
        }
        return false;
    }

    private static List<Thread> createThreads(int numberOfActions, MapKeeper mapKeeper) {
        Thread commonThread1 = new MyThread("Common Thread", mapKeeper, numberOfActions);
        Thread commonThread2 = new MyThread("Common Thread", mapKeeper, numberOfActions);
        Thread commonThread3 = new MyThread("Common Thread", mapKeeper, numberOfActions);

        List<Thread> commonGroupList = new ArrayList<>();

        commonGroupList.add(commonThread1);
        commonGroupList.add(commonThread2);
        commonGroupList.add(commonThread3);

        return commonGroupList;
    }

    private static void startThreadsAndGetResult(List<Thread> threadList, String msg) {
        long commonStartTime = System.currentTimeMillis();
        for (Thread thread :
                threadList) {
            thread.start();
        }
        while (groupIsAlive(threadList)) ;
        long commonFinalTime = System.currentTimeMillis();
        System.out.println((commonFinalTime - commonStartTime) + " " + msg);
    }
}

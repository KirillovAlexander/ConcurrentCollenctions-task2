package ru.netology;

import java.util.Map;

public class MapKeeper {
    private Map<Integer, Integer> map;

    public MapKeeper(Map<Integer, Integer> map) {
        this.map = map;
    }

    public void update(int value) {
        map.put(value, value);
    }
}

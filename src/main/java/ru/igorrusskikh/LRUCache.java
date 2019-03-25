package ru.igorrusskikh;

import java.util.HashMap;
import java.util.TreeMap;


public class LRUCache {
    HashMap<Integer, Object> vals;//cache K and V
    HashMap<Integer, Long> times;//K and  time
    TreeMap<Long, Integer> lists;// time and K
    int cap;


    public LRUCache(int capacity) {
        cap = capacity;
        vals = new HashMap<>();
        times = new HashMap<>();
        lists = new TreeMap<>();

    }

    public Object get(int key) {
        if (!vals.containsKey(key))
            return null;

        lists.remove(times.get(key));
        long currentTime=System.nanoTime();
        times.put(key,currentTime);
        lists.put(currentTime,key);
        return vals.get(key);
    }

    public void set(int key, Object value) {
        if (cap <= 0)
            return;

        if (vals.containsKey(key)) {
            vals.put(key, value);
            get(key);
            return;
        }
        if (vals.size() >= cap) {

            times.remove(lists.firstEntry().getValue());
            vals.remove(lists.firstEntry().getValue());
            lists.remove(lists.firstEntry().getKey());


        }

        long currentTime=System.nanoTime();
        vals.put(key, value);
        times.put(key, currentTime);
        lists.put(currentTime,key);
    }
}

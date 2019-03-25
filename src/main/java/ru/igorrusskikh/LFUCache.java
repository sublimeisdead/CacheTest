package ru.igorrusskikh;
import java.util.*;

public class LFUCache {
    HashMap<Integer, Object> vals;//cache K and V
    HashMap<Integer, Integer> counts;//K and counters
    HashMap<Integer, LinkedHashSet<Integer>> lists;//Counter and item list
    int cap;
    int min = -1;

    public LFUCache(int capacity) {
        cap = capacity;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    public Object get(int key) {
        if (!vals.containsKey(key))
            return null;

        int count = counts.get(key);

        counts.put(key, count + 1);

        lists.get(count).remove(key);


        if (count == min && lists.get(count).size() == 0)
            min++;
        if (!lists.containsKey(count + 1))
            lists.put(count + 1, new LinkedHashSet<>());
        lists.get(count + 1).add(key);
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
            int evit = lists.get(min).iterator().next();
            lists.get(min).remove(evit);
            vals.remove(evit);
            counts.remove(evit);
        }

        vals.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}
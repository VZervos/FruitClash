package utilities;

import java.util.Map;

public class Utility {
    public static int getRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static <K> void addMaps(Map<K, Integer> map1, Map<K, Integer> map2) {
        for (Map.Entry<K, Integer> entry : map2.entrySet()) {
            K key = entry.getKey();
            Integer value = entry.getValue();

            map1.merge(key, value, Integer::sum);
        }
    }
}

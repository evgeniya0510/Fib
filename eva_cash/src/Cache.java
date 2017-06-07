import java.util.HashMap;
import java.util.Map;
import java.util.Collections;


public class Cache {

    public HashMap<Integer, Long> hashCache = new HashMap<Integer, Long>();
    private HashMap<Integer, Integer> cacheHistory = new HashMap<Integer, Integer>();
    private int CACHE_SIZE;

    public Cache(int cacheSize) {
        this.CACHE_SIZE = cacheSize;
    }

    public void addValue(int key, long value) {
        if(this.hashCache.size() < this.CACHE_SIZE) {
            this.hashCache.put(key, value);
        } else {
            this.updateCache(key, value);
        }
    }

    public void updateCache(int key, long value) {
        int solve = this.solver(key);
        if(solve >= 0) {
            hashCache.remove(solve);
            hashCache.put(key, value);
        }
    }

    private int solver(int key) {
        // Map для хранения числа хранящегося в кэше и количество его повторений
        HashMap<Integer, Integer> arr = new HashMap<Integer, Integer>();

        // Берем ключи из кэша и кладем в map ключ и количество его повторений из истории
        for(int cacheKey : this.hashCache.keySet()) {
            arr.put(cacheKey, this.cacheHistory.get(cacheKey));
        }

        // Находим минимальное количество повторений
        int minValue = Collections.min(arr.values());

        // Если менее популярное число из кэша повторяется меньше раз чем проверяемое число
        // то вернуть число которое нужно заменить
        if(minValue < this.cacheHistory.get(key)) {
            for(Map.Entry<Integer, Integer> entry: arr.entrySet()) {
                if(entry.getValue() == minValue) {
                    return entry.getKey();
                }
            }
        }
        return -1;
    }

    public void updateHistory(int keyValue) {
        if(this.cacheHistory.containsKey(keyValue)) {
           this.cacheHistory.put(keyValue, this.cacheHistory.get(keyValue) + 1);
        } else {
            this.cacheHistory.put(keyValue, 1);
        }
    }

    public long getValueByKey(int key) {
        return this.hashCache.get(key);
    }

    public boolean checkKey(int key) {
        return this.hashCache.containsKey(key);
    }
}

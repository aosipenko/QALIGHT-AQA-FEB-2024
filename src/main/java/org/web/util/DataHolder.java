package org.web.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DataHolder {

    private static final HashMap<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        if (map.containsKey(key)) {
            throw new RuntimeException("key " + key + " already in use!");
        }
        map.put(key, value);
    }

    public void replace(String key, Object value) {
        if (!map.containsKey(key)) {
            throw new RuntimeException("key " + key + " does not exist and cannot be replaced!");
        }
        map.put(key, value);
    }

    public Object get(String key, Object... defaultValue) {
        if (!map.containsKey(key) && defaultValue.length == 0) {
            return defaultValue[0];
        }
        return map.get(key);
    }

    public static void reset() {
        map.clear();
    }
}

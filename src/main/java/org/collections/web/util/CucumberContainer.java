package org.collections.web.util;

import java.util.HashMap;

public class CucumberContainer {

    private final HashMap<String, Object> map;

    private static final CucumberContainer instance = new CucumberContainer();

    private CucumberContainer() {
        map = new HashMap<>();
    }

    public static CucumberContainer getInstance() {
        return instance;
    }

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
}

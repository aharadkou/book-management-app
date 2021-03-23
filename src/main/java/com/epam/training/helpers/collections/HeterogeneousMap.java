package com.epam.training.helpers.collections;

import java.util.HashMap;
import java.util.Map;

public class HeterogeneousMap {

    private Map<Class<?>, Object> items = new HashMap<>();

    /**
     * Put item in map.
     * @param type class of an putted instance
     * @param instance putted instance
     * @param <T> type of putted instance
     */
    public <T> void putItem(final Class<T> type, final T instance) {
        items.put(type, instance);
    }

    /**
     * Get item from map.
     * @param type class of derivable instance
     * @param <T> type of derivable instance
     * @return instance of certain type
     */
    public <T> T getItem(final Class<T> type) {

        return type.cast(items.get(type));
    }
}

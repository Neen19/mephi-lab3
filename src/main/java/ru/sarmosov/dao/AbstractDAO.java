package ru.sarmosov.dao;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<V> {

    private final Class<V> clazz;
    protected final Cache<String, V> cache = Caffeine.newBuilder()
            .maximumSize(1000)
            .build();

    protected AbstractDAO(Class<V> clazz) {
        this.clazz = clazz;
    }

    public V findById (String id) {
        return cache.getIfPresent(id);
    }

    public List<V> findAll () {
        return new ArrayList<>(cache.asMap().values());
    }

    public void save(String key, V entity) {
        cache.put(key, entity);
    }

    public void delete(String id) {
        cache.invalidate(id);
    }

    public void deleteAll() {
        cache.invalidateAll();
    }

}
package org.openutilities.rm.am.service;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Cache service connects with the global cache.
 */
@Service
public class CacheService
{
    public static final String METER_CACHE = "meters";
    public static final String UP_CACHE = "usagePoints";

    @Autowired
    private HazelcastInstance hazelcastInstance;

    /**
     * Add object to the cache.
     * @param cacheName
     * @param key
     * @param value
     */
    public void addObjectToCache(String cacheName, Object key, Object value)
    {
        if (key != null)
        {
            hazelcastInstance.getMap(cacheName).put(key, value);
        }
    }

    /**
     * Get object from the cache.
     * @param cacheName
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getObjectFromCache(String cacheName, Object key)
    {
        return (T) hazelcastInstance.getMap(cacheName).get(key);
    }

    /**
     * Clean a cache map.
     *
     * @param cacheName to clean
     */
    public void cleanCache(String cacheName)
    {
        hazelcastInstance.getMap(cacheName).clear();
    }
}

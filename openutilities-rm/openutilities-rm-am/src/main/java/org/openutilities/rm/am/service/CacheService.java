package org.openutilities.rm.am.service;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Cache service connects with the global cache.
 */
@Service
public class CacheService
{
    @Autowired
    private HazelcastInstance hazelcastInstance;

    public void addObjectToCache(String cacheName, Object key, Object value)
    {
        if (key != null)
        {
            hazelcastInstance.getMap(cacheName).put(key, value);
        }
    }

    public <T> T getObjectFromCache(String cacheName, Object key)
    {
        return (T) hazelcastInstance.getMap(cacheName).get(key);
    }
}

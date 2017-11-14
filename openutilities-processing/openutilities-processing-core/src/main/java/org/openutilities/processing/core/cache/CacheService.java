package org.openutilities.processing.core.cache;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.openutilities.processing.core.config.Configuration;

public class CacheService
{
    private HazelcastInstance client;

    public CacheService()
    {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress(Configuration.getPropertyAsString("hazelcast.hostname").concat("5701"));

        client = HazelcastClient.newHazelcastClient();
    }

    public <T> T getObjectFromCache(String cacheName, Object key)
    {
        return (T) client.getMap(cacheName).get(key);
    }
}

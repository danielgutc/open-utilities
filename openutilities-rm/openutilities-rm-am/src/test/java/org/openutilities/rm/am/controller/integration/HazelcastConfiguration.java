package org.openutilities.rm.am.controller.integration;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfiguration
{
    @Bean
    public HazelcastInstance createHazelcastInstance()
    {
        return Hazelcast.newHazelcastInstance();
    }
}

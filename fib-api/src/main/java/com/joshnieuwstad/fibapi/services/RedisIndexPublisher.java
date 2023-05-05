package com.joshnieuwstad.fibapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.joshnieuwstad.fibapi.Entity.Index;
import com.joshnieuwstad.fibapi.interfaces.IndexPublisher;

@Service
public class RedisIndexPublisher implements IndexPublisher {
    @Autowired
    private ChannelTopic topic;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public RedisIndexPublisher() {}

    public RedisIndexPublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    public void publish(final String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}

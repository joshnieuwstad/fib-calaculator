package com.joshnieuwstad.fibapi.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.joshnieuwstad.fibapi.interfaces.IndexPublisher;
import com.joshnieuwstad.fibapi.services.RedisIndexPublisher;
import com.joshnieuwstad.fibapi.services.RedisValueSubscriber;

@Configuration
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String REDIS_HOST;
    @Value("${spring.data.redis.port}")
    private Integer REDIS_PORT;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(REDIS_HOST, REDIS_PORT);
        return new JedisConnectionFactory(config);
    }   

    @Bean
    RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer(); 
        container.setConnectionFactory(jedisConnectionFactory()); 
        container.addMessageListener(messageListener(), new ChannelTopic("channel:value"));
        return container; 
    }

    @Bean
    IndexPublisher redisPublisher() { 
        return new RedisIndexPublisher(redisTemplate(), topic());
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisValueSubscriber(), "onMessage");
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("channel:index");
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setEnableTransactionSupport(true);
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }

}

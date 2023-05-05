package com.joshnieuwstad.fibapi.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.joshnieuwstad.fibapi.Entity.Value;

@Service
public class RedisValueSubscriber implements MessageListener {
    Logger logger = LoggerFactory.getLogger(RedisValueSubscriber.class);

    public static List<Value> messageList = new ArrayList<Value>();

    public void onMessage(final Message message, final byte[] pattern) {
        Gson gson = new Gson();
        Value value = gson.fromJson(new String(message.getBody()), Value.class);
        messageList.add(value);
        logger.info("Message received: " + new String(message.getBody()));
    }

    public static List<Value> getMessageList() {
        return messageList;
    }
}

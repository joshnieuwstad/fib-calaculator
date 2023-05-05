package com.joshnieuwstad.fibapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joshnieuwstad.fibapi.Entity.Index;
import com.joshnieuwstad.fibapi.Entity.Value;
import com.joshnieuwstad.fibapi.repository.IndexRepository;
import com.joshnieuwstad.fibapi.services.RedisIndexPublisher;
import com.joshnieuwstad.fibapi.services.RedisValueSubscriber;


@RestController
@RequestMapping("api/")
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IndexRepository indexRepository;

    @Autowired
    private RedisIndexPublisher redisIndexPublisher;
  
    @GetMapping("/")
    public String getTestResponse() {
        return "Hi";
    }

    @GetMapping("/values/all")
    public ArrayList<Integer> getSeenIndices() {
        Iterable<Index> indexEntities = indexRepository.findAll();
        ArrayList<Integer> indices = new ArrayList<Integer>();

        for (Index index : indexEntities) {
            indices.add(index.getIndex());
        }

        return indices;
    }

    @GetMapping("/values/current")
    public List<Value> getCurrentValues() {
        return RedisValueSubscriber.getMessageList();
    }

    @PostMapping("/values")
    public String addIndex(@RequestBody Index index) {
        if (index.getIndex() >= 40) {
            return new String("Failed");
        }

        indexRepository.save(index);

        redisIndexPublisher.publish(Integer.toString(index.getIndex()));

        return new String("Success");
    }
}

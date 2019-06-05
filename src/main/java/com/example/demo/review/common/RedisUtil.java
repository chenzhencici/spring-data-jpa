package com.example.demo.review.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil<K,V> {

    @Autowired
    RedisTemplate<K,V> redisTemplate;

    public V get(K key){
        return redisTemplate.opsForValue().get(key);
    }

    public boolean set(K key,V value){
        redisTemplate.opsForValue().set(key, value);
        return true;
    }

    public boolean update(K key,V value) {
        redisTemplate.opsForValue().getAndSet(key, value);
        return true;
    }

    public boolean delete(K key){
        Boolean delete = redisTemplate.delete(key);
        return delete;
    }
}

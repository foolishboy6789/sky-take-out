package com.sky.service.impl;

import com.sky.service.ShopService;
import com.sky.utils.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void startOrStop(String key, Integer status) {
        String value = SerializeUtil.serialize(status);
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Integer getStatus(String statusKey) {
        String value = stringRedisTemplate.opsForValue().get(statusKey);
        return SerializeUtil.deserialize(value, Integer.class);
    }
}

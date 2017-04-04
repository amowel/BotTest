package com.weblab.repository;

import com.weblab.model.UserRequestsInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by amowel on 02.04.17.
 */
@Repository
@Slf4j
public class SimpleUserRequestsInfoRepository implements UserRequestsInfoRepository {

    public static final String Key = "vk:";
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public void saveUserRequestsInfo(UserRequestsInfo userRequestsInfo) {
        final Map< String, Integer > properties = new HashMap<>();
        properties.put("requestCounter",1);
        redisTemplate.opsForHash().putAll(Key+userRequestsInfo.getVkId(),properties);
        redisTemplate.expire(Key+userRequestsInfo.getVkId(),20, TimeUnit.SECONDS);
    }

    @Override
    public UserRequestsInfo findUserRequestsInfo(int vkId) {
        List list = redisTemplate.opsForHash().values(Key+vkId);
        if(list.isEmpty())
            return null;
        return new UserRequestsInfo(vkId,Integer.parseInt((String)list.get(0)));
    }

    @Override
    public void updateUserRequestsInfo(UserRequestsInfo userRequestsInfo) {
        redisTemplate.opsForHash().increment(Key+ userRequestsInfo.getVkId(),"requestCounter",1);
    }
}

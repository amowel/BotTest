package com.weblab.dal;

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

    private static final String KEY = "vk:";
    private static final String KEY_BANNED = "banned:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserRequestsInfo saveUserRequestsInfo(UserRequestsInfo userRequestsInfo) {
        final Map<String, Integer> properties = new HashMap<>();
        properties.put("requestCounter", 1);
        redisTemplate.opsForHash().putAll(KEY + userRequestsInfo.getVkId(), properties);
        redisTemplate.expire(KEY + userRequestsInfo.getVkId(), 20, TimeUnit.SECONDS);
        return userRequestsInfo;
    }

    @Override
    public UserRequestsInfo findUserRequestsInfo(int vkId) {
        List list = redisTemplate.opsForHash().values(KEY + vkId);
        if (list.isEmpty())
            return null;
        return new UserRequestsInfo(vkId, Integer.parseInt((String) list.get(0)));
    }

    @Override
    public boolean isBanned(Long vkId) {
        return redisTemplate.opsForValue().get(KEY_BANNED + vkId) != null;
    }

    @Override
    public void updateUserRequestsInfo(UserRequestsInfo userRequestsInfo) {
        if (redisTemplate.opsForHash().increment(KEY + userRequestsInfo.getVkId(), "requestCounter", 1) > 5)
            redisTemplate.opsForValue().set(KEY_BANNED + userRequestsInfo.getVkId(), true, 300, TimeUnit.SECONDS);
        log.info("User key will be expired in {}", redisTemplate.getExpire(KEY + userRequestsInfo.getVkId()));
    }


}

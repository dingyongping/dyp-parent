package com.dyp.tools.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.Objects;

public class CommonRedisUtils {

    public static Logger logger = LoggerFactory.getLogger(CommonRedisUtils.class);
    public static final String LOCK_PREFIX = "redisLock";  //前缀+key
    public static final int LOCK_TIMEOUT = 300; //失效时间

    /**
     *  分布式锁
     */
    public static boolean lock(String key,RedisTemplate redisTemplate){
        String lock_key = LOCK_PREFIX + key;
        // 使用lambda表达式
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            long datalong = System.currentTimeMillis() + LOCK_TIMEOUT + 1;
            Boolean connNx = connection.setNX(lock_key.getBytes(), String.valueOf(datalong).getBytes());
            logger.info("CommonRedisUtils====>>>lock_key"+key+"上锁成功");
            if (connNx) {
                return true;
            } else {
                byte[] value = connection.get(lock_key.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long timeOutData = Long.parseLong(new String(value));
                    if (timeOutData < System.currentTimeMillis()) {
                        // 锁失效时间
                        byte[] oldValue = connection.getSet(lock_key.getBytes(), String.valueOf(System.currentTimeMillis() +
                                LOCK_TIMEOUT + 1).getBytes());
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis(); // 防止死锁
                    }
                }
            }
            return false;
        });
    }

    /**
     * 解锁
     * @param key
     */
    public static void unlock(String key,RedisTemplate redisTemplate) {
        logger.info("CommonRedisUtils====>>>unlock"+key+"解锁成功");
        redisTemplate.delete(key);
    }

}





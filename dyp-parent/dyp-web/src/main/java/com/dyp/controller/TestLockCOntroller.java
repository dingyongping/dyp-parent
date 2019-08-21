package com.dyp.controller;

import com.dyp.tools.generator.CommonRedisUtils;
import com.dyp.tools.generator.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/lock")
public class TestLockCOntroller {

    @Autowired
    RedisTemplate redisTemplate;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        String key= UUIDGenerator.getUUID();//测试使用uuid
        boolean lock = CommonRedisUtils.lock(key,redisTemplate);
        if (lock) {
            // 执行逻辑操作
            logger.info("TestLockController===>lock执行逻辑操作");
            CommonRedisUtils.unlock(key,redisTemplate);
        }
        return "成功";

    }


}
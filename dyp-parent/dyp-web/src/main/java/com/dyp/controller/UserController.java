package com.dyp.controller;


import com.alibaba.fastjson.JSON;
import com.dyp.entity.User;
import com.dyp.service.IUserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/dyp/user")
@Api(value = " 测试用户管理")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserService iUserService;

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value="用户管理-查询", notes="用户管理-查询")
    @ApiResponses({
            @ApiResponse(code = 400, message = "用户管理400异常"),
            @ApiResponse(code = 407, message = "用户管理407异常"),
            @ApiResponse(code = 500, message = "服务器内部错误")
            })
    @RequestMapping("/list")
    @ResponseBody
    public List<User> list() {
        List<User>  list=iUserService.selectList(null);
        // 具体使用
        redisTemplate.opsForList().leftPush("user:list", JSON.toJSONString(list));
        stringRedisTemplate.opsForValue().set("user:name", "张三");
        return list;
    }


    @RequestMapping("/add")
    @ResponseBody
    public String add() {

        int i= (int)(1+Math.random()*(10-1+1));

        //insert
        User user = new User();
        user.setUsername("dyp"+i);
        user.setCreate_time(new Date());
        boolean res = iUserService.insert(user);

        return res ? "success" : "fail";
    }

}


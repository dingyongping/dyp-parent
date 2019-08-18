package com.dyp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@CrossOrigin //跨域
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "平台密钥失效！！";
    }


}


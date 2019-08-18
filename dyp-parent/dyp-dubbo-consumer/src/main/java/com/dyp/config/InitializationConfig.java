package com.dyp.config;

import com.dyp.entity.base.Initialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitializationConfig  implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("初始化开始......");
        //值可以从数据库加载,目前先写死
        Initialization.platformKe="dyp";
        logger.info("初始化值platformKe......："+Initialization.platformKe);
        logger.info("初始化完毕......");
    }


}

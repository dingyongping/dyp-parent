package com.dyp.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestSchedule {

    //创建日期格式化
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss");

    @Scheduled(fixedRate = 3000) //  每隔 3000 毫秒执行一次
    public void fixedRateData() {
        System.out.println("每隔3秒钟执行一次： " + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 28 10 ? * *")  //指定时间执行  10点28分
    public void cronData() {
        System.out.println("在指定时间 " + dateFormat.format(new Date()) + "执行");
    }
}

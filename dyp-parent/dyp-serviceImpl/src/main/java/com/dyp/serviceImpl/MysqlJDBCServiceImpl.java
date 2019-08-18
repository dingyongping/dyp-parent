package com.dyp.serviceImpl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dyp.service.MysqlJDBCService;
import com.dyp.tools.generator.MysqlJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
@Transactional(rollbackFor=Exception.class) //指定回滚,遇到异常Exception时回滚
public class MysqlJDBCServiceImpl implements MysqlJDBCService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public int setCreateTable(String url, String username, String password, String tableName, String sql) {
        return  MysqlJDBC.setCreateTable(url,username,password,tableName,sql);
    }

    @Override
    public Map setInsertData(String url, String username, String password, String[] sqls) {
        return   MysqlJDBC.setInsertData(url,username,password,sqls);
    }
    @Override
    public int setInsertData(String url, String username, String password, String sql) {
        return   MysqlJDBC.setInsertData(url,username,password,sql);
    }

    @Override
    public JSONArray getDataToJsonArry(String url, String username, String password, String sql) {
        return MysqlJDBC.getDataToJsonArry(url,username,password,sql);
    }

    @Override
    public JSONObject getDataToJSONObject(String url, String username, String password, String sql) {
        return MysqlJDBC.getDataToJSONObject(url,username,password,sql);
    }

    @Override
    @Async
    public Map setSelectTable(String url, String username, String password, String sql) {

        logger.info("MysqlJDBCServiceImpl====>start======getTaskExecutor=====");
        logger.info("MysqlJDBCServiceImpl当前运行的线程名称：" + Thread.currentThread().getName());
        Map map=MysqlJDBC.setSelectTable(url,username,password,sql);
        logger.info("MysqlJDBCServiceImpl====>end======getTaskExecutor=====");
        return map;

    }
}

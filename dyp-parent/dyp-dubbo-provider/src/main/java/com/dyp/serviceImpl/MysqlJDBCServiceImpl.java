package com.dyp.serviceImpl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dyp.service.MysqlJDBCService;
import com.dyp.tools.generator.MysqlJDBC;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.Map;


@Service

public class MysqlJDBCServiceImpl implements MysqlJDBCService {
    @Override
    public int setCreateTable(String url, String username, String password, String tableName, String sql) {

        return  MysqlJDBC.setCreateTable(url,username,password,tableName,sql);
    }

    @Override
    @Transactional(rollbackFor=Exception.class) //指定回滚,遇到异常Exception时回滚
    public Map setInsertData(String url, String username, String password, String[] sqls) {
        return   MysqlJDBC.setInsertData(url,username,password,sqls);
    }
    @Override
    @Transactional(rollbackFor=Exception.class) //指定回滚,遇到异常Exception时回滚
    public int setInsertData(String url, String username, String password, String sql) {
        return   MysqlJDBC.setInsertData(url,username,password,sql);
    }

    @Override
    @Transactional(rollbackFor=Exception.class) //指定回滚,遇到异常Exception时回滚
    public JSONArray getDataToJsonArry(String url, String username, String password, String sql) {
        return MysqlJDBC.getDataToJsonArry(url,username,password,sql);
    }

    @Override
    @Transactional(rollbackFor=Exception.class) //指定回滚,遇到异常Exception时回滚
    public JSONObject getDataToJSONObject(String url, String username, String password, String sql) {
        return MysqlJDBC.getDataToJSONObject(url,username,password,sql);
    }

    @Override
    @Transactional(rollbackFor=Exception.class) //指定回滚,遇到异常Exception时回滚
    public Map setSelectTable(String url, String username, String password, String sql) {
        return MysqlJDBC.setSelectTable(url,username,password,sql);
    }
}

package com.dyp.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface MysqlJDBCService {

    int setCreateTable(String url, String username, String password, String tableName, String sql);
    Map setInsertData(String url, String username, String password, String[] sqls);
    int setInsertData(String url, String username, String password, String sql);
    JSONArray getDataToJsonArry(String url, String username, String password, String sql);
    JSONObject getDataToJSONObject(String url, String username, String password, String sql);
      Map setSelectTable(String url,String username,String password,String sql);

}

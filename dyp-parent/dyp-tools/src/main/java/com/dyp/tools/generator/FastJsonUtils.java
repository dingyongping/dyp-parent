package com.dyp.tools.generator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class FastJsonUtils {

    public static Logger logger = LoggerFactory.getLogger(FastJsonUtils.class);//添加日志

    /**
     * 将resultSet转JSONObject
     * @param rs
     * @return
     * @throws SQLException
     * @throws JSONException
     */
    public static JSONObject getToJsonObject(ResultSet rs) throws SQLException, JSONException
    {
        logger.info("FastJsonUtils==>getToJsonObject......start");
        // json对象
        JSONObject jsonObj = new JSONObject();
        // 获取列数
        ResultSetMetaData rsd = rs.getMetaData();
        int columnCount = rsd.getColumnCount();
        // 遍历ResultSet中的每条数据
        if (rs.next()) {
            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName =rsd.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
        }
        return jsonObj;
    }
    /**
     * 将resultSet转JSONArray
     * @param rs
     * @return
     * @throws SQLException
     * @throws JSONException
     */
    public static JSONArray getToJsonArry(ResultSet rs) throws SQLException,JSONException
    {
        logger.info("MysqlJDBC==>getToJsonArry......start");
        // json数组
        JSONArray array = new JSONArray();
        // 获取列数
        ResultSetMetaData rsd = rs.getMetaData();
        int columnCount = rsd.getColumnCount();
        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();
            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName =rsd.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.add(jsonObj);
        }
        return array;
    }
}

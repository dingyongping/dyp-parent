package com.dyp.tools.generator;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * uuid生成工具类
 */
public class UUIDGenerator {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
    public static void main(String[] args) {
        String str="{\"result\":\"1\",\"msg\":\"成功\",\"count\":19,\"list\":[{\"column_key\":\"PRI\",\"column_name\":\"id\",\"data_type\":\"varchar\",\"column_comment\":\"主键uuid\",\"column_type\":\"varchar(32)\"},{\"column_key\":\"\",\"column_name\":\"father_uuid\",\"data_type\":\"varchar\",\"column_comment\":\"父uuid\",\"column_type\":\"varchar(32)\"},{\"column_key\":\"\",\"column_name\":\"code\",\"data_type\":\"varchar\",\"column_comment\":\"编码\",\"column_type\":\"varchar(200)\"},{\"column_key\":\"\",\"column_name\":\"dse_name\",\"data_type\":\"varchar\",\"column_comment\":\"数据源英文名称\",\"column_type\":\"varchar(200)\"},{\"column_key\":\"\",\"column_name\":\"dsc_name\",\"data_type\":\"varchar\",\"column_comment\":\"数据源中文名称\",\"column_type\":\"varchar(200)\"},{\"column_key\":\"\",\"column_name\":\"ds_type\",\"data_type\":\"varchar\",\"column_comment\":\"数据源类型\",\"column_type\":\"varchar(200)\"},{\"column_key\":\"\",\"column_name\":\"db_type\",\"data_type\":\"varchar\",\"column_comment\":\"数据库类型\",\"column_type\":\"varchar(200)\"},{\"column_key\":\"\",\"column_name\":\"ip_address\",\"data_type\":\"varchar\",\"column_comment\":\"ip地址\",\"column_type\":\"varchar(200)\"},{\"column_key\":\"\",\"column_name\":\"username\",\"data_type\":\"varchar\",\"column_comment\":\"用户名\",\"column_type\":\"varchar(200)\"},{\"column_key\":\"\",\"column_name\":\"password\",\"data_type\":\"varchar\",\"column_comment\":\"密码\",\"column_type\":\"varchar(200)\"},{\"column_key\":\"\",\"column_name\":\"port\",\"data_type\":\"varchar\",\"column_comment\":\"端口\",\"column_type\":\"varchar(200)\"},{\"column_key\":\"\",\"column_name\":\"db_name\",\"data_type\":\"varchar\",\"column_comment\":\"数据库名称\",\"column_type\":\"varchar(200)\"},{\"column_key\":\"\",\"column_name\":\"order_num\",\"data_type\":\"int\",\"column_comment\":\"排序\",\"column_type\":\"int(11)\"},{\"column_key\":\"\",\"column_name\":\"status\",\"data_type\":\"int\",\"column_comment\":\"状态  0：禁用   1：正常\",\"column_type\":\"int(11)\"},{\"column_key\":\"\",\"column_name\":\"running\",\"data_type\":\"int\",\"column_comment\":\"是否使用  0：否   1：是\",\"column_type\":\"int(11)\"},{\"column_key\":\"\",\"column_name\":\"remarks\",\"data_type\":\"text\",\"column_comment\":\"备注\",\"column_type\":\"text\"},{\"column_key\":\"\",\"column_name\":\"create_user_id\",\"data_type\":\"bigint\",\"column_comment\":\"创建者ID\",\"column_type\":\"bigint(20)\"},{\"column_key\":\"\",\"column_name\":\"create_time\",\"data_type\":\"timestamp\",\"column_comment\":\"创建时间\",\"column_type\":\"timestamp\"},{\"column_key\":\"\",\"column_name\":\"update_time\",\"data_type\":\"timestamp\",\"column_comment\":\"更新时间\",\"column_type\":\"timestamp\"}],\"tableName\":\"data_source_config\"}\n";
        Map map= JSONObject.parseObject(str);
        List<Map> mapL=(List<Map>)map.get("list");

        StringBuilder sbr=new StringBuilder();
        String tableName="data_source_config";
        sbr.append("CREATE TABLE `").append(tableName).append("` (");
        for(int i=0;i<mapL.size();i++) {
            String column_name = mapL.get(i).get("column_name")+ "";
            String data_type = mapL.get(i).get("data_type")+ "";
            String column_comment = mapL.get(i).get("column_comment")+ "";
            String column_type = mapL.get(i).get("column_type")+ "";
            String column_key = mapL.get(i).get("column_key")+ "";
            if(column_key.equals("PRI")){
                sbr.append(column_name).append(" ").append(column_type).append(" ").append("primary key ").append("COMMENT '").append(column_comment).append("',");
            }else{
                sbr.append(column_name).append(" ").append(column_type).append(" ").append("DEFAULT NULL COMMENT '").append(column_comment).append("',");
            }
        }
        sbr.append("ut_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '批理生成时间'");
        sbr.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='").append(tableName).append("';");
        System.out.println(sbr.toString());
        //System.out.println(getUUID());

    }
}

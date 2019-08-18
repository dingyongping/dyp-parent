package com.dyp.tools.generator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dyp.entity.result.Sqlmsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlJDBC {
    public static final String url = "localhost:3306/test";//数据库连接地址
    public static final String name = "com.mysql.jdbc.Driver";//mysql驱动
    public static final String username = "root";//mysql用户名
    public static final String password = "root";//mysql密码
    public static Connection conn = null;//数据库连接
    public static Statement stmt = null;//SQL语句发送器
    public static ResultSet rs = null;//结果集
    public static Logger logger = LoggerFactory.getLogger(MysqlJDBC.class);//日志
    static{
        try {
            Class.forName(name);// 动态加载mysql驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    private static void close(){
        try{
            if(rs!=null){ rs.close(); }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            if(stmt!=null){ stmt.close(); }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            if(conn!=null){ conn.close(); }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    /**
     * 连接数据库
     * @param url
     * @param username
     * @param password
     * @return
     */
    public static Connection getConnection(String url,String username,String password){
        try{
            logger.info("MysqlJDBC==>getConnection===start===");
            String url01 ="jdbc:mysql://"+url+"?&useUnicode=true&characterEncoding=UTF8";
            conn= DriverManager.getConnection(url01,username,password);//获取连接对象
            logger.info("MysqlJDBC==>getConnection===conn="+conn);
            return conn;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 创建表
     * @param url
     * @param username
     * @param password
     * @param tableName
     * @param sql
     * @return
     */
    public static int setCreateTable(String url,String username,String password,String tableName,String sql){
        logger.info("MysqlJDBC==>setCreateTable===start===");
        MysqlJDBC.getConnection(url,username,password);
        int result=0;
        try {
            stmt = conn .createStatement();
            String sql1 = "DROP TABLE  IF exists "+tableName;//删除表
            int result1 = stmt.executeUpdate(sql1);
            result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
            logger.info("MysqlJDBC==>setCreateTable==>result="+result+";sql="+sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();//关闭连接
        }
        return result;
    }
    /**
     * 添加数据
     * @param url
     * @param username
     * @param password
     * @param sql
     * @return
     */
    public static int setInsertData(String url, String username, String password,String sql){
        logger.info("MysqlJDBC==>setInsertData===start===");
        MysqlJDBC.getConnection(url,username,password);
        int result=0;
        try {
            stmt = conn .createStatement();
            result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
            logger.info("MysqlJDBC==>setInsertData==>result="+result+";sql="+sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return  result;
    }
    /**
     * 添加数据
     * @param url （localhost:3306/marketing）
     * @param username
     * @param password
     * @param strs
     * @return
     */
    public static Map setInsertData(String url, String username, String password,String [] strs){
        logger.info("MysqlJDBC==>setInsertData....[]..start");
        MysqlJDBC.getConnection(url,username,password);
        Map ListM=new HashMap<>();
        int result=0;
        ArrayList<Sqlmsg> lt=new ArrayList<Sqlmsg>();
        try {
            stmt = conn .createStatement();
            for(int x = 0; x < strs.length; x++) {
                System.out.println(strs[x]); //通过循环控制索引
                result = stmt.executeUpdate(strs[x]);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
                Sqlmsg sg=new Sqlmsg();
                sg.setResult(result+"");
                sg.setSql(strs[x]);
                lt.add(sg);
            }
            ListM.put("results",lt);
            // sql = "create table IF not exists student(NO char(20),name varchar(20),primary key(NO))";
            ListM.put("result","1");
            ListM.put("msg","成功");
        } catch (SQLException e) {
            e.printStackTrace();
            ListM.put("result","-1");
            ListM.put("msg","失败");
        }finally {
            close();
        }
        return ListM;

    }
    /**
     * 查询
     * @param url
     * @param username
     * @param password
     * @param sql
     * @return
     */
    public static void setSelectData(String url,String username,String password,String sql){
        logger.info("MysqlJDBC==>setSelectData===start===");
        MysqlJDBC.getConnection(url,username,password);
        try {
            stmt = conn .createStatement();
            rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
            logger.info("MysqlJDBC==>setSelectData==>sql="+sql);
            while(rs.next()){
                System.out.println("id:"+rs.getString(1)+";no:"+rs.getString(2)+";name:"+rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
    }
    /**
     * 查询所有表json自定义
     * @param url
     * @param username
     * @param password
     * @param sql
     * @return
     */
    public static Map setSelectTable(String url,String username,String password,String sql){
        logger.info("MysqlJDBC==>setSelectTable......start");
        MysqlJDBC.getConnection(url,username,password);
        Map ListM=new HashMap<>();
        String result="";
        try {
            stmt = conn .createStatement();
            // sql = "select * from student";
            logger.info("MysqlJDBC==>setSelectTable......sql="+sql);
            rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
            JSONArray arrays=FastJsonUtils.getToJsonArry(rs);
            rs.last(); // 将光标移动到最后一行
            int rowCount = rs.getRow(); // 得到当前行号，即结果集记录数
            ListM.put("count",rowCount);
            ListM.put("sql",sql);
            ListM.put("result","1");
            ListM.put("msg","成功");
            ListM.put("list",arrays);
            //result= resultSetToJson(rs,ListM);
        } catch (SQLException e) {
            e.printStackTrace();
            ListM.put("result","-1");
            ListM.put("msg","失败");
        }finally {
            close();
        }
        return ListM;
    }

    /**
     * 查询
     * @param url
     * @param username
     * @param password
     * @param sql
     * @return
     */
    public static JSONObject getDataToJSONObject(String url, String username, String password, String sql){
        logger.info("MysqlJDBC==>getDataToJSONObject===start===");
        MysqlJDBC.getConnection(url,username,password);
        try {
            stmt = conn .createStatement();
            rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
            logger.info("MysqlJDBC==>getDataToJSONObject==>sql="+sql);
            return FastJsonUtils.getToJsonObject(rs);//格式转JSONObject
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return  null;
    }
    /**
     * 查询
     * @param url
     * @param username
     * @param password
     * @param sql
     * @return
     */
    public static JSONArray getDataToJsonArry(String url, String username, String password, String sql){
        logger.info("MysqlJDBC==>getDataToJsonArry===start===");
        MysqlJDBC.getConnection(url,username,password);
        try {
            stmt = conn .createStatement();
            rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
            logger.info("MysqlJDBC==>getDataToJsonArry==>sql="+sql);
            return FastJsonUtils.getToJsonArry(rs);//格式转JSONArray
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return  null;
    }



    public static String  strbsql(String tename,String tcname,String str){
        logger.info("MysqlJDBC==>strbsql......start");
        StringBuilder sbr=new StringBuilder();
        sbr.append("CREATE TABLE `").append(tename).append("` (");
        sbr.append("id int auto_increment primary key COMMENT '自增主键id',");
        sbr.append("father_id INT(11) COMMENT '父id',");
        sbr.append(str);
        sbr.append("order_num INT(11) DEFAULT '0' COMMENT '排序',");
        sbr.append("STATUS INT(11) DEFAULT '1' COMMENT '状态  0：禁用   1：正常',");
        sbr.append("running INT(11) DEFAULT '1' COMMENT '是否使用  0：否   1：是',");
        sbr.append("create_user_id BIGINT(20) DEFAULT NULL COMMENT '创建者ID',");
        sbr.append("create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',");
        sbr.append("update_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'");
        sbr.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='").append(tcname).append("';");
        logger.info("组装键表语句："+sbr.toString());

        return sbr.toString();
    }

    public static String  strInsertsql(String tename,String reqstr,String resstr){
        logger.info("MysqlJDBC==>strInsertsql......start");
        StringBuilder sbr=new StringBuilder();
        sbr.append("INSERT INTO ").append(tename).append(" (");
        String str01=reqstr.substring(0,reqstr.length()-1);
        sbr.append(str01);
        sbr.append(") VALUES ( ");
        String str02=resstr.substring(0,resstr.length()-1);
        sbr.append(str02);
        sbr.append(" );");
        logger.info("组装添加数据语句："+sbr.toString());
        return sbr.toString();
    }





    public static String  strselectsql(String tename,String reqstr){
        logger.info("MysqlJDBC==>strselectsql......start");
        StringBuilder sbr=new StringBuilder();
        sbr.append("SELECT * FROM ").append(tename).append(" WHERE 1=1 ");
        sbr.append(reqstr);
        logger.info("组装查询数据语句："+sbr.toString());
        return sbr.toString();
    }





    public static void main(String[] args) {
       /* String sql="SHOW TABLES";
        JSONArray  json= MysqlJDBC.getDataToJsonArry(url,username,password,sql);//查询数据
        System.out.println(json.toString());
        JSONObject jct =(JSONObject)json.get(0);
        String tableName=jct.getString("Tables_in_test").toString();
        String sql01="SELECT column_name,data_type,column_comment,column_type,column_key FROM information_schema.COLUMNS WHERE table_name = '"+tableName+"'";
        JSONArray  json01= MysqlJDBC.getDataToJsonArry(url,username,password,sql01);//查询数据
        System.out.println(json01.toString());
        String sssql="CREATE TABLE `test_table` (id int auto_increment primary key COMMENT '自增主键id',father_id INT(11) COMMENT '父id',`param02` varchar(200) DEFAULT NULL COMMENT '字段2',`param03` varchar(200) DEFAULT NULL COMMENT '字段3',`param01` varchar(200) DEFAULT NULL COMMENT '字段1',order_num INT(11) DEFAULT '0' COMMENT '排序',STATUS INT(11) DEFAULT '1' COMMENT '状态  0：禁用   1：正常',running INT(11) DEFAULT '1' COMMENT '是否使用  0：否   1：是',create_user_id BIGINT(20) DEFAULT NULL COMMENT '创建者ID',create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',update_time TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间') ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试表名';";

*/

    }
}

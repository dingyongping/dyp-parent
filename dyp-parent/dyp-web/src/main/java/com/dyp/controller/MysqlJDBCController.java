package com.dyp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dyp.service.MysqlJDBCService;
import com.dyp.tools.generator.MysqlJDBC;
import com.dyp.tools.generator.UUIDGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * MysqlJDBC 前端控制器
 * </p>
 *
 * @author dyp
 * @since 2019-07-17
 */
@Controller
@RequestMapping("/marketing/jdbc")
@Api(value = "mysql-jdbc管理")
public class MysqlJDBCController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${mysql.jabc.url}")
    private String url;

    @Value("${mysql.jabc.username}")
    private String username;

    @Value("${mysql.jabc.password}")
    private String password;
    @Autowired
    private MysqlJDBCService mysqlJDBCService;

    @ApiOperation(value = "mysql-jdbc管理-添加表名", notes = "jmysql-jdbc管理-添加表名")
    @RequestMapping("/setCreateTable")
    @ResponseBody
    public String setCreateTable(HttpServletRequest request, HttpServletResponse response) {
        logger.info("MysqlJDBCController==>setCreateTable......start");
        String tableName = request.getParameter("tableName");
        String sql = request.getParameter("sql");
        Map ListM=new HashMap<>();

        //是否为空
        String[] strs = {"url","username","password","tableName","sql"};
        //if (StringUtils.isBlank(""))//判断字符串为空
        for(int i=0;i<strs.length;i++)
        {
            logger.info("传过来的值"+strs[i]+":"+ request.getParameter(strs[i]));
            if (StringUtils.isBlank(request.getParameter(strs[i]))){
                ListM.put("result","-1");
                ListM.put("msg",strs[i]+"不能为空");
                return JSON.toJSONString(ListM);
            }
        }
        mysqlJDBCService.setCreateTable(url, username, password, tableName, sql);
        return null;
    }

    @ApiOperation(value = "mysql-jdbc管理-创建表", notes = "jmysql-jdbc管理-创建表")
    @RequestMapping("/setCreateTableF")
    @ResponseBody
    public String setCreateTableF(HttpServletRequest request, HttpServletResponse response) {
        logger.info("MysqlJDBCController==>setSelectTable......start");
        String code = request.getParameter("code");
        Map ListM=new HashMap<>();
        String[] strs = {"code"};//是否为空
        for(int i=0;i<strs.length;i++)//if (StringUtils.isBlank(""))//判断字符串为空
        {
            logger.info("传过来的值"+strs[i]+":"+ request.getParameter(strs[i]));
            if (StringUtils.isBlank(request.getParameter(strs[i]))){
                ListM.put("result","-1");
                ListM.put("msg",strs[i]+"不能为空");
                return JSON.toJSONString(ListM);
            } }
        //查询表信息
        String sql="SELECT * FROM db_table where code='" + code + "'" ;
        JSONObject jsb = mysqlJDBCService.getDataToJSONObject(url, username, password, sql);

        String tableId=jsb.getString("id").toString();
        String tename=jsb.getString("dbet_name").toString();
        String tcname=jsb.getString("dbct_name").toString();
        //查询表字段
        String fsql="SELECT * FROM db_table_field WHERE db_table_id='"+tableId+"'";
        JSONArray jarry=  mysqlJDBCService.getDataToJsonArry(url, username, password, fsql);
        StringBuilder sbr=new StringBuilder();
   for (int i=0;i<jarry.size();i++)
   {
       JSONObject jso=(JSONObject)jarry.get(i);
       sbr.append("`").append(jso.getString("dbef_name").toString()).append("` varchar(200) DEFAULT NULL COMMENT '")
               .append("").append(jso.getString("dbcf_name").toString()).append("',");
   }
        String sqlC=MysqlJDBC.strbsql(tename,tcname,sbr.toString());//生成表语句
        int nOk=  mysqlJDBCService.setCreateTable(url, username, password,tename,sqlC);
        ListM.put("result",nOk);
        ListM.put("msg","添加成功！");
        return JSON.toJSONString(ListM);
    }

    @ApiOperation(value = "mysql-jdbc管理-动态生成表数据", notes = "jmysql-jdbc管理-动态生成表数据")
    @RequestMapping("/setInsertTableData")
    @ResponseBody
    public String setInsertTableData(HttpServletRequest request, HttpServletResponse response) {
        logger.info("MysqlJDBCController==>setInsertTableData......start");
        String code = request.getParameter("code");
        Map ListM=new HashMap<>();
        String[] strs = {"code"};//是否为空
        for(int i=0;i<strs.length;i++)//if (StringUtils.isBlank(""))//判断字符串为空
        {
            logger.info("传过来的值"+strs[i]+":"+ request.getParameter(strs[i]));
            if (StringUtils.isBlank(request.getParameter(strs[i]))){
                ListM.put("result","-1");
                ListM.put("msg",strs[i]+"不能为空");
                return JSON.toJSONString(ListM);
            } }
        //查询表信息
        String sql="SELECT * FROM db_table where code='" + code + "'" ;
        JSONObject jsb = mysqlJDBCService.getDataToJSONObject(url, username, password, sql);

        String tableId=jsb.getString("id").toString();
        String tename=jsb.getString("dbet_name").toString();
        String tcname=jsb.getString("dbct_name").toString();
        //查询表字段
        String fsql="SELECT * FROM db_table_field WHERE db_table_id='"+tableId+"'";
        JSONArray jarry=  mysqlJDBCService.getDataToJsonArry(url, username, password, fsql);
        StringBuilder sbr=new StringBuilder();

        StringBuilder reqs=new StringBuilder();
        StringBuilder ress=new StringBuilder();
        for (int i=0;i<jarry.size();i++)
        {
            JSONObject jso=(JSONObject)jarry.get(i);
           String dbef_name = jso.getString("dbef_name").toString();
            if (!StringUtils.isBlank(request.getParameter(dbef_name))){
                reqs.append(dbef_name).append(",");
                ress.append("'").append(request.getParameter(dbef_name)).append("',");
            }
        }
        String sqlC=MysqlJDBC.strInsertsql(tename,reqs.toString(),ress.toString());
        int nOk=  mysqlJDBCService.setInsertData(url, username, password,sqlC);
        ListM.put("result",nOk);
        ListM.put("msg","添加成功！");
        return JSON.toJSONString(ListM);
    }
    @ApiOperation(value = "mysql-jdbc管理-动态查询表数据", notes = "jmysql-jdbc管理-动态查询表数据")
    @RequestMapping("/setselectTableData")
    @ResponseBody
    public String setselectTableData(HttpServletRequest request, HttpServletResponse response) {
        logger.info("MysqlJDBCController==>setselectTableData......start");
        String code = request.getParameter("code");
        Map ListM=new HashMap<>();
        String[] strs = {"code"};//是否为空
        for(int i=0;i<strs.length;i++)//if (StringUtils.isBlank(""))//判断字符串为空
        {
            logger.info("传过来的值"+strs[i]+":"+ request.getParameter(strs[i]));
            if (StringUtils.isBlank(request.getParameter(strs[i]))){
                ListM.put("result","-1");
                ListM.put("msg",strs[i]+"不能为空");
                return JSON.toJSONString(ListM);
            } }
        //查询表信息
        String sql="SELECT * FROM db_table where code='" + code + "'" ;
        JSONObject jsb = mysqlJDBCService.getDataToJSONObject(url, username, password, sql);

        String tableId=jsb.getString("id").toString();
        String tename=jsb.getString("dbet_name").toString();
        String tcname=jsb.getString("dbct_name").toString();
        //查询表字段
        String fsql="SELECT * FROM db_table_field WHERE db_table_id='"+tableId+"'";
        JSONArray jarry=  mysqlJDBCService.getDataToJsonArry(url, username, password, fsql);
        StringBuilder sbr=new StringBuilder();

        StringBuilder reqs=new StringBuilder();

        for (int i=0;i<jarry.size();i++)
        {
            JSONObject jso=(JSONObject)jarry.get(i);
            String dbef_name = jso.getString("dbef_name").toString();
            if (!StringUtils.isBlank(request.getParameter(dbef_name))){
                reqs.append("AND ").append(dbef_name).append(" = '").append(request.getParameter(dbef_name)).append("' ");
            }
        }
        String sqlC=MysqlJDBC.strselectsql(tename,reqs.toString());
        JSONArray list=  mysqlJDBCService.getDataToJsonArry(url, username, password,sqlC);
        ListM.put("List",list);
        ListM.put("result","1");
        ListM.put("msg","添加成功！");
        return JSON.toJSONString(ListM);
    }
    @ApiOperation(value = "mysql-jdbc管理-添加表数据", notes = "jmysql-jdbc管理-添加表数据")
    @RequestMapping("/setInsertData")
    @ResponseBody
    public String setInsertData(HttpServletRequest request, HttpServletResponse response) {
        logger.info("MysqlJDBCController==>setInsertData......start");
        String url = request.getParameter("url");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String dbet_name = request.getParameter("dbet_name");
        String dbct_name = request.getParameter("dbct_name");
        Map ListM=new HashMap<>();

        //是否为空
        String[] strs = {"code","dbet_name","dbct_name"};
        //if (StringUtils.isBlank(""))//判断字符串为空
        for(int i=0;i<strs.length;i++)
        {
            logger.info("传过来的值"+strs[i]+":"+ request.getParameter(strs[i]));
            if (StringUtils.isBlank(request.getParameter(strs[i]))){
                ListM.put("result","-1");
                ListM.put("msg",strs[i]+"不能为空");
                return JSON.toJSONString(ListM);
            }
        }
        String sql="INSERT INTO db_table(id,CODE,dbet_name,dbct_name)VALUES" +
                "('" + UUIDGenerator.getUUID() + "','" + code + "','" + dbet_name + "','" + dbct_name + "');";
        int nOk=  mysqlJDBCService.setInsertData(url, username, password, sql);
        ListM.put("result",nOk);
        ListM.put("msg","添加成功！");
        return JSON.toJSONString(ListM);
    }

    @ApiOperation(value = "mysql-jdbc管理-添加表字段", notes = "jmysql-jdbc管理-添加表字段")
    @RequestMapping("/setInsertDataField")
    @ResponseBody
    public String setInsertDataField(HttpServletRequest request, HttpServletResponse response) {
        logger.info("MysqlJDBCController==>setInsertDataField......start");
        String code = request.getParameter("code");
        String dbef_name = request.getParameter("dbef_name");
        String dbcf_name = request.getParameter("dbcf_name");
        String order_num = request.getParameter("order_num");
        Map ListM=new HashMap<>();
        //是否为空
        String[] strs = {"code","dbef_name","dbcf_name","order_num"};
        //if (StringUtils.isBlank(""))//判断字符串为空
        for(int i=0;i<strs.length;i++)
        {
            logger.info("传过来的值"+strs[i]+":"+ request.getParameter(strs[i]));
            if (StringUtils.isBlank(request.getParameter(strs[i]))){
                ListM.put("result","-1");
                ListM.put("msg",strs[i]+"不能为空");
                return JSON.toJSONString(ListM);
            }
        }
        int nOk=0;
        String sql="SELECT * FROM db_table where code='" + code + "'" ;
        JSONObject jsb = mysqlJDBCService.getDataToJSONObject(url, username, password, sql);
        if(jsb!=null) {
            String db_table_id = jsb.getString("id").toString();
            String sqlf = "INSERT INTO db_table_field(id,db_table_id,dbef_name,dbcf_name,order_num)VALUES" +
                    "('" + UUIDGenerator.getUUID() + "','" + db_table_id + "','" + dbef_name + "','" + dbcf_name + "','" + order_num + "');";
            nOk = mysqlJDBCService.setInsertData(url, username, password, sqlf);
        }
        ListM.put("result",nOk);
        ListM.put("msg","添加成功！");
        return JSON.toJSONString(ListM);
    }
    @ApiOperation(value = "mysql-jdbc管理-查询表数据", notes = "jmysql-jdbc管理-查询表数据")
    @RequestMapping("/setSelectTable")
    @ResponseBody
    public String setSelectTable(HttpServletRequest request, HttpServletResponse response) {
        logger.info("MysqlJDBCController==>setSelectTable......start");
        String code = request.getParameter("code");
        Map ListM=new HashMap<>();
        //是否为空
        String[] strs = {"code"};
        //if (StringUtils.isBlank(""))//判断字符串为空
        for(int i=0;i<strs.length;i++)
        {
            logger.info("传过来的值"+strs[i]+":"+ request.getParameter(strs[i]));
            if (StringUtils.isBlank(request.getParameter(strs[i]))){
                ListM.put("result","-1");
                ListM.put("msg",strs[i]+"不能为空");
                return JSON.toJSONString(ListM);
            }
        }
        String sql="SELECT * FROM db_table where code='" + code + "'" ;
        JSONObject jsb = mysqlJDBCService.getDataToJSONObject(url, username, password, sql);
        String tableId=jsb.getString("id").toString();
        String fsql="SELECT * FROM db_table_field WHERE db_table_id='"+tableId+"'";
        Map map=  mysqlJDBCService.setSelectTable(url, username, password, fsql);
        ListM.put("result","1");
        ListM.put("table",jsb);
        ListM.put("table_field",map);
        return JSON.toJSONString(ListM);
    }

}

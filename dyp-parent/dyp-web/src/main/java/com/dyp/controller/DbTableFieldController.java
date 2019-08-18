package com.dyp.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dyp.entity.DbTable;
import com.dyp.entity.DbTableField;
import com.dyp.service.IDbTableFieldService;
import com.dyp.service.IDbTableService;
import com.dyp.tools.generator.UUIDGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设置表字段表 前端控制器
 * </p>
 *
 * @author dyp
 * @since 2019-07-28
 */
@Controller
@RequestMapping("/dyp/dbTableField")
@Api(value = "字段管理")
public class DbTableFieldController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IDbTableFieldService IDbTableFieldService;

    @Autowired
    private IDbTableService iDbTableService;

    @ApiOperation(value = "字段管理添加表字段", notes = "字段管理添加表字段")
    @RequestMapping("/setAddDate")
    @ResponseBody
    public String setAddDate(HttpServletRequest request, HttpServletResponse response) {
        logger.info("DbTableFieldController==>setAddDate......start");
        String tbf_code = request.getParameter("tbf_code");
        String tb_code = request.getParameter("tb_code");
        String dbef_name = request.getParameter("dbef_name");
        String dbcf_name = request.getParameter("dbcf_name");
        Map ListM=new HashMap<>();
        //是否为空
        String[] strs = {"tbf_code","dbef_name","dbcf_name","tb_code"};
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
        DbTable DbTable=  (DbTable) iDbTableService.selectOne(new EntityWrapper<DbTable>()
                .eq("code",tb_code)
        );
        logger.info("数据表数据:"+ JSON.toJSONString(DbTable));
        if(DbTable!=null){
            DbTableField tb=new DbTableField();
            tb.setId(UUIDGenerator.getUUID());
            tb.setDbTableId(DbTable.getId());
            tb.setCode(tbf_code);
            tb.setDbefName(dbef_name);
            tb.setDbcfName(dbcf_name);
            int selCount=   IDbTableFieldService.selectCount(new EntityWrapper<DbTableField>()
                    .eq("code",tbf_code)
                    .eq("dbef_name",dbef_name)
                    .eq("dbcf_name",dbcf_name)
            );
            logger.info("数据是否存在selCount:"+ selCount);
            if(selCount==0){
                boolean acount= IDbTableFieldService.insert(tb);
                ListM.put("result",acount);
                ListM.put("msg","添加成功");
            }else{
                ListM.put("result","-1");
                ListM.put("msg","数据已重复");
            }
        }else{
            ListM.put("result","-1");
            ListM.put("msg","数据表不存在");
        }

        return JSON.toJSONString(ListM);
    }

    @ApiOperation(value = "查询表数据", notes = "查询表数据")
    @RequestMapping("/getselectDate")
    @ResponseBody
    public String getselectDate(HttpServletRequest request, HttpServletResponse response) {
        logger.info("DbTableFieldController==>getselectDate......start");
        String tb_code = request.getParameter("tb_code");
        Map ListM=new HashMap<>();
        String[] strs = {"tb_code"};
        for(int i=0;i<strs.length;i++)
        {
            logger.info("传过来的值"+strs[i]+":"+ request.getParameter(strs[i]));
            if (StringUtils.isBlank(request.getParameter(strs[i]))){
                ListM.put("result","-1");
                ListM.put("msg",strs[i]+"不能为空");
                return JSON.toJSONString(ListM);
            }
        }
        DbTable dbTable=  (DbTable) iDbTableService.selectOne(new EntityWrapper<DbTable>().eq("code",tb_code));
        logger.info("数据表数据:"+ JSON.toJSONString(dbTable));
        if(dbTable!=null){
            ListM.put("result","0");
            ListM.put("dbTable",dbTable);
            List<DbTableField> dtfList=IDbTableFieldService.selectList(new EntityWrapper<DbTableField>()
                    .eq("db_table_id",dbTable.getId())
            );
            ListM.put("dtfList",dtfList);
        }else{
            ListM.put("result","-1");
            ListM.put("msg","数据表不存在");
        }
        return JSON.toJSONString(ListM);
    }
	
}

package com.dyp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dyp.entity.DbTable;
import com.dyp.service.IDbTableService;
import com.dyp.tools.generator.UUIDGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@CrossOrigin //跨域
@RequestMapping("/dyp/dbTable")
@Api(value = "表管理")
public class DbTableController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //@Autowired
    @Reference
    private IDbTableService iDbTableService;

    @ApiOperation(value = "添加表", notes = "添加表")
    @RequestMapping("/setAddDate")
    @ResponseBody
    public String setAddDate(HttpServletRequest request, HttpServletResponse response) {
        logger.info("DbTableController==>setAddDate......start");
        String tb_code = request.getParameter("tb_code");
        String dbet_name = request.getParameter("dbet_name");
        String dbct_name = request.getParameter("dbct_name");
        Map ListM=new HashMap<>();
        //是否为空
        String[] strs = {"tb_code","dbet_name","dbct_name"};
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
        //判断是否是存在记录
        int selCount=   iDbTableService.selectCount(new EntityWrapper<DbTable>()
                .eq("code",tb_code)
                .eq("dbet_name",dbet_name)
                .eq("dbct_name",dbct_name)
        );
        logger.info("数据是否存在selCount:"+ selCount);
        if(selCount==0){
            DbTable tb=new DbTable();
            tb.setId(UUIDGenerator.getUUID());
            tb.setCode(tb_code);
            tb.setDbetName(dbet_name);
            tb.setDbctName(dbct_name);
            boolean acount= iDbTableService.insert(tb);
            ListM.put("result",acount);
            ListM.put("msg","添加成功");
        }else{
            ListM.put("result","-1");
            ListM.put("msg","数据已重复");
        }
        return JSON.toJSONString(ListM);

    }

    @ApiOperation(value = "查询表", notes = "查询表")
    @RequestMapping("/getselectDate")
    @ResponseBody
    public String getselectDate(HttpServletRequest request, HttpServletResponse response) {
        logger.info("DbTableController==>getselectDate......start");
        String tb_code = request.getParameter("tb_code");
        Map ListM=new HashMap<>();
        //是否为空
        String[] strs = {"tb_code"};
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
        //查询数据
        DbTable dbTable=   iDbTableService.selectOne(new EntityWrapper<DbTable>()
                .eq("code",tb_code)
        );
        logger.info("数据dbTable:"+ JSON.toJSONString(dbTable));
        if(dbTable!=null){
            ListM.put("result","0");
            ListM.put("dbTable",dbTable);
        }else{
            ListM.put("result","-1");
            ListM.put("msg","没有数据");
        }

        return JSON.toJSONString(ListM);

    }


}

package com.dyp.tools.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成器
 */
public class CodeGenerator {

    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String AUTHOR = "dyp";
    // 输出到哪个目录
    public static final String dirPath = "c:\\dyp";
    //生成类
    public static final String PACKAGE = "com";
    public static final String MODEL_NAME = "dyp";


    public static  void generateByTables(String... tableNames) {
        //自动生成器
        AutoGenerator at = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
        //at.setTemplateEngine(new FreemarkerTemplateEngine());
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(dirPath);
        gc.setAuthor(AUTHOR);
        gc.setFileOverride(true); //是否覆盖
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        at.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName(DRIVER);
        dsc.setUsername(USER_NAME);
        dsc.setPassword(PASSWORD);
        dsc.setUrl(DB_URL);
        at.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        //strategy.setTablePrefix(new String[] { "tb_", "sys_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(tableNames); // 需要生成的表
        strategy.setEntityBuilderModel(true);
        at.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PACKAGE);
        pc.setModuleName(MODEL_NAME);
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setMapper("dao");
        pc.setService("service");
        pc.setServiceImpl("serviceImpl");
        pc.setXml("mapper");
        at.setPackageInfo(pc);

        // 注入自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("dyp", this.getConfig().getGlobalConfig().getAuthor() + "-aut");
                this.setMap(map);
            }
        };
        at.setCfg(cfg);
        // 执行生成
        at.execute();
        // 打印注入设置【可无】
        System.err.println(at.getCfg().getMap().get("dyp"));
    }
    /**
     * MySQL 生成演示
     */
    public static void main(String[] args) {
        //指定生成的表名
        String[] tableNames = new String[]{"db_table","db_table_field"};
        generateByTables(tableNames);

    }

}

package com.dyp.config;

import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 数据源配置和工作流引擎对象创建
 */
@Configuration
public class ActivitiConfig  extends AbstractProcessEngineAutoConfiguration {

    /**
     * 工作流数据库
     */
    @Autowired
    @Qualifier("activiti")
    private DataSource activitiDataSource;

    /**
     * 工作流 引擎对象创建
     * @param transactionManager
     * @param springAsyncExecutor
     * @return
     * @throws IOException
     */
    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            PlatformTransactionManager transactionManager,
            SpringAsyncExecutor springAsyncExecutor) throws IOException {
        return baseSpringProcessEngineConfiguration(
                activitiDataSource,
                transactionManager,
                springAsyncExecutor);
    }
}

/*关于多数据源的配置方式网上有很多种，可以自己了解下*/



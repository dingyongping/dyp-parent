package com.dyp.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${thread.core.pool.size}")
    private int coreSize; // 核心线程数
    @Value("${max.pool.size}")
    private int maxSize; // 设置最大线程数
     @Value("${queue.capacity}")
    private int queueCapacity; // 设置队列容量
    @Value("${keep.alive.seconds}")
    private int keepAliveSeconds; // 设置线程活跃时间（秒）

    @Bean
    public TaskExecutor getTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(maxSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 默认线程名称
        executor.setThreadNamePrefix("thread-");
        // 将”线程池的拒绝策略”由DiscardPolicy修改为CallerRunsPolicy之后，
        // 当有任务添加到线程池被拒绝时，
        // 线程池会将被拒绝的任务添加到”线程池正在运行的线程”中取运行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

}

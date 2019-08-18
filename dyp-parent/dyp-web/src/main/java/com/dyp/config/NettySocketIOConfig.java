package com.dyp.config;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettySocketIOConfig {

    @Value("${netty.socketio.host}")
    private String host;
    @Value("${netty.socketio.port}")
    private Integer port;
    @Value("${netty.socketio.bossCount}")
    private int bossCount;
    @Value("${netty.socketio.workCount}")
    private int workCount;
    @Value("${netty.socketio.allowCustomRequests}")
    private boolean allowCustomRequests;
    @Value("${netty.socketio.upgradeTimeout}")
    private int upgradeTimeout;
    @Value("${netty.socketio.pingTimeout}")
    private int pingTimeout;
    @Value("${netty.socketio.pingInterval}")
    private int pingInterval;

    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setSocketConfig(socketConfig);
        config.setHostname(host);//地址
        config.setPort(port);//端口
        config.setBossThreads(bossCount);// socket连接数大小
        config.setWorkerThreads(workCount);//
        config.setAllowCustomRequests(allowCustomRequests);//
        config.setUpgradeTimeout(upgradeTimeout);//超时时间（毫秒）
        config.setPingTimeout(pingTimeout);// Ping超时时间（毫秒）
        config.setPingInterval(pingInterval);//Ping间隔（毫秒
        return new SocketIOServer(config);
    }


}

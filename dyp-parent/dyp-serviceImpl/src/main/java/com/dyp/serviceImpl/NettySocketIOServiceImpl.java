package com.dyp.serviceImpl;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.dyp.entity.NettyPushMessage;
import com.dyp.service.NettySocketIOService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NettySocketIOServiceImpl implements NettySocketIOService {


    // 用来存已连接的客户端
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * Spring IoC容器创建之后，在加载NettySocketIOServiceImpl Bean之后启动
     * @throws Exception
     */
    @PostConstruct
    private void autoStartup() throws Exception {
        start();
    }

    /**
     * Spring IoC容器在销毁NettySocketIOServiceImpl Bean之前关闭,避免重启项目服务端口占用问题
     * @throws Exception
     */
    @PreDestroy
    private void autoStop() throws Exception  {
        stop();
    }

    @Override
    public void start() {
        // 监听客户端连接
        socketIOServer.addConnectListener(client -> {
            String loginUserNum = getParamsByClient(client);
            if (loginUserNum != null) {
                clientMap.put(loginUserNum, client);
            }
        });

        // 监听客户端断开连接
        socketIOServer.addDisconnectListener(client -> {
            String loginUserNum = getParamsByClient(client);
            if (loginUserNum != null) {
                clientMap.remove(loginUserNum);
                client.disconnect();
            }
        });
        // 处理自定义的事件，与连接监听类似
        socketIOServer.addEventListener(PUSH_EVENT, NettyPushMessage.class, (client, data, ackSender) -> {
            // TODO do something
        });
        socketIOServer.start();
    }
    @Override
    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    @Override
    public void pushMessageToUser(NettyPushMessage NettyPushMessage) {
        String loginUserNum = NettyPushMessage.getLoginUserNum()+"";
        if (StringUtils.isNotBlank(loginUserNum)) {
            SocketIOClient client = clientMap.get(loginUserNum);
            if (client != null)
                client.sendEvent(PUSH_EVENT, NettyPushMessage);
        }
    }
    /**
     * 获取client连接中的参数，可更改
     */
    private String getParamsByClient(SocketIOClient client) {
        // 从请求的连接中拿出参数（这里的loginUserNum必须是唯一标识）
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> list = params.get("loginUserNum");
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}

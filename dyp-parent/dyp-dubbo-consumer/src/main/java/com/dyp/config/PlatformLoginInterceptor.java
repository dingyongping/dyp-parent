package com.dyp.config;

import com.dyp.entity.base.Initialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlatformLoginInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(getClass());
    //平台密钥
  //  private String platformKey="dyp";
    //方法执行前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String backUrl = "http://" + request.getServerName() //服务器地址
                + ":" + request.getServerPort()           //端口号
                + request.getContextPath() ;     //项目名称
        logger.info("进到拦截方法中");
        System.out.println("拦截地址："+backUrl);
        System.out.println("读取初始化的值："+Initialization.platformKe);
        String reqKey=request.getParameter("key");
        if(!Initialization.platformKe.equals(reqKey)){
            response.sendRedirect(backUrl+"/error/index");
            return false;
        }
        return true;
    }
    //方法执行后
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception { }
    //页面渲染前
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception { }

}

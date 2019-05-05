package com.mwy.demo.filter;

import com.alibaba.fastjson.JSON;
import com.mwy.demo.util.TimeUnitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@WebFilter(filterName = "interfaceFilter",urlPatterns = "/*")
public class InterfaceFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(InterfaceFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String ip = req.getRemoteAddr();
        String uri = req.getRequestURI();
        Map param = req.getParameterMap();
        String contentType=req.getContentType();
        long time = System.currentTimeMillis();

        logger.info("[{}]-收到[{}]的请求:{}, 入参:{},请求类型:{}", time, ip, uri, JSON.toJSONString(param),contentType);
//        System.out.println("["+time+"]-收到["+ip+"]的请求:"+uri+", 入参:"+JSON.toJSONString(param)+",请求类型:"+contentType);
        chain.doFilter(request, response);
        String useTime = TimeUnitUtils.parseTime(System.currentTimeMillis() - time, MILLISECONDS);
        logger.info("[{}]-请求已处理,用时:{}", time, useTime);
//        System.out.println("["+time+"]-请求已处理,用时:"+useTime);
    }

    @Override
    public void destroy() {

    }
}

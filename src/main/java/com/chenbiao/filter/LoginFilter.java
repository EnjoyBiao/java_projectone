package com.chenbiao.filter;

import com.alibaba.fastjson2.JSONObject;
import com.chenbiao.pojo.Result;
import com.chenbiao.utils.JwtUtils;
import com.github.pagehelper.util.StringUtil;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@WebFilter(urlPatterns = "/*")
/**
 * 登录过滤
 * */
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //获取请求URL
        String url = request.getRequestURL().toString();
         //判断请求是否包含登录login;如果包含，说明是登录操作，放行
        if(url.contains("login")){
           filterChain.doFilter(servletRequest,servletResponse);
           return;
        }
         // 获取请求头中的令牌token
        String jwt = request.getHeader("token");//token 和前端约定好的，是什么字段就取什么字段
         // 判断令牌是否存在，否则登录失败（未登录）
        if(!StringUtils.hasLength(jwt)){
           // 不存在
            Result erro = Result.error("登录失败");
           // 行为现在在过滤器中，没有在控制器中，所以需要手动转化为json格式
            //// 借助工具阿里巴巴-fastjson
            String notLogin = JSONObject.toJSONString(erro);
            response.getWriter().write(notLogin);
            return;
        }
         // 解析令牌，如果解析失败，返回失败（未登录）
        try {
            JwtUtils.parseJwt(jwt);
        }catch (Exception exception){// 解析失败
            exception.printStackTrace();
            Result erro = Result.error("登录失败");
            // 行为现在在过滤器中，没有在控制器中，所以需要手动转化为json格式
            //// 借助工具阿里巴巴-fastjson
            String notLogin = JSONObject.toJSONString(erro);
            response.getWriter().write(notLogin);
            return;
        }
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }
}

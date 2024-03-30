package com.chenbiao.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.chenbiao.pojo.Result;
import com.chenbiao.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginCheckIntorcepter implements HandlerInterceptor {
    @Override //资源运行前执行，返回true 放行，返回false 不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //return HandlerInterceptor.super.preHandle(request, response, handler);
       log.info("HandlerInterceptor拦截器");
        if(true){ // 此段代码用于方便测试其他接口，不用判断登录和token,如果有了登录和token,可以删除
            return true;
        }
        //获取请求URL
        String url = request.getRequestURL().toString();
        //判断请求是否包含登录login;如果包含，说明是登录操作，放行
        if(url.contains("login")){
            return true;
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
            return false;
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
            return false;
        }
        //放行
        return  true;
    }

    @Override //目标资源运行后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override //试图渲染完成后执行，最后执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

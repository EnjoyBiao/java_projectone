package com.chenbiao.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
// @WebFilter(urlPatterns = "/*")  //注释掉后就不再走这个过滤器
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("请求初始化了");
        // Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("请求拦截了");
        System.out.println("放行前的逻辑");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("放行后的逻辑--回来后不回去执行前面");
    }

    @Override
    public void destroy() {
        System.out.println("请求销毁了");
        //Filter.super.destroy();
    }
}

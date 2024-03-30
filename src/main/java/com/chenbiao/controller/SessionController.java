package com.chenbiao.controller;

import com.chenbiao.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class SessionController {
    @GetMapping("/c1")
    public Result cookile1(HttpServletResponse response){
        //设置cookie ,响应cookie
        response.addCookie(new Cookie("username","chenbiao"));
        return Result.success();
    }
    @GetMapping("/c2")
    public Result cookile2(HttpServletRequest request){
        //获取所有的cookie
         Cookie[] cookies = request.getCookies();
        for (Cookie item: cookies) {
            if(item.getName().equals("chenbiao")){
                System.out.println("chenbiao=="+item.getValue());
            }
        }
        return Result.success();
    }
}

package com.chenbiao.controller;

import com.chenbiao.pojo.Emp;
import com.chenbiao.pojo.Result;
import com.chenbiao.service.EmpService;
import com.chenbiao.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;
  @PostMapping("/login")
  public Result login(@RequestBody Emp emp){
      log.info("登录账号{},密码:{}",emp);
      Emp rult = empService.login(emp);
      // 如果登录成功
      if(rult !=null){
          Map<String,Object>  claims = new HashMap<>();
          claims.put("id",rult.getId());
          claims.put("name",rult.getName());
          claims.put("username",rult.getUsername());
          String str = JwtUtils.generationJwt(claims);
          return Result.success(str);
      }
      //登录失败
      return Result.error("用户名或密码错误!");
  }
}

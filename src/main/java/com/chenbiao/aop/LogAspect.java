package com.chenbiao.aop;

import com.alibaba.fastjson2.JSONObject;
import com.chenbiao.mapper.OperateLogMapper;
import com.chenbiao.pojo.OperateLog;
import com.chenbiao.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect//切面类
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.chenbiao.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 操作人id --当前登录ID
          //String jwt = request.getHeader("token"); // 获取ID
         // Claims claims = JwtUtils.parseJwt(jwt);
         // Integer operateUser = (Integer) claims.get("id");
          Integer operateUser = 114;
        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        // 操作类名
        String className = joinPoint.getTarget().getClass().getName();
        // 操作方法名
        String methoudName = joinPoint.getSignature().getName();
        // 操作方法参数
        Object[] arg = joinPoint.getArgs();
        String methoudParams= Arrays.toString(arg);

         Long start = System.currentTimeMillis();
        //调用原始目标方法运行
        Object result = joinPoint.proceed();
         Long end = System.currentTimeMillis();
        //操作耗时
         Long  costTime = end-start;
        //方法返回值
         String returnValue = JSONObject.toJSONString(result);
        // 记录操作日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methoudName,methoudParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

         log.info("记录aop操作日志{}",operateLog);
          // 去部门删除的控制器添加log
        return result;
    }
}

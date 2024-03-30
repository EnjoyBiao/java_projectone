package com.chenbiao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
 用于学习 aop
* **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateLog {
    private Integer id;
    private Integer operateUser;
    private LocalDateTime operateTime;
    private String className;
    private String methoudName;
    private String methoudParams;
    private String returnValue;
    private Long  costTime;
}

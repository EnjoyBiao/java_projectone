package com.chenbiao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 员工实体类
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Short gender;
    private String image;
    private Short job;
    private LocalDate entrydate;
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime  updateTime;
}
/***
 * 测试数据
 *{
 * "image": "http://tlias.itheima.com/1.jpg",
 * "username": "linpingzhi",
 * "name":"林平之",
 * "gender": 1,
 * "job":1,
 * "entrydate":"2015-09-18",
 * "deptId":1
 * }
 *
 */

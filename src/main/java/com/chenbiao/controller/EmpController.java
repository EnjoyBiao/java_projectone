package com.chenbiao.controller;

import com.chenbiao.pojo.Emp;
import com.chenbiao.pojo.PageBean;
import com.chenbiao.pojo.Result;
import com.chenbiao.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/*
* 员工管理的controller
*
*
* */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                     @RequestParam(defaultValue = "10")  Integer pageSise,
                     String name,
                    @RequestParam(defaultValue = "1") Short gender,
                    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                    @DateTimeFormat(pattern = "yyyy-MM-dd")   LocalDate end){
         log.info("请求页码{},每页条数{},{}{}{}",page,pageSise,name,gender,begin,end);
        PageBean pageBean =  empService.page(page,pageSise,name,gender,begin,end);
        return Result.success(pageBean);
    }
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除量为:{}",ids);
        empService.delete(ids);
        return Result.success();
    }
    @PostMapping
    public Result add(@RequestBody Emp emp){
        empService.save(emp);
        return Result.success();
    }
}

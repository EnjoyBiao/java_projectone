package com.chenbiao.controller;

import com.chenbiao.anno.Log;
import com.chenbiao.mapper.EmpMapper;
import com.chenbiao.pojo.Dept;
import com.chenbiao.pojo.Result;
import com.chenbiao.service.DeptService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * 部门管理的controller
 * */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;
    // @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
   public Result list(){
        // 调用service查询部门数据
       List <Dept> deptList = deptService.list();
        log.info("查询全部部门数据");
       return Result.success(deptList);
   }
   @Log
   @DeleteMapping("/{id}")
   public Result delete(@PathVariable  Integer id){
        deptService.deleteDatas(id); // 根据id 删除部门
        log.info("根据id删除：{}",id);
        return Result.success();
   }
   /*
     新增部门
   * **/
    @PostMapping
    public Result add(@RequestBody Dept dept){
         deptService.save(dept);
        log.info("新增的部门：{}",dept);
        return Result.success();
    }
    /**
     * 修改部门
     * **/

    @PutMapping
    public Result modify(@RequestBody Dept dept){
        deptService.modify(dept);
        return Result.success();
    }

}

package com.chenbiao.service;

import com.chenbiao.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;


/*
 * 部门管理的实现类接口
 **/
public interface DeptService {
    // 查询全部数据
    List<Dept> list();
    // g根据id删除
    void deleteDatas(Integer id);
    /*
    * 添加部门
    * */
    void save(Dept dept);
    /**
     * 修改部门
     * */
    void modify(Dept dept);
}

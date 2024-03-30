package com.chenbiao.service.impl;

import com.chenbiao.mapper.DeptMapper;
import com.chenbiao.mapper.EmpMapper;
import com.chenbiao.pojo.Dept;
import com.chenbiao.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class) //spring 事务, rollbackFor = Exception.class,表示出现所有的异常都会出现事务的回滚
    @Override
    public void  deleteDatas(Integer id){

         deptMapper.delete(id);

        // 删除对应的员工信息
        log.info("删除对应的员工{}",id);
        empMapper.deletEmpByDeptId(id);
    }

    @Override
    public void save(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.save(dept);
    }

    @Override
    public void modify(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.modify(dept);
    }
}

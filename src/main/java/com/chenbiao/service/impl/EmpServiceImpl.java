package com.chenbiao.service.impl;

import com.chenbiao.mapper.EmpMapper;
import com.chenbiao.pojo.Emp;
import com.chenbiao.pojo.PageBean;
import com.chenbiao.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
//    @Override
//    public PageBean page(Integer page,Integer pageSize) {
//        Long count =  empMapper.count();
//        Integer start = (page-1) * pageSize;
//        log.info("serve层page={},pagesize={}",page,pageSize);
//        List<Emp> list =  empMapper.page(start,pageSize);
//        PageBean pageBean = new PageBean(count,list);
//        return pageBean;
//    }
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);

        // 执行查询结果
        List<Emp> empList = empMapper.list(name,gender,begin,end);
        Page<Emp> emp = (Page<Emp>)empList;
        // 返回封装对象
        PageBean pageBean = new PageBean(emp.getTotal(),emp.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> list) {
        empMapper.delete(list);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    @Override
    public Emp login(Emp emp) {
        Emp reult =  empMapper.getDataBynameAndPwd(emp);
        return reult;
    }

}

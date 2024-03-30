package com.chenbiao.service;

import com.chenbiao.pojo.Emp;
import com.chenbiao.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/*
* 员工管理的实现类接口
**/
public interface EmpService {
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    //    @Override
    //    public PageBean page(Integer page,Integer pageSize) {
    //        Long count =  empMapper.count();
    //        Integer start = (page-1) * pageSize;
    //        log.info("serve层page={},pagesize={}",page,pageSize);
    //        List<Emp> list =  empMapper.page(start,pageSize);
    //        PageBean pageBean = new PageBean(count,list);
    //        return pageBean;
    //    }
//    PageBean page(Integer page, Integer pageSize);
    void delete(List<Integer> list);
    /**
     * 新增操作
     * */
    void save(Emp emp);

    /**
     * 登录操作
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}

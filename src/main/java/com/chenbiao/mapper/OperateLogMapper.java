package com.chenbiao.mapper;

import com.chenbiao.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperateLogMapper {
    @Insert("insert into operate_log ( operate_user, operate_time, class_name, methoud_name, methoud_params, return_value, cost_time) " +
            "values (#{operateUser},#{operateTime},#{className},#{methoudName},#{methoudParams},#{returnValue},#{costTime})")
    public void insert(OperateLog operateLog);
}

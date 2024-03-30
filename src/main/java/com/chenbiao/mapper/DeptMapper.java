package com.chenbiao.mapper;

import com.chenbiao.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*
      查询全部部门数据
    * **/
    @Select("select * from tb_dept")
    List<Dept> list();
     @Delete("delete from tb_dept where id = #{id} ")
     void delete(Integer id);

     @Insert("insert into tb_dept (name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void save(Dept dept);

     @Update("update tb_dept set name = #{name},create_time = #{createTime},update_time = #{updateTime} where id = #{id}")
    void modify(Dept dept);
}

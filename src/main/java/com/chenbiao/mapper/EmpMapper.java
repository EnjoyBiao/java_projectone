package com.chenbiao.mapper;

import com.chenbiao.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /*
     获取总记录数
    * **/
    @Select("select count(*) from tb_emp")
    Long count();
    /*
     获取分页数据
    * **/
//    @Select("select * from tb_emp limit #{start}, #{pageSize}")
//     List<Emp> page(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
    @Select("select * from tb_emp limit #{arg0}, #{arg1}")
    List<Emp> page(Integer start,  Integer pageSize);

     /**
      * 通过插件获取分页
      * */
    // @Select("select * from tb_emp")
     List<Emp> list(@Param("name") String name,@Param("gender") Short gender,@Param("begin") LocalDate begin,@Param("end") LocalDate end);

     /*
       批量删除
     * **/
     void delete(List<Integer> ids);

     @Insert("insert into tb_emp (username,  name, gender, image, job, entrydate, dept_id, create_time, update_time ) values " +
             "(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})" )
    void save(Emp emp);

     @Select("select * from tb_emp where username=#{username} and password=#{password}")
     Emp getDataBynameAndPwd(Emp emp);
     /*
       根据部门ID，删除对应的员工信息
      */
     @Delete("delete from tb_emp where dept_id = #{deptId} ")
    void deletEmpByDeptId(Integer deptId);
}

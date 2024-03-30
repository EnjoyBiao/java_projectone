package com.chenbiao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String   msg;
    private Object   data;
    public static Result success(){//增删改成功
        return new Result(1,"success",null);
    }
    public static Result success(Object data){// 查询成功
        return new Result(1,"success",data);
    }
    public static Result error(String msg){//失败相应
        return  new Result(0,msg,null);
    }
}

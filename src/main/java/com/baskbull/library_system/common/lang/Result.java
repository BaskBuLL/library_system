package com.baskbull.library_system.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    //200是正常 非200表示异常
    private int code;
    private String msg;
    private Object data;

    public static Result ok(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result ok(){
        return ok(null);
    }

    public static Result ok(Object data){
        return ok(200,"操作成功",data);
    }

    public static Result error(int code,String msg,Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static Result error(String msg,Object data){
        return error(400,msg,data);
    }

    public static Result error(String msg){
        return error(400,msg,null);
    }

}

package com.hyd.base.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    /**
     * 构造函数私有化
     */
    private Response(){}

    /**
     * 请求成功的静态方法
     * @return
     */
    public static Response success(){
        Response response = new Response();
        response.setSuccess(true);
        response.setCode(000000);
        response.setMessage("请求成功");
        return response;
    }

    /**
     * 请求失败的静态方法
     * @return
     */
    public static Response failed(){
        Response response = new Response();
        response.setSuccess(true);
        response.setCode(999999);
        response.setMessage("请求失败");
        return response;
    }

    public Response success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Response message(String message){
        this.setMessage(message);
        return this;
    }

    public Response code(Integer code){
        this.setCode(code);
        return this;
    }

    public Response data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Response data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}

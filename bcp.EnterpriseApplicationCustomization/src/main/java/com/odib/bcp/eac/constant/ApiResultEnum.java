/**
 * Copyright (C), 2015-2017, 北京老司机
 * FileName:ApiResultEnum
 * Author: lizhuo
 * Date: 2017/12/7 11:26
 * Description:json返回结果枚举
 */
package com.odib.bcp.eac.constant;

import com.alibaba.fastjson.JSONObject;
import com.odib.bcp.eac.core.generic.ApiResult;

/**
 * <br>
 * 〈json返回结果枚举〉
 *  @author lizhuo
 * @create 2017/12/7
 * @since 1.0.0
 */
public enum ApiResultEnum {
    /**
     * 请求成功
     */
    SUCCESS("0", "请求成功"),
    /**
     * 请求失败，通常是服务器内部错误
     */
    FAILED("-1", "暂无数据,请稍后再试"),
    /**
     * 密码错误
     */
    LOGIN_100001("100001", "密码错误"),
    /**
     * 用户不存在
     */
    LOGIN_100002("100002", "用户不存在"),
    /**
     * 验证码错误
     */
    LOGIN_100003("100003", "验证码错误"),
    /**
     * token校验失败
     */
    LOGIN_100004("100004", "token校验失败"),
    /**
     * 用户已经停用
     */
    LOGIN_100005("100005", "用户已经停用"),
    ;
    private String code;
    private String message;
    ApiResultEnum(String code, String message){
        this.code = code;
        this.message = message;
    }
    public JSONObject getJSONObject(){
        JSONObject result = new JSONObject();
        result.put("code", Integer.parseInt(code));
        result.put("message", message);
        return result;
    }
    public ApiResult<Object> build(){
        ApiResult<Object> result = new ApiResult<>();
        result.setCode(Integer.parseInt(code));
        result.setMessage(message);
        return result;
    }
    public <T> ApiResult<T> build(T data){
        ApiResult<T> result = new ApiResult<>();
        result.setCode(Integer.parseInt(code));
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    public String getCode(){
        return code;
    }
}

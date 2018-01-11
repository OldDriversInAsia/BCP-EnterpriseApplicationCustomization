/**
 * Copyright (C), 2015-2017, 北京老司机
 * FileName: ApiResult
 * Author: lizhuo
 * Date: 2017/12/6 17:47
 * Description: 返回结果
 */
package com.odib.bcp.eac.generic;

/**
 * <br>
 * 〈返回结果〉
 *
 * @author lizhuo
 * @create 2017/12/6
 * @since 1.0.0
 */
public class ApiResult<T> {
    private Integer code;
    private String message;
    private T data;
    public ApiResult() {

    }

    public ApiResult(T data) {
        this.code = 0;
        this.data = data;
    }

    public static ApiResult success(Object data) {
        return new ApiResult<>(data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

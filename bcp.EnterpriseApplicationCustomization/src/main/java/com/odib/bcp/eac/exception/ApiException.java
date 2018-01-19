/**
 * Copyright (C), 2015-2018,
 * FileName: ApiException
 * Author: lizhuo
 * Date: 2018/1/12 15:12
 * Description: api异常
 */
package com.odib.bcp.eac.exception;

import com.odib.bcp.eac.constant.ApiResultEnum;

/**
 * <br>
 * 〈api异常〉
 *  @author lizhuo
 * @create 2018/1/12
 * @since 1.0.0
 */
public class ApiException extends RuntimeException {
    public ApiException(ApiResultEnum apiResultEnum){
        this.apiResultEnum = apiResultEnum;
    }

    private ApiResultEnum apiResultEnum;

    public ApiResultEnum getApiResultEnum() {
        return apiResultEnum;
    }

    public void setApiResultEnum(ApiResultEnum apiResultEnum) {
        this.apiResultEnum = apiResultEnum;
    }
}

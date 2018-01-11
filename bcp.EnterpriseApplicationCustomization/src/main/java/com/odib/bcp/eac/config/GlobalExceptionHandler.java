/**
 * Copyright (C), 2015-2018,
 * FileName: GlobalExceptionHandler
 * Author: lizhuo
 * Date: 2018/1/10 11:38
 * Description: 统一异常处理
 */
package com.odib.bcp.eac.config;

import com.odib.bcp.eac.constant.ApiResultEnum;
import com.odib.bcp.eac.generic.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <br>
 * 〈统一异常处理〉
 *  @author lizhuo
 * @create 2018/1/10
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler
    @ResponseBody
    public ApiResult<Object> errorHandler(HttpServletRequest req, Exception e) throws Exception{
        log.error("server error:{}", e);
        ApiResult<Object> result = ApiResultEnum.FAILED.build();
        return result;
    }
}

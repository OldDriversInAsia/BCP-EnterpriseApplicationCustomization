/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: ApiController
 * Author: lizhuo
 * Date: 2018/1/9 20:06
 * Description: 接口控制器类
 */
package com.odib.bcp.eac.controller;

import com.odib.bcp.eac.constant.ApiResultEnum;
import com.odib.bcp.eac.generic.ApiResult;
import com.odib.bcp.eac.model.dto.BaseUserDto;
import com.odib.bcp.eac.model.pojo.BaseUser;
import com.odib.bcp.eac.service.BaseUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * <br>
 * 〈接口控制器类〉
 *  @author lizhuo
 * @create 2018/1/9
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @Resource
    private BaseUserService baseUserService;

    @RequestMapping("/test")
    public ApiResult<String> test(String param){
        String data = "Your param is " + param;
        ApiResult<String> result = ApiResultEnum.SUCCESS.build(data);
        return result;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ApiResult<BaseUser> getUser(@PathVariable Integer id){
        BaseUser baseUser = baseUserService.selectById(id);
        ApiResult<BaseUser> result = ApiResultEnum.SUCCESS.build(baseUser);
        return result;
    }

//    public ApiResult<Integer> insertUser(BaseUserDto baseUserDto){
//
//    }
}

/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: BaseUserServiceImpl
 * Author: lizhuo
 * Date: 2018/1/9 19:55
 * Description: 用户服务类
 */
package com.odib.bcp.eac.service.impl;
import java.util.Date;

import com.odib.bcp.eac.dao.BaseUserMapper;
import com.odib.bcp.eac.generic.GenericDao;
import com.odib.bcp.eac.generic.GenericServiceImpl;
import com.odib.bcp.eac.model.dto.BaseUserDto;
import com.odib.bcp.eac.model.pojo.BaseUser;
import com.odib.bcp.eac.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <br>
 * 〈用户服务类〉
 *  @author lizhuo
 * @create 2018/1/9
 * @since 1.0.0
 */
@Service
public class BaseUserServiceImpl extends GenericServiceImpl<BaseUser, Integer> implements BaseUserService{

    @Resource
    BaseUserMapper baseUserMapper;

    @Override
    public GenericDao<BaseUser, Integer> getDao() {
        return baseUserMapper;
    }

    @Override
    public Integer insert(BaseUserDto baseUserDto) {
        String pinyin = baseUserDto.getPinyin();

        BaseUser baseUser = new BaseUser();
        baseUser.setGraduate(baseUserDto.getGraduate());
        baseUser.setDiploma(baseUserDto.getDiploma());
        baseUser.setTelephone(baseUserDto.getTelephone());
        baseUser.setGender(baseUserDto.getGender());
        baseUser.setBirthday(baseUserDto.getBirthday());
        baseUser.setLoginname(baseUserDto.getPinyin());//TODO:
        baseUser.setName(baseUserDto.getName());
        baseUser.setPinyin(pinyin);
        baseUser.setPassword(baseUserDto.getPassword());//TODO:
        baseUser.setSalt("");//TODO:
        baseUser.setStatus(0);//TODO:
        baseUser.setEmail(baseUserDto.getEmail());
        baseUser.setIdentity(baseUserDto.getIdentity());

        return null;
    }
}

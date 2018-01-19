package com.odib.bcp.eac.dao;

import com.odib.bcp.eac.core.generic.GenericDao;
import com.odib.bcp.eac.model.pojo.BaseUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseUserMapper extends GenericDao<BaseUser, Integer> {
    BaseUser selectByPinYin(String pinyin);
    List<BaseUser> selectList();
    BaseUser selectByLoginName(String loginName);
}
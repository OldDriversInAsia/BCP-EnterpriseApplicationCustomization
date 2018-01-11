package com.odib.bcp.eac.dao;

import com.odib.bcp.eac.generic.GenericDao;
import com.odib.bcp.eac.model.pojo.BaseUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseUserMapper extends GenericDao<BaseUser, Integer> {

}
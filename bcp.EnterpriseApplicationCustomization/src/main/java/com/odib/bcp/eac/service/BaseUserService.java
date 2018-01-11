package com.odib.bcp.eac.service;

import com.odib.bcp.eac.generic.GenericService;
import com.odib.bcp.eac.model.dto.BaseUserDto;
import com.odib.bcp.eac.model.pojo.BaseUser;

public interface BaseUserService extends GenericService<BaseUser, Integer> {
    Integer insert(BaseUserDto baseUserDto);
}

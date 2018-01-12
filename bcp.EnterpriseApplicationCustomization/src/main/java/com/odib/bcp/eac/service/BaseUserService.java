package com.odib.bcp.eac.service;

import com.odib.bcp.eac.core.generic.ApiResult;
import com.odib.bcp.eac.core.generic.GenericService;
import com.odib.bcp.eac.model.dto.BaseUserDto;
import com.odib.bcp.eac.model.pojo.BaseUser;
import com.odib.bcp.eac.model.vo.BaseUserVo;

import java.util.List;

public interface BaseUserService extends GenericService<BaseUser, Integer> {
    ApiResult<Integer> insert(BaseUserDto baseUserDto);
    ApiResult<List<BaseUserVo>> selectUserVoList();
    BaseUser selectByPinyin(String pinyin);
    ApiResult<BaseUserVo> login(String loginName, String password);
    BaseUser selectByLoginName(String loginName);
}

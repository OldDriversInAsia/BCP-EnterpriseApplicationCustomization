package com.odib.bcp.eac.service;

import com.odib.bcp.eac.core.generic.GenericService;
import com.odib.bcp.eac.core.orm.mybatis.Page;
import com.odib.bcp.eac.model.dto.BaseUserDto;
import com.odib.bcp.eac.model.pojo.BaseUser;
import com.odib.bcp.eac.model.vo.BaseUserVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface BaseUserService extends GenericService<BaseUser, Integer> {
    Integer insert(BaseUserDto baseUserDto);
    List<BaseUserVo> selectUserVoList(Integer pageNum, Integer pageSize);
    BaseUser selectByPinyin(String pinyin);
    BaseUserVo login(String loginName, String password, HttpServletResponse response);
    BaseUser selectByLoginName(String loginName);
}

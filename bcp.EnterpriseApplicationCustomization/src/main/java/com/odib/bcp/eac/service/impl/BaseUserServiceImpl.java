/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: BaseUserServiceImpl
 * Author: lizhuo
 * Date: 2018/1/9 19:55
 * Description: 用户服务类
 */
package com.odib.bcp.eac.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.odib.bcp.eac.constant.ApiResultEnum;
import com.odib.bcp.eac.constant.CommonValue;
import com.odib.bcp.eac.dao.BaseUserMapper;
import com.odib.bcp.eac.exception.ApiException;
import com.odib.bcp.eac.core.generic.GenericDao;
import com.odib.bcp.eac.core.generic.GenericServiceImpl;
import com.odib.bcp.eac.model.dto.BaseUserDto;
import com.odib.bcp.eac.model.pojo.BaseUser;
import com.odib.bcp.eac.model.vo.BaseUserVo;
import com.odib.bcp.eac.service.BaseUserService;
import com.odib.bcp.eac.service.LoginService;
import com.odib.bcp.eac.util.CookieUtils;
import com.odib.bcp.eac.util.PasswordUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 * 〈用户服务类〉
 *  @author lizhuo
 * @create 2018/1/9
 * @since 1.0.0
 */
@Service
public class BaseUserServiceImpl extends GenericServiceImpl<BaseUser, Integer> implements BaseUserService{

    /**
     * 最大重复次数
     */
    private static final Integer RESET_MAX_TIMES = 3;
    @Value("${odib.bcp.host}")
    private String HOST;

    @Autowired
    BaseUserMapper baseUserMapper;
    @Autowired
    RedisServiceImpl redisService;
    @Autowired
    LoginService loginService;

    @Override
    public GenericDao<BaseUser, Integer> getDao() {
        return baseUserMapper;
    }

    @Override
    public BaseUser selectByPinyin(String pinyin) {
        return baseUserMapper.selectByPinYin(pinyin);
    }

    @Override
    public BaseUserVo login(String loginName, String password, HttpServletResponse response) {
        BaseUserVo baseUserVo;
        BaseUser baseUser = selectByLoginName(loginName);
        if(null == baseUser){
            throw new ApiException(ApiResultEnum.LOGIN_100002);
        }
        if(!BaseUser.Status.OPEN.equals(baseUser.getStatus())){
            throw new ApiException(ApiResultEnum.LOGIN_100005);
        }
        password = PasswordUtil.passwordHash(password, baseUser.getSalt());
        if(baseUser.getPassword().equals(password)){
            baseUserVo = BaseUserVo.covert(baseUser);
        }else{
            throw new ApiException(ApiResultEnum.LOGIN_100001);
        }
        String token = DigestUtils.md5Hex(System.currentTimeMillis() + loginName);
        loginService.setLoginToken(token, baseUser.getIdNo());
        CookieUtils.addCookie(response, CommonValue.LOGIN_TOKEN_COOKIE_KEY, token, "/",CommonValue.LOGIN_TOKEN_COOKIE_TIME_HOUR * 60 * 60);
        return baseUserVo;
    }

    @Override
    public BaseUser selectByLoginName(String loginName) {
        return baseUserMapper.selectByLoginName(loginName);
    }

    @Override
    public Integer insert(BaseUserDto baseUserDto) {
        String pinyin = baseUserDto.getPinyin();
        Integer num = getLoginNameLevel(pinyin);
        String salt = PasswordUtil.createSalt();
        String password = PasswordUtil.passwordHash(baseUserDto.getPassword(), salt);

        String loginName = pinyin;
        if(num != null){
            loginName = loginName + num;
        }

        BaseUser baseUser = baseUserDto.covertPojo();

        baseUser.setLoginname(loginName);
        baseUser.setPassword(password);
        baseUser.setSalt(salt);

        int resetTimes = 0;
        int row = 0;
        while (true){
            try{
                if(resetTimes < RESET_MAX_TIMES){
                    row = insert(baseUser);
                }
                break;
            }catch (Exception e){
                e.printStackTrace();
                if(null == num){
                    num = 0;
                }
                num++;
                loginName = pinyin + num;
                baseUser.setLoginname(loginName);
                resetTimes ++;
            }
        }

        return row;
    }

    @Override
    public Page<BaseUserVo> selectUserVoList(Integer pageNum, Integer pageSize) {
        Page<BaseUser> baseUserList = selectList(pageNum, pageSize);
        Page<BaseUserVo> baseUserVoList = new Page<>();
        baseUserVoList.setPages(baseUserList.getPages());
        baseUserVoList.setEndRow(baseUserList.getEndRow());
        baseUserVoList.setPageNum(baseUserList.getPageNum());
        baseUserVoList.setPageSize(baseUserList.getPageSize());
        baseUserVoList.setStartRow(baseUserList.getStartRow());
        baseUserVoList.setTotal(baseUserList.getTotal());
        baseUserVoList.setReasonable(baseUserList.getReasonable());
        baseUserVoList.setPageSizeZero(baseUserList.getPageSizeZero());
        baseUserVoList.setOrderBy(baseUserList.getOrderBy());
        baseUserVoList.setOrderByOnly(baseUserList.isOrderByOnly());
        baseUserVoList.setCount(baseUserList.isCount());
        baseUserVoList.setCountColumn(baseUserList.getCountColumn());

        baseUserList.forEach(baseUser -> baseUserVoList.add(BaseUserVo.covert(baseUser)));
        return baseUserVoList;
    }


    private Page<BaseUser> selectList(Integer pageNum, Integer pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> baseUserMapper.selectList());
    }

    private Integer getLoginNameLevel(String pinyin){
        Integer num = null;
        BaseUser baseUserExist = baseUserMapper.selectByPinYin(pinyin);
        if(baseUserExist != null){
            String loginNameExist = baseUserExist.getLoginname();
            String numStr = loginNameExist.substring(pinyin.length(), loginNameExist.length());
            if(StringUtils.isEmpty(numStr)) {
                num = 0;
            }else{
                num = Integer.valueOf(numStr);
            }
            num++;
        }
        return num;
    }
}

/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: BaseUserServiceImpl
 * Author: lizhuo
 * Date: 2018/1/9 19:55
 * Description: 用户服务类
 */
package com.odib.bcp.eac.service.impl;

import com.odib.bcp.eac.constant.ApiResultEnum;
import com.odib.bcp.eac.dao.BaseUserMapper;
import com.odib.bcp.eac.exception.ApiException;
import com.odib.bcp.eac.core.generic.ApiResult;
import com.odib.bcp.eac.core.generic.GenericDao;
import com.odib.bcp.eac.core.generic.GenericServiceImpl;
import com.odib.bcp.eac.model.dto.BaseUserDto;
import com.odib.bcp.eac.model.pojo.BaseUser;
import com.odib.bcp.eac.model.vo.BaseUserVo;
import com.odib.bcp.eac.service.BaseUserService;
import com.odib.bcp.eac.util.PasswordUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
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

    private static final Integer RESET_MAX_TIMES = 3;

    @Resource
    BaseUserMapper baseUserMapper;

    @Override
    public GenericDao<BaseUser, Integer> getDao() {
        return baseUserMapper;
    }

    @Override
    public BaseUser selectByPinyin(String pinyin) {
        return baseUserMapper.selectByPinYin(pinyin);
    }

    @Override
    public ApiResult<BaseUserVo> login(String loginName, String password) {
        ApiResult<BaseUserVo> result;
        BaseUser baseUser = selectByLoginName(loginName);
        if(null == baseUser){
            throw new ApiException(ApiResultEnum.LOGIN_100002);
        }
        if(!BaseUser.Status.OPEN.equals(baseUser.getStatus())){
            throw new ApiException(ApiResultEnum.LOGIN_100005);
        }
        password = PasswordUtil.passwordHash(password, baseUser.getSalt());
        if(baseUser.getPassword().equals(password)){
            result = ApiResultEnum.SUCCESS.build(convertVo(baseUser));
        }else{
            throw new ApiException(ApiResultEnum.LOGIN_100001);
        }
        return result;
    }

    @Override
    public BaseUser selectByLoginName(String loginName) {
        return baseUserMapper.selectByLoginName(loginName);
    }

    @Override
    public ApiResult<Integer> insert(BaseUserDto baseUserDto) {
        String pinyin = baseUserDto.getPinyin();
        Integer num = getLoginNameLevel(pinyin);
        String salt = PasswordUtil.createSalt();
        String password =PasswordUtil.passwordHash(baseUserDto.getPassword(), salt);

        String loginName = pinyin;
        if(num != null){
            loginName = loginName + num;
        }

        BaseUser baseUser = new BaseUser();
        baseUser.setGraduate(baseUserDto.getGraduate());
        baseUser.setDiploma(baseUserDto.getDiploma());
        baseUser.setTelephone(baseUserDto.getTelephone());
        baseUser.setGender(baseUserDto.getGender());
        baseUser.setBirthday(new Date(baseUserDto.getBirthday()));
        baseUser.setLoginname(loginName);
        baseUser.setName(baseUserDto.getName());
        baseUser.setPinyin(pinyin);
        baseUser.setPassword(password);
        baseUser.setSalt(salt);
        baseUser.setEmail(baseUserDto.getEmail());
        baseUser.setIdentity(baseUserDto.getIdentity());

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

        return ApiResultEnum.SUCCESS.build(row);
    }

    @Override
    public ApiResult<List<BaseUserVo>> selectUserVoList() {
        List<BaseUser> baseUserList = selectList();
        List<BaseUserVo> baseUserVoList = new ArrayList<>();
        for(BaseUser baseUser : baseUserList){
            baseUserVoList.add(convertVo(baseUser));
        }
        return ApiResultEnum.SUCCESS.build(baseUserVoList);
    }

    @Override
    public List<BaseUser> selectList() {
        return baseUserMapper.selectList();
    }

    private Integer getLoginNameLevel(String pinyin){
        Integer num = null;
        BaseUser baseUserExist = baseUserMapper.selectByPinYin(pinyin);
        if(baseUserExist != null){
            String loginnameExist = baseUserExist.getLoginname();
            String numStr = loginnameExist.substring(pinyin.length(), loginnameExist.length());
            if("".equals(numStr)) {
                num = 0;
            }else{
                num = Integer.valueOf(numStr);
            }
            num++;
        }
        return num;
    }
    private BaseUserVo convertVo(BaseUser baseUser){
        BaseUserVo baseUserVo = new BaseUserVo();
        baseUserVo.setIdNo(baseUser.getIdNo());
        baseUserVo.setGraduate(baseUser.getGraduate());
        baseUserVo.setDiploma(baseUser.getDiploma());
        baseUserVo.setTelephone(baseUser.getTelephone());
        baseUserVo.setGender(baseUser.getGender());
        baseUserVo.setBirthday(baseUser.getBirthday());
        baseUserVo.setLoginname(baseUser.getLoginname());
        baseUserVo.setName(baseUser.getName());
        baseUserVo.setPinyin(baseUser.getPinyin());
        baseUserVo.setStatus(baseUser.getStatus());
        baseUserVo.setEmail(baseUser.getEmail());
        baseUserVo.setIdentity(baseUser.getIdentity());

        return baseUserVo;
    }
}

/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: BaseUserDto
 * Author: lizhuo
 * Date: 2018/1/10 12:08
 * Description: 用户信息前端接收类
 */
package com.odib.bcp.eac.model.dto;

import java.util.Date;

/**
 * <br>
 * 〈用户信息前端接收类〉
 *  @author lizhuo
 * @create 2018/1/10
 * @since 1.0.0
 */
public class BaseUserDto {
    /**
     * 毕业学校
     */
    private String graduate;

    /**
     * 0:中专及以下;1:高中;2:大专;3:本科;4:硕士;5:博士
     */
    private Integer diploma;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 0:男;1:女
     */
    private Integer gender;

    /**
     * 出生时间
     */
    private Date birthday;

    /**
     * 姓名
     */
    private String name;

    /**
     * 姓名拼音
     */
    private String pinyin;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证号
     */
    private String identity;

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public Integer getDiploma() {
        return diploma;
    }

    public void setDiploma(Integer diploma) {
        this.diploma = diploma;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}

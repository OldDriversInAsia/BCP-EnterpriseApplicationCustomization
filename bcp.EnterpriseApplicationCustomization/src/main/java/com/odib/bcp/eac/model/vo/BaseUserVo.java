/**
 * Copyright (C), 2015-2018, 北京老司机
 * FileName: BaseUserVo
 * Author: lizhuo
 * Date: 2018/1/12 14:44
 * Description: 基础用户展示对象
 */
package com.odib.bcp.eac.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * <br>
 * 〈基础用户展示对象〉
 *  @author lizhuo
 * @create 2018/1/12
 * @since 1.0.0
 */
public class BaseUserVo {
    @JsonProperty("id_no")
    private Integer idNo;
    private String graduate;
    private Integer diploma;
    private String telephone;
    private Integer gender;
    private Date birthday;
    private String loginname;
    private String name;
    private String pinyin;
    private Integer status;
    private String email;
    private String identity;

    public Integer getIdNo() {
        return idNo;
    }

    public void setIdNo(Integer idNo) {
        this.idNo = idNo;
    }

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

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

package com.odib.bcp.eac.model.pojo;

import java.util.Date;

public class BaseUser {
    /**
     * 编号(自增)
     */
    private Integer idNo;

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
     * 登录名称
     */
    private String loginname;

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
     * 盐
     */
    private String salt;

    /**
     * 1:启用;2:停用;3:删除;
     */
    private Integer status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证号
     */
    private String identity;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    public interface Status{
        Integer OPEN = 1;
        Integer BLOCK = 2;
        Integer DELETE = 3;
    }
    /**
     * 编号(自增)
     * @return id_no 编号(自增)
     */
    public Integer getIdNo() {
        return idNo;
    }

    /**
     * 编号(自增)
     * @param idNo 编号(自增)
     */
    public void setIdNo(Integer idNo) {
        this.idNo = idNo;
    }

    /**
     * 毕业学校
     * @return graduate 毕业学校
     */
    public String getGraduate() {
        return graduate;
    }

    /**
     * 毕业学校
     * @param graduate 毕业学校
     */
    public void setGraduate(String graduate) {
        this.graduate = graduate == null ? null : graduate.trim();
    }

    /**
     * 0:中专及以下;1:高中;2:大专;3:本科;4:硕士;5:博士
     * @return diploma 0:中专及以下;1:高中;2:大专;3:本科;4:硕士;5:博士
     */
    public Integer getDiploma() {
        return diploma;
    }

    /**
     * 0:中专及以下;1:高中;2:大专;3:本科;4:硕士;5:博士
     * @param diploma 0:中专及以下;1:高中;2:大专;3:本科;4:硕士;5:博士
     */
    public void setDiploma(Integer diploma) {
        this.diploma = diploma;
    }

    /**
     * 电话
     * @return telephone 电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 电话
     * @param telephone 电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 0:男;1:女
     * @return gender 0:男;1:女
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 0:男;1:女
     * @param gender 0:男;1:女
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 出生时间
     * @return birthday 出生时间
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 出生时间
     * @param birthday 出生时间
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 登录名称
     * @return loginname 登录名称
     */
    public String getLoginname() {
        return loginname;
    }

    /**
     * 登录名称
     * @param loginname 登录名称
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    /**
     * 姓名
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 姓名拼音
     * @return pinyin 姓名拼音
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 姓名拼音
     * @param pinyin 姓名拼音
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 盐
     * @return salt 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 盐
     * @param salt 盐
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 1:启用;2:停用;3:删除;
     * @return status 1:启用;2:停用;3:删除;
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 1:启用;2:停用;3:删除;
     * @param status 1:启用;2:停用;3:删除;
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 邮箱
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 身份证号
     * @return identity 身份证号
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 身份证号
     * @param identity 身份证号
     */
    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    /**
     * 创建时间
     * @return created_at 创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 创建时间
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 更新时间
     * @return updated_at 更新时间
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 更新时间
     * @param updatedAt 更新时间
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
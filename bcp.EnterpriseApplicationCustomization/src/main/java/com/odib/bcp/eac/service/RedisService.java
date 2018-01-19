package com.odib.bcp.eac.service;

public interface RedisService {
    void setLoginToken(String token, Integer idNo);
    Integer getLoginToken(String token);
}

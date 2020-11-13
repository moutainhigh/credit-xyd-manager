package com.winchampion.credit.common.interfaces.country.res;

public class GetTokenResult {

    //token
    private String token;

    //过期时间：时间戳
    private long expiresTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }
}

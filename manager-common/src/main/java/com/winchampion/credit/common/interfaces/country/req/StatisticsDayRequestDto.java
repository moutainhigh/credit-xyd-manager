package com.winchampion.credit.common.interfaces.country.req;

import java.util.List;

/**
 * 请求参数封装类
 */
public class StatisticsDayRequestDto {

    private String token;

    //请求数据
    private List<StatisticsDayRequest> requestData;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<StatisticsDayRequest> getRequestData() {
        return requestData;
    }

    public void setRequestData(List<StatisticsDayRequest> requestData) {
        this.requestData = requestData;
    }

    @Override
    public String toString() {
        return "RequestDto [requestData=" + requestData + ",token=" + token + "]";
    }
}

package com.winchampion.credit.common.interfaces.country.req;

import java.util.List;

/**
 * 请求参数封装类
 */
public class StatisticsAllRequestDto {

    private String token;

    //请求数据
    private List<StatisticsAllRequest> requestData;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<StatisticsAllRequest> getRequestData() {
        return requestData;
    }

    public void setRequestData(List<StatisticsAllRequest> requestData) {
        this.requestData = requestData;
    }

    @Override
    public String toString() {
        return "RequestDto [requestData=" + requestData + ",token=" + token + "]";
    }
}

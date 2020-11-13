package com.winchampion.credit.common.interfaces.country.req;

/**
 * 请求参数封装类
 */
public class RequestDto<T> {

    /**
     * 请求数据
     */
    private T requestData;

    public T getData() {
        return requestData;
    }

    public void setData(T requestData) {
        this.requestData = requestData;
    }

    @Override
    public String toString() {
        return "RequestDto [requestData=" + requestData + "]";
    }
}

package com.winchampion.credit.common.interfaces.country.res;

public class ResultVo<T> {

    //成功标志： 接口返回是否成功标志，true 成功，false 失败
    private boolean success;

    //错误代码：错误代码，正常情况下返回 0，表示没有错误
    private String errorCode;

    //错误信息：具体的错误信息
    private String errorMessage;

    //时间戳：当前时间的时间戳
    private long timestamp;

    //返回的结果对象：具体的结果信息，里面包含一个 json 对象
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ResultVo [success=" + success + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
                + ", timestamp=" + timestamp + ", data=" + data + "]";
    }
}

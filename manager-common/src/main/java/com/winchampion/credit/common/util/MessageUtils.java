package com.winchampion.credit.common.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.winchampion.credit.common.message.response.SmsVariableResponse;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by liwei on 2018/5/16.
 */
public class MessageUtils {

    private static final String charset = "UTF-8";
    private static String account = "18116032165";
    private static String pwd = "c5d8fa5755abe17108045b948";
//    private static String msg = "【卫诚房贷通】尊敬的{$var}客户，您申请的房屋（{$var}）估值为{$var}万元，具体详情请登录系统查看。";
    private static String msg = "今日上报国家平台数据自动调用程序失败，请及时处理";
    
    private static String signId = "236725";
    
    private static String smsVariableRequestUrl = "http://api.feige.ee/SmsService/Send";

    public static String sendSmsByPost(String code, String phone) {

        try {
           Map<String, String> param = Maps.newHashMap();
           param.put("Account", account);
           param.put("Pwd", pwd);
           param.put("Content", String.format(msg, code));
           param.put("Mobile", phone);
           param.put("SignId", signId);
           
           String reslut = HttpClientUtil.post(smsVariableRequestUrl, param, charset);
           return reslut;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

//        String params = "13918139731,602608";
        String response = sendSmsByPost( "602608","15201940014");
        System.out.println("response after request result is : " + response);
        SmsVariableResponse smsVariableResponse = JSON.parseObject(response, SmsVariableResponse.class);
        System.out.println("response  toString is : " + smsVariableResponse);

    }
}

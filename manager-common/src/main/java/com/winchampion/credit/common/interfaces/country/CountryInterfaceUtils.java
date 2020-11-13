package com.winchampion.credit.common.interfaces.country;

import com.google.gson.reflect.TypeToken;
import com.winchampion.credit.common.interfaces.country.req.*;
import com.winchampion.credit.common.interfaces.country.res.GetTokenResult;
import com.winchampion.credit.common.interfaces.country.res.PullPublicKeyResult;
import com.winchampion.credit.common.interfaces.country.res.ResultVo;
import com.winchampion.credit.common.util.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CountryInterfaceUtils {

    private Logger logger = LoggerFactory.getLogger(CountryInterfaceUtils.class);

    @Value("${Country.appId}")
    private String appId;

    @Value("${Country.appKey}")
    private String appKey;

    @Value("${Country.appSecret}")
    private String appSecret;

    @Value("${Country.pullPublicKeyUrl}")
    private String pullPublicKeyUrl;

    @Value("${Country.getTokenUrl}")
    private String getTokenUrl;

    @Value("${Country.statisticsAllUrl}")
    private String statisticsAllUrl;

    @Value("${Country.statisticsDayUrl}")
    private String statisticsDayUrl;

    /**
     * 地方平台公钥获取接口（当前为明文数据，该接口暂无法调通）
     * 获取服务平台的公钥，用于客户端请求加密（目前没加密可先不用）
     * 接口方式：Restful；接口地址：/api/pullPublicKey；请求方式：POST
     * 参数内容类型：application/json
     */
    private ResultVo<PullPublicKeyResult> pullPublicKey(String appId){

        ResultVo<PullPublicKeyResult> resultVo = new ResultVo<>();
        try {
            URL targetUrl = new URL(pullPublicKeyUrl);

            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");

            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("appId", appId);

            RequestDto<Map<String, String>> requestDto = new RequestDto<>();
            requestDto.setData(paramMap);

            String input = GsonUtil.gson.toJson(requestDto);
            logger.debug("国家平台公钥获取接口参数：" + input);

            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                    (httpConnection.getInputStream())));
            String output;
            while ((output = responseBuffer.readLine()) != null) {
                logger.debug("国家平台公钥获取接口返回数据：" + output);
                resultVo = GsonUtil.gson.fromJson(output, new TypeToken<ResultVo<PullPublicKeyResult>>() {}.getType());
            }
            httpConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultVo;
    }

    /**
     * 获取令牌接口
     * 接口方式：Restful；接口地址：/api/getToken；请求方式：POST
     * 参数内容类型：application/json
     */
    public ResultVo<GetTokenResult> getToken(){
        ResultVo<GetTokenResult> resultVo = new ResultVo<>();
        try {
            URL targetUrl = new URL(getTokenUrl);

            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");

            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("appId", appId);
            paramMap.put("appKey", appKey);
            paramMap.put("appSecret", appSecret);

            RequestDto<Map> requestDto = new RequestDto<>();
            requestDto.setData(paramMap);

            String input = GsonUtil.gson.toJson(requestDto);
            logger.debug("国家平台获取令牌接口参数：" + input);

            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
            String output;
            while ((output = responseBuffer.readLine()) != null) {
                logger.debug("国家平台获取令牌接口返回数据：" + output);
                resultVo = GsonUtil.gson.fromJson(output, new TypeToken<ResultVo<GetTokenResult>>() {}.getType());
            }
            httpConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultVo;
    }

    /**
     * 融资统计信息回传接口（全量）
     * 地方平台回传融资统计信息数据（全量）
     * 接口方式：Restful；接口地址：/api/qydkxx/financingStatisticsByAll；请求方式：POST
     * 参数内容类型：application/json
     */
    public ResultVo<Object> financingStatisticsByAll(List<StatisticsAllRequest> statisticsAllRequest, String token){
        ResultVo<Object> resultVo = new ResultVo<>();
        try {
            URL targetUrl = new URL(statisticsAllUrl);

            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");

            StatisticsAllRequestDto requestDto = new StatisticsAllRequestDto();
            requestDto.setRequestData(statisticsAllRequest);
            requestDto.setToken(token);

            String input = GsonUtil.gson.toJson(requestDto);
            logger.debug("国家平台回传全量统计信息接口参数：" + input);

            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
            String output;
            while ((output = responseBuffer.readLine()) != null) {
                logger.debug("国家平台回传全量统计信息接口返回数据：" + output);
                resultVo = GsonUtil.gson.fromJson(output, new TypeToken<ResultVo<Object>>() {}.getType());
            }
            httpConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultVo;
    }

    /**
     * 融资统计信息回传接口（按日增量）
     * 地方平台回传融资统计信息数据（按日增量）
     * 接口方式：Restful；接口地址：/api/qydkxx/financingStatisticsByDay；请求方式：POST
     * 参数内容类型：application/json
     */
    public ResultVo<Object> financingStatisticsByDay(List<StatisticsDayRequest> statisticsDayRequest, String token){
        ResultVo<Object> resultVo = new ResultVo<>();
        try {
            URL targetUrl = new URL(statisticsDayUrl);

            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");

            StatisticsDayRequestDto requestDto = new StatisticsDayRequestDto();
            requestDto.setRequestData(statisticsDayRequest);
            requestDto.setToken(token);

            String input = GsonUtil.gson.toJson(requestDto);
            logger.debug("国家平台回传日增量统计信息接口参数：" + input);

            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
            String output;
            while ((output = responseBuffer.readLine()) != null) {
                logger.debug("国家平台回传日增量统计信息接口返回数据：" + output);
                resultVo = GsonUtil.gson.fromJson(output, new TypeToken<ResultVo<Object>>() {}.getType());
            }
            httpConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultVo;
    }

    public static void main(String[] args) {
        CountryInterfaceUtils interfaceUtils = new CountryInterfaceUtils();
        //{"success":true,"errorCode":"","errorMessage":"","timestamp":1603785044530,"data":{"token":"qdB52GpFWeHeKg261Zu9uDNJfW9muQ0UuEM3AMouveI0fieTA/A4SqkjdNWLNZXGkLSLRkpD1bBlTDK7PXb3KEvsLGxugDZ96+nhNmhFDi2HnSUWj4kRQU6jgKElsm0FQQX5+hN13th8QZSxRGX89L8516K/5P1qP7et6cOf75UKKiFNm06SoOrGBVYq5FPfCZ/5JGo4FT52OjhUKNl17Q==","expiresTime":1603792244528}}
        //{"success":true,"errorCode":"","errorMessage":"","timestamp":1603785044530,"data":{"token":"qdB52GpFWeHeKg261Zu9uDNJfW9muQ0UuEM3AMouveI0fieTA/A4SqkjdNWLNZXGkLSLRkpD1bBlTDK7PXb3KEvsLGxugDZ96+nhNmhFDi2HnSUWj4kRQU6jgKElsm0FQQX5+hN13th8QZSxRGX89L8516K/5P1qP7et6cOf75UKKiFNm06SoOrGBVYq5FPfCZ/5JGo4FT52OjhUKNl17Q==","expiresTime":1603792244528}}
        //{"success":true,"errorCode":"","errorMessage":"","timestamp":1603786293611,"data":{"token":"qdB52GpFWeHeKg261Zu9uDNJfW9muQ0UuEM3AMouveI0fieTA/A4SqkjdNWLNZXGkLSLRkpD1bBlTDK7PXb3KEvsLGxugDZ96+nhNmhFDi2HnSUWj4kRQU6jgKElsm0FQQX5+hN13th8QZSxRGX89L8516K/5P1qP7et6cOf75UKKiFNm06SoOrGBVYq5FPffUw3+IDFcJv7Jn7d2hOJhg==","expiresTime":1603793493609}}

        ResultVo<GetTokenResult> tokenResultResultVo = interfaceUtils.getToken();
        if(tokenResultResultVo.getSuccess()){
            String token = tokenResultResultVo.getData().getToken();
            interfaceUtils.testAll(interfaceUtils, token);
            interfaceUtils.testDay(interfaceUtils, token);
        }
    }

    private void testAll(CountryInterfaceUtils interfaceUtils, String token){
        StatisticsAllRequest statisticsAllRequest = new StatisticsAllRequest();
        statisticsAllRequest.setSjlyId("123");
        statisticsAllRequest.setRegisteredEnterpriseNum(0);
        statisticsAllRequest.setCreditEnterpriseNum(0);
        statisticsAllRequest.setLoanEnterpriseNum(0);
        statisticsAllRequest.setCreditLoanEnterpriseNum(0);
        statisticsAllRequest.setFinancingNeedsEnterpriseNum(0);
        statisticsAllRequest.setCreditAmount(new BigDecimal(0));
        statisticsAllRequest.setCreditNum(0);
        statisticsAllRequest.setLoanAmount(new BigDecimal(0));
        statisticsAllRequest.setLoanNum(0);
        statisticsAllRequest.setCreditLoanAmount(new BigDecimal(0));
        statisticsAllRequest.setCreditLoanNum(0);
        statisticsAllRequest.setFinancingNeedsAmount(new BigDecimal(0));
        statisticsAllRequest.setFinancingNeedsNum(0);
        statisticsAllRequest.setDockingNum(0);
        statisticsAllRequest.setOverdueNum(0);
        statisticsAllRequest.setAverageLendingRate(new BigDecimal(0));
        statisticsAllRequest.setFullBusinessProcessAveragePeriod(0);
        statisticsAllRequest.setCreditAveragePeriod(0);
        statisticsAllRequest.setLoanAveragePeriod(0);
        statisticsAllRequest.setCreditLoanAveragePeriod(0);
        statisticsAllRequest.setNearlyYearLoanAmount(new BigDecimal(0));
        statisticsAllRequest.setNearlyYearLoanNum(0);
        statisticsAllRequest.setNearlyYearLoanEnterpriseNum(0);
        statisticsAllRequest.setNearlyYearCreditLoanAmount(new BigDecimal(0));
        statisticsAllRequest.setNearlyYearCreditLoanNum(0);
        statisticsAllRequest.setNearlyYearCreditLoanEnterpriseNum(0);
        statisticsAllRequest.setNearlyYearFinancingNeedsAmount(new BigDecimal(0));
        statisticsAllRequest.setNearlyYearFinancingNeedsNum(0);
        statisticsAllRequest.setSettledInFinancialInstitutionNum(0);
        statisticsAllRequest.setFinancialProductsNum(0);
        statisticsAllRequest.setStatisticsBeginTime("2019-09-24 00:00:00");
        statisticsAllRequest.setStatisticsEndTime("2020-10-28 15:05:20");

        List<StatisticsAllRequest> requestList = new ArrayList<>();
        requestList.add(statisticsAllRequest);

        ResultVo<Object> resultVo = interfaceUtils.financingStatisticsByAll(requestList, token);
        System.out.println();
    }

    private void testDay(CountryInterfaceUtils interfaceUtils, String token){
        StatisticsDayRequest statisticsDayRequest = new StatisticsDayRequest();
        statisticsDayRequest.setSjlyId("456");
        statisticsDayRequest.setRegisteredEnterpriseNum(0);
        statisticsDayRequest.setCreditEnterpriseNum(0);
        statisticsDayRequest.setLoanEnterpriseNum(0);
        statisticsDayRequest.setCreditLoanEnterpriseNum(0);
        statisticsDayRequest.setFinancingNeedsEnterpriseNum(0);
        statisticsDayRequest.setCreditAmount(new BigDecimal(0));
        statisticsDayRequest.setCreditNum(0);
        statisticsDayRequest.setLoanAmount(new BigDecimal(0));
        statisticsDayRequest.setLoanNum(0);
        statisticsDayRequest.setCreditLoanAmount(new BigDecimal(0));
        statisticsDayRequest.setCreditLoanNum(0);
        statisticsDayRequest.setFinancingNeedsAmount(new BigDecimal(0));
        statisticsDayRequest.setFinancingNeedsNum(0);

//        List<IndustryStatistics> industryStatisticsList = new ArrayList<>();
//        statisticsDayRequest.setEnterpriseIndustryStatistics(industryStatisticsList);

//        List<ScaleStatistics> scaleStatisticsList = new ArrayList<>();
//        statisticsDayRequest.setEnterpriseScaleStatistics(scaleStatisticsList);

//        List<RevenueIntervalStatistics> revenueIntervalStatisticsList = new ArrayList<>();
//        statisticsDayRequest.setEnterpriseRevenueIntervalStatistics(revenueIntervalStatisticsList);

//        List<TaxIntervalStatistics> taxIntervalStatisticsList = new ArrayList<>();
//        statisticsDayRequest.setEnterpriseTaxIntervalStatistics(taxIntervalStatisticsList);

//        List<LoanAmountRangeStatistics> loanAmountRangeStatisticsList = new ArrayList<>();
//        statisticsDayRequest.setSuccessLoanAmountRangeStatistics(loanAmountRangeStatisticsList);

//        List<LoanAmountTypeStatistics> loanAmountTypeStatisticsList = new ArrayList<>();
//        statisticsDayRequest.setLoanAmountTypeStatistics(loanAmountTypeStatisticsList);

//        List<FinancialInstitutionStatistics> financialInstitutionStatisticsList = new ArrayList<>();
//        statisticsDayRequest.setFinancialInstitutionStatistics(financialInstitutionStatisticsList);

//        List<NeedsAmountRangeStatistics> needsAmountRangeStatisticsList = new ArrayList<>();
//        statisticsDayRequest.setNeedsAmountRangeStatistics(needsAmountRangeStatisticsList);

        statisticsDayRequest.setSettledInFinancialInstitutionNum(0);

//        List<SettledInFinancialInstitutionStatistics> statisticsList = new ArrayList<>();
//        statisticsDayRequest.setSettledInFinancialInstitutionStatistics(statisticsList);

        statisticsDayRequest.setFinancialProductsNum(0);

        List<FinancialProductsStatistics> financialProductsStatisticsList = new ArrayList<>();
        statisticsDayRequest.setFinancialProductsStatistics(financialProductsStatisticsList);
        statisticsDayRequest.setStatisticsBeginTime("2020-10-27 01:00:00");
        statisticsDayRequest.setStatisticsEndTime("2020-10-28 00:59:59");

        List<StatisticsDayRequest> requestList = new ArrayList<>();
        requestList.add(statisticsDayRequest);

        ResultVo<Object> resultVo = interfaceUtils.financingStatisticsByDay(requestList, token);
        System.out.println();
    }

//        /* 调用流程 */
//        //1.获取公钥，用于对推送数据加密
//        String pushKey = interfaceUtils.pullPublicKey("");
//        //2.收集用户上报的数据
//        String data1 = null;
//        //3.1数据摘要及签名
//        //3.1.1生成数据集摘要
//        String digest = SM3(data1);
//        //3.1.2对摘要签名
//        String signature = SM2(pushKey, digest);
//        //3.2数据集加密处理
//        //3.2.1创建对称加密密钥
//        String key1 = "";//128位随机码
//        //3.2.2加密原始数据（对称加密）
//        String data2 = SM4(key1, data1);
//        //3.2.3加密密钥（非对称加密）
//        String key2 = SM2(pushKey, key1);
//        //4.推送以下三种数据
//        //data2、key2、signature
//        //5.数据解密及处理
//        //5.1解密密钥
//        String priKey = "";
//        key1 = SM2(priKey, key2);
//        //5.2解密数据
//        data1 = SM4(key1, data2);
//        //5.3生成解密数据摘要
//        String digest1 = SM3(data1);
//        //5.4签名解密
//        digest = SM2(priKey, signature);
//        //5.5验证digest是否与digest1一致，则可检测数据是否被篡改
}

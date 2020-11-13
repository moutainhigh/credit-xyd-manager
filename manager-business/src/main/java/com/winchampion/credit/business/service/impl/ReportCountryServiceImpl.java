package com.winchampion.credit.business.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.winchampion.credit.business.Enum.PushDateEnums;
import com.winchampion.credit.business.dao.ReportCountryDao;
import com.winchampion.credit.business.domain.*;
import com.winchampion.credit.business.service.ReportCountryService;
import com.winchampion.credit.business.service.SmsLogService;
import com.winchampion.credit.business.vo.IncrementVo;
import com.winchampion.credit.business.domain.IndexShowDo;
import com.winchampion.credit.common.interfaces.country.CountryInterfaceUtils;
import com.winchampion.credit.common.interfaces.country.req.StatisticsAllRequest;
import com.winchampion.credit.common.interfaces.country.req.StatisticsDayRequest;
import com.winchampion.credit.common.interfaces.country.res.GetTokenResult;
import com.winchampion.credit.common.interfaces.country.res.ResultVo;
import com.winchampion.credit.common.util.BeanUtil;
import com.winchampion.credit.common.util.DateUtils;
import com.winchampion.credit.common.util.IdGen;
import com.winchampion.credit.common.util.StringUtils;
import com.winchampion.credit.user.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/28
 * @description:上报国家平台服务类实现类
 */
@Service
public class ReportCountryServiceImpl implements ReportCountryService {
    private static Logger logger = LoggerFactory.getLogger(ReportCountryServiceImpl.class);
    @Autowired
    private ReportCountryDao reportCountryDao;
    @Autowired
    private SmsLogService smsLogService;
    @Autowired
    private CountryInterfaceUtils countryInterfaceUtils;
    private int quanResult;

    /**
     * 从上传记录表中获取最新长传的 全量记录信息
     *
     * @return
     */
    @Override
    public UploadQuantityDO getNewestQuantity() {
        return reportCountryDao.getNewestQuantity();
    }

    /**
     * 从上传记录表中获取最新长传的 增量记录信息
     *
     * @return
     */
    @Override
    public UploadIncrementDO getNewestIncrement() {
        return reportCountryDao.getNewestIncrement();
    }

    /**
     * 查询上传上报 记录
     *
     * @return
     */
    @Override
    public List<UploadReportDo> getAllReport(Map<String, Object> map) {
        // TODO 加条件查询
        List<UploadReportDo> lists = reportCountryDao.getAllReport(map);
        return lists;
    }

    /**
     * 查询上传上报记录数量
     *
     * @return
     */
    @Override
    public int count(Map<String, Object> map) {
        // TODO 加条件查询数量
        int total = reportCountryDao.count(map);
        return total;
    }

    /**
     * 插入上传记录，并把记录编号和增量，全量数据插入到数据库中
     *
     * @return
     */
    @Override
    @Transactional
    public int insertUploadRecord(List<IncrementDo> incrementList, List<QuantityDo> quantityList,
                                  List<IndexShowDo> indexShowList,String recordId) {
       /* UploadDo uploadDo = new UploadDo();
        String recordId = IdGen.uuid();
        uploadDo.setId(recordId);
        uploadDo.setDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        uploadDo.setFilePath(filePath);
        // 版本号：YYYYMMddHHmmss+三位流水号
        String version = DateUtils.getSysDateAndTime(DateUtils.DATE_TIME_PATTERN_1) +
                StringUtils.getRandomNumberCode(3);
        uploadDo.setVersion(version);
        uploadDo.setUserId(UserUtils.getUserId());*/


        // 插入增量数据和全量数据
        // 插入Excel 增量sheet 数据
        //UploadIncrementDO incrementDO = convertDateIncrement(incrementList);
        UploadIncrementDO incrementDO = getIncrementDo(incrementList);

        logger.info("recordId 为：" + recordId);
        incrementDO.setId(IdGen.uuid());
        incrementDO.setRecordId(recordId);

        int incResult = reportCountryDao.insertIncrementRecord(incrementDO);
        if (incResult > 0) {
            logger.info("Excel 增量sheet 数据 记录插入成功！");
        } else {
            logger.debug("Excel 增量sheet 数据 记录插入失败！incrementDO为-->" + incrementDO.toString());
        }
        // 插入Excel 全量sheet 数据
        //UploadQuantityDO quantityDO = convertDateQuantity(quantityList);
        UploadQuantityDO quantityDO = getQuantityDo(quantityList);

        quantityDO.setId(IdGen.uuid());
        quantityDO.setRecordId(recordId);
        int quanResult = reportCountryDao.insertQuantityRecord(quantityDO);
        if (quanResult > 0) {
            logger.info("Excel 全量sheet 数据 记录插入成功！ ");
        } else {
            logger.debug("Excel 全量sheet 数据 记录插入失败！incrementDO为-->" + quantityDO.toString());
        }
        // 给首页展示数据 对象赋值
        int productResult = reportCountryDao.insertProductStatistics(indexShowList);
        if (productResult > 0) {
            logger.info("Excel 首页展示数据上传记录 插入成功！");
        } else {
            logger.debug("Excel 首页展示数据上传记录 插入失败！statisticsDO-->" + indexShowList.toString());
        }
        if (incResult > 0 && quanResult > 0 && productResult > 0) {
            // 处理Excel中 数据转换为推送国家平台的数据

            // 推送数据到国家平台
            // 获取增量数据
            UploadIncrementDO newestIncrement = reportCountryDao.getNewestIncrement();
            // 获取全量数据
            UploadQuantityDO newestQuantity = reportCountryDao.getNewestQuantity();
            // 推送 增量和全量数据到国家信易贷平台
            pushDate(newestIncrement, newestQuantity, "1");
            return 1;
        } else {
            return -1;
        }

    }

    /**
     * 获取全量实体类
     * @param quantityList
     * @return
     */
    private UploadQuantityDO getQuantityDo(List<QuantityDo> quantityList) {
        Map<String, String> map = getQlThisTimePushDate(quantityList);
        UploadQuantityDO quantity = new UploadQuantityDO();
        quantity.setRegisteredEnterpriseNum(Integer.parseInt(map.get("thisTime0")));
        quantity.setCreditEnterpriseNum(Integer.parseInt(map.get("thisTime1")));
        quantity.setLoanEnterpriseNum(Integer.parseInt(map.get("thisTime2")));
        quantity.setCreditLoanEnterpriseNum(Integer.parseInt(map.get("thisTime3")));
        quantity.setFinancingNeedsEnterpriseNum(Integer.parseInt(map.get("thisTime4")));
        quantity.setCreditAmount(new BigDecimal(map.get("thisTime5")).setScale(2,BigDecimal.ROUND_UP));
        quantity.setCreditNum(Integer.parseInt(map.get("thisTime6")));
        quantity.setLoanAmount(new BigDecimal(map.get("thisTime7")).setScale(2,BigDecimal.ROUND_UP));
        quantity.setLoanNum(Integer.parseInt(map.get("thisTime8")));
        quantity.setCreditLoanAmount(new BigDecimal(map.get("thisTime9")).setScale(2,BigDecimal.ROUND_UP));
        quantity.setCreditLoanNum(Integer.parseInt(map.get("thisTime10")));
        quantity.setFinancingNeedsAmount(new BigDecimal(map.get("thisTime11")).setScale(2,BigDecimal.ROUND_UP));
        quantity.setFinancingNeedsNum(Integer.parseInt(map.get("thisTime12")));
        quantity.setDockingNum(Integer.parseInt(map.get("thisTime13")));
        quantity.setOverdueNum(Integer.parseInt(map.get("thisTime14")));
        quantity.setAverageLendingRate(new BigDecimal(map.get("thisTime15")).setScale(2,BigDecimal.ROUND_UP));
        quantity.setFullBusinessProcessAveragePeriod(Integer.parseInt(map.get("thisTime16")));
        quantity.setCreditAveragePeriod(Integer.parseInt(map.get("thisTime17")));
        quantity.setLoanAveragePeriod(Integer.parseInt(map.get("thisTime18")));
        quantity.setCreditLoanAveragePeriod(Integer.parseInt(map.get("thisTime19")));
        quantity.setNearlyYearLoanAmount(new BigDecimal(map.get("thisTime20")).setScale(2,BigDecimal.ROUND_UP));
        quantity.setNearlyYearLoanNum(Integer.parseInt(map.get("thisTime21")));
        quantity.setNearlyYearLoanEnterpriseNum(Integer.parseInt(map.get("thisTime22")));
        quantity.setNearlyYearCreditLoanAmount(new BigDecimal(map.get("thisTime23")).setScale(2,BigDecimal.ROUND_UP));
        quantity.setNearlyYearCreditLoanNum(Integer.parseInt(map.get("thisTime24")));
        quantity.setNearlyYearCreditLoanEnterpriseNum(Integer.parseInt(map.get("thisTime25")));
        quantity.setNearlyYearFinancingNeedsAmount(new BigDecimal(map.get("thisTime26")).setScale(2,BigDecimal.ROUND_UP));
        quantity.setNearlyYearFinancingNeedsNum(Integer.parseInt(map.get("thisTime27")));
        quantity.setSettledInFinancialInstitutionNum(Integer.parseInt(map.get("thisTime28")));
        quantity.setFinancialProductsNum(Integer.parseInt(map.get("thisTime29")));
        return quantity;
    }


    public static Map<String,String> getThisTimePushDate(List<IncrementDo> incrementList){

        Map<String,String> map = new HashMap<>();

        try {

            int total = incrementList.size();
            for (int i = 0; i < total; i++) {
                map.put("thisTime"+i,incrementList.get(i).getThisTimePushDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 获取全量的数据
     * @param incrementList
     * @return
     */
    public static Map<String,String> getQlThisTimePushDate(List<QuantityDo> incrementList){

        Map<String,String> map = new HashMap<>();

        try {

            int total = incrementList.size();
            for (int i = 0; i < total; i++) {
                map.put("thisTime"+i,incrementList.get(i).getThisTimePushDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }


    /**
     * 优化后的设置增量实体类的方法
     * @return
     */
    public static UploadIncrementDO getIncrementDo(List<IncrementDo> incrementList){
        Map<String, String> map = getThisTimePushDate(incrementList);

        UploadIncrementDO increment = new UploadIncrementDO();
        // 注册企业数
        increment.setRegisteredEnterpriseNum(Integer.parseInt(map.get("thisTime0")));
        // 授信企业数
        increment.setCreditEnterpriseNum(Integer.parseInt(map.get("thisTime1")));
        // 放款企业数
        increment.setLoanEnterpriseNum(Integer.parseInt(map.get("thisTime2")));
        // 信用放款企业数
        increment.setCreditLoanEnterpriseNum(Integer.parseInt(map.get("thisTime3")));
        // 融资需求企业数
        increment.setFinancingNeedsEnterpriseNum(Integer.parseInt(map.get("thisTime4")));
        // 授信金额
        increment.setCreditAmount(new BigDecimal(map.get("thisTime5")));
        // 授信笔数
        increment.setCreditNum(Integer.parseInt(map.get("thisTime6")));
        // 放款金额
        increment.setLoanAmount(new BigDecimal(map.get("thisTime7")).setScale(2,BigDecimal.ROUND_UP));
        // 放款笔数
        increment.setLoanNum(Integer.parseInt(map.get("thisTime8")));
        // 信用放款金额
        increment.setCreditLoanAmount(new BigDecimal(map.get("thisTime9")).setScale(2,BigDecimal.ROUND_UP));
        // 信用放款笔数
        increment.setCreditLoanNum(Integer.parseInt(map.get("thisTime10")));
        // 融资需求金额
        increment.setFinancingNeedsAmount(new BigDecimal(map.get("thisTime11")).setScale(2,BigDecimal.ROUND_UP));
        // 融资需求笔数
        increment.setFinancingNeedsNum(Integer.parseInt(map.get("thisTime12")));
        // 企业行业统计信息
        increment.setComTradeCensusInfo(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime13"))==true?null:Integer.parseInt(map.get("thisTime13")));
        // 行业类型编码
        increment.setTradeTypeCode(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime14"))==true?null:Integer.parseInt(map.get("thisTime14")));
        //各行业类型注册企业数
        increment.setAlltradeTypeRegComNum(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime15"))==true?null:Integer.parseInt(map.get("thisTime15")));
        //各行业类型授信企业数
        increment.setAlltradeTypeGrantNum(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime16"))==true?null:Integer.parseInt(map.get("thisTime16")));
        //企业规模统计信息
        increment.setComScaleTotalInfo(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime17"))==true?null:Integer.parseInt(map.get("thisTime17")));
        //规模编码
        increment.setScaleCode(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime18"))==true?null:Integer.parseInt(map.get("thisTime18")));
        //各规模注册企业数
        increment.setScaleRegisterComNum(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime19"))==true?null:Integer.parseInt(map.get("thisTime19")));
        //各规模授信企业数
        increment.setScaleGrantComNum(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime20"))==true?null:Integer.parseInt(map.get("thisTime20")));
        //企业营收区间统计信息
        increment.setComRevenueRegionInfo(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime21"))==true?null:Integer.parseInt(map.get("thisTime21")));
        //营收区间编码
        increment.setRevenueRegionCode(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime22"))==true?null:Integer.parseInt(map.get("thisTime22")));
        //各营收区间注册企业数
        increment.setAllrevenueRegisterComNum(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime23"))==true?null:Integer.parseInt(map.get("thisTime23")));
        //企业税收区间统计信息
        increment.setComTaxRegionTotalInfo(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime24"))==true?null:Integer.parseInt(map.get("thisTime24")));
        //税收区间编码
        increment.setTaxRegionCode(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime25"))==true?null:Integer.parseInt(map.get("thisTime25")));
        //各税收区间注册企业数
        increment.setAlltaxRegionRegiComNum(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime26"))==true?null:Integer.parseInt(map.get("thisTime26")));
        //成功放款金额区间统计信息
        increment.setSuccLoanMonregiTotInfo(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime27"))==true?null:Integer.parseInt(map.get("thisTime27")));
        //金额区间编码
        increment.setMoneyRegionCode(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime28"))==true?null:Integer.parseInt(map.get("thisTime28")));
        //各区间放款金额
        increment.setAllRegionLoanMoney(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime29"))==true?null:new BigDecimal(map.get("thisTime29")));
        //放款类型统计信息
        increment.setLoanTypeTotalInfo(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime30"))==true?null:Integer.parseInt(map.get("thisTime30")));
        //放款类型编码
        increment.setLoanTypeCode(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime31"))==true?null:Integer.parseInt(map.get("thisTime31")));
        //各类型放款金额
        increment.setAllTypeLoanMoney(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime32"))==true?null:new BigDecimal(map.get("thisTime32")));
        //金融机构统计信息
        increment.setFinMechaTotalInfo(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime33"))==true?null:Integer.parseInt(map.get("thisTime33")));
        //金融机构名称
        increment.setFinMechaName(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime34"))==true?null:map.get("thisTime34"));
        //各金融机构放款金额
        increment.setAllfinMechaLoanMoney(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime35"))==true?null:new BigDecimal(map.get("thisTime35")));
        //各金融机构放款笔数
        increment.setAllfinMechaLoanTotal(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime36"))==true?null:Integer.parseInt(map.get("thisTime36")));
        //各金融机构放款企业数
        increment.setAllfinMechaLoanComNum(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime37"))==true?null:Integer.parseInt(map.get("thisTime37")));
        //各金融机构受理融资需求笔数
        increment.setAllfinMechaAccNeedNum(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime38"))==true?null:Integer.parseInt(map.get("thisTime38")));
        //各金融机构受理需求金额
        increment.setAllfinMechaAccNeedMony(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime39"))==true?null:new BigDecimal(map.get("thisTime39")));
        //需求金额区间统计信息
        increment.setNeedMoneyRegionTotInfo(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime40"))==true?null:Integer.parseInt(map.get("thisTime40")));
        //金额区间编码(需求金额区间)
        increment.setMoneyRegionCode2(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime41"))==true?null:Integer.parseInt(map.get("thisTime41")));
        //各区间的需求金额
        increment.setAllRegionNeedMoney(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime42"))==true?null:new BigDecimal(map.get("thisTime42")));
        //入驻金融机构数
        increment.setSettledInFinancialInstitutionNum(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime43"))==true?null:Integer.parseInt(map.get("thisTime43")));
        //入驻金融机构统计信息
        increment.setSettlementFinTotalInfo(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime44"))==true?null:Integer.parseInt(map.get("thisTime44")));
        //机构类型编码
        increment.setFinTypeCode(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime45"))==true?null:Integer.parseInt(map.get("thisTime45")));
        //各类型金融机构数
        increment.setAllTypeFinNum(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime46"))==true?null:Integer.parseInt(map.get("thisTime46")));
        //金融产品数 gai
        increment.setFinancialProductsNum(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime47"))==true?null:Integer.parseInt(map.get("thisTime47")));
        //金融产品统计信息
        increment.setFinProductTotalInfo(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime48"))==true?null:Integer.parseInt(map.get("thisTime48")));
        //产品类型编码
        increment.setProductTypeCode(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime49"))==true?null:Integer.parseInt(map.get("thisTime49")));
        //各类型金融产品数
        increment.setAllTypeFinproductNum(com.alibaba.excel.util.StringUtils.isEmpty(map.get("thisTime50"))==true?null:Integer.parseInt(map.get("thisTime50")));

        return increment;
    }



    private UploadQuantityDO convertDateQuantity(List<IncrementVo> quantityList) {
        UploadQuantityDO uploadQuantity = new UploadQuantityDO();
        if(quantityList.get(0).getThisTimePushDate().contains(",")){
            String str0 = quantityList.get(0).getThisTimePushDate().replace(",","");
            uploadQuantity.setRegisteredEnterpriseNum(Integer.parseInt(str0));
        }else{
            uploadQuantity.setRegisteredEnterpriseNum(Integer.parseInt(quantityList.get(0).getThisTimePushDate()));

        }
        if(quantityList.get(1).getThisTimePushDate().contains(",")){
            String str1 = quantityList.get(1).getThisTimePushDate().replace(",","");
            uploadQuantity.setCreditEnterpriseNum(Integer.parseInt(str1));

        }else{
            uploadQuantity.setCreditEnterpriseNum(Integer.parseInt(quantityList.get(1).getThisTimePushDate()));
        }
        if(quantityList.get(2).getThisTimePushDate().contains(",")){
            String str2 = quantityList.get(2).getThisTimePushDate().replace(",","");
            uploadQuantity.setLoanEnterpriseNum(Integer.parseInt(str2));

        }else{
            uploadQuantity.setLoanEnterpriseNum(Integer.parseInt(quantityList.get(2).getThisTimePushDate()));
        }
        if(quantityList.get(3).getThisTimePushDate().contains(",")){
            String str3 = quantityList.get(3).getThisTimePushDate().replace(",","");
            uploadQuantity.setCreditLoanEnterpriseNum(Integer.parseInt(str3));

        }else{
            uploadQuantity.setCreditLoanEnterpriseNum(Integer.parseInt(quantityList.get(3).getThisTimePushDate()));
        }
        if(quantityList.get(4).getThisTimePushDate().contains(",")){
            String str4 = quantityList.get(4).getThisTimePushDate().replace(",","");
            uploadQuantity.setFinancingNeedsEnterpriseNum(Integer.parseInt(str4));
        }else{
            uploadQuantity.setFinancingNeedsEnterpriseNum(Integer.parseInt(quantityList.get(4).getThisTimePushDate()));
        }
        if(quantityList.get(5).getThisTimePushDate().contains(",")){
            String str5 = quantityList.get(5).getThisTimePushDate().replace(",","");
            uploadQuantity.setCreditAmount(new BigDecimal(str5).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            uploadQuantity.setCreditAmount(new BigDecimal(quantityList.get(5).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        if(quantityList.get(6).getThisTimePushDate().contains(",")){
            String str6 = quantityList.get(6).getThisTimePushDate().replace(",","");
            uploadQuantity.setCreditNum(Integer.parseInt(str6));
        }else{
            uploadQuantity.setCreditNum(Integer.parseInt(quantityList.get(6).getThisTimePushDate()));
        }
        if(quantityList.get(7).getThisTimePushDate().contains(",")){
            String str7 = quantityList.get(7).getThisTimePushDate().replace(",","");
            uploadQuantity.setLoanAmount(new BigDecimal(str7).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            uploadQuantity.setLoanAmount(new BigDecimal(quantityList.get(7).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        if(quantityList.get(8).getThisTimePushDate().contains(",")){
            String str8 = quantityList.get(8).getThisTimePushDate().replace(",","");
            uploadQuantity.setLoanNum(Integer.parseInt(str8));

        }else{
            uploadQuantity.setLoanNum(Integer.parseInt(quantityList.get(8).getThisTimePushDate()));
        }
        if(quantityList.get(9).getThisTimePushDate().contains(",")){
            String str9 = quantityList.get(9).getThisTimePushDate().replace(",","");
            uploadQuantity.setCreditLoanAmount(new BigDecimal(str9).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            uploadQuantity.setCreditLoanAmount(new BigDecimal(quantityList.get(9).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        if(quantityList.get(10).getThisTimePushDate().contains(",")){
            String str10 = quantityList.get(10).getThisTimePushDate().replace(",","");
            uploadQuantity.setCreditLoanNum(Integer.parseInt(str10));
        }else{
            uploadQuantity.setCreditLoanNum(Integer.parseInt(quantityList.get(10).getThisTimePushDate()));
        }
        if(quantityList.get(11).getThisTimePushDate().contains(",")){
            String str11 = quantityList.get(11).getThisTimePushDate().replace(",","");
            uploadQuantity.setFinancingNeedsAmount(new BigDecimal(str11).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            uploadQuantity.setFinancingNeedsAmount(new BigDecimal(quantityList.get(11).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        if(quantityList.get(12).getThisTimePushDate().contains(",")){
            String str12 = quantityList.get(12).getThisTimePushDate().replace(",","");
            uploadQuantity.setFinancingNeedsNum(Integer.parseInt(str12));

        }else{
            uploadQuantity.setFinancingNeedsNum(Integer.parseInt(quantityList.get(12).getThisTimePushDate()));
        }
        if(quantityList.get(13).getThisTimePushDate().contains(",")){
            String str13 = quantityList.get(13).getThisTimePushDate().replace(",","");
            uploadQuantity.setDockingNum(Integer.parseInt(str13));

        }else{
            uploadQuantity.setDockingNum(Integer.parseInt(quantityList.get(13).getThisTimePushDate()));
        }
        if(quantityList.get(14).getThisTimePushDate().contains(",")){
            String str14 = quantityList.get(14).getThisTimePushDate().replace(",","");
            uploadQuantity.setOverdueNum(Integer.parseInt(str14));

        }else{
            uploadQuantity.setOverdueNum(Integer.parseInt(quantityList.get(14).getThisTimePushDate()));
        }
        if(quantityList.get(15).getThisTimePushDate().contains(",")){
            String str15 = quantityList.get(15).getThisTimePushDate().replace(",","");
            uploadQuantity.setAverageLendingRate(new BigDecimal(str15).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            uploadQuantity.setAverageLendingRate(new BigDecimal(quantityList.get(15).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        if(quantityList.get(16).getThisTimePushDate().contains(",")){
            String str16 = quantityList.get(16).getThisTimePushDate().replace(",","");
            uploadQuantity.setFullBusinessProcessAveragePeriod(Integer.parseInt(str16));

        }else{
            uploadQuantity.setFullBusinessProcessAveragePeriod(Integer.parseInt(quantityList.get(16).getThisTimePushDate()));
        }
        if(quantityList.get(17).getThisTimePushDate().contains(",")){
            String str17 = quantityList.get(17).getThisTimePushDate().replace(",","");
            uploadQuantity.setCreditAveragePeriod(Integer.parseInt(str17));
        }else{
            uploadQuantity.setCreditAveragePeriod(Integer.parseInt(quantityList.get(17).getThisTimePushDate()));
        }
        if(quantityList.get(18).getThisTimePushDate().contains(",")){
            String str18 = quantityList.get(18).getThisTimePushDate().replace(",","");
            uploadQuantity.setLoanAveragePeriod(Integer.parseInt(str18));

        }else{
            uploadQuantity.setLoanAveragePeriod(Integer.parseInt(quantityList.get(18).getThisTimePushDate()));
        }
        if(quantityList.get(19).getThisTimePushDate().contains(",")){
            String str19 = quantityList.get(19).getThisTimePushDate().replace(",","");
            uploadQuantity.setCreditLoanAveragePeriod(Integer.parseInt(str19));

        }else{
            uploadQuantity.setCreditLoanAveragePeriod(Integer.parseInt(quantityList.get(19).getThisTimePushDate()));
        }
        if(quantityList.get(20).getThisTimePushDate().contains(",")){
            String str20 = quantityList.get(20).getThisTimePushDate().replace(",","");
            uploadQuantity.setNearlyYearLoanAmount(new BigDecimal(str20).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            uploadQuantity.setNearlyYearLoanAmount(new BigDecimal(quantityList.get(20).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        if(quantityList.get(21).getThisTimePushDate().contains(",")){
            String str21 = quantityList.get(21).getThisTimePushDate().replace(",","");
            uploadQuantity.setNearlyYearLoanNum(Integer.parseInt(str21));
        }else{
            uploadQuantity.setNearlyYearLoanNum(Integer.parseInt(quantityList.get(21).getThisTimePushDate()));
        }
        if(quantityList.get(22).getThisTimePushDate().contains(",")){
            String str22 = quantityList.get(22).getThisTimePushDate().replace(",","");
            uploadQuantity.setNearlyYearLoanEnterpriseNum(Integer.parseInt(str22));
        }else{
            uploadQuantity.setNearlyYearLoanEnterpriseNum(Integer.parseInt(quantityList.get(22).getThisTimePushDate()));
        }
        if(quantityList.get(23).getThisTimePushDate().contains(",")){
            String str23 = quantityList.get(23).getThisTimePushDate().replace(",","");
            uploadQuantity.setNearlyYearCreditLoanAmount(new BigDecimal(str23).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            uploadQuantity.setNearlyYearCreditLoanAmount(new BigDecimal(quantityList.get(23).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        if(quantityList.get(24).getThisTimePushDate().contains(",")){
            String str24 = quantityList.get(24).getThisTimePushDate().replace(",","");
            uploadQuantity.setNearlyYearCreditLoanNum(Integer.parseInt(str24));
        }else{
            uploadQuantity.setNearlyYearCreditLoanNum(Integer.parseInt(quantityList.get(24).getThisTimePushDate()));
        }
        if(quantityList.get(25).getThisTimePushDate().contains(",")){
            String str25 = quantityList.get(25).getThisTimePushDate().replace(",","");
            uploadQuantity.setNearlyYearCreditLoanEnterpriseNum(Integer.parseInt(str25));

        }else{
            uploadQuantity.setNearlyYearCreditLoanEnterpriseNum(Integer.parseInt(quantityList.get(25).getThisTimePushDate()));
        }
        if(quantityList.get(26).getThisTimePushDate().contains(",")){
            String str26 = quantityList.get(26).getThisTimePushDate().replace(",","");
            uploadQuantity.setNearlyYearFinancingNeedsAmount(new BigDecimal(str26).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            uploadQuantity.setNearlyYearFinancingNeedsAmount(new BigDecimal(quantityList.get(26).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        if(quantityList.get(27).getThisTimePushDate().contains(",")){
            String str27 = quantityList.get(27).getThisTimePushDate().replace(",","");
            uploadQuantity.setNearlyYearFinancingNeedsNum(Integer.parseInt(str27));

        }else{
            uploadQuantity.setNearlyYearFinancingNeedsNum(Integer.parseInt(quantityList.get(27).getThisTimePushDate()));
        }
        if(quantityList.get(28).getThisTimePushDate().contains(",")){
            String str28 = quantityList.get(28).getThisTimePushDate().replace(",","");
            uploadQuantity.setSettledInFinancialInstitutionNum(Integer.parseInt(str28));

        }else{
            uploadQuantity.setSettledInFinancialInstitutionNum(Integer.parseInt(quantityList.get(28).getThisTimePushDate()));
        }
        if(quantityList.get(29).getThisTimePushDate().contains(",")){
            String str29 = quantityList.get(29).getThisTimePushDate().replace(",","");
            uploadQuantity.setFinancialProductsNum(Integer.parseInt(str29));

        }else{
            uploadQuantity.setFinancialProductsNum(Integer.parseInt(quantityList.get(29).getThisTimePushDate()));
        }

        return uploadQuantity;
    }

    private UploadIncrementDO convertDateIncrement(List<IncrementVo> incrementList) {
        UploadIncrementDO newestIncrement = new UploadIncrementDO();

        newestIncrement.setRegisteredEnterpriseNum(Integer.parseInt(incrementList.get(0).getThisTimePushDate()));
        newestIncrement.setCreditEnterpriseNum(Integer.parseInt(incrementList.get(1).getThisTimePushDate()));
        //       放款企业数gai
        newestIncrement.setLoanEnterpriseNum(Integer.parseInt(incrementList.get(2).getThisTimePushDate()));
        //       信用放款企业数gai
        newestIncrement.setCreditLoanEnterpriseNum(Integer.parseInt(incrementList.get(3).getThisTimePushDate()));
        //       融资需求企业数gai
        newestIncrement.setFinancingNeedsEnterpriseNum(Integer.parseInt(incrementList.get(4).getThisTimePushDate()));

        if(incrementList.get(5).getThisTimePushDate().contains(",")){
            String str5 = incrementList.get(5).getThisTimePushDate().replace(",","");
            //       授信金额gai
            newestIncrement.setCreditAmount(new BigDecimal(str5).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            newestIncrement.setCreditAmount(new BigDecimal(incrementList.get(5).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        //       授信笔数gai
        newestIncrement.setCreditNum(Integer.parseInt(incrementList.get(6).getThisTimePushDate()));
        if(incrementList.get(7).getThisTimePushDate().contains(",")){
            String str7 = incrementList.get(7).getThisTimePushDate().replace(",","");
            //       放款金额gai
            newestIncrement.setLoanAmount(new BigDecimal(str7).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            newestIncrement.setLoanAmount(new BigDecimal(incrementList.get(7).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        //      放款笔数gai
        if(incrementList.get(8).getThisTimePushDate().contains(",")){
            String str8 = incrementList.get(8).getThisTimePushDate().replace(",","");
            //       放款金额gai
            newestIncrement.setLoanNum(Integer.parseInt(str8));
        }else{
            newestIncrement.setLoanNum(Integer.parseInt(incrementList.get(8).getThisTimePushDate()));
        }
        if(incrementList.get(9).getThisTimePushDate().contains(",")){
            String str9 = incrementList.get(9).getThisTimePushDate().replace(",","");
            //       信用放款金额gai
            newestIncrement.setCreditLoanAmount(new BigDecimal(str9).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            newestIncrement.setCreditLoanAmount(new BigDecimal(incrementList.get(9).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        //       信用放款笔数
        newestIncrement.setCreditLoanNum(Integer.parseInt(incrementList.get(10).getThisTimePushDate()));
        if(incrementList.get(11).getThisTimePushDate().contains(",")){
            String str11 = incrementList.get(11).getThisTimePushDate().replace(",","");
            // 融资需求金额 gai
            newestIncrement.setFinancingNeedsAmount(new BigDecimal(str11).setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            newestIncrement.setFinancingNeedsAmount(new BigDecimal(incrementList.get(11).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        //融资需求笔数 gai
        newestIncrement.setFinancingNeedsNum(Integer.parseInt(incrementList.get(12).getThisTimePushDate()));
        //      企业行业统计信息();
        //newestIncrement.setComTradeCensusInfo(Integer.parseInt(incrementList.get(13).getThisTimePushDate()));
        //       行业类型编码();
        if (StringUtils.isNotBlank(incrementList.get(14).getThisTimePushDate())) {
            newestIncrement.setTradeTypeCode(Integer.parseInt(incrementList.get(14).getThisTimePushDate()));
        }
        if (StringUtils.isNotBlank(incrementList.get(15).getThisTimePushDate())) {
            //      各行业类型注册企业数();
            newestIncrement.setAlltradeTypeRegComNum(Integer.parseInt(incrementList.get(15).getThisTimePushDate()));
        }
        if (StringUtils.isNotBlank(incrementList.get(16).getThisTimePushDate())) {
            //      各行业类型授信企业数();
            newestIncrement.setAlltradeTypeGrantNum(Integer.parseInt(incrementList.get(16).getThisTimePushDate()));
        }

        //        企业规模统计信息();
        //newestIncrement.setComScaleTotalInfo(Integer.parseInt(incrementList.get(17).getThisTimePushDate()));
        if (StringUtils.isNotBlank(incrementList.get(18).getThisTimePushDate())) {
            //        规模编码();
            newestIncrement.setScaleCode(Integer.parseInt(incrementList.get(18).getThisTimePushDate()));
        }
        if (StringUtils.isNotBlank(incrementList.get(19).getThisTimePushDate())) {
            //       各规模注册企业数();
            newestIncrement.setScaleRegisterComNum(Integer.parseInt(incrementList.get(19).getThisTimePushDate()));
        }
        if (StringUtils.isNotBlank(incrementList.get(20).getThisTimePushDate())) {
            //       各规模授信企业数();
            newestIncrement.setScaleGrantComNum(Integer.parseInt(incrementList.get(20).getThisTimePushDate()));
        }
        if (StringUtils.isNotBlank(incrementList.get(20).getThisTimePushDate())) {
            //       各规模授信企业数();
            newestIncrement.setScaleGrantComNum(Integer.parseInt(incrementList.get(20).getThisTimePushDate()));
        }


        //       企业营收区间统计信息();
        //newestIncrement.setComRevenueRegionInfo(Integer.parseInt(incrementList.get(21).getThisTimePushDate()));
        if (StringUtils.isNotBlank(incrementList.get(22).getThisTimePushDate())) {
//      营收区间编码();
            newestIncrement.setRevenueRegionCode(Integer.parseInt(incrementList.get(22).getThisTimePushDate()));
        }
        if(StringUtils.isNotBlank(incrementList.get(23).getThisTimePushDate())){

            //     各营收区间注册企业数();
            newestIncrement.setAllrevenueRegisterComNum(Integer.parseInt(incrementList.get(23).getThisTimePushDate()));
        }

        //       企业税收区间统计信息();
        //newestIncrement.setComTaxRegionTotalInfo(Integer.parseInt(incrementList.get(24).getThisTimePushDate()));
        if(StringUtils.isNotBlank(incrementList.get(25).getThisTimePushDate())){

            //    税收区间编码();
            newestIncrement.setTaxRegionCode(Integer.parseInt(incrementList.get(25).getThisTimePushDate()));
        }
        if(StringUtils.isNotBlank(incrementList.get(26).getThisTimePushDate())){
            //   各税收区间注册企业数();
            newestIncrement.setAlltaxRegionRegiComNum(Integer.parseInt(incrementList.get(26).getThisTimePushDate()));
        }
        //      成功放款金额区间统计信息();
        //newestIncrement.setSuccLoanMonregiTotInfo(Integer.parseInt(incrementList.get(27).getThisTimePushDate()));
        if(StringUtils.isNotBlank(incrementList.get(28).getThisTimePushDate())){
           //      金额区间编码();
            newestIncrement.setMoneyRegionCode(Integer.parseInt(incrementList.get(28).getThisTimePushDate()));
        }
        if(StringUtils.isNotBlank(incrementList.get(29).getThisTimePushDate())){
            if(incrementList.get(29).getThisTimePushDate().contains(",")){
                String str29 = incrementList.get(29).getThisTimePushDate().replace(",","");
                //       各区间放款金额();
                newestIncrement.setAllRegionLoanMoney(new BigDecimal(str29).setScale(2, BigDecimal.ROUND_HALF_UP));
            }else{
                newestIncrement.setAllRegionLoanMoney(new BigDecimal(incrementList.get(29).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
            }

        }

        //        放款类型统计信息();
        //newestIncrement.setLoanTypeTotalInfo(Integer.parseInt(incrementList.get(30).getThisTimePushDate()));
        if(StringUtils.isNotBlank(incrementList.get(31).getThisTimePushDate())){
//        放款类型编码();
            newestIncrement.setLoanTypeCode(Integer.parseInt(incrementList.get(31).getThisTimePushDate()));
        }
        if(StringUtils.isNotBlank(incrementList.get(32).getThisTimePushDate())){
            if(incrementList.get(32).getThisTimePushDate().contains(",")){
                String str32 = incrementList.get(32).getThisTimePushDate().replace(",","");
                //       各区间放款金额();
                newestIncrement.setAllTypeLoanMoney(new BigDecimal(str32).setScale(2, BigDecimal.ROUND_HALF_UP));
            }else{
                //       各类型放款金额();
                newestIncrement.setAllTypeLoanMoney(new BigDecimal(incrementList.get(32).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
            }

        }
//      金融机构统计信息();
        //newestIncrement.setFinMechaTotalInfo(Integer.parseInt(incrementList.get(33).getThisTimePushDate()));
        if(StringUtils.isNotBlank(incrementList.get(34).getThisTimePushDate())){
            //       金融机构名称();
            newestIncrement.setFinMechaName(incrementList.get(34).toString());
        }
        if(StringUtils.isNotBlank(incrementList.get(35).getThisTimePushDate())){
           if(incrementList.get(35).getThisTimePushDate().contains(",")){
               String str35 = incrementList.get(35).getThisTimePushDate().replace(",","");
               //       各区间放款金额();
               newestIncrement.setAllfinMechaLoanMoney(new BigDecimal(str35).setScale(2, BigDecimal.ROUND_HALF_UP));
           }else{
               //       各金融机构放款金额();
               newestIncrement.setAllfinMechaLoanMoney(new BigDecimal(incrementList.get(35).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
           }
        }
        if(StringUtils.isNotBlank(incrementList.get(36).getThisTimePushDate())){

            //      各金融机构放款笔数();
            newestIncrement.setAllfinMechaLoanTotal(Integer.parseInt(incrementList.get(36).getThisTimePushDate()));
        }
        if(StringUtils.isNotBlank(incrementList.get(37).getThisTimePushDate())){
            //       各金融机构放款企业数();
            newestIncrement.setAllfinMechaLoanComNum(Integer.parseInt(incrementList.get(37).getThisTimePushDate()));
        }
        if(StringUtils.isNotBlank(incrementList.get(38).getThisTimePushDate())){

            //     各金融机构受理融资需求笔数();
            newestIncrement.setAllfinMechaAccNeedNum(Integer.parseInt(incrementList.get(38).getThisTimePushDate()));
        }
        if(StringUtils.isNotBlank(incrementList.get(39).getThisTimePushDate())){
              if(incrementList.get(39).getThisTimePushDate().contains(",")){
                  String str39 = incrementList.get(39).getThisTimePushDate().replace(",","");
                  //      各金融机构受理需求金额();
                  newestIncrement.setAllfinMechaAccNeedMony(new BigDecimal(str39).setScale(2, BigDecimal.ROUND_HALF_UP));
              }else{
                  //      各金融机构受理需求金额();
                  newestIncrement.setAllfinMechaAccNeedMony(new BigDecimal(incrementList.get(39).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
              }

        }

        //      需求金额区间统计信息();
        //newestIncrement.setNeedMoneyRegionTotInfo(40);
        if(StringUtils.isNotBlank(incrementList.get(41).getThisTimePushDate())){
            //金额区间编码(需求金额区间)();
            newestIncrement.setMoneyRegionCode2(Integer.parseInt(incrementList.get(41).getThisTimePushDate()));
        }
        if(StringUtils.isNotBlank(incrementList.get(42).getThisTimePushDate())){
             if(incrementList.get(42).getThisTimePushDate().contains(",")){
                 String str42 = incrementList.get(42).getThisTimePushDate().replace(",","");
                 //      各区间的需求金额();
                 newestIncrement.setAllRegionNeedMoney(new BigDecimal(str42).setScale(2, BigDecimal.ROUND_HALF_UP));
             }else{
                 //      各区间的需求金额();
                 newestIncrement.setAllRegionNeedMoney(new BigDecimal(incrementList.get(42).getThisTimePushDate()).setScale(2, BigDecimal.ROUND_HALF_UP));
             }
        }
        if(StringUtils.isNotBlank(incrementList.get(43).getThisTimePushDate())){

            //     入驻金融机构数();
            newestIncrement.setSettledInFinancialInstitutionNum(Integer.parseInt(incrementList.get(43).getThisTimePushDate()));
        }

        //       入驻金融机构统计信息();
        //newestIncrement.setSettlementFinTotalInfo(Integer.parseInt(incrementList.get(44).getThisTimePushDate()));
        if(StringUtils.isNotBlank(incrementList.get(45).getThisTimePushDate())){

            //      机构类型编码();
            newestIncrement.setFinTypeCode(Integer.parseInt(incrementList.get(45).getThisTimePushDate()));
        }
        if(StringUtils.isNotBlank(incrementList.get(46).getThisTimePushDate())){
            //     各类型金融机构数();
            newestIncrement.setAllTypeFinNum(Integer.parseInt(incrementList.get(46).getThisTimePushDate()));
        }
        if(StringUtils.isNotBlank(incrementList.get(47).getThisTimePushDate())){

            //金融产品数 gai();
            newestIncrement.setFinancialProductsNum(Integer.parseInt(incrementList.get(47).getThisTimePushDate()));
        }
        //      金融产品统计信息();
        //newestIncrement.setFinProductTotalInfo(Integer.parseInt(incrementList.get(48).getThisTimePushDate()));
        if(StringUtils.isNotBlank(incrementList.get(49).getThisTimePushDate())){

            //      产品类型编码();
            newestIncrement.setProductTypeCode(Integer.parseInt(incrementList.get(49).getThisTimePushDate()));
        }
        if(StringUtils.isNotBlank(incrementList.get(50).getThisTimePushDate())){
            //        各类型金融产品数();
            newestIncrement.setAllTypeFinproductNum(Integer.parseInt(incrementList.get(50).getThisTimePushDate()));

        }
        return newestIncrement;
    }


    /**
     * 插入上传excel 的记录
     *
     * @param uploadDo
     * @return
     */
    @Override
    public int insertUploadRecord(UploadDo uploadDo) {
        return reportCountryDao.insertUploadRecord(uploadDo);
    }


    /**
     * 验证首页展示数据格式是否正确
     * 验证规则：
     * “首页展示数据”sheet中“UUID”“机构表示”“月份”“统计类型”“数量”“删除标识”是否为空，
     * * 若为空，则提示“首页展示数据必填项为空”，
     * * 其他问题导致上传失败则提示“请重新上传”；
     *
     * @param indexShowList
     */
    public ResultVo<IndexShowDo> checkIndexShowList(List<IndexShowDo> indexShowList) {

        ResultVo<IndexShowDo> resultVo = new ResultVo<>();

        n:
        for (int i = 0; i < indexShowList.size(); i++) {
            String uuid = indexShowList.get(i).getUuid();
            String type = indexShowList.get(i).getStatisticsType();
            String count = indexShowList.get(i).getStatisticsType();
            String isDelFlag = indexShowList.get(i).getStatisticsType();
            if (StringUtils.isBlank(uuid) || StringUtils.isBlank(type)
                    || StringUtils.isBlank(count) || StringUtils.isBlank(isDelFlag)) {
                String errorMsg = PushDateEnums.CODE_1006.getMsg();
                logger.debug(errorMsg);
                resultVo.setSuccess(false);
                resultVo.setErrorCode(PushDateEnums.CODE_1006.getCode());
                resultVo.setErrorMessage(PushDateEnums.CODE_1006.getMsg());
                break n;
            } else {
                // 正常
                resultVo.setSuccess(true);
                resultVo.setErrorCode(PushDateEnums.CODE_0000.getCode());
                resultVo.setErrorMessage(PushDateEnums.CODE_0000.getMsg());

            }
        }
        return resultVo;
    }

    /**
     * 验证Excel 增量数据和全量数据格式是否正确
     * 校验规则：
     * * 1.“增量”sheet中的“本次上报数据”是否均大于等于0，
     * * 若不满足则提示“数据上传失败，增量数据字段XXX本次上报数据不满足要求”（XXX为对应字段中文名）；
     * * 1.1、 “本次上报数据”中必填字段是否填写，
     * * 若不满足则提示“数据上传失败，增量数据字段XXX为必填字段”（XXX为对应字段中文名），
     * * 其他问题导致上传失败则提示“请重新上传”；
     * * <p>
     * * 2. “全量”sheet中的“本次上报数据”是否均大于等于“上次报送数据”，
     * * 若不满足则提示“数据上传失败，全量数据字段XXX本次上报数据不满足要求”（XXX为对应字段中文名）；
     * * 2.1“本次上报数据”中必填字段是否填写，
     * * 若不满足则提示“数据上传失败，全量数据字段XXX为必填字段”（XXX为对应字段中文名）
     * * ，其他问题导致上传失败则提示“请重新上传”；
     *
     * @param incrementList
     * @param quantityList
     */
    public ResultVo<IncrementVo> checkIncrementAndQuantity(List<IncrementDo> incrementList,
                                                           List<QuantityDo> quantityList) {
        // 读取第一个sheet 增量
        ResultVo<IncrementVo> resultVo = new ResultVo<>();

        q:
        for (int i = 0; i < incrementList.size(); i++) {

            String date = incrementList.get(i).getThisTimePushDate();
            String dateDesc = incrementList.get(i).getField();
            String ifNeed = incrementList.get(i).getIsRequired();

            // 验证必填项
            if ("Y".equals(ifNeed) && StringUtils.isNotBlank(ifNeed)) {

                // 1、验证“增量”sheet中的“本次上报数据”是否均大于等于0，若不满足则提示“数据上传失败，
                // 增量数据字段XXX本次上报数据不满足要求”（XXX为对应字段中文名）
                if (StringUtils.isNotBlank(date)) {
                    BigDecimal zero = new BigDecimal("0");
                    BigDecimal dateBigDecimal = new BigDecimal(date);
                    if (dateBigDecimal.compareTo(zero) == -1) {
                        logger.info("第" + i + "个 有问题CODE_1001");
                        String errorMsg = String.format(PushDateEnums.CODE_1001.getMsg(), dateDesc);
                        logger.info("增量1提示： " + errorMsg);
                        resultVo.setSuccess(false);
                        resultVo.setErrorCode(PushDateEnums.CODE_1001.getCode());
                        resultVo.setErrorMessage(errorMsg);
                        break q;
                    }

                    //return resultVo;
                }
                // 2、“本次上报数据”中必填字段是否填写，若不满足则提示“数据上传失败，增量数据字段XXX为必填字段”（XXX为对应字段中文名）
                if (StringUtils.isBlank(date)) {
                    logger.info("第 " + i + "个 有问题CODE_1002");
                    String errorMsg = String.format(PushDateEnums.CODE_1002.getMsg(), dateDesc);
                    logger.info("增量2提示： " + errorMsg);
                    resultVo.setSuccess(false);
                    resultVo.setErrorCode(PushDateEnums.CODE_1002.getCode());
                    resultVo.setErrorMessage(errorMsg);
                    break q;
                    //return resultVo;
                }
            } else {

                resultVo.setSuccess(true);
                resultVo.setErrorCode(PushDateEnums.CODE_0000.getCode());
                resultVo.setErrorMessage(PushDateEnums.CODE_0000.getMsg());
                //return resultVo;
            }

        }
        // 读取第二个sheet 全量

        n:
        for (int i = 0; i < quantityList.size(); i++) {
            String dateDesc = quantityList.get(i).getField();
            String lastPushDate = quantityList.get(i).getLastPushDate();
            String thisPushDate = quantityList.get(i).getThisTimePushDate();
            String ifNeed = quantityList.get(i).getIsRequired();

            // 验证必填项
            if ("Y".equals(ifNeed) && StringUtils.isNotBlank(ifNeed)) {

                // 1、“本次上报数据”是否均大于等于“上次报送数据”，若不满足则提示“数据上传失败，全量数据字段XXX本次上报数据不满足要求”
                if (StringUtils.isNotBlank(lastPushDate) && StringUtils.isNotBlank(lastPushDate)) {
                    BigDecimal lastBig = new BigDecimal(lastPushDate);
                    BigDecimal thisBig = new BigDecimal(lastPushDate);
                    if (thisBig.compareTo(lastBig) == -1) {
                        logger.info("第 " + i + "个 有问题CODE_1003");
                        String errorMsg = String.format(PushDateEnums.CODE_1003.getMsg(), dateDesc);
                        logger.info("全量1提示： " + errorMsg);
                        resultVo.setSuccess(false);
                        resultVo.setErrorCode(PushDateEnums.CODE_1003.getCode());
                        resultVo.setErrorMessage(errorMsg);
                        // 跳出循环，给出提示
                        break n;
                    }

                    //return resultVo;
                }
                // 2、“本次上报数据”中必填字段是否填写，若不满足则提示“数据上传失败，全量数据字段XXX为必填字段”
                if (StringUtils.isBlank(thisPushDate)) {
                    logger.info("第 " + i + "个 有问题CODE_1004");
                    String errorMsg = String.format(PushDateEnums.CODE_1004.getMsg(), dateDesc);
                    logger.info("全量2提示： " + errorMsg);
                    resultVo.setSuccess(false);
                    resultVo.setErrorCode(PushDateEnums.CODE_1004.getCode());
                    resultVo.setErrorMessage(errorMsg);
                    break n;
                    //return resultVo;
                }
            } else {
                resultVo.setSuccess(true);
                resultVo.setErrorCode(PushDateEnums.CODE_0000.getCode());
                // resultVo.setErrorMessage(PushDateEnums.CODE_0000.getMsg());
            }
        }
        return resultVo;
    }

    /**
     * 获取最新上传的文件路径
     *
     * @return
     */
    @Override
    public UploadDo getNewUpload() {

        return reportCountryDao.getNewUpload();
    }

    @Override
    public UploadDo getDateUpload(String date) {
        return reportCountryDao.getDateUpload(date);
    }

    @Override
    public List<IncrementDo> readExcel(String filePath,int sheetNo,int headerNum) {
        List<IncrementDo> incrementDos = null;
        try {
            incrementDos = readExcelTest(filePath,sheetNo,headerNum);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("解析出问题了！");
        }
        return incrementDos;
    }
    @Override
    public List<QuantityDo> readExcel2(String filePath) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File(filePath));

        List<QuantityDo> dataList = new ArrayList<QuantityDo>();

        // 读取第一个sheet ，从第2行开始读（不读取标题行）
        EasyExcelFactory.readBySax(inputStream, new Sheet(2,1), new AnalysisEventListener() {

            @Override
            public void invoke(Object object, AnalysisContext analysisContext) {

                logger.info("当前行：{} 对应的对象信息为：{}", analysisContext.getCurrentRowNum(), object);
                System.out.println(("当前行对应的对象信息为"+analysisContext.getCurrentRowNum()+ object));
                // 读取到的Excel对象的值
                ArrayList userObj = (ArrayList) object;

                QuantityDo incrementDo = new QuantityDo();
                incrementDo.setId(userObj.get(0) == null ? null :userObj.get(0).toString());
                incrementDo.setField(userObj.get(1) == null ? null :userObj.get(1).toString());
                incrementDo.setLastPushDate(userObj.get(2) == null ? null :userObj.get(2).toString());
                incrementDo.setThisTimePushDate(userObj.get(3) == null ? null :userObj.get(3).toString());
                incrementDo.setHowToPush(userObj.get(4) == null ? null :userObj.get(4).toString());
                incrementDo.setIsRequired(userObj.get(5) == null ? null :userObj.get(5).toString());
                dataList.add(incrementDo);



                // 每批插入的数量
                int perReadCount = 2000;
                Integer currentRowNum = analysisContext.getCurrentRowNum();
                if (currentRowNum % perReadCount == 0) {
                    logger.info("存储dataList的大小为：{}",dataList.size());
                    System.out.printf("", "存储dataList的大小为" + dataList.size());
                    //dataList.clear();
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                logger.info("最后一批存储dataList的大小为：{}",dataList.size());
                System.out.println("最后一批存储dataList的大小为：{}"+dataList.size());
                //dataList.clear();//解析结束销毁不用的资源
            }
        });

        System.out.println("读取完毕");
        logger.debug("hello");
        return dataList;
    }

    @Override
    public List<QuantityDo> readExcelSheet2(String filePath, int sheetNo, int headerNum) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File(filePath));

        List<QuantityDo> dataList = new ArrayList<>();

        // 读取第一个sheet ，从第2行开始读（不读取标题行）
        EasyExcelFactory.readBySax(inputStream, new Sheet(sheetNo,headerNum), new AnalysisEventListener() {

            @Override
            public void invoke(Object object, AnalysisContext analysisContext) {

                logger.info("当前行：{} 对应的对象信息为：{}", analysisContext.getCurrentRowNum(), object);
                System.out.println(("当前行对应的对象信息为"+analysisContext.getCurrentRowNum()+ object));
                // 读取到的Excel对象的值
                ArrayList userObj = (ArrayList) object;

                QuantityDo incrementDo = new QuantityDo();
                incrementDo.setId(userObj.get(0) == null ? null :userObj.get(0).toString());
                incrementDo.setField(userObj.get(1) == null ? null :userObj.get(1).toString());
                incrementDo.setLastPushDate(userObj.get(2) == null ? null :userObj.get(2).toString());
                incrementDo.setThisTimePushDate(userObj.get(3) == null ? null :userObj.get(3).toString());
                incrementDo.setHowToPush(userObj.get(4) == null ? null :userObj.get(4).toString());
                incrementDo.setIsRequired(userObj.get(5) == null ? null :userObj.get(5).toString());
                dataList.add(incrementDo);


                // 每批插入的数量
                int perReadCount = 2000;
                Integer currentRowNum = analysisContext.getCurrentRowNum();
                if (currentRowNum % perReadCount == 0) {
                    logger.info("存储dataList的大小为：{}",dataList.size());
                    System.out.printf("", "存储dataList的大小为" + dataList.size());
                    //dataList.clear();
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                logger.info("最后一批存储dataList的大小为：{}",dataList.size());
                System.out.println("最后一批存储dataList的大小为：{}"+dataList.size());
                //dataList.clear();//解析结束销毁不用的资源
            }
        });

        System.out.println("读取完毕");
        logger.debug("hello");
        return dataList;
    }

    /**
     * 读取Sheet3中的值
     * @param filePath
     * @param sheetNo
     * @param headerNum
     * @return
     */
    @Override
    public List<IndexShowDo> readExcelSheet3(String filePath, int sheetNo, int headerNum) throws FileNotFoundException {

        InputStream inputStream = new FileInputStream(new File(filePath));

        List<IndexShowDo> dataList = new ArrayList<IndexShowDo>();

        // 读取第一个sheet ，从第2行开始读（不读取标题行）
        EasyExcelFactory.readBySax(inputStream, new Sheet(sheetNo,headerNum), new AnalysisEventListener() {

            @Override
            public void invoke(Object object, AnalysisContext analysisContext) {

                logger.info("当前行：{} 对应的对象信息为：{}", analysisContext.getCurrentRowNum(), object);
                System.out.println(("当前行对应的对象信息为"+analysisContext.getCurrentRowNum()+ object));
                // 读取到的Excel对象的值
                ArrayList userObj = (ArrayList) object;

                IndexShowDo indexShowDo = new IndexShowDo();
                indexShowDo.setUuid(userObj.get(0) == null ? null :userObj.get(0).toString());
                indexShowDo.setMechanismFlag(userObj.get(1) == null ? null :userObj.get(1).toString());
                indexShowDo.setMonth(userObj.get(2) == null ? null :userObj.get(2).toString());
                indexShowDo.setStartDate(userObj.get(3) == null ? null :userObj.get(3).toString());
                indexShowDo.setEndDate(userObj.get(4) == null ? null :userObj.get(4).toString());
                indexShowDo.setStatisticsType(userObj.get(5) == null ? null :userObj.get(5).toString());
                indexShowDo.setCount(userObj.get(6) == null ? null :userObj.get(6).toString());
                indexShowDo.setIsDelFlag(userObj.get(7) == null ? null :userObj.get(7).toString());
                dataList.add(indexShowDo);


                // 每批插入的数量
                int perReadCount = 2000;
                Integer currentRowNum = analysisContext.getCurrentRowNum();
                if (currentRowNum % perReadCount == 0) {
                    logger.info("存储dataList的大小为：{}",dataList.size());
                    System.out.printf("", "存储dataList的大小为" + dataList.size());
                    //dataList.clear();
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                logger.info("最后一批存储dataList的大小为：{}",dataList.size());
                System.out.println("最后一批存储dataList的大小为：{}"+dataList.size());
                //dataList.clear();//解析结束销毁不用的资源
            }
        });

        System.out.println("读取完毕");
        logger.debug("hello");
        return dataList;
    }



    /**
     * 读取sheet 1和2 中的值
     * @param filePath
     * @param sheetNo
     * @param headerNum
     * @return
     * @throws Exception
     */
    public List<IncrementDo> readExcelTest(String filePath,int sheetNo,int headerNum) throws Exception {

        InputStream inputStream = new FileInputStream(new File(filePath));

        System.out.println("开始读取");

        // List<DemoData> dataList = new ArrayList<DemoData>();
        List<IncrementDo> dataList = new ArrayList<IncrementDo>();

        // 读取第一个sheet ，从第2行开始读（不读取标题行）
        EasyExcelFactory.readBySax(inputStream, new Sheet(sheetNo,headerNum), new AnalysisEventListener() {

            @Override
            public void invoke(Object object, AnalysisContext analysisContext) {

                logger.info("当前行：{} 对应的对象信息为：{}", analysisContext.getCurrentRowNum(), object);
                System.out.println(("当前行对应的对象信息为"+analysisContext.getCurrentRowNum()+ object));
                // 读取到的Excel对象的值
                ArrayList userObj = (ArrayList) object;

                IncrementDo incrementDo = new IncrementDo();
                incrementDo.setId(userObj.get(0) == null ? null :userObj.get(0).toString());
                incrementDo.setField(userObj.get(1) == null ? null :userObj.get(1).toString());
                incrementDo.setLastPushDate(userObj.get(2) == null ? null :userObj.get(2).toString());
                incrementDo.setThisTimePushDate(userObj.get(3) == null ? null :userObj.get(3).toString());
                incrementDo.setHowToPush(userObj.get(4) == null ? null :userObj.get(4).toString());
                incrementDo.setIsRequired(userObj.get(5) == null ? null :userObj.get(5).toString());
                dataList.add(incrementDo);


                // 每批插入的数量
                int perReadCount = 2000;
                Integer currentRowNum = analysisContext.getCurrentRowNum();
                if (currentRowNum % perReadCount == 0) {
                    logger.info("存储dataList的大小为：{}",dataList.size());
                    System.out.printf("", "存储dataList的大小为" + dataList.size());
                    //dataList.clear();
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                logger.info("最后一批存储dataList的大小为：{}",dataList.size());
                System.out.println("最后一批存储dataList的大小为：{}"+dataList.size());
                //dataList.clear();//解析结束销毁不用的资源
            }
        });

        System.out.println("读取完毕");
        logger.debug("hello");
        return dataList;

    }
    /**
     * 读取sheet 2 全量的值
     * @param filePath
     * @return
     * @throws Exception
     */
    public List<QuantityDo> readExcelTest2(String filePath) throws Exception {

        InputStream inputStream = new FileInputStream(new File(filePath));

        System.out.println("开始读取");

        // List<DemoData> dataList = new ArrayList<DemoData>();
        List<QuantityDo> dataList = new ArrayList<QuantityDo>();

        // 读取第一个sheet ，从第2行开始读（不读取标题行）
        EasyExcelFactory.readBySax(inputStream, new Sheet(2,1), new AnalysisEventListener() {

            @Override
            public void invoke(Object object, AnalysisContext analysisContext) {

                logger.info("当前行：{} 对应的对象信息为：{}", analysisContext.getCurrentRowNum(), object);
                System.out.println(("当前行对应的对象信息为"+analysisContext.getCurrentRowNum()+ object));
                // 读取到的Excel对象的值
                ArrayList userObj = (ArrayList) object;

                QuantityDo incrementDo = new QuantityDo();
                incrementDo.setId(userObj.get(0) == null ? null :userObj.get(0).toString());
                incrementDo.setField(userObj.get(1) == null ? null :userObj.get(1).toString());
                incrementDo.setLastPushDate(userObj.get(2) == null ? null :userObj.get(2).toString());
                incrementDo.setThisTimePushDate(userObj.get(3) == null ? null :userObj.get(3).toString());
                incrementDo.setHowToPush(userObj.get(4) == null ? null :userObj.get(4).toString());
                incrementDo.setIsRequired(userObj.get(5) == null ? null :userObj.get(5).toString());
                dataList.add(incrementDo);


                // 每批插入的数量
                int perReadCount = 2000;
                Integer currentRowNum = analysisContext.getCurrentRowNum();
                if (currentRowNum % perReadCount == 0) {
                    logger.info("存储dataList的大小为：{}",dataList.size());
                    System.out.printf("", "存储dataList的大小为" + dataList.size());
                    //dataList.clear();
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                logger.info("最后一批存储dataList的大小为：{}",dataList.size());
                System.out.println("最后一批存储dataList的大小为：{}"+dataList.size());
                //dataList.clear();//解析结束销毁不用的资源
            }
        });

        System.out.println("读取完毕");
        logger.debug("hello");
        return dataList;

    }

    /**
     * 推送全量数据和增量接口道国家信易贷平台
     *
     * @param newestIncrement 增量DO
     * @param newestQuantity  全量 DO
     * @param type            上报类型（1：手动上报  2：定时任务）
     */
    public void pushDate(UploadIncrementDO newestIncrement, UploadQuantityDO newestQuantity, String type) {
        ResultVo<GetTokenResult> tokenResultResultVo = countryInterfaceUtils.getToken();
        String token = "";
        // 获取到版本号
        String version = newestIncrement.getVersion();
        // 增量接口上报状态
        String dayStatus = "";
        // 全量接口上传状态
        String allStatus = "";
        if (tokenResultResultVo.getSuccess()) {
            token = tokenResultResultVo.getData().getToken();
            try {

                ResultVo<Object> dayResult = pushIncrementDate(newestIncrement, token);
                ResultVo<Object> allResult = pushQuantityDate(newestQuantity, token);
                // 增量接口上报状态（1：上报成功 2：上报失败）
                dayStatus = dayResult.getSuccess() == true ? "1" : "2";
                // 全量接口上传状态（1：上报成功 2：上报失败）
                allStatus = allResult.getSuccess() == true ? "1" : "2";

                // 如果上报失败，发送短信通知刘赟（手机号：15901607911）、邓鑫（手机号：13167076589）
                if ("2".equals(dayStatus) || "2".equals(allStatus)) {
                    smsLogService.sendSms();
                }
                // 添加 增量和全量接口 上报的记录
                // 插入上报日志记录
                ReportRecordDo reportRecordDo = setReportRecord(type, version, dayStatus, allStatus);
                int result = reportCountryDao.addPushDateRecord(reportRecordDo);
                if (result > 0) {
                    logger.debug("上报日志记录成功！");
                } else {
                    logger.debug("上报日志记录失败！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug(" 上报全量和上报增量接口有问题，请检查！");
            }
        } else {
            // 没有获取到token,推送失败原因是：其他

            // 插入上报日志记录
            ReportRecordDo reportRecordDo = setReportRecord(type, version, dayStatus, allStatus);
            int result = reportCountryDao.addPushDateRecord(reportRecordDo);
            if (result > 0) {
                logger.debug("上报日志记录成功！");
            } else {
                logger.debug("上报日志记录失败！");
            }
        }
    }


    /**
     * @param type      上报类型（1：手动上报  2：定时任务）
     * @param version   上报版本
     * @param dayStatus 增量接口上报状态（1：上报成功 2：上报失败）
     * @param allStatus 全量接口上传状态（1：上报成功 2：上报失败）
     * @return
     */
    private ReportRecordDo setReportRecord(String type, String version, String dayStatus, String allStatus) {
        ReportRecordDo reportRecordDo = new ReportRecordDo();
        reportRecordDo.setDate(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        // 增量接口上报状态
        reportRecordDo.setIncrementStatus(dayStatus);
        // 全量接口上报状态
        reportRecordDo.setQuantityStatus(allStatus);
        reportRecordDo.setVersion(version);
        reportRecordDo.setId(IdGen.uuid());
        reportRecordDo.setType(type);
        return reportRecordDo;
    }

    /**
     * 推送增量数据
     *
     * @param
     * @param token
     */
    public ResultVo<Object> pushIncrementDate(UploadIncrementDO newestIncrement, String token) throws Exception {
        StatisticsDayRequest statisticsDayRequest = new StatisticsDayRequest();

        BeanUtil.copyProperties(newestIncrement, statisticsDayRequest);
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        String today = DateUtils.getDate(dateFormat);
        String yesterday = DateUtils.dateConvert(today, -1, dateFormat);
        statisticsDayRequest.setSjlyId(newestIncrement.getVersion());
        statisticsDayRequest.setStatisticsBeginTime(yesterday);
        statisticsDayRequest.setStatisticsEndTime(today);

        logger.debug("statisticsDayRequest 增量 实体类为： " + statisticsDayRequest.toString());
        List<StatisticsDayRequest> requestList = new ArrayList<>();
        requestList.add(statisticsDayRequest);
        logger.debug("======融资统计信息回传接口（按日增量）start======");
        ResultVo<Object> resultVo = countryInterfaceUtils.financingStatisticsByDay(requestList, token);
        logger.debug("======融资统计信息回传接口（按日增量）end=======");
        System.out.println("结果为： " + resultVo);
        return resultVo;
    }

    /**
     * 推送全量数据
     *
     * @param newestQuantity
     * @param token
     * @return
     * @throws Exception
     */
    public ResultVo<Object> pushQuantityDate(UploadQuantityDO newestQuantity, String token) throws Exception {
        StatisticsAllRequest statisticsAllRequest = new StatisticsAllRequest();
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        BeanUtil.copyProperties(newestQuantity, statisticsAllRequest);

        String today = DateUtils.getDate(dateFormat);
        String yesterday = DateUtils.dateConvert(today, -1, dateFormat);

        statisticsAllRequest.setSjlyId(newestQuantity.getVersion());
        statisticsAllRequest.setStatisticsBeginTime(yesterday);
        statisticsAllRequest.setStatisticsEndTime(today);

        logger.debug("newestQuantity 全量实体类为： " + newestQuantity.toString());
        List<StatisticsAllRequest> requestList = new ArrayList<>();
        requestList.add(statisticsAllRequest);
        logger.debug("融资统计信息回传接口（全量）start---");
        ResultVo<Object> resultVo = countryInterfaceUtils.financingStatisticsByAll(requestList, token);
        logger.debug("融资统计信息回传接口（全量）end---");

        System.out.println("结果为： " + resultVo);
        return resultVo;
    }


}

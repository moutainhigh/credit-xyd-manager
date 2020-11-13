package com.winchampion.credit.business.dao;

import com.winchampion.credit.business.domain.*;
import com.winchampion.credit.business.domain.IndexShowDo;

import java.util.List;
import java.util.Map;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/28
 * @description:上报国家平台DAO
 */
public interface ReportCountryDao {

    /**
     * 从上传记录表中获取最新长传的 全量记录信息
     * @return
     */
    UploadQuantityDO getNewestQuantity();

    /**
     * 从上传记录表中获取最新长传的 增量记录信息
     * @return
     */
    UploadIncrementDO getNewestIncrement();

    /**
     * 插入上报记录
     * @param reportRecordVo
     * @return
     */
    int addPushDateRecord(ReportRecordDo reportRecordVo);

    /**
     * 查询上传上报 记录
     * @return
     */
    List<UploadReportDo> getAllReport(Map<String, Object> map);
    /**
     * 查询上传上报 记录 条数
     * @return
     */
    int count(Map<String, Object> map);

    /**
     * 插入上传excel 记录
     * @param uploadDo
     * @return
     */
    int insertUploadRecord(UploadDo uploadDo);

    /**
     * 插入 excel 增量上传记录
     * @param incrementDO
     */
    int insertIncrementRecord(UploadIncrementDO incrementDO);
    /**
     * 插入 excel 全量上传记录
     * @param quantityDO
     */
    int insertQuantityRecord(UploadQuantityDO quantityDO);
    /**
     * 插入 excel 首页展示数据上传记录
     * @param indexShowList
     */
    int insertProductStatistics(List<IndexShowDo> indexShowList);

    /**
     * 获取最新上传的文件路径
     * @return
     */
    UploadDo getNewUpload();

    /**
     * 获取指定日期的下载文件
     * @param date
     * @return
     */
    UploadDo getDateUpload(String date);
}

package com.winchampion.credit.business.service;

import com.winchampion.credit.business.domain.*;
import com.winchampion.credit.business.vo.IncrementVo;
import com.winchampion.credit.business.domain.IndexShowDo;
import com.winchampion.credit.common.interfaces.country.res.ResultVo;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/28
 * @description:上报国家平台服务类
 */
public interface ReportCountryService {

    /**
     * 从上传记录表中获取最新长传的 全量记录信息
     *
     * @return
     */
    UploadQuantityDO getNewestQuantity();

    /**
     * 从上传记录表中获取最新长传的 增量记录信息
     *
     * @return
     */
    UploadIncrementDO getNewestIncrement();

    /**
     * 推送增量和全量数据到国家信易贷平台
     *
     * @param newestIncrement
     * @param newestQuantity
     * @param type
     */
    void pushDate(UploadIncrementDO newestIncrement, UploadQuantityDO newestQuantity, String type);

    /**
     * 查询上传上报 记录
     *
     * @return
     */
    List<UploadReportDo> getAllReport(Map<String, Object> map);

    /**
     * 查询上传上报记录数量
     *
     * @return
     */
    int count(Map<String, Object> map);

    /**
     * 插入上传记录，并把记录编号和增量，全量数据插入到数据库中
     *
     * @return
     */
    int insertUploadRecord(List<IncrementDo> incrementList, List<QuantityDo> quantityList,
                           List<IndexShowDo> indexShowList,String recordId);

    /**
     * 插入上传excel 的记录
     * @param uploadDo
     * @return
     */
    int insertUploadRecord(UploadDo uploadDo);

    ResultVo<IndexShowDo> checkIndexShowList(
                                             List<IndexShowDo> indexShowList);
  /*  ResultVo<IncrementVo> checkIncrementAndQuantity(List<IncrementVo> incrementList,
                                                    List<IncrementVo> quantityList);*/

   ResultVo<IncrementVo> checkIncrementAndQuantity(List<IncrementDo> incrementList,
                                                    List<QuantityDo> quantityList);

    UploadDo getNewUpload();

    UploadDo getDateUpload(String date);

    /**
     * 读取Excel 中的值
     */
    List<IncrementDo> readExcel(String filePath,int sheetNo,int headerNum);

    /**
     * 读取Excel Sheet 3中的值
     * @param filePath
     * @param i
     * @param i1
     * @return
     */
    List<IndexShowDo> readExcelSheet3(String filePath, int i, int i1) throws FileNotFoundException;

    /**
     * 读取sheet2中的值
     * @param filePath
     * @return
     */
    List<QuantityDo> readExcel2(String filePath) throws FileNotFoundException;

    List<QuantityDo> readExcelSheet2(String filePath, int i, int i1) throws FileNotFoundException;
}

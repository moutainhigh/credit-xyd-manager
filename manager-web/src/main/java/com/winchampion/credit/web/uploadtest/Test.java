/*
package com.winchampion.credit.web.uploadtest;

import com.winchampion.credit.business.vo.IncrementVo;
import com.winchampion.credit.common.util.ImportExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/27
 * @description:
 *//*

public class Test {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static List<IncrementVo> readExcel1(String path, int headerNum, int lastDataRowNum, int sheetIndex) {


        File fileXls = new File(path);
        List<IncrementVo> list = new ArrayList<>();
        try {
            ImportExcel ei = new ImportExcel(fileXls, headerNum, lastDataRowNum, sheetIndex);
            list = ei.getDataList(IncrementVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void readExcel(String path) {
        // 读取第一个sheet 增量
        List<IncrementVo> list = readExcel1(path, 0, 52, 0);
        Boolean is_NeedNullFlag = false;
        for (int i = 0; i < list.size(); i++) {
            String date = list.get(i).getThisTimePushDate();
            String dateDesc = list.get(i).getField();
            String ifNeed = list.get(i).getIsRequired();
            is_NeedNullFlag = (null == ifNeed) || ("".equals(ifNeed)) || (ifNeed.length() == 0);
            // 验证必填项
            if ("Y".equals(ifNeed) && !is_NeedNullFlag) {
                // 1、验证“增量”sheet中的“本次上报数据”是否均大于等于0，若不满足则提示“数据上传失败，
                // 增量数据字段XXX本次上报数据不满足要求”（XXX为对应字段中文名）
                Boolean is_dateNullFlag = (null == date) || ("".equals(date)) || (date.length() == 0);
                if (!is_dateNullFlag && Integer.parseInt(date) < 0) {
                    String errorMsg = String.format(PushDateEnums.CODE_1001.getMsg(), dateDesc);
                    logger.info("增量1提示： " + errorMsg);
                }
                // 2、“本次上报数据”中必填字段是否填写，若不满足则提示“数据上传失败，增量数据字段XXX为必填字段”（XXX为对应字段中文名）
                if (is_dateNullFlag) {
                    String errorMsg = String.format(PushDateEnums.CODE_1002.getMsg(), dateDesc);
                    logger.info("增量2提示： " + errorMsg);
                }
            }
            // 3、其他问题导致上传失败提示“请重新上传”
        }
        // 读取第二个sheet 全量
        List<IncrementVo> list2 = readExcel1(path, 0, 31, 1);
        for (int i = 0; i < list2.size(); i++) {
            String dateDesc = list2.get(i).getField();
            String lastPushDate = list2.get(i).getLastPushDate();
            String thisPushDate = list2.get(i).getThisTimePushDate();
            String ifNeed = list2.get(i).getIsRequired();
            is_NeedNullFlag = (null == ifNeed) || ("".equals(ifNeed)) || (ifNeed.length() == 0);
            // 验证必填项
            if ("Y".equals(ifNeed) && !is_NeedNullFlag) {
                // 1、“本次上报数据”是否均大于等于“上次报送数据”，若不满足则提示“数据上传失败，全量数据字段XXX本次上报数据不满足要求”
                Boolean lastIsNull = (null == lastPushDate) || ("".equals(lastPushDate)) || (lastPushDate.length() == 0);
                Boolean thisIsNull = (null == thisPushDate) || ("".equals(thisPushDate))
                        || (thisPushDate.length() == 0);
                if(!lastIsNull && !thisIsNull && Integer.parseInt(thisPushDate)<Integer.parseInt(lastPushDate)){
                    String errorMsg = String.format(PushDateEnums.CODE_1003.getMsg(), dateDesc);
                    logger.info("全量1提示： " + errorMsg);
                }
                // 2、“本次上报数据”中必填字段是否填写，若不满足则提示“数据上传失败，全量数据字段XXX为必填字段”
                if (thisIsNull) {
                    String errorMsg = String.format(PushDateEnums.CODE_1004.getMsg(), dateDesc);
                    logger.info("全量2提示： " + errorMsg);
                }
            }
        }
        // 最终插入 增量表 和 全量表中

    }

    public static void main(String args[]) {
        //String path = "D:\\wanglichao_work\\temp\\20200917.xlsx";
        String path = "D:\\wanglichao_work\\temp\\upload202010271411.xlsx";
        //hiveOptions.insertTabTemp1(tableName,path,0,5001,0);
        new Test().readExcel(path);

    }
}
*/

package com.winchampion.credit.business.vo;

import com.winchampion.credit.business.domain.IndexShowDo;
import com.winchampion.credit.common.interfaces.country.res.ResultVo;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/30
 * @description:
 */
public class UploadResponseVo {

    private ResultVo<IndexShowDo> indexResult;
    private ResultVo<IncrementVo> increAndQuan;

    public ResultVo<IndexShowDo> getIndexResult() {
        return indexResult;
    }

    public void setIndexResult(ResultVo<IndexShowDo> indexResult) {
        this.indexResult = indexResult;
    }

    public ResultVo<IncrementVo> getIncreAndQuan() {
        return increAndQuan;
    }

    public void setIncreAndQuan(ResultVo<IncrementVo> increAndQuan) {
        this.increAndQuan = increAndQuan;
    }
}

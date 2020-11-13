package com.winchampion.credit.business.vo;

import com.winchampion.credit.business.domain.CompanyFinanceNeedsDO;
import com.winchampion.credit.business.domain.CreditReportDo;
import com.winchampion.credit.business.domain.CustomerCompanyDO;
import com.winchampion.credit.business.domain.CustomerDO;

import java.io.Serializable;
import java.util.List;

/**
 * 注册账户详情 Vo
 * @author liwei
 */
public class RegisterAccountDetailVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //客户信息
    private CustomerDO customerDO;
    //客户企业信息
    private List<CustomerCompanyDO> customerCompanyDOList;
    //发布的融资需求
    private List<CompanyFinanceNeedsDO> companyFinanceNeedsDOList;
    //个人信用报告查询记录
    private List<CreditReportDo> creditReportDoList;

    public CustomerDO getCustomerDO() {
        return customerDO;
    }

    public void setCustomerDO(CustomerDO customerDO) {
        this.customerDO = customerDO;
    }

    public List<CustomerCompanyDO> getCustomerCompanyDOList() {
        return customerCompanyDOList;
    }

    public void setCustomerCompanyDOList(List<CustomerCompanyDO> customerCompanyDOList) {
        this.customerCompanyDOList = customerCompanyDOList;
    }

    public List<CompanyFinanceNeedsDO> getCompanyFinanceNeedsDOList() {
        return companyFinanceNeedsDOList;
    }

    public void setCompanyFinanceNeedsDOList(List<CompanyFinanceNeedsDO> companyFinanceNeedsDOList) {
        this.companyFinanceNeedsDOList = companyFinanceNeedsDOList;
    }

    public List<CreditReportDo> getCreditReportDoList() {
        return creditReportDoList;
    }

    public void setCreditReportDoList(List<CreditReportDo> creditReportDoList) {
        this.creditReportDoList = creditReportDoList;
    }
}

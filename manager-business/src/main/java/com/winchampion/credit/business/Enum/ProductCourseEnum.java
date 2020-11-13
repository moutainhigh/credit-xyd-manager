package com.winchampion.credit.business.Enum;

import com.winchampion.credit.common.util.StringUtils;

public enum ProductCourseEnum {
	CODE_0001("1","您提交了贷款申请"),
	/**【中国建设银行反馈】您符合本产品的申请条件**/
	CODE_0002("2","【中国建设银行反馈】您符合本产品的申请条件"),
	/**【中国建设银行反馈】您不符合本产品的申请条件**/
	CODE_0003("3","【中国建设银行反馈】您不符合本产品的申请条件"),
	/**您取消了贷款申请**/
	CODE_0004("4","您取消了贷款申请"),
	/**您发起查询申信贷所需的信用信息**/
	CODE_0005("5","您发起查询申信贷所需的信用信息"),
	/**您确认推送信用信息给中国建设银行，并同意了《贷中贷后信用信息查询授权书》**/
	CODE_0006("6","您确认推送信用信息给中国建设银行，并同意了《贷中贷后信用信息查询授权书》"),
	/**查无您的信用信息，您不符合本产品的申请条件**/
	CODE_0007("7","查无您的信用信息，您不符合本产品的申请条件"),
	/**信用查询服务暂时无法联通，请稍后再重新申请**/
	CODE_0008("8","信用查询服务暂时无法联通，请稍后再重新申请"),
	/**【中国建设银行反馈】您人脸比对通过**/
	CODE_0009("9","【中国建设银行反馈】您人脸比对通过"),
	/**【中国建设银行反馈】您的申请未通过，原因：您的人脸比对未通过。**/
	CODE_00010("10","【中国建设银行反馈】您的申请未通过，原因：您的人脸比对未通过。"),
	/**【中国建设银行反馈】您的申请已通过，预授信额度：%s，年化利率：%s**/
	CODE_00011("11","【中国建设银行反馈】您的申请已通过，预授信额度：%s元，年化利率：%s%%"),
	/**很抱歉，目前申请服务暂时无法连通或存在异常，请稍后再重新申请。**/
	CODE_00012("12","很抱歉，目前申请服务暂时无法连通或存在异常，请稍后再重新申请。"),
	/**【中国建设银行反馈】您的申请未通过，原因：您的个人信用不符合产品要求。**/
	CODE_00013("13","【中国建设银行反馈】您的申请未通过，原因：您的个人信用不符合产品要求。"),
	/**您同意了《查询使用授权（贷中贷后管理）》**/
	CODE_00014("14","您同意了《查询使用授权（贷中贷后管理）》"),
	/**【中国建设银行反馈】您的申请未通过，原因：您的个人信用不符合产品要求。**/
	CODE_00015("15","【交通银行反馈】您的申请未通过，原因：您的个人信用不符合产品要求。"),
	/**【交通银行反馈】您的申请已通过，预授信额度：%s，年化利率：%s**/
	CODE_00016("16","【交通银行反馈】您的申请已通过，预授信额度：%s元，年化利率：%s%%"),
	/**【交通银行反馈】您的申请已通过，预授信额度：%s，年化利率：%s**/
	CODE_00017("17","【交通银行反馈】您的申请已通过，预授信额度：%s元"),
	/**您填报的经营企业全称为：%s**/
	CODE_00018("18","您填报的经营企业全称为：%s"),
	/**恭喜您信用良好且符合静安区产业融资扶持政策，您将享受相应的优惠！微信扫码后开启后续流程。**/
	CODE_00019("19","恭喜您信用良好且符合静安区产业融资扶持政策，您将享受相应的优惠！微信扫码后开启后续流程。"),
	/**申请成功！微信扫码后开启后续流程。**/
	CODE_00020("20","申请成功！微信扫码后开启后续流程。");
	
	private String code;
    private String msg;
    
    private ProductCourseEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
    private ProductCourseEnum(String code, String msg, String options){
        this.code = code;
        this.msg = msg;
    }

    public static ProductCourseEnum getMsg(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (ProductCourseEnum obj : values()) {
            if(code.equals(obj.code)) {
                return obj;
            }
        }
        return null;
    }
    
    public static String getMsgStr(String value) {  
    	ProductCourseEnum[] productCourseEnums = values();  
        for (ProductCourseEnum productCourseEnum : productCourseEnums) {  
            if (productCourseEnum.code.equals(value)) {  
                return productCourseEnum.msg;  
            }  
        }  
        return null;  
    }
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {	
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

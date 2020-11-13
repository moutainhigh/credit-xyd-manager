package com.winchampion.credit.common.config;

public class Constant {
    //演示系统账户
    public static String DEMO_ACCOUNT = "test";
    //自动去除表前缀
    public static String AUTO_REOMVE_PRE = "true";
    //停止计划任务
    public static String STATUS_RUNNING_STOP = "stop";
    //开启计划任务
    public static String STATUS_RUNNING_START = "start";
    //通知公告阅读状态-未读
    public static String OA_NOTIFY_READ_NO = "0";
    //通知公告阅读状态-已读
    public static int OA_NOTIFY_READ_YES = 1;
    //部门根节点id
    public static Long DEPT_ROOT_ID = 0l;
    //缓存方式
    public static String CACHE_TYPE_REDIS ="redis";

    public static String LOG_ERROR = "error";

	/**
	 * 删除标记（0：正常；1：删除；2：审核；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	
	/**
	 * 产品类型  1.金融产品，2信用产品
	 */
	public static final String PRODUCT_FINANCIAL = "1";
	public static final String PRODUCT_CREDIT = "2";
	
	public static final String SHOW = "1";//不展示  用于产品字段是否展示标识
	
	/**
	 * 产品发布状态 0.未发布，1.已发布
	 */
	public static final String PRODUCT_NOT_ISSUE = "0";
	public static final String PRODUCT_YES_ISSUE = "1";
	public static final String PRODUCT_HAS_RECYCLED = "2";
	
	/**
	 * 产品变更操作标识  1.创建，2.编辑，3.发布，4.回收，5.删除
	 */
	public static final String PRODUCT_CHANGE_NEW = "1";
	public static final String PRODUCT_CREDIT_EDIT = "2";
	public static final String PRODUCT_CREDIT_ISSUE = "3";
	public static final String PRODUCT_CREDIT_RECYCLE = "4";
	public static final String PRODUCT_CREDIT_DEL = "5";
	
	//1：金融机构，2：信用机构
	public static final String INS_TYPE_FINACIAL = "1";
	public static final String INS_TYPE_CREDIT = "2";

	/***
	 * 前台用户REDIS登录前缀
	 */
	public static final String CUSTOMER_LOGIN_PREFIX = "XYD_LOGIN_";
	public static final String CUSTOMER_LOGIN_TOKEN_PREFIX = "XYD_LOGIN_TOKEN_";
	
	/**********************产品申请状态********************/
	/**申请中**/
	public static final String PRODUCT_APPLY_RESULT_ONE = "1";
	/**取消申请**/
	public static final String PRODUCT_APPLY_RESULT_TWO = "2";
	/**申请成功**/
	public static final String PRODUCT_APPLY_RESULT_THREE = "3";
	/**审批异常终止**/
	public static final String PRODUCT_APPLY_RESULT_FOUR = "4";
	/**审批未通过**/
	public static final String PRODUCT_APPLY_RESULT_FIVE = "5";
	/**审批通过**/
	public static final String PRODUCT_APPLY_RESULT_SIX = "6";
	/**申请失败**/
	public static final String PRODUCT_APPLY_RESULT_SEVEN = "7";

	public static final String COUSTOMER_SOURCEDESC_INTERFACE = "1";//接口注册
	
	public static final String COUSTOMER_CERTIFICATIONDESC_INTERFACE = "1";//取信接口实名
	
	public static final String  REGISTERED = "已注册";
	public static final String  NOT_UPDATED = "未注册未更新";
	public static final String  IS_DIRTY = "未注册已更新";
}

package com.winchampion.credit.business.domain.base;

import com.winchampion.credit.common.config.Constant;
import com.winchampion.credit.common.util.DateUtils;
import com.winchampion.credit.common.util.IdGen;
import com.winchampion.credit.common.util.StringUtils;
import com.winchampion.credit.user.domain.UserDO;
import com.winchampion.credit.user.utils.UserUtils;

/**
 * 中台 公共实体类，  对有 createBy、createDate、updateDate、updateBy、delFlag 有效 其他的实体不要继承
 * Created by Champion China . 
 * @author: zhangxin  
 * @date:2020年2月28日
 * @time:上午10:27:52
 * @email:zhangxin@champion-credit.com  
 * @version: 1.0
 */
public abstract class UserBaseDO {
	
	//
	private String id;
	
	//创建时间
	private String createDate;
	//创建人
	private String createBy;
	//更新时间
	private String updateDate;
	//更新人
	private String updateBy;
		
		
	//是否删除
	private String delFlag;//删除标记（0：正常；1：删除；2：审核）
		
	
	/**
	 * 用户信息
	 */
	private UserDO updateUserDo;
	
	/**
	 * 保存初始化 设置默认值
	 * @author: zhangxin  
	 * @date:2020年2月27日  下午1:36:50
	 */
	public void saveBefore() {
		if(StringUtils.isBlank(id)) {
			delFlag = Constant.DEL_FLAG_NORMAL;
			id = IdGen.uuid();
			createBy = UserUtils.getUserId();
			createDate = DateUtils.getSysDateAndTime();
		}else {
			updateBy = UserUtils.getUserId();
			updateDate = DateUtils.getSysDateAndTime();
		}
	}
	
	
	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：更新人
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新人
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：是否删除
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：是否删除
	 */
	public String getDelFlag() {
		return delFlag;
	}


	public UserDO getUpdateUserDo() {
		return updateUserDo;
	}

	public void setUpdateUserDo(UserDO updateUserDo) {
		this.updateUserDo = updateUserDo;
	}

}

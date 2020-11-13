package com.winchampion.credit.common.util;

import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author weichen
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{
	
	public static String money(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$"); 
		if(StringUtils.isNotEmpty(str)&&pattern.matcher(str).matches()) {
			//minAvailable = Integer.valueOf(minAvailable)/
			BigDecimal bigDecimal = new BigDecimal(str);
			// 转换为万元（除以10000）
			BigDecimal decimal = bigDecimal.divide(new BigDecimal("10000"));
			 // 保留两位小数
			/*DecimalFormat formater = new DecimalFormat("0");
			// 四舍五入
		    formater.setRoundingMode(RoundingMode.HALF_UP);*/
			// 格式化完成之后得出结果
			String formatNum = decimal.toPlainString();
			return formatNum;
		}
		return null;
	}
	/**
	 * 获取随随机码  只返回数字
	 * @author: zhangxin
	 * @date:2020年3月2日  下午1:44:44
	 * @param num 长度
	 * @return
	 */
	public static String getRandomNumberCode(int num ) {
		StringBuilder sbd = new StringBuilder();
		// 随机数
		Random random = new Random();

		for(int i = 0 ; i < num ; i++) {
			sbd.append(random.nextInt(10));
		}
		return sbd.toString();
	}
}

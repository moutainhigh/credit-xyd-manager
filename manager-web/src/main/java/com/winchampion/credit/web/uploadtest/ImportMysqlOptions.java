package com.winchampion.credit.web.uploadtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @author 王立朝 wanglichao@champion-credit.com
 * @date 2020/10/27
 * @description:
 */
public class ImportMysqlOptions {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://192.168.25.185:3309/liwei_bootdo?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    private static String user = "xyd-manager";
    private static String password = "xyd-manager";

    private static Connection con = null;
    private static Statement state = null;
    private static ResultSet res = null;

    /**
     * 加载驱动,创建连接
     */
    public void init() throws ClassNotFoundException, SQLException {
        logger.info("初始化加载。。。。。。");
        Class.forName(driverName);
        con = DriverManager.getConnection(url, user, password); //有用户名密码
        state = con.createStatement();
    }


}

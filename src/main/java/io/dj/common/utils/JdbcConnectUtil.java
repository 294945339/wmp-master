package io.dj.common.utils;

import com.xiaoleilu.hutool.setting.dialect.Props;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/7
 **/
public class JdbcConnectUtil {

    private final Props props = new Props("db-otherSys.properties");
    private final String CRM_URL = props.getProperty("crmUrl");
    private final String CRM_USER = props.getStr("crmUser");
    private final String CRM_PASSWORD = props.getStr("crmPassword");
    private final String IPOS_URL = props.getProperty("iposUrl");
    private final String IPOS_USER = props.getStr("iposUser");
    private final String IPOS_PASSWORD = props.getStr("iposPassword");
    private final String DAP_URL = props.getProperty("dapUrl");
    private final String DAP_USER = props.getStr("dapUser");
    private final String DAP_PASSWORD = props.getStr("dapPassword");
    private final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private final String SQLSERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public Connection getIposConnect(){
        return getMysqlConnect(IPOS_URL,IPOS_USER,IPOS_PASSWORD);
    }

    public Connection getCrmConnect(){
        return getSqlserverConnect(CRM_URL,CRM_USER,CRM_PASSWORD);
    }

    public Connection getDapConnect(){
        return getMysqlConnect(DAP_URL,DAP_USER,DAP_PASSWORD);
    }

    public Connection getMysqlConnect(String url, String user, String password) {
        return getConnect(MYSQL_DRIVER, url, user, password);
    }

    public Connection getSqlserverConnect(String url, String user, String password) {
        return getConnect(SQLSERVER_DRIVER, url, user, password);
    }

    private Connection getConnect(String driver, String url, String user, String password) {
        Connection trueConn = null;
        try {
            Class.forName(driver).newInstance();
            trueConn = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            System.out.println("数据连接出错了:" + ex.toString());
        }
        return trueConn;
    }

}

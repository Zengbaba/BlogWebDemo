package com.zengrui.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Zeng Rui on 2017/8/21.
 */
public class DBconnect {
    private Connection connection;
    private String driver;
    private String url;
    private String username;
    private String password;
    //无参构造方法
    public DBconnect(){};
    //有参
    public DBconnect(String driver,String url,String username,String password){
        this.driver = driver;
        this.password = password;
        this.url = url;
        this.username =username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
    //连接数据库方法
    public Connection getDBConnection() throws SQLException, ClassNotFoundException {
        if(connection ==null){
            Class.forName(this.driver);
            connection = DriverManager.getConnection(url,username,password);
        }
        return connection;
    }

    //关闭连接方法
    public void closeConnection() throws Exception{
        if (connection!= null && connection.isClosed())
            connection.close();
    }





}

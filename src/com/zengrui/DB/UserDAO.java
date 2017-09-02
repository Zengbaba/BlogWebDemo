package com.zengrui.DB;

import com.zengrui.DB.Inerface.Dao;
import com.zengrui.DB.Inerface.Model;
import com.zengrui.DB.Model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeng Rui on 2017/8/21.
 */
public class UserDAO implements Dao {
    User user = null;
    String erroMsg = null;
    Connection connection = null;
    DBconnect dBconnect =null;

    public UserDAO() throws Exception {
        //数据库连接
        dBconnect = new DBconnect("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://localhost;databaseName=blogWeb_database",
                "zengrui", "123456");
        connection = dBconnect.getDBConnection();
    }

    //插入
    @Override
    public boolean insert(Model model) throws Exception {
        user = (User) model;
        String sql = "INSERT INTO tb_user VALUES ('" + user.getName() + "','" + user.getPassword() + "')";
        if (connection != null) {
            Statement statement = connection.createStatement();
            if (statement.executeUpdate(sql) != 1) {
                return false;
            }
        }
        return true;

    }

    @Override
    public boolean update(Model model, String attribute, String condition) throws Exception {
        user = (User) model;
        String sql = null;
        if (attribute.equals("password"))
            sql = "UPDATE  tb_user SET password ='" + user.getPassword() + "' WHERE name ='" + condition + "'";
        else if (attribute.equals("name"))
            sql = "UPDATE  tb_user SET name ='" + user.getName() + "' WHERE name ='" + condition + "'";
        else return false;
        System.out.println(sql);
        if (connection != null) {
            Statement statement = connection.createStatement();
            if (statement.executeUpdate(sql) != 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean delete(String attribute, String condition) throws Exception {
        String sql = null;
        sql = "DELETE FROM tb_user WHERE name ='" + condition + "'";
        System.out.println(sql);
        if (connection != null) {
            Statement statement = connection.createStatement();
            if (statement.executeUpdate(sql) != 1) {
                return false;
            }
        }
        return true;
    }

    //按照属性查询
    @Override
    public Model queryById(Model model) throws Exception {
        user = (User) model;
        String sql = "SELECT * FROM tb_user WHERE name = '" + user.getName() + "'";
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                User result = new User();
                result.setName(rs.getString(1));
                result.setPassword(rs.getString(2));
                return result;
            }

        }
        return null;
    }

    @Override
    //返回全部User
    public List queryAll() throws Exception {
        String sql = null;
        sql = "SELECT * FROM tb_user";
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            rs = statement.executeQuery(sql);
            List<User> list = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString(1));
                user.setPassword(rs.getString(2));
                list.add(user);

            }
            return list;
        }
        return null;
    }

    public boolean getLogin(User user) throws Exception {
        /*检查登陆*/
        User check = null;

        if ((check = (User) queryById(user)) != null) {
            if (check.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void closeConn() throws Exception {
        dBconnect.closeConnection();
    }


}

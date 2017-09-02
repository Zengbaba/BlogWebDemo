package com.zengrui.DB;

import com.zengrui.DB.Inerface.Dao;
import com.zengrui.DB.Inerface.Model;
import com.zengrui.DB.Model.Blog;
import com.zengrui.DB.Model.Blog;
import com.zengrui.DB.Model.Blog;
import com.zengrui.DB.Model.Content;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeng Rui on 2017/8/23.
 */
public class BlogDAO implements Dao {
    Blog blog = null;
    Connection connection = null;
    DBconnect dBconnect =null;

    public BlogDAO() {
        //数据库连接
        dBconnect = new DBconnect("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://localhost;databaseName=blogWeb_database",
                "zengrui", "123456");
        try {
            connection = dBconnect.getDBConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载驱动失败");
        }
    }

    @Override
    public boolean insert(Model model) throws SQLException {
        blog = (Blog) model;
        String sql = "INSERT INTO tb_blog VALUES ('" + blog.getId() + "','" + blog.getTitel() + "','" + blog.getTime() +
                "','" + blog.getData() + "','" + blog.getAuthor() + "')";
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
        blog= (Blog) model;
        String sql = null;

        if(attribute.equals("titel")){
            sql = "UPDATE tb_blog SET titel ='"+blog.getTitel()+"' WHERE id='"+condition+"'";
        }

        if(attribute.equals("date")){
            sql = "UPDATE tb_blog SET date ='"+blog.getData()+"' WHERE id='"+condition+"'";
        }

        if(attribute.equals("all")){
            sql = "UPDATE tb_blog SET titel ='"+blog.getTitel()+"',date = '"+blog.getData()+"' WHERE id='"+condition+"'";
        }

        if (connection != null && attribute!=null) {
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
        sql = "DELETE FROM tb_blog WHERE " + attribute + " ='" + condition + "'";
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
    public Model queryById(Model model) throws SQLException {
        blog = (Blog) model;
        String sql = "SELECT * FROM tb_blog WHERE id = '" + blog.getId() + "'";
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                Blog result = new Blog();
                result.setId(rs.getString(1));
                result.setTitel(rs.getString(2));
                result.setTime(rs.getTime(3));
                result.setData(rs.getDate(4));
                result.setAuthor(rs.getString(5));
                return result;
            }

        }
        return null;
    }

    @Override
    public List queryAll() throws Exception {
        String sql = null;
        sql = "SELECT * FROM tb_blog";
        return getList(sql);
    }

    public List search(String keyword) throws SQLException {
        String sql = null;
        sql = "SELECT * FROM tb_blog WHERE titel LIKE '%" + keyword + "%' OR CONVERT(varchar,date,120) Like '%" + keyword + "%'" +
                " OR CONVERT(varchar,time,108) Like '%" + keyword + "%'" + "OR author LIKE '%" + keyword + "%'";
        return getList(sql);

    }

    public List searchid(String keyword) throws SQLException {
        String sql = null;
        sql = "SELECT * FROM tb_blog WHERE id ='" + keyword + "'";
        return getList(sql);

    }


    private List getList(String sql) throws SQLException {

        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            rs = statement.executeQuery(sql);
            List<Blog> list = new ArrayList<Blog>();

            while (rs.next()) {
                Blog blog2 = new Blog();
                blog2.setId(rs.getString(1));
                blog2.setTitel(rs.getString(2));
                blog2.setTime(rs.getTime(3));
                blog2.setData(rs.getDate(4));
                blog2.setAuthor(rs.getString(5));
                list.add(blog2);
            }
            return list;


        }
        return null;
    }

    @Override
    public void closeConn() throws Exception {
        dBconnect.closeConnection();
    }
}

package com.zengrui.DB;

import com.zengrui.DB.Inerface.Dao;
import com.zengrui.DB.Inerface.Model;
import com.zengrui.DB.Model.Blog;
import com.zengrui.DB.Model.Content;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Zeng Rui on 2017/8/31.
 */
public class ContentDAO implements Dao {
    Content content  = null;
    Connection connection = null;
    DBconnect dBconnect =null;

    public ContentDAO() {
        //数据库连接
         dBconnect= new DBconnect("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://localhost;databaseName=blogWeb_database",
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
    public boolean insert(Model model) throws Exception {
        content= (Content) model;
        char[] titleChar =content.getTitel();
        char[] blogChar = content.getBlog();
        StringBuffer titel = new StringBuffer();
        StringBuffer blog = new StringBuffer();
        for (int i = 0; i < titleChar.length; i++) {
            titel.append(titleChar[i]);
        }
        for (int i = 0; i < blogChar.length; i++) {
            blog.append(blogChar[i]);
        }
        String sql = "INSERT INTO tb_content VALUES ('" + content.getId() + "','" + titel + "','"+blog+ "')";
        if (connection != null) {
            Statement statement = connection.createStatement();
            if (statement.executeUpdate(sql) != 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(Model model, String attribute, String condition) throws SQLException {
        String sql = null;
        //字符串转换
        content= (Content) model;
        char[] titleChar =content.getTitel();
        char[] blogChar = content.getBlog();
        StringBuffer titel = new StringBuffer();
        StringBuffer blog = new StringBuffer();
        for (int i = 0; i < titleChar.length; i++) {
            titel.append(titleChar[i]);
        }
        for (int i = 0; i < blogChar.length; i++) {
            blog.append(blogChar[i]);
        }
        if(attribute.equals("titel")){
            sql = "UPDATE tb_content SET titel ='"+titel+"' WHERE id='"+condition+"'";
        }

        if(attribute.equals("blog")){
            sql = "UPDATE tb_content SET blog ='"+blog+"' WHERE id='"+condition+"'";
        }

        if(attribute.equals("all")){
            sql = "UPDATE tb_content SET blog ='"+blog+"',titel ='"+titel+"' WHERE id='"+condition+"'";
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
        if(!attribute.equals("") && !condition.equals("")){
            String sql = null;
            sql = "DELETE FROM tb_content WHERE "+attribute+" ='" + condition + "'";
            System.out.println(sql);
            if (connection != null ) {
                Statement statement = connection.createStatement();
                if (statement.executeUpdate(sql) != 1) {
                    return false;
                }
            }
            return true;
        }
       return false;
    }

    @Override
    public Model queryById(Model model) throws SQLException {
        content = (Content) model;
        String sql = "SELECT * FROM tb_content WHERE id = '" + content.getId() + "'";
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                content.setId(rs.getString(1));
                content.setTitel(rs.getString(2).toCharArray());
                content.setBlog(rs.getString(3).toCharArray());
                return content;
            }

        }
        return null;
    }

    @Override
    public List queryAll() throws Exception {
        return null;
    }

    @Override
    public void closeConn() throws Exception {
        dBconnect.closeConnection();
    }
}


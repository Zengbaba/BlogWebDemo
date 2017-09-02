package com.zengrui.DB;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import com.zengrui.DB.Model.Blog;
import com.zengrui.DB.Model.Content;
import com.zengrui.DB.Model.User;

import java.sql.Date;
import java.sql.Time;


/**
 * Created by Zeng Rui on 2017/8/22.
 */
public class test {
    public static void main(String[] args)  {
        String a ="第3篇博客";
        String b ="更新11";
        String c =a+"";
        System.out.println(radomNum());
        System.out.println(c.equals(a));
/*

        Content content =new Content("000002",a.toCharArray(),b.toCharArray());
        ContentDAO contentDAO = new ContentDAO();
        BlogDAO blogDAO = new BlogDAO();*/
        try {
           /* java.util.Date date =  new java.util.Date();
            System.out.println(new Time(date.getTime()));
            System.out.println(blogDAO.searchid("111111"));*/
            /*for (int i = 4; i < 6; i++) {
                Blog blog = new Blog("123456", "曾睿的第" + Integer.toString(i) + "篇博客", new Time(date.getTime()),
                new Date(Date.valueOf("2017-1-12").getTime()), "zengrui");
                System.out.println("INSERT INTO tb_user VALUES ('" + blog.getId() + "','" + blog.getTitel() + "','" + blog.getTime() +
                        "','" + blog.getData() + "','" + blog.getAuthor() + "')");
                blogDAO.insert(blog);
            }*/
            //contentDAO.insert(content);
            //contentDAO.update(content,"blog","000002");
            //contentDAO.delete("id","000001");
            /*StringBuffer c = new StringBuffer();
            for (int i = 0; i < 4; i++) {
                c.append(((Content)contentDAO.queryById(new Content("000002",null,null))).getBlog()[i]);
            }

            System.out.println(c);
*/

            } catch (Exception e) {
            e.printStackTrace();
        }



        /*Blog blog = new Blog("000001","曾睿的第一篇博客",new Time(Time.valueOf("12:36:00").getTime()),
                new Date(Date.valueOf("2017-1-12").getTime()),"曾睿");
        System.out.println(blog.getAuthor()+blog.getId()+"   "+blog.getData()+"           "+blog.getTime());
        System.out.println("INSERT INTO tb_user VALUES ('" + blog.getId() + "','" + blog.getTitel() + "','"+blog.getTime()+
                "','"+blog.getData()+"','"+blog.getAuthor()+"')");*/
       /* User user = new User();
        UserDAO userDAO = null;
        try {
            userDAO = new UserDAO();
            user.setPassword("123456");
            user.setName("zengrui");
            boolean s = userDAO.getLogin(user);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();

        }
*/

        /*try {
            UserDAO userDAO = new UserDAO();
            User user = new User();
            user.setName("lijihong");
            user.setPassword("971226");
            userDAO.insert(user);
           //userDAO.delete("name","jack");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败！");
        }*/

    }

    private static String radomNum() {
        String num = "";
        for(int i = 0;i<=5;i++){
            int bit = (int)(Math.random()*10);
            System.out.println(bit);
            num += Integer.toString(bit);
        }
        return num;
    }
}

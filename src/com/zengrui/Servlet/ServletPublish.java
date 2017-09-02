package com.zengrui.Servlet;

import com.zengrui.DB.BlogDAO;
import com.zengrui.DB.ContentDAO;
import com.zengrui.DB.Model.Blog;
import com.zengrui.DB.Model.Content;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Timer;

/**
 * Created by Zeng Rui on 2017/8/31.
 */
@WebServlet(name = "ServletPublish",urlPatterns={"/PublishServlet"})
public class ServletPublish extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        char[] titel = ((String)request.getParameter("titel")).toCharArray();
        char[] blogContent = ((String)request.getParameter("content")).toCharArray();
        String year =request.getParameter("year");
        String month =request.getParameter("month");
        String day =request.getParameter("day");
        String date =year+"-"+month+"-"+day;
        String id = radomNum();

        try {
            BlogDAO blogDAO = new BlogDAO();
            ContentDAO contentDAO = new ContentDAO();
            //检查id是否重复
             while (!blogDAO.searchid(id).isEmpty()){
                id =radomNum();
            }
            java.util.Date time = new  java.util.Date();
            Blog blog = new Blog(id,(String)request.getParameter("titel"),
                    new Time(time.getTime()),
                    new Date(Date.valueOf(date).getTime()),
                    (String)(request.getSession().getAttribute("name")));

            Content content =new Content(id,titel,blogContent);
            blogDAO.insert(blog);
            contentDAO.insert(content);
            blogDAO.closeConn();
            contentDAO.closeConn();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("数据库插入失败！");
        }
        //跳转预览界面
        response.sendRedirect("/MainPageServlet?method=login");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String purpose = request.getParameter("purpose");
        if(!purpose.equals("")&&purpose !=null){

            if(purpose.equals("homepage")){
                response.sendRedirect("/MainPageServlet?method=login");

            }

            if(purpose.equals("quit")){
                response.sendRedirect("/MainPageServlet?method=quit");
            }
        }









    }

    private static String radomNum() {
        String num = "";
        for(int i = 0;i<=5;i++){
            int bit = (int)(Math.random()*10);
            num += Integer.toString(bit);
        }
        return num;
    }

}

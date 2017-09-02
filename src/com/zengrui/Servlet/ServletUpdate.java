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
import java.sql.Time;

/**
 * Created by Zeng Rui on 2017/9/2.
 */
@WebServlet(name = "ServletUpdate",urlPatterns = {"/UpdateServlet"})
public class ServletUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //处理更新请求
        request.setCharacterEncoding("utf-8");
        char[] titel = ((String)request.getParameter("titel")).toCharArray();
        char[] blogContent = ((String)request.getParameter("content")).toCharArray();
        String year =request.getParameter("year");
        String month =request.getParameter("month");
        String day =request.getParameter("day");
        String date =year+"-"+month+"-"+day;
        try {
            BlogDAO blogDAO = new BlogDAO();
            ContentDAO contentDAO = new ContentDAO();
            java.util.Date time = new  java.util.Date();
            String blogname = "blog"+request.getParameter("count");
            String id = ((Blog)request.getSession().getAttribute(blogname)).getId();
            Blog blog = new Blog(id,
                    (String)request.getParameter("titel"),
                    new Time(time.getTime()),
                    new Date(Date.valueOf(date).getTime()),
                    (String)(request.getSession().getAttribute("name")));

            Content content =new Content(id,titel,blogContent);
            blogDAO.update(blog,"all",id);
            contentDAO.update(content,"all",id);
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


    }
}

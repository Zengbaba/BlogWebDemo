package com.zengrui.Servlet;

import com.sun.corba.se.spi.activation.Server;
import com.zengrui.DB.BlogDAO;
import com.zengrui.DB.ContentDAO;
import com.zengrui.DB.Model.Blog;
import com.zengrui.DB.Model.Content;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.DoubleToIntFunction;

/**
 * Created by Zeng Rui on 2017/8/22.
 */
@WebServlet(name = "ServletMainPage", urlPatterns = {"/MainPageServlet"})
public class ServletMainPage extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("method");//获取方法名
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("没有传递method参数,请给出你想调用的方法");
        }
        System.out.println(name);
        Class c = this.getClass();//获得当前类的Class对象
        Method method = null;
        try {
            //获得Method对象
            method = c.getMethod(name, HttpServletRequest.class, HttpServletResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("没有找到" + name + "方法，请检查该方法是否存在");
        }

        try {
            method.invoke(this, request, response);//反射调用方法
        } catch (Exception e) {
            System.out.println("你调用的方法" + name + ",内部发生了异常");
            throw new RuntimeException(e);
        }


    }

    //处理登录请求的方法
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = null;
        name = (String) request.getSession().getAttribute("name");
        System.out.println(name);
        //设置blog对象
        Blog blog;
        BlogDAO blogDAO = new BlogDAO();
        //查询对应作者的所有博客
        List<Blog> bloglist = blogDAO.queryAll();
        //数量
        int blognum = bloglist.size();
        //把所有博客对象放入request
        for (int i = 0; i < blognum; i++) {
            blog = bloglist.get(i);
            String blogname = "blog" + Integer.toString(i);
            //发送给主页的jsp
            request.getSession().setAttribute(blogname, blog);
        }
        request.getSession().setAttribute("count", blognum);
        request.setAttribute("name", name);
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/jsp/welcome.jsp?curPage=1");
        blogDAO.closeConn();
        dispatcher.forward(request, response);

    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("delete");
        String page = request.getParameter("curPage");
        BlogDAO blogDAO = new BlogDAO();
        ContentDAO contentDAO = new ContentDAO();
        contentDAO.delete("id",id);
        blogDAO.delete("id", id);

        //把更新后的blog发给主页
        List<Blog> bloglist = blogDAO.queryAll();
        //数量
        int blognum = bloglist.size();
        Blog blog = new Blog();
        //把所有博客对象放入request
        for (int i = 0; i < blognum; i++) {
            blog = bloglist.get(i);
            String blogname = "blog" + Integer.toString(i);
            //发送给主页的jsp
            request.getSession().setAttribute(blogname, blog);
        }
        request.getSession().setAttribute("count", blognum);
        RequestDispatcher dispatcher;
        blogDAO.closeConn();
        contentDAO.closeConn();
        dispatcher = request.getRequestDispatcher("/jsp/welcome.jsp?curPage=" + page);
        dispatcher.forward(request, response);
    }

    public void search(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String keyword = request.getParameter("keyword");
        //查找
        BlogDAO blogDAO = new BlogDAO();
        List<Blog> bloglist = blogDAO.search(keyword);
        //数量
        int blognum = bloglist.size();
        Blog blog = new Blog();
        //把所有博客对象放入request
        for (int i = 0; i < blognum; i++) {
            blog = bloglist.get(i);
            String blogname = "blog" + Integer.toString(i);
            //发送给主页的jsp
            request.getSession().setAttribute(blogname, blog);
        }
        request.getSession().setAttribute("count", blognum);
        blogDAO.closeConn();
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/jsp/welcome.jsp?curPage=1");
        dispatcher.forward(request, response);
    }

    public void show(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取第几条博客
        String blogname = "blog"+request.getParameter("count");
        String id = ((Blog)request.getSession().getAttribute(blogname)).getId();
        ContentDAO contentDAO = new ContentDAO();
        BlogDAO blogDAO =new BlogDAO();
        Content content = new Content();
        Blog blog = new Blog();
        blog.setId(id);
        content.setId(id);
        content.setBlog(((Content)contentDAO.queryById(content)).getBlog());
        content.setTitel(((Content)contentDAO.queryById(content)).getTitel());
        String date = ((Blog)blogDAO.queryById(blog)).getData().toString()+
                " "+((Blog)blogDAO.queryById(blog)).getTime().toString();
        blog.setAuthor(((Blog)blogDAO.queryById(blog)).getAuthor());
        request.setAttribute("content", content);
        request.setAttribute("date",date);
        request.setAttribute("blog",blog);
        blogDAO.closeConn();
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/jsp/show.jsp");
        dispatcher.forward(request, response);

    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String blogname = "blog"+request.getParameter("count");
        String id = ((Blog)request.getSession().getAttribute(blogname)).getId();
        ContentDAO contentDAO = new ContentDAO();
        BlogDAO blogDAO =new BlogDAO();
        Content content = new Content();
        Blog blog = new Blog();
        blog.setId(id);
        content.setId(id);
        content.setBlog(((Content)contentDAO.queryById(content)).getBlog());
        content.setTitel(((Content)contentDAO.queryById(content)).getTitel());
        //日期的转换
        Date date = ((Blog)blogDAO.queryById(blog)).getData();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(date);
        sdf =new SimpleDateFormat("MM");
        String month = sdf.format(date);
        sdf =new SimpleDateFormat("dd");
        String day = sdf.format(date);

        blog.setAuthor(((Blog)blogDAO.queryById(blog)).getAuthor());
        request.setAttribute("content", content);
        request.setAttribute("year",year);
        request.setAttribute("month",month);
        request.setAttribute("day",day);
        request.setAttribute("blog",blog);
        request.setAttribute("count",request.getParameter("count"));
        blogDAO.closeConn();
        RequestDispatcher dispatcher;
        dispatcher = request.getRequestDispatcher("/jsp/update.jsp");
        dispatcher.forward(request, response);

    }
    public void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("/jsp/login.jsp");

    }


}

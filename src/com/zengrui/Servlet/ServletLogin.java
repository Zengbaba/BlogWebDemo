package com.zengrui.Servlet;

import com.zengrui.DB.Model.User;
import com.zengrui.DB.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Zeng Rui on 2017/8/20.
 */
@WebServlet(name="login",urlPatterns={"/loginServlet"})
public class ServletLogin extends HttpServlet {
    public  void service(HttpServletRequest request, HttpServletResponse response)
    throws ServletException,IOException{
        request.setCharacterEncoding("utf-8");
        String erroMsg = " ";
        //requestDispatcher request收发
        RequestDispatcher requestDispatcher;
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        User user =new User();
        user.setPassword(pwd);
        user.setName(username);
        try {
            //判断密码
            UserDAO userDAO =new UserDAO();
            System.out.println(userDAO.getLogin(user));
            if(userDAO.getLogin(user)){
                //获取session对象
                HttpSession session = request.getSession(true);
                //设置属性，传入用户名
                session.setAttribute("name",username);
                userDAO.closeConn();
                requestDispatcher = request.getRequestDispatcher("/MainPageServlet?method=login");
                requestDispatcher.forward(request,response);
            }
            else {
                erroMsg += "密码错误或用户名不存在！";
            }
        } catch (Exception e) {
            e.printStackTrace();
            erroMsg += "密码错误或用户名不存在！";
        }
        finally {
            if(!erroMsg.equals(" ")){
                requestDispatcher = request.getRequestDispatcher("/jsp/login.jsp");
                request.setAttribute("erroMsg",erroMsg);
                requestDispatcher.forward(request,response);
            }
        }

    }
}

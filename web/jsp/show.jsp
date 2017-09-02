<%@ page import="com.zengrui.DB.Model.Content" %>
<%@ page import="com.zengrui.DB.Model.Blog" %><%--
  Created by IntelliJ IDEA.
  User: Zeng Rui
  Date: 2017/9/1
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=((Content) request.getAttribute("content")).getTitel()%>
    </title>
    <%request.setCharacterEncoding("utf-8");%>
</head>
<body>
<h1><%=((Content) request.getAttribute("content")).getTitel()%>
</h1>
<h2><%=(String) request.getAttribute("date")%>                         <%=((Blog) request.getAttribute("blog")).getAuthor()%>
</h2>
<div><%=((Content) request.getAttribute("content")).getBlog()%>
</div>
</body>
</html>

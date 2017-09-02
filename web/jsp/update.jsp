<%@ page import="com.zengrui.DB.Model.Content" %><%--
  Created by IntelliJ IDEA.
  User: Zeng Rui
  Date: 2017/9/1
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <title>修改文章</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css" media="all" />
    <%request.setCharacterEncoding("utf-8");%>
</head>
<body>
<!-- Header -->
<div id="header">
    <div class="shell">
        <!-- Logo + Top Nav -->
        <div id="top">
            <h1><a href="/PublishServlet?purpose=homepage"><%=request.getSession().getAttribute("name")%></a></h1>
            <div id="top-navigation">
                欢迎&nbsp;&nbsp;<a href="/PublishServlet?purpose=homepage"><strong><%=request.getSession().getAttribute("name")%></strong></a>
                <span>|</span>
                <a href="/PublishServlet?purpose=quit">退出</a>
            </div>
        </div>
        <!-- End Logo + Top Nav -->

        <!-- Main Nav -->
        <div id="navigation">
            <ul>
                <li><a href="/PublishServlet?purpose=homepage" ><span>首页</span></a></li>
                <li><a href="publish" class="active"><span>发布文章</span></a></li>
            </ul>
        </div>
        <!-- End Main Nav -->
    </div>
</div>
<!-- End Header -->

<!-- Container -->
<div id="container">
    <div class="shell">

        <!-- Small Nav -->
        <div class="small-nav">
            <a href="index">首页</a>
            <span>&gt;</span>
            发布文章
        </div>
        <!-- End Small Nav -->

        <br />
        <!-- Main -->
        <div id="main">
            <div class="cl">&nbsp;</div>

            <!-- Content -->
            <div id="content">


                <!-- Box -->
                <div class="box">
                    <!-- Box Head -->
                    <div class="box-head">
                        <h2>发布文章</h2>
                    </div>
                    <!-- End Box Head -->

                    <form action="/UpdateServlet?count=<%=request.getAttribute("count")%>" method="post">

                        <!-- Form -->
                        <div class="form">
                            <p>
                                <span class="req">最多100字</span>
                                <label>标题<span>*</span></label>
                                <input type="text" class="field size1" name="titel" value="<%=((Content)request.getAttribute("content")).getTitel()%>" />
                            </p>
                            <p class="inline-field">
                                <label>Date</label>
                                <input type="text" class="field size2" name="year" value="<%=request.getAttribute("year")%>"/>
                                <input type="text" class="field size3" name="month" value="<%=request.getAttribute("month")%>"/>
                                <input type="text" class="field size3" name="day" value="<%=request.getAttribute("day")%>"/>
                            </p>

                            <p>
                                <label>内容<span>*</span></label>
                                <textarea class="field size1" rows="10" cols="30" name="content"><%=((Content)request.getAttribute("content")).getBlog()%>
                                </textarea>
                            </p>

                        </div>
                        <!-- End Form -->

                        <!-- Form Buttons -->
                        <div class="buttons">
                            <input type="button" class="button" value="preview" />
                            <input type="submit" class="button" value="提交" />
                        </div>
                        <!-- End Form Buttons -->
                    </form>
                </div>
                <!-- End Box -->

            </div>
            <!-- End Content -->

            <div class="cl">&nbsp;</div>
        </div>
        <!-- Main -->
    </div>
</div>
<!-- End Container -->

<!-- Footer -->
<div id="footer">
    <div class="shell">
        <span class="left">&copy; 2010 - CompanyName</span>
        <span class="right">
			Design by <a href="http://chocotemplates.com" target="_blank" title="The Sweetest CSS Templates WorldWide">Chocotemplates.com</a>
		</span>
    </div>
</div>
<!-- End Footer -->

</body>
</html>
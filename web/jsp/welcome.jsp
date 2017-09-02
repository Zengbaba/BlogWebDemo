<%@ page import="com.zengrui.DB.Model.Blog" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--
  Created by IntelliJ IDEA.
  User: Zeng Rui
  Date: 2017/8/20
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title><%=request.getSession().getAttribute("name")%>的博客</title>
    <link rel="stylesheet" href="/css/style.css" type="text/css" media="all"/>
    <%request.setCharacterEncoding("utf-8");%>
    <%
        String check = (String) request.getParameter("msg");
        String erroMsg = null;
        if (check != null) {
            if (check.equals("1"))
                erroMsg = "当前为最后一页！";
            if (check.equals("2"))
                erroMsg = "当前为第一页！";
    %>
    <script type="text/javascript">
        alert("<%=erroMsg%>");
    </script>
    <%

        }%>

</head>
<body>
<!-- Header -->
<div id="header">
    <div class="shell">
        <!-- Logo + Top Nav -->
        <div id="top">
            <h1><a href="#"><%=request.getSession().getAttribute("name")%>
            </a></h1>
            <div id="top-navigation">
                欢迎&nbsp;&nbsp;<a href="#"><strong><%=request.getSession().getAttribute("name")%>
            </strong></a>
                <span>|</span>
                <a href="/MainPageServlet?method=quit">退出</a>
            </div>
        </div>
        <!-- End Logo + Top Nav -->

        <!-- Main Nav -->
        <div id="navigation">
            <ul>
                <li><a href="/jsp/welcome.jsp" class="active"><span>首页</span></a></li>
                <li><a href="/jsp/publish.jsp"><span>发布文章</span></a></li>
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
            <a href="#">首页</a>
            <span>&gt;</span>
            文章列表
        </div>
        <!-- End Small Nav -->

        <br/>
        <!-- Main -->
        <div id="main">
            <div class="cl">&nbsp;</div>

            <!-- Content -->
            <div id="content">

                <!-- Box -->
                <div class="box">
                    <!-- Box Head -->
                    <div class="box-head">
                        <h2 class="left">文章列表</h2>
                        <div class="right">
                            <form action="/MainPageServlet" method="get" id="search">
                                <label>搜索文章</label>
                                <input type="text" class="field small-field" name="keyword"/>
                                <button type="submit" class="button" value="search" name="method">搜索</button>
                            </form>

                        </div>
                    </div>
                    <!-- End Box Head -->

                    <!-- Table -->
                    <div class="table">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <th width="13"><input type="checkbox" class="checkbox"/></th>
                                <th>标题</th>
                                <th>日期</th>
                                <th>作者</th>
                                <th width="110" class="ac"></th>
                            </tr>
                            <!-- 用java脚本控制博客对象在的输出 -->
                            <jsp:useBean id="blog" class="com.zengrui.DB.Model.Blog" scope="session"/>
                            <%
                                //当前页码
                                int curPage = Integer.parseInt(request.getParameter("curPage"));
                                int count = (int) request.getSession().getAttribute("count");
                                //总页数
                                int totalPage = (count / 8) + 1;
                                //最后一页的条数
                                int lastPageCount = count - (totalPage - 1) * 8;
                                //当前页码超出页码范围的情况
                                String msg = null;
                                if (curPage > totalPage) {
                                    msg = "1";
                                    curPage--;
                                }
                                if (curPage < 1) {
                                    msg = "2";
                                    curPage++;
                                }
                                if (msg != null) {
                                    //跳转页面
                                    response.sendRedirect("/jsp/welcome.jsp?curPage=" + curPage + "&msg=" + msg);
                                }
                                //一页最多展示8条博客，数量小于8的情况
                                if (curPage == totalPage) {
                                    for (int i = 0; i < Math.min(lastPageCount, 8); i++) {
                                        String blogname = "blog" + Integer.toString(i + (curPage - 1) * 8);
                                        blog = (Blog) request.getSession().getAttribute(blogname);
                                        if (i % 2 == 0) {
                            %>
                            <!--单双数背景颜色区别-->
                            <tr>
                                <td><input type="checkbox" class="checkbox"/></td>
                                <td><h3><a href="/MainPageServlet?method=show&count=<%=i%> " target="_blank"><%=blog.getTitel()%>
                                </a></h3></td>
                                <td><%=blog.getData() + " " + blog.getTime()%>
                                </td>
                                <td><a href="#"><%=blog.getAuthor()%>
                                </a></td>
                                <td>
                                    <a href="/MainPageServlet?&curPage=<%=curPage%>&method=delete&delete=<%=blog.getId()%>"
                                       class="ico del">删除</a><a href="/MainPageServlet?method=update&count=<%=i%>" class="ico edit">编辑</a></td>
                            </tr>
                            <%
                            } else {%>
                            <tr class="odd">
                                <td><input type="checkbox" class="checkbox"/></td>
                                <td><h3><a href="/MainPageServlet?method=show&count=<%=i%>" target="_blank"><%=blog.getTitel()%>
                                </a></h3></td>
                                <td><%=blog.getData() + " " + blog.getTime()%>
                                </td>
                                <td><a href="#"><%=blog.getAuthor()%>
                                </a></td>
                                <td><a href="/MainPageServlet?&curPage=<%=curPage%>&method=delete&delete=<%=blog.getId()%>" class="ico del">删除</a>
                                    <a href="/MainPageServlet?method=update&count=<%=i%>" class="ico edit">编辑</a></td>
                            </tr>
                            <%
                                        }
                                    }
                                }
                            %>
                            <!--大于8条的情况-->
                            <%
                                //一页最多展示8条博客，数量大于8的情况
                                if (curPage < totalPage) {
                                    for (int i = 0; i < 8; i++) {
                                        String blogname = "blog" + Integer.toString(i + (curPage - 1) * 8);
                                        blog = (Blog) request.getSession().getAttribute(blogname);
                                        if (i % 2 == 0) {
                            %>
                            <tr>
                                <td><input type="checkbox" class="checkbox"/></td>
                                <td><h3><a href="/MainPageServlet?method=show&count=<%=i%>" target="_blank"><%=blog.getTitel()%>
                                </a></h3></td>
                                <td><%=blog.getData() + " " + blog.getTime()%>
                                </td>
                                <td><a href="#"><%=blog.getAuthor()%>
                                </a></td>
                                <td>
                                    <a href="/MainPageServlet?&curPage=<%=curPage%>&method=delete&delete=<%=blog.getTitel()%>"
                                       class="ico del">删除</a>
                                    <a href="/MainPageServlet?method=update&count=<%=i%>" class="ico edit">编辑</a></td>
                            </tr>
                            <%
                            } else {%>
                            <tr class="odd">
                                <td><input type="checkbox" class="checkbox"/></td>
                                <td><h3><a href="/MainPageServlet?method=show&count=<%=i%>" target="_blank"><%=blog.getTitel()%>
                                </a></h3></td>
                                <td><%=blog.getData() + " " + blog.getTime()%>
                                </td>
                                <td><a href=""><%=blog.getAuthor()%>
                                </a></td>
                                <td><a href="/MainPageServlet?&curPage=<%=curPage%>&method=delete&delete=<%=blog.getId()%>" class="ico del">删除</a>
                                    <a href="/MainPageServlet?method=update&count=<%=i%>" class="ico edit">编辑</a></td>
                            </tr>
                            <%
                                        }
                                    }
                                }
                            %>

                        </table>


                        <!-- Pagging -->
                        <div class="pagging">
                            <div class="left">1-2</div>
                            <div class="right">
                                <a href="/jsp/welcome.jsp?curPage=<%=curPage-1%>">上一页</a>
                                <!--页码逻辑判断-->
                                <%
                                    if (curPage >= 1 && curPage <= totalPage) {
                                        //小于4页的情况
                                        if (totalPage <= 4) {
                                            for (int i = 0; i < totalPage; i++) {
                                                if (i + 1 == curPage) {%>
                                <a class="focus" href="/jsp/welcome.jsp?curPage=<%=i+1%>"><%=i + 1%>
                                </a>

                                <%} else {%>
                                <a href="/jsp/welcome.jsp?curPage=<%=i+1%>"><%=i + 1%>
                                </a>
                                <%
                                            }
                                        }
                                    }
                                    //大于4页的情况
                                    if (totalPage > 4) {
                                        if (curPage <= (totalPage - 4)) {
                                            for (int i = curPage; i < curPage + 4; i++) {
                                                //显示标记当前页码
                                                if (i == curPage) {%>
                                <span><%=i%></span>
                                <%} else {%>
                                <a href="/jsp/welcome.jsp?curPage=<%=i%>"><%=i%>
                                </a>
                                <%
                                        }

                                    }
                                }
                                //最后4页的情况
                                else if (curPage > (totalPage - 4)) {
                                    for (int i = totalPage - 3; i <= totalPage; i++) {
                                        //显示标记当前页码
                                        if (i == curPage) {%>
                                <span><%=i%></span>

                                <%} else {%>
                                <a href="/jsp/welcome.jsp?curPage=<%=i%>"><%=i%>
                                </a>
                                <%
                                            }
                                        }


                                    }
                                    //尾页显示的部分
                                    if (curPage <= (totalPage - 4)) {%>
                                <span>...</span>
                                <a href="/jsp/welcome.jsp?curPage=<%=totalPage%>"><%=totalPage%>
                                </a>
                                <%
                                            }
                                        }
                                    }
                                %>
                                <a href="/jsp/welcome.jsp?curPage=<%=curPage+1%>">下一页</a>
                                <a href="/jsp/welcome.jsp?curPage=<%=totalPage%>">最后一页</a>
                            </div>
                        </div>
                        <!-- End Pagging -->

                    </div>
                    <!-- Table -->

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
        <span class="left">&copy; 2017 - ZengRui</span>
        <span class="right">
			Design by <a href="http://chocotemplates.com" target="_blank" title="The Sweetest CSS Templates WorldWide">ZengRui</a>
		</span>
    </div>
</div>
<!-- End Footer -->

</body>
</html>
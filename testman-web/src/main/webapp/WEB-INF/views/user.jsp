<%--
  Created by IntelliJ IDEA.
  User: liuzhilei
  Date: 2016/10/17
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List,com.liu.common.GameUser" %>
<html>
<head>
    <title></title>
</head>
<body>
<!--加放JAVA代码-->
<%
    List<GameUser> list = (List)request.getAttribute("list");
    for(int i=0;i<list.size();i++){
%>
<h1><%=list.get(i).getUserPin()%></h1>
<%
    }
%>
</body>
</html>

<%@ page import="domain.UserProfile" %><%--
  Created by IntelliJ IDEA.
  User: damia
  Date: 11.11.2018
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    if(session.getAttribute("userPrem")==null && session.getAttribute("userAdmin")==null)
        response.sendRedirect("index.jsp");
%>

<div>Congrats you are premium user</div>
</body>
</html>

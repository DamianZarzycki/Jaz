<%@ page import="domain.UserProfile" %>
<%@ page import="repo.UserRepo" %>
<%--
  Created by IntelliJ IDEA.
  User: damia
  Date: 09.11.2018
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Premium user</title>
</head>
<body>

<%


    if (session.getAttribute("userAdmin") == null) response.sendRedirect("site");

%>
<h1>Admins Site</h1>

<div>Set premium</div>
<form action="premiumSet" method="post">
    <input type="text" name="txtusername" required="required" /> Nazwa uzytkownika <br/>
    <input type="submit" value="Execute.." />
</form>
<div>Delate premium</div>
<form action="premiumDel" method="post">
    <input type="text" name="txtusername" required="required" /> Nazwa uzytkownika <br/>
    <input type="submit" value="Execute.." />
</form>

<form action="logout"><input type="submit" value="Logout"></form>
</body>
</html>

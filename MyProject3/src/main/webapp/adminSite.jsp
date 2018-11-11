<%--
  Created by IntelliJ IDEA.
  User: damia
  Date: 11.11.2018
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Admin</h1>
<div>Type login and password form admin</div>
<form action="adminsite" method="post">
    <input type="text" name="txtusername" required="required" /> Name <br/>
    <input type="text" name="txtpassword" required="required" /> Password <br/>
    <input type="submit" value="Execute.." />
</form>
</body>
</html>

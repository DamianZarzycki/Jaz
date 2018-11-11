<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@ page import="domain.UserProfile" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<%
    if (session.getAttribute("userSite") != null) response.sendRedirect("site");

%>


<h1>Rejestracja</h1>
<form action="add" method="post">

<input type="text" name="txtusername" required="required" /> Nazwa uzytkownika <br/>
<input type="text" name="txtemail" required="required"/> Email <br/>
<input type="text" name="txtpassword" required="required" /> Haslo <br/>
<input type="text" name="textCpassword" required="required" /> Powtorz haslo <br/>
<input type="submit" value="Register.." required="required" />

</form>
</body>
</html>
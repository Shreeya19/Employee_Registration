<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%@page import="dbConnect.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Registration</title>
</head>
<body>
<%
Statement statement = null;
ResultSet resultSet = null;
%>
<% 
try
{
	Connection conn = Connect.connect();
	String empid1 = request.getParameter("empid");
	java.sql.PreparedStatement ps = conn.prepareStatement("delete from employees where empid = ?");
	ps.setString(1,empid1);
	int i = ps.executeUpdate();

	if(i == 1)
	{
		response.sendRedirect("viewEmployee.jsp");
		System.out.println("Record Deleted Successfully...");
	}
}
catch (Exception e)
{
	System.err.println(e);	
	e.printStackTrace();
}
%>
</body>
</html>
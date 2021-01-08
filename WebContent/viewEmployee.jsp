<%@page import="java.util.*" %>
<%@page import="java.sql.*"%>
<%@page import="dbConnect.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<title>Employee Registration</title>
</head>
<body>
	<div class="container">
		<h1>Employee Details</h1>
		<hr>
		<table class="table">
			<tr>
            	<th>ID</th>
                <th>Name</th>
                <th>Contact Number</th>
                <th>Email ID</th>
                <th>Domain</th>
                <th>Action</th>
            </tr>
            <% 
            	Statement statement = null;
                ResultSet resultSet = null;
			%>
            <%
            try{
            	Connection con = Connect.connect();
                statement = con.createStatement();
                String sql ="select * from employees";
                resultSet = statement.executeQuery(sql);
                while(resultSet.next())
				{
            %>
                <tr>
                	<td><%=resultSet.getInt("empid") %></td>
                    <td><%=resultSet.getString("name") %></td>
                    <td><%=resultSet.getString("contact") %></td>
                    <td><%=resultSet.getString("email") %></td>
                    <td><%=resultSet.getString("domain") %></td>
                    
                    <td><a href="deleteEmployee.jsp?empid=<%=resultSet.getString("empid")%>" onclick="return confirm_alert(this);">Delete</a></td>
                        
                </tr>
            <%
                }
                //connection.close();
            }
			catch(Exception e) {
            	e.printStackTrace();
            }
            %>
		</table>
		<hr>
		<button type="button" onclick="window.location='index.html'" class="registerbtn2">New Registration</button>
	</div>
</body>
</html>
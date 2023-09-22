<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.util.Date"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage="err.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>찬돌이</h1>입니다
<% int i; // 지역변수, _jspService()내부 %>
<% out.print(new Date()); %>
<hr>
<%=new Date() %>
<hr>
<%! int i; // 멤버변수 %>
<% i=99; %>
지역변수 i:<%= i%>, 멤버변수 i: <%= this.i%>
<hr>
<% for(int j = 1; j <= 1000; j ++) { %>
	<sapn> <%=j%> </sapn>
<% } %>
<hr>
<%
try{
FileInputStream fis = null;
fis = new FileInputStream("a.txt");
}catch(FileNotFoundException e) {
%><%--  <h3><%=e.getMessage() %></h3> --%>
<%	

	RequestDispatcher rd = request.getRequestDispatcher("err.jsp");
	rd.forward(request, response);
}
%>
</body>
</html>
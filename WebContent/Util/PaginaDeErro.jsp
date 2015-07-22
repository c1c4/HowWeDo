<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isErrorPage="true" import="java.io.*"%>

<%
	String mensagemErro = "";
    mensagemErro = exception.toString();
    out.println("<!--");
    out.println(mensagemErro);
    out.println("-->");
    
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Erro encontrado</title>
</head>
<body>

	<h1>Exception - Erro encontrado</h1>
	<br>
	<p>Ocorreu o seguinte erro:</p>
	<p><%= mensagemErro %></p>

</body>
</html>
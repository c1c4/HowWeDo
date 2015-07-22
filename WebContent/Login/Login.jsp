<%@ page import="dto.UsuarioDTO"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@page errorPage="/Util/PaginaDeErro.jsp" %>    
<link rel=stylesheet type="text/css" href="/ExemploJSP2/Content/estilos/Mylayout.css">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sistema model</title>
</head>
<body>

<script type="text/javascript">
	function Validate(){
		var usuario=document.formLogin.usuario;
		var senha=document.formLogin.senha;
		
		if ((usuario.value==null)||(usuario.value=="")){
			alert("Informe o nome do usuário");
			usuario.focus();
			return false;
		}
		
		if ((senha.value==null)||(senha.value=="")){
			alert("Informe a senha do usuário");
			senha.focus();
			return false;
		}
		
		return true
	}
</script>

<form name="formLogin" action="/HowWeDo/Login" method=post onsubmit="return Validate()">

<%
	Boolean isLoginOk = (Boolean)session.getAttribute("loginOk");
	if(isLoginOk != null && !isLoginOk)
	{%>
		<h1>Dados para autenticação inválidos</h1>
	<%
		session.setAttribute("loginOk", true);
	}
%>

	<table>
		<tr>
			<td>Usuário:</td>
			<td><input type="text" size="50" name="usuario" maxlength="50"></td>
		</tr>
		<tr>
			<td>Senha:</td>
			<td><input type="password" size="50" name="senha" maxlength="50"></td>
		</tr>
		<tr>
			<td><input type="submit" value="Acessar" /></td>
			<td><a href="/HowWeDo/Usuario/Registro.jsp">Registrar</a></td>
		</tr>
	</table>
</form>

</BODY>
</HTML>
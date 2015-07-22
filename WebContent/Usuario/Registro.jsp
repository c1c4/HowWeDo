<jsp:useBean id="usuarioDTO" class="dto.UsuarioDTO"></jsp:useBean>
<jsp:setProperty property="*" name="usuarioDTO" />
<link rel=stylesheet type="text/css" href="/HowWeDo/Content/estilos/Mylayout.css" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>

<%
	String[] erros = (String[]) request.getAttribute("erros");
	if (erros != null && erros.length > 0) {
%>
<div class="htmldbNotification">
<ul>
	<%
		for (int i = 0; i < erros.length; i++) {
	%>
	<li><%=erros[i]%> <%
 	}
 %>
</ul>
</div>
<%
	}
%>

<table id="obTable" cellspacing="0" cellpadding="0" border="0" style="margin: 5px; width: 1325px;">
	<tbody>
		<tr>
			<td id="dbaseFrameHolder" class="dbaseFrameHolder" width="100%"
				valign="top">
				<table id="obRightHeader" cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<td id="ob_ObjectsDetail" width="100%">Registro de usuários</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td>

				<form action="/HowWeDo/RegistrarUsuario" method="post">
					<table class="htmldbStandard3" cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td class="htmldbFD">E-mail:</td>
							<td><input type="mail" name="email"
								value='<jsp:getProperty property="email" name="usuarioDTO"/>'
								size=50 maxlength="50"></td>
						</tr>
						<tr>
							<td class="htmldbFD">Login:</td>
							<td><input type="text" name="login"
								value='<jsp:getProperty property="login" name="usuarioDTO"/>'
								size=50 maxlength="50"></td>
						</tr>
						<tr>
							<td class="htmldbFD">Senha:</td>
							<td><input type="password" name="senha"
								value='<jsp:getProperty property="senha" name="usuarioDTO"/>'
								size=50 maxlength="50"></td>
						</tr>
						<tr>
							<td><input class="htmldbButton" type="submit" value="Registrar"></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</tbody>
</table>

</BODY>
</HTML>
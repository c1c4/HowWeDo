<%@page import="dto.UsuarioDTO"%>
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
	
	<table id="obTable" style="margin: 5px;">
		<tbody>
			<tr>
				<td><p class="tituloCadastro">Inserir usuário</p></td>
			</tr>
			<tr>
				<td>
	 				<form action="/HowWeDo/AtualizarUsuario" method="post">
						<input type="hidden" name="acao" value="Inclusao">
						<table class="htmldbStandard3" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td class="htmldbFD">Nome:</td>
								<td><input type="text" name="nome"
									value='<jsp:getProperty property="nome" name="usuarioDTO"/>'
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
								<td colspan="2"><input class="htmldbButton" type="submit"
									value="Gravar">
									<input class="htmldbButton"
									type='button' value='Usuários'
									onclick='window.location="/ExemploJSP2/Usuario/Index.jsp"'>
									<input class="htmldbButton" type='button'
									value='Menu principal'
									onclick='window.location="/HowWeDo/Menu/MenuInicial.jsp"'>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	
</BODY>
</HTML>
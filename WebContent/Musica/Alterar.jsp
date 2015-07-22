<%@page import="dto.DtoMusica"%>
<jsp:useBean id="musicaDTO" scope="request" class="dto.DtoMusica"></jsp:useBean>
<jsp:setProperty property="*" name="musicaDTO" />
<link rel=stylesheet type="text/css"
	href="/HowWeDo/Content/estilos/Mylayout.css" />
	
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
				<td><p class="tituloCadastro">Alterar Música</p></td>
			</tr>
			<tr>
				<td>
					<form action="/HowWeDo/AtualizarMusica" method="post">
						<input type="hidden" name="acao" value="Alteracao">
						<input type="hidden" name="codigo" value='<jsp:getProperty property="codigo" name="musicaDTO"/>'>
						<table class="htmldbStandard3" cellspacing="0" cellpadding="0"
							border="0">
							
							<tr>
								<td class="htmldbFD">Nome do Artista:</td>
								<td><input type="text" name="artista"></td>
							</tr>
							<tr>
								<td class="htmldbFD">Nome da Música:</td>
								<td><input type="text" name="nome"></td>
							</tr>
							<tr>
								<td colspan="2"><input class="htmldbButton" type="submit"
									value="Gravar"> 
									<input class="htmldbButton"
									type='button' value='Usuários'
									onclick='window.location="/HowWeDo/Usuario/Index.jsp"'>
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
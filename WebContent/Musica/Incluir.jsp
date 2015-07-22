<%@page import="dto.DtoArtista"%>
<%@page import="java.util.ArrayList"%>
<jsp:useBean id="musicaDTO" class="dto.DtoMusica"></jsp:useBean>
<jsp:useBean id="generoVO" class="Vo.GeneroVO"></jsp:useBean>
<%@ page import="dto.UsuarioDTO"%>
<%@ page import="dto.DtoGenero"%>

<link rel=stylesheet type="text/css"
	href="/HowWeDo/Content/estilos/Mylayout.css" />

<%
	UsuarioDTO usuarioDTOLogin = (UsuarioDTO) session
	.getAttribute("usuarioLogado");

	if (usuarioDTOLogin == null) {
		usuarioDTOLogin = new UsuarioDTO();

	}

	if (usuarioDTOLogin == null) {
%>
<jsp:forward page="/index.jsp"></jsp:forward>
<%
	}
%>

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

	<%
		ArrayList<DtoGenero> genero = (ArrayList) generoVO.getGeneros();
	%>

	<table id="obTable" cellspacing="0" cellpadding="0" border="0"
		style="margin: 5px; width: 1325px;">
		<tbody>
			<tr>
				<td id="dbaseFrameHolder" class="dbaseFrameHolder" width="100%"
					valign="top">
					<table id="obRightHeader" cellspacing="0" cellpadding="0"
						border="0">
						<tbody>
							<tr>
								<td id="ob_ObjectsDetail" width="100%">Upload de Músicas</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<form id="form1" action="/HowWeDo/AtualizarMusica" method="post"
						enctype="multipart/form-data">
						<input type="hidden" name="acao" value="Inclusao">
						<table class="htmldbStandard3" cellspacing="0" cellpadding="0"
							border="0">
							<tr>
								<td class="htmldbFD">Nome da Música:</td>
								<td><input type="text" name="nome"></td>
							</tr>
							<tr>
								<td class="htmldbFD">Artista:</td>
								<td><input type="text" id="artista" name="artista"></td>
							</tr>
							<tr>
								<td class="htmldbFD">Genero:</td>
								<td><select name="genero" id="genero">
										<%
											for (int i = 0; i < genero.size(); i++) {
										%>
										<option value=<%=genero.get(i).getCodigo()%>>
											<%=genero.get(i).getNome()%></option>
										<%
											}
										%>
								</select></td>
							</tr>
							<tr>
								<td class="htmldbFD">Faça o Upload da Música:</td>
								<td><input type="file" name="musica"></td>
							</tr>
							<tr>
								<td><input class="htmldbButton" type="submit"
									value="Gravar"></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</tbody>
	</table>

</BODY>
</HTML>
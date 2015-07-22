<%@page import="business.UsuarioBusiness"%>
<%@page import="dto.UsuarioDTO"%>
<%@page import="java.util.List"%>
<link rel=stylesheet type="text/css"
	href="/HowWeDo/Content/estilos/Mylayout.css" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>

<body>
	<%
		List<UsuarioDTO> usuarios = (List<UsuarioDTO>)session.getAttribute("listaUsuarios");
	%>
	<table id="obTable" style="margin: 5px;">
		<tr>
			<td class="dbaseFrameHolder" width="100%" valign="top">
				<table id="obRightHeader" cellspacing="0" cellpadding="0" border="0">
					<tbody>
					<tr>
						<td><p class="tituloCadastro">Opções usuários</p></td>
					</tr>
					<tr>
						<td><input class="htmldbButton" type='button' value='Menu principal'
						    onclick='window.location="/ExemploJSP2/Menu/MenuInicial.jsp"'>
						    <input class="htmldbButton" type='button' value='Novo'
						    onclick='window.location="/HowWeDo/Usuario/Inserir.jsp"'></td>
					</tr>
					
						<tr>
							<td>
								<%
											if (usuarios.size() > 0) {
										%>
								<div style="height: 450px; width: 318px; overflow: auto">
									<table class="htmldbStandard3" cellspacing="0" cellpadding="0"
										border="0">
										<thead>
											<tr>
												<th id="COL01">Login</th>
												<th id="COL02">Email</th>
												<th id="COL03"></th>
											</tr>
										</thead>
										<tbody>
											<%
														for (UsuarioDTO usuarioDTO : usuarios) {
													%>
											<tr>
												<td id="COL01"><%=usuarioDTO.getLogin()%></td>
												<td id="COL02"><%=usuarioDTO.getEmail()%></td>
												<td id="COL03"><a
													href='/HowWeDo/BuscarUsuario?acao=Alterar&codigo=<%=usuarioDTO.getCodigo()%>'><img
														src="/HowWeDo/Content/imagens/edit.png" border="0"></a> <a
													href='/HowWeDo/BuscarUsuario?acao=Excluir&codigo=<%=usuarioDTO.getCodigo()%>'><img
														src="/HowWeDo/Content/imagens/excluir.png" border="0"></a></td>

											</tr>
											<%
														}
													%>
										</tbody>
									</table>
								</div> <%
 	} else {
 %>
								<p>Nenhum usuário cadastrado</p> <%
 	}
 %>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		</tbody>
	</table>
</BODY>
</HTML>
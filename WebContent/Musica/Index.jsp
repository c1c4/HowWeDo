<%@page import="business.MusicaBusiness"%>
<%@page import="dto.DtoMusica"%>
<%@page import="java.util.List"%>
<link rel=stylesheet type="text/css"
	href="/HowWeDo/Content/estilos/Mylayout.css" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>

<body>
	<%
		List<DtoMusica> musicas = (List<DtoMusica>)session.getAttribute("listaMusica");
	%>
	<table id="obTable" style="margin: 5px;">
		<tr>
			<td class="dbaseFrameHolder" width="100%" valign="top">
				<table id="obRightHeader" cellspacing="0" cellpadding="0" border="0">
					<tbody>
					<tr>
						<td><p class="tituloCadastro">Opções Músicas</p></td>
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
											if (musicas.size() > 0) {
										%>
								<div style="height: 450px; width: 318px; overflow: auto">
									<table class="htmldbStandard3" cellspacing="0" cellpadding="0"
										border="0">
										<thead>
											<tr>
												<th id="COL01">Artista</th>
												<th id="COL02">Nome da Musica</th>
												<th id="COL03"></th>
											</tr>
										</thead>
										<tbody>
											<%
														for (DtoMusica musicasDTO : musicas) {
													%>
											<tr>
										     	<td id="COL01"><%=musicasDTO.getArtista().getNome()%></td>
												<td id="COL02"><%=musicasDTO.getNome()%></td>
												<td id="COL03"><a
													href='/HowWeDo/BuscarMusica?acao=Alterar&codigo=<%=musicasDTO.getCodigo()%>'><img
														src="/HowWeDo/Content/imagens/edit.png" border="0"></a> <a
													href='/HowWeDo/BuscarMusica?acao=Excluir&codigo=<%=musicasDTO.getCodigo()%>'><img
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
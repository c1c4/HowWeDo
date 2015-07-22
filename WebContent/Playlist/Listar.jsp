<%@page import="business.Playlistbusiness"%>
<%@page import="dto.DtoPlaylist"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="dto.UsuarioDTO"%>
<%@ page import="dto.DtoPlaylist"%>
<jsp:useBean id="playlistDTO" class="dto.DtoPlaylist"></jsp:useBean>
<link rel=stylesheet type="text/css"
	href="/HowWeDo/Content/estilos/Mylayout.css" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    
</head>   

<body>
	<%
		List<DtoPlaylist> playlist = (List<DtoPlaylist>)session.getAttribute("listaPlaylist");
	%>
	<table id="obTable" style="margin: 5px;">
		<tr>
			<td class="dbaseFrameHolder" width="100%" valign="top">
				<table id="obRightHeader" cellspacing="5" cellpadding="5" border="0">
					<tbody>
					<tr>
						<td><p class="tituloCadastro">Minhas ads</p></td>
					</tr>
					<tr>
						<td><!-- <input class="htmldbButton" type='button' value='Menu principal'
						    onclick='window.location="/ProjetoRepo/Menu/MenuInicial.jsp"'>
						    <input class="htmldbButton" type='button' value='Novo'
						    onclick='window.location="/ProjetoRepo/Musica/Inserir.jsp"'> --></td>
					</tr>
					
						<tr>
							<td>
								<%
											if (playlist.size() > 0) {
										%>
								<div style="height: 450px; width: 318px; overflow: auto">
									<table class="htmldbStandard3" cellspacing="0" cellpadding="0"
										border="0">
										<thead>
											<tr>
												<th id="COL01">NOME</th>
											</th>																					
										</thead> 
										<tbody>
										
										
											        <%
														for (DtoPlaylist dtoPlaylist : playlist) {
													%>
											<tr>
												<td id="COL01"><%=dtoPlaylist.getNome()%></td>

												<td id="COL02"><a
													
													href='/ProjetoRepo/AtualizarPlaylist?acao=Excluir&codigo=<%=dtoPlaylist.getCodigo()%>'><img
														src="C:/Users/7 IEQ/Desktop/Paulo/2ª Semestre/Projeto/Workspace/ProjetoRepo/WebContent/Content/imagens/excluir.png" border="0"></a></td>

											</tr>
											<%
														}
													%>
										</tbody>
									</table>
								</div> <%
 	} else {
 %>
								<p>Nenhuma Playlist cadastrada</p> <%
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
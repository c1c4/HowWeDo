<%@page import="business.MusicaBusiness"%>
<%@page import="dto.DtoMusica"%>
<%@page import="business.Playlistbusiness"%>
<%@page import="dto.DtoPlaylist"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="dto.UsuarioDTO"%>
<%@ page import="dto.DtoPlaylist"%>
<jsp:useBean id="playlistDTO" class="dto.DtoPlaylist"></jsp:useBean>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Incluir Música na Playlist</title>
<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

	<%
		String musica = request.getParameter("CodigoMusica");
	%>

	<%
		List<DtoPlaylist> playlist = (List<DtoPlaylist>) session
				.getAttribute("listaPlaylist");
	%>

	<div class="modal-header">
		<h2 class="modal-title" id="myModalLabel">Minhas Playlists</h2>
	</div>
	<div class="modal-body">


		<%
			if (playlist.size() > 0) {
		%>
		<table class="table table-hover">

			<thead>
				<tr>
					<th id="COL01"></th>
					<th id="COL02"></th>
					<th id="COL03"></th>
				</tr>
			</thead>
			<tbody>
				<%
					for (DtoPlaylist dtoPlaylist : playlist) {
				%>



				<tr>

					<td id="COL01"><h4><%=dtoPlaylist.getNome()%></h4></td>
					<td id="COL02"></td>
					<td id="COLM03"><a
						class="btn btn-primary glyphicon glyphicon-plus"
						style="background-color: black"
						href='/HowWeDo/AdicionarMusicaPlaylist?acao=Incluir&codigoPlaylist=<%=dtoPlaylist.getCodigo()%>&CodigoMusica=<%=musica%>'></a></td>








				</tr>
				<%
					}
				%>


			</tbody>
		</table>
		
	</div>
	<%
		} else {
	%>
	<p>Nenhuma playlist cadastrada</p>
	<%
		}
	%>
	
</BODY>
</HTML>
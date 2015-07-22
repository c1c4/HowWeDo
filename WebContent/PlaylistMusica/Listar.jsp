<%@page import="dto.DtoPlaylistMusica"%>
<%@page import="business.MusicaBusiness"%>
<%@page import="dto.DtoMusica"%>
<%@page import="business.Playlistbusiness"%>
<%@page import="dto.DtoPlaylist"%>
<%@page import="java.util.List"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Músicas da Playlist</title>
<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<%
		List<DtoPlaylistMusica> playlistMusicas = (List<DtoPlaylistMusica>) session
				.getAttribute("listaPlaylistMusica");
	%>
	<div class="modal-header">
		<h2 class="modal-title" id="myModalLabel">Músicas da Playlist</h2>
	</div>
	<div class="modal-body">
		<%
			if (playlistMusicas.size() > 0) {
		%>
		<table class="table table-hover">



			<thead>
				<tr>
					<th id="COL01">Música</th>
					<th id="COL02">Artista</th>
					<th id="COL03">Gênero</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (DtoPlaylistMusica playlistMusicasDTO : playlistMusicas) {
				%>
				<tr>

					<td id="COL01"><%=playlistMusicasDTO.getCodigoMusica().getNome()%></td>
					<td id="COL02"><%=playlistMusicasDTO.getCodigoMusica().getArtista()
							.getNome()%></td>
					<td id="COL03"><%=playlistMusicasDTO.getCodigoMusica().getGenero()
							.getNome()%></td>




				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<div align="center">
			<input type="button" value="Fechar" name="Fechar"
				class="btn btn-primary" style="background-color: black"
				onClick="window.close(); " />
		</div>
	</div>
	<%
		} else {
	%>
	<p>Nenhum usuário cadastrado</p>
	<%
		}
	%>



</BODY>
</HTML>
<%@page import="business.Playlistbusiness"%>
<%@page import="dto.DtoPlaylist"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="dto.UsuarioDTO"%>
<%@ page import="dto.DtoPlaylist"%>
<jsp:useBean id="playlistDTO" class="dto.DtoPlaylist"></jsp:useBean>
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
<%
	List<DtoPlaylist> playlist = (List<DtoPlaylist>)session.getAttribute("listaPlaylist");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<script src="js/jquery.min.js"></script>
<title>HowWeDo?</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/principal.css" rel="stylesheet">
<script type="text/javascript">
	$(document).ready(function() {
		$('#desconectar').on('click', function(e) { //bind no click event dos links do menu
			if (confirm("Tem certeza que deseja se desconectar?")) {
				if (window.top.location != self.location)
					window.top.location.href = "index.jsp";
				if (window.parent.location != self.location)
					parent.location.href = "index.jsp";
			}
		});
	});
</script>
<style>
#olho {
	color: black;
	text-decoration: none;
	margin-top: -8px;
	margin-left: -80px;
}
</style>
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<a class="navbar-brand" href="principal.jsp"> <img alt="Brand"
					src="img/nome.png">
				</a>
			</div>
			<a class="navbar-brand navbar-right"> Bem-vindo <%=usuarioDTOLogin.getLogin()%>
			</a>

			<!-- /.navbar-header -->
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li class="sidebar-search">
							<div class="input-group custom-search-form">
								<form action="/HowWeDo/BuscarMusica"
									style="white-space: nowrap;">
									<input type="hidden" name="acao" value="procurar"> <input
										type="text" class="form-control" size="10px" name="parametro"
										placeholder="M�sica, artista ou g�nero."> &nbsp;
									<button class="btn btn btn-primary glyphicon glyphicon-search"
										type="submit" style="background-color: black"></button>
								</form>
							</div> <!-- /input-group -->
						</li>
						<li><a class="nav nav-second-level" style="color: black;"
							href="meusarquivos.jsp"><span
								class="glyphicon glyphicon-briefcase" aria-hidden="true"></span>
								Meus Arquivos</a></li>
						<li><a class="active" class="nav nav-second-level"
							style="color: black;" href="playlist.jsp"><span
								class="glyphicon glyphicon-play" aria-hidden="true"></span>
								Playlist's</a></li>
						<li><a class="nav nav-second-level" style="color: black;"
							href="favoritos.jsp"><span class="glyphicon glyphicon-star"
								aria-hidden="true"></span> Favoritos</a></li>
						<li><a class="nav nav-second-level" style="color: black;"
							href="historico.jsp"><span
								class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
								Hist�rico</a></li>
						<li><a class="nav nav-second-level" style="color: black;"
							href="upload.jsp"><span class="glyphicon glyphicon-open"
								aria-hidden="true"></span> Upload</a></li>
						<li><a class="nav nav-second-level" style="color: black;"
							href="configuracoes.jsp"><span
								class="glyphicon glyphicon-cog" aria-hidden="true"></span>
								Configura��es da Conta</a></li>
						<li><a id="desconectar" class="nav nav-second-level"
							style="color: black;" href="principal.jsp"><span
								class="glyphicon glyphicon-off" aria-hidden="true"></span>
								Desconectar</a></li>
						<%
							if (usuarioDTOLogin.getLogin().equals("adm")) {
						%>
						<li><a class="nav nav-second-level" style="color: black;"
							href="genero.jsp"><span class="glyphicon glyphicon-plus"
								aria-hidden="true"></span> Cadastrar G�nero (s� adm pode ver)</a></li>
						<%
							}
						%>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="modal-header">
						<h2 class="modal-title" id="myModalLabel">Playlist's</h2>
					</div>
					<div class="modal-body">
						<form role="form" action="principal.jsp" method="post">


							<a class="btn btn-primary" type="button"
								style="background-color: black"
								href="/HowWeDo/IncluirPlaylistia">Nova Playlist </a>
							<div class="table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>Nome</th>
											<th></th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<%
											for (DtoPlaylist dtoPlaylist : playlist) {
										%>
										<tr>
											<td id="COL01"><%=dtoPlaylist.getNome()%></td>
											<td><a type="button"
												class="btn glyphicon glyphicon-eye-open" id="olho"
												href='/HowWeDo/AdicionarMusicaPlaylist?acao=Listar&codigoPlaylist=<%=dtoPlaylist.getCodigo()%>'></a></td>
											<td><a type="button" id="remover"
												href='/HowWeDo/AtualizarPlaylist?acao=Excluir&codigoPlaylist=<%=dtoPlaylist.getCodigo()%>'
												class="glyphicon glyphicon-remove" style="color:black"></a></td>
										</tr>
										<%
											}
										%>

									</tbody>
								</table>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>

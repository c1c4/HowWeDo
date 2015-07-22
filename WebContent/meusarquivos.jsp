<%@page import="business.MusicaBusiness"%>
<%@page import="dto.DtoMusica"%>
<%@page import="java.util.List"%>
<%@ page import="java.io.*"%>
<%@ page import="dto.UsuarioDTO"%>
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
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<style>
#caneta {
	color: black;
	text-decoration: none;
	margin-top: -8px;
}

#caneta:hover {
	color: yellow;
	text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
	text-decoration: none;
	cursor: pointer;
}

#remover {
	color: black;
	text-decoration: none;
	margin-top: -8px;
}

#remover:hover {
	color: red;
	text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
	text-decoration: none;
	cursor: pointer;
}

#play {
	color: black;
	text-decoration: none;
}

#play:hover {
	color: green;
	text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
	text-decoration: none;
	cursor: pointer;
}
</style>
</head>
<body>
	<%
		List<DtoMusica> musicas = (List<DtoMusica>)session.getAttribute("listaMusica");
		       
		        List<DtoMusica>	musicasUsuario = (List<DtoMusica>)session.getAttribute("listaMusicaUsuario");
	%>
	<%
		Boolean isAlterarOk = (Boolean)session.getAttribute("alterarOk");
			if(isAlterarOk != null && isAlterarOk)
			{
	%>
	<script>
		alert("Alterado Com Sucesso!");
	</script>
	<%
		session.setAttribute("alterarOk", false);
			}
	%>
	<%
		Boolean isExcluirOk = (Boolean)session.getAttribute("excluirOk");
			if(isExcluirOk != null && isExcluirOk)
			{
	%>
	<script>
		alert("Excluido Com Sucesso!");
	</script>
	<%
		session.setAttribute("excluirOk", false);
			}
	%>
	<%
		Boolean isExisteFavOk = (Boolean)session.getAttribute("existeFavOk");
			if(isExisteFavOk != null && isExisteFavOk)
			{
	%>
	<script>
		alert("Existe um favoritos com essa música!");
	</script>
	<%
		session.setAttribute("existeFavOk", false);
			}
	%>
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
										placeholder="Música, artista ou gênero."> &nbsp;
									<button class="btn btn btn-primary glyphicon glyphicon-search"
										type="submit" style="background-color: black"></button>
								</form>
							</div> <!-- /input-group -->
						</li>
						<li><a class="active" class="nav nav-second-level"
							style="color: black;" href="meusarquivos.jsp"><span
								class="glyphicon glyphicon-briefcase" aria-hidden="true"></span>
								Meus Arquivos</a></li>
						<li><a class="nav nav-second-level" style="color: black;"
							href="playlist.jsp"><span class="glyphicon glyphicon-play"
								aria-hidden="true"></span> Playlist's</a></li>
						<li><a class="nav nav-second-level" style="color: black;"
							href="favoritos.jsp"><span class="glyphicon glyphicon-star"
								aria-hidden="true"></span> Favoritos</a></li>
						<li><a class="nav nav-second-level" style="color: black;"
							href="historico.jsp"><span
								class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
								Histórico</a></li>
						<li><a class="nav nav-second-level" style="color: black;"
							href="upload.jsp"><span class="glyphicon glyphicon-open"
								aria-hidden="true"></span> Upload</a></li>
						<li><a class="nav nav-second-level" style="color: black;"
							href="configuracoes.jsp"><span
								class="glyphicon glyphicon-cog" aria-hidden="true"></span>
								Configurações da Conta</a></li>
						<li><a id="desconectar" class="nav nav-second-level"
							style="color: black;" href="principal.jsp"><span
								class="glyphicon glyphicon-off" aria-hidden="true"></span>
								Desconectar</a></li>
						<%
							if (usuarioDTOLogin.getLogin().equals("adm")) {
						%>
						<li><a class="nav nav-second-level" style="color: black;"
							href="genero.jsp"><span class="glyphicon glyphicon-plus"
								aria-hidden="true"></span> Cadastrar Gênero</a></li>
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
						<h2 class="modal-title" id="myModalLabel">Meus Arquivos</h2>
					</div>
					<div class="modal-body">
						<div class="input-group custom-search-form">
							<form name="buscarMeusArquivos" action="/HowWeDo/BuscarMusica"
								style="white-space: nowrap;">
								<input type="hidden" name="acao" value="meusArquivosProcurar">
								<input type="text" class="form-control"
									name="parametroMusicaUsuario" size="30px"
									placeholder="Música, artista ou gênero."> &nbsp; <a
									class="btn btn btn-primary glyphicon glyphicon-search"
									type="submit" style="background-color: black"></a>
							</form>
						</div>
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Música</th>
										<th>Artista</th>
										<th>Genero</th>
										<th></th>
										<th></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<%
										if (musicas == null) {
									%>
									<h1>Não Há Músicas Cadastradas Para esse usuário</h1>

									<%
										} else if (musicasUsuario == null || musicasUsuario.isEmpty()) {
																									
																								for (DtoMusica musicasDTO : musicas) {
									%>
									<tr>
										<td><%=musicasDTO.getNome()%></td>
										<td><%=musicasDTO.getArtista().getNome()%></td>
										<td><%=musicasDTO.getGenero().getNome()%></td>
										<td><a type="submit" value="PLay" id="play"
											class="glyphicon glyphicon-play"
											onclick="play(<%=musicasDTO.getCodigo()%>);"
											href='/HowWeDo/IncluirHistorico?acao=incluir&codigoMusica=<%=musicasDTO.getCodigo()%>&jsp=meusarquivos.jsp' /></a></td>
										<td><a type="button" id="caneta"
											href='/HowWeDo/BuscarMusica?acao=Alterar&codigo=<%=musicasDTO.getCodigo()%>'
											class="glyphicon glyphicon-pencil"></a></td>
										<td><a type="button" id="remover"
											href='/HowWeDo/BuscarMusica?acao=Excluir&codigo=<%=musicasDTO.getCodigo()%>'
											class="glyphicon glyphicon-remove"></a></td>
									</tr>
									<script>
										play = function(musica) {

											var fplayer = parent.document
													.getElementById("playerframe");

											var p = fplayer.contentDocument
													.getElementById("player");

											p.setAttribute("src",
													"ListarTeste?acao=Buscar&codigo="
															+ musica);

											p.play();

										}
									</script>
									<%
										}
										} else
											for (DtoMusica musicasDTO : musicasUsuario) {
									%>
									<tr>
										<td><%=musicasDTO.getNome()%></td>
										<td><%=musicasDTO.getArtista().getNome()%></td>
										<td><%=musicasDTO.getGenero().getNome()%></td>
										<td><a type="submit" value="PLay" id="play"
											class="glyphicon glyphicon-play"
											onclick="play(<%=musicasDTO.getCodigo()%>);" 
											href='/HowWeDo/IncluirHistorico?acao=incluir&codigoMusica=<%=musicasDTO.getCodigo()%>&jsp=meusarquivos.jsp'/></a></td>
										<td><a type="button" id="caneta"
											href='/HowWeDo/BuscarMusica?acao=Alterar&codigo=<%=musicasDTO.getCodigo()%>'
											class="glyphicon glyphicon-pencil"></a></td>
										<td><a type="button" id="remover"
											href='/HowWeDo/BuscarMusica?acao=Excluir&codigo=<%=musicasDTO.getCodigo()%>'
											class="glyphicon glyphicon-remove"></a></td>
									</tr>
									<%
										session.removeAttribute("listaMusicaUsuario");
											}
									%>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

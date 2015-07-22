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
</head>
<body>
	<%
		Boolean isAlterarOk = (Boolean) session.getAttribute("alterarOk");
		if (isAlterarOk != null && isAlterarOk) {
	%>
	<script>
		alert("Alterado Com Sucesso!");
	</script>
	<%
		session.setAttribute("alterarOk", true);
		}
	%>
	<%
		Boolean isErrorOk = (Boolean) session.getAttribute("errorsOK");
		if (isErrorOk != null && !isErrorOk) {
	%>
	<script>
		alert("Preencha os Campos que Deseje Alterar");
	</script>
	<%
		session.setAttribute("errorsOK", true);
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
		<%@page import="dto.DtoMusica"%>
		<jsp:useBean id="musicaDTO" scope="request" class="dto.DtoMusica"></jsp:useBean>
		<jsp:useBean id="artista" scope="request" class="dto.DtoMusica"></jsp:useBean>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="modal-header">
						<h2 class="modal-title" id="myModalLabel">Meus Arquivos</h2>
					</div>
					<div class="modal-body">
						<form role="form" action="/HowWeDo/AtualizarMusica" method="post">
							<input type="hidden" name="acao" value="Alteracao"> <input
								type="hidden" name="codigo"
								value='<jsp:getProperty property="codigo" name="musicaDTO"/>'>
							<div class="form-group">
								<label for="musicanome">Nome</label> <input type="text"
									class="form-control" id="musica" name="nome"
									value='<jsp:getProperty property="nome" name="musicaDTO"/>'>
							</div>
							<div class="form-group">
								<label for="musicanome">Artista</label> <input type="text"
									class="form-control" id="musica" name="artista"
									value=<%=musicaDTO.getArtista().getNome()%>>
							</div>
							<div class="modal-footer">
								<input type="submit" value="Alterar" class="btn btn-primary"
									style="background-color: black" /> <a type="btn"
									class="btn btn-default" href="meusarquivos.jsp">Fechar</a>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>

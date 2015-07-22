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
		session.setAttribute("alterarOk", false);
		}
	%>
	<%
		Boolean isErrosOk = (Boolean) session.getAttribute("errorsOk");
		if (isErrosOk != null && isErrosOk) {
	%>
	<script>
		alert("Preencha os Campos Necessários!");
	</script>
	<%
		session.setAttribute("errorsOk", false);
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
                            <form action="/HowWeDo/BuscarMusica" style="white-space: nowrap;">
                                <input type="hidden" name="acao" value="procurar"> <input type="text" class="form-control"
										size="10px" name="parametro"
										placeholder="Música, artista ou gênero."> &nbsp;
                                <button class="btn btn btn-primary glyphicon glyphicon-search" type="submit" style="background-color: black" ></button>                                          
                            </form>
							</div> <!-- /input-group -->
						</li>
						<li><a class="nav nav-second-level" style="color: black;"
							href="meusarquivos.jsp"><span
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
						<li><a class="active" class="nav nav-second-level"
							style="color: black;" href="configuracoes.jsp"><span
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
		<%@page errorPage="/Util/PaginaDeErro.jsp"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="modal-header">
						<h2 class="modal-title" id="myModalLabel">Configurações da
							Conta</h2>
					</div>

					<div class="modal-body">
						<form role="form" action="/HowWeDo/AtualizarUsuario" method="post">
							<h4>Alterar E-mail</h4>
							<div class="form-group">
								<label for="email">Seu e-mail:&nbsp;</label></br> <span>
									******&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</div>
							<div class="form-group">
								<label for="email">Novo e-mail:&nbsp;</label> <input type="text"
									class="form-control" id="newemail" name="email"
									placeholder="Digite seu novo e-mail.">
							</div>
							<div class="modal-footer">
								<input type="submit" value="Alterar Email" name="AlterarEmail"
									class="btn btn-primary" style="background-color: black" />
							</div>

							<h4>Alterar Senha</h4>
							<div class="form-group">
								<label for="email">Sua senha:&nbsp;</label></br> <span>
									******&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</div>
							<div class="form-group">
								<label for="email">Nova senha:&nbsp;</label> <input type="text"
									class="form-control" id="newsenha" name="senha"
									placeholder="Digite sua nova senha.">
							</div>
							<div class="modal-footer">
								<input type="submit" value="Alterar Senha" name="AlterarSenha"
									class="btn btn-primary" style="background-color: black" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>

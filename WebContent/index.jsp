<%@ page import="dto.UsuarioDTO"%>
<%
	Boolean isIncluirOk = (Boolean)request.getAttribute("incluirOk");
	if(isIncluirOk != null && !isIncluirOk)
	{%>
<script>alert("Incluido Com Sucesso!");</script>
<%
		request.setAttribute("incluirOK", true);
	}
%>
<%
	Boolean isExisteOk = (Boolean)request.getAttribute("existeOk");
	if(isExisteOk != null && !isExisteOk)
	{%>
<script>alert("Já existe um usuário cadastrado com esse login ou \nEmail já possui conta no site");</script>
<%
		request.setAttribute("existeOk", true);
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
<link rel="shortcut icon" href="img/icone.ico">

<title>HowWeDo?</title>


<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="css/entrada.css" rel="stylesheet">

<!-- Fonts-->
<link href="font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Kaushan+Script'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>

</head>

<body id="page-top" class="index">
	<!-- Cabeçalho -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header page-scroll">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand page-scroll" href="#page-top">HowWeDo?</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li class="hidden"><a href="#page-top"></a></li>
					<li><a class="btn" data-toggle="modal"
						data-target="#modalEntrar">Entrar</a></li>
					<li><a class="btn" data-toggle="modal"
						data-target="#modalCadastro">Cadastre-se</a></li>
					<li><a class="page-scroll" href="#sobre">Sobre</a></li>
					<li><a class="page-scroll" href="#team">Time</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Principal -->
	<header>
		<div class="container">
			<div class="intro-text">
				<div class="intro-lead-in"></div>
				<div class="intro-heading"></div>
				</br>
				</br>
				</br>
				</br>
				</br>
				</br>
				</br>
				</br>
				</br>
				</br> <a class="btn btn btn-xl" data-toggle="modal"
					data-target="#modalCadastro">Cadastre-se!</a></br> <label>Você
					já tem uma conta?<a class="btn" data-toggle="modal"
					data-target="#modalEntrar">Entrar</a>
				</label>
			</div>
		</div>
	</header>

	<!-- Modal Entrar-->
	<div class="modal fade" id="modalEntrar" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Fechar</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Entrar</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="/HowWeDo/Login" method="post"
						onsubmit="return Validate()">
						<%
	Boolean isLoginOk = (Boolean)session.getAttribute("loginOk");
	if(isLoginOk != null && !isLoginOk)
	{%>
						<script>alert("Dados para autenticação inválidos");</script>
						<%
		session.setAttribute("loginOk", true);
	}
%>
						<div class="form-group">
							<label for="usuario">Usuario</label> <input type="text"
								class="form-control" id="usuario" name="usuario"
								placeholder="Digite seu usuario." required>
						</div>
						<div class="form-group">
							<label for="senha">Senha</label> <input type="password"
								class="form-control" id="senha" name="senha"
								placeholder="Digite sua senha." required>
						</div>
						<div class="modal-footer">
							<input type="submit" value="Entrar" class="btn btn-primary" />
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Fechar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Cadastro-->
	<div class="modal fade" id="modalCadastro" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Fechar</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Cadastre-se</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="/HowWeDo/RegistrarUsuario" method="post">
						<div class="form-group">
							<label for="usuario">Usuario</label> <input type="text"
								class="form-control" id="usuario" name="login"
								placeholder="Digite seu usuario." required>
						</div>
						<div class="form-group">
							<label for="email">Endereço de e-mail</label> <input type="email"
								class="form-control" id="email" name="email" placeholder="Digite seu e-mail."
								required>
						</div>
						<div class="form-group">
							<label for="senha">Senha</label> <input type="password"
								class="form-control" id="senha" name="senha" placeholder="Digite sua senha."
								required>
						</div>
						<div class="modal-footer">
							<input type="submit" value="Cadastrar" class="btn btn-primary" />
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Fechar</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>

	<!-- Sobre -->
	<section id="sobre">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Sobre</h2>
					<h3 class="section-subheading text-muted"></h3>
				</div>
			</div>
			<div class="row text-center">
				<div class="col-md-4">
					<span class="fa-stack fa-4x"> <i
						class="fa fa-circle fa-stack-2x text-primary"></i> <i
						class="fa fa-upload fa-stack-1x fa-inverse"></i>
					</span>
					<h4 class="service-heading">Faça o upload de suas músicas</h4>
					<p class="text-muted">Armazene e compartilhe suas melhores
						músicas.</p>
				</div>
				<div class="col-md-4">
					<span class="fa-stack fa-4x"> <i
						class="fa fa-circle fa-stack-2x text-primary"></i> <i
						class="fa fa-mobile fa-stack-1x fa-inverse"></i>
					</span>
					<h4 class="service-heading">Acesse de onde quiser</h4>
					<p class="text-muted">Curta suas músicas em qualquer lugar e a
						qualquer hora.</p>
				</div>
				<div class="col-md-4">
					<span class="fa-stack fa-4x"> <i
						class="fa fa-circle fa-stack-2x text-primary"></i> <i
						class="fa fa-play fa-stack-1x fa-inverse"></i>
					</span>
					<h4 class="service-heading">Ouça músicas instantaneamente</h4>
					<p class="text-muted">Adicione em sua playlist ou salve para
						mais tarde suas músicas preferidas.</p>
				</div>
			</div>
		</div>
	</section>

	<!-- Time -->
	<section id="team" class="bg-light-gray">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Time</h2>
					<h3 class="section-subheading text-muted">Estes são nossos
						desenvolvedores.</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3">
					<div class="team-member">
						<img src="img/time/1.jpg" class="img-responsive img-circle" alt="">
						<h4>Carlos Flamarin</h4>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="team-member">
						<img src="img/time/2.jpg" class="img-responsive img-circle" alt="">
						<h4>Felipe Arado</h4>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="team-member">
						<img src="img/time/3.jpg" class="img-responsive img-circle" alt="">
						<h4>Paulo Ricardo</h4>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="team-member">
						<img src="img/time/4.jpg" class="img-responsive img-circle" alt="">
						<h4>Vitor Piassi</h4>
					</div>
				</div>
			</div>

		</div>
	</section>

	<!--rodapé-->
	<footer>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<span class="copyright">Copyright &copy; HowWeDo? 2014</span>
				</div>
			</div>
		</div>
	</footer>

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="js/classie.js"></script>
	<script src="js/cbpAnimatedHeader.js"></script>

	<!-- Tema JavaScript -->
	<script src="js/howwedo.js"></script>

</body>

</html>

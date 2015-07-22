/**
 * 
 */
play = function(musica) {
	
	document.getElementById("player");
	document.getElementById("player").setAttribute("src", "ListarTeste?acao=Buscar&codigo=" + musica);
	document.getElementById("player").play();
	
}

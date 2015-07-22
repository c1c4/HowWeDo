function dateMask(inputData, e) {
	var tecla;

	if (document.all) // Internet Explorer
		tecla = event.keyCode;
	else
		//Outros Browsers
		tecla = e.which;

	if (tecla >= 47 && tecla < 58) { // numeros de 0 a 9 e '/'
		var data = inputData.value;

		//se for um numero coloca no input
		if (tecla > 47 && tecla < 58) {
			if (data.length == 2 || data.length == 5) {
				data += '/';

			}
		} else if (tecla == 47) { //se for a barra, so deixa colocar se estiver na posicao certa
			if (data.length != 2 && data.length != 5) {
				return false;
			}
		}
		//atualiza o input da data
		inputData.value = data;
		return true;

	} else if (tecla == 8 || tecla == 0) // Backspace, Delete e setas direcionais(para mover o cursor, apenas para FF)
		return true;
	else
		return false;
}

	function navegacao(href, player){
		if (href != '#'){
			history.pushState(null, null, href);
			$.ajax({
				url: href, 
				success: function(response){
					if(href != '#'){
						//Forçando o parser
						var data = $('<div>'+response+'</div>').find('#player').html(); //Procura no objeto 'response' a div #player do arquivo url
						player.html(data).fadeIn();
					}
				}
			});
		}
	}
package dto;

import java.io.Serializable;

public class DtoFavoritoMusica implements Serializable{
	private int codigo;
	DtoFavoritos codigoFavoritos;
	DtoMusica codigoMusica;
	
	public DtoFavoritoMusica()
	{
		this.codigo = 0;
		this.codigoFavoritos = null;
		this.codigoMusica = null;
	}

	public DtoFavoritoMusica(int codigo, DtoFavoritos codigoFavoritos,
			DtoMusica codigoMusica) {
		super();
		this.codigo = codigo;
		this.codigoFavoritos = codigoFavoritos;
		this.codigoMusica = codigoMusica;
	}
	
	public DtoFavoritoMusica(DtoFavoritos codigoFavoritos,
			DtoMusica codigoMusica) {
		super();		
		this.codigoFavoritos = codigoFavoritos;
		this.codigoMusica = codigoMusica;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public DtoFavoritos getCodigoFavoritos() {
		return codigoFavoritos;
	}

	public void setCodigoFavoritos(DtoFavoritos codigoFavoritos) {
		this.codigoFavoritos = codigoFavoritos;
	}

	public DtoMusica getCodigoMusica() {
		return codigoMusica;
	}

	public void setCodigoMusica(DtoMusica codigoMusica) {
		this.codigoMusica = codigoMusica;
	}
	
	

}

package dto;

import java.io.Serializable;

public class DtoPlaylistMusica implements Serializable{
	private int codigo;
	DtoPlaylist codigoPlaylist;
	DtoMusica codigoMusica;
	
	public DtoPlaylistMusica()
	{
		this.codigo = 0;
		this.codigoPlaylist = null;
		this.codigoMusica = null;
	}

	public DtoPlaylistMusica(int codigo, DtoPlaylist codigoPlaylist,
			DtoMusica codigoMusica) {
		super();
		this.codigo = codigo;
		this.codigoPlaylist = codigoPlaylist;
		this.codigoMusica = codigoMusica;
	}
	
	public DtoPlaylistMusica(DtoPlaylist codigoPlaylist,
			DtoMusica codigoMusica) {
		super();		
		this.codigoPlaylist = codigoPlaylist;
		this.codigoMusica = codigoMusica;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public DtoPlaylist getCodigoPlaylist() {
		return codigoPlaylist;
	}

	public void setCodigoPlaylist(DtoPlaylist codigoPlaylist) {
		this.codigoPlaylist = codigoPlaylist;
	}

	public DtoMusica getCodigoMusica() {
		return codigoMusica;
	}

	public void setCodigoMusica(DtoMusica codigoMusica) {
		this.codigoMusica = codigoMusica;
	}
	
	

}

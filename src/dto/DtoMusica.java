package dto;

import java.io.Serializable;

public class DtoMusica implements Serializable {
	private int codigo;
	private String musica;
	private String nome;
	private DtoArtista artista;
	private UsuarioDTO usuario;
	private DtoGenero genero;
	
	public DtoMusica()
	{
		this.codigo = 0;
		this.nome = "";
		this.artista = null;
		this.genero = null;
		this.usuario = null;
	}
	
	public DtoMusica(int codigo, String musica, String nome, 
			DtoArtista artista, UsuarioDTO usuario, DtoGenero genero) {
		super();
		this.codigo = codigo;
		this.musica = musica;
		this.nome = nome;
		this.artista = artista;
		this.usuario = usuario;
		this.genero = genero;
	}
	
	public DtoMusica(String musica, String nome, DtoArtista artista, 
			UsuarioDTO usuario, DtoGenero genero) {
		this.musica = musica;
		this.nome = nome;
		this.artista = artista;
		this.usuario = usuario;
		this.genero = genero;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMusica() {
		return musica;
	}
	public void setMusica(String musica) {
		this.musica = musica;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public DtoArtista getArtista() {
		return artista;
	}

	public void setArtista(DtoArtista artista) {
		this.artista = artista;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public DtoGenero getGenero() {
		return genero;
	}

	public void setGenero(DtoGenero genero) {
		this.genero = genero;
	}
}

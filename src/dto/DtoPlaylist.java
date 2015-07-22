package dto;

import java.io.Serializable;

public class DtoPlaylist implements Serializable {
	private int codigo;
	private String nome;
	private UsuarioDTO usuario;
	
	public DtoPlaylist()
	{
		this.codigo = 0;
		this.nome = "";
		this.usuario = null;
	}

	public DtoPlaylist(int codigo, String nome, UsuarioDTO usuario) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.usuario = usuario;
	}
	
	public DtoPlaylist(String nome, UsuarioDTO usuario) {
		super();
		this.nome = nome;
		this.usuario = usuario;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
	

}

package dto;

import java.io.Serializable;

public class DtoArtista implements Serializable{
	private int codigo;
	private String nome;
	
	public DtoArtista()
	{
		this.codigo = 0;
		this.nome = "";
	}
	
	
	public DtoArtista(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public DtoArtista(String nome) {
		this.nome = nome;
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
}

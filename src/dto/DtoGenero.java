package dto;

import java.io.Serializable;

public class DtoGenero implements Serializable {
	private int codigo;
	private String nome;
	
	public DtoGenero()
	{
		this.codigo = 0;
		this.nome = "";
	}
	
	public DtoGenero(int codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public DtoGenero(String nome) {
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

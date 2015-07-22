package dto;

import java.io.Serializable;

public class DtoFavoritos implements Serializable {
	private int codigo;
	
	public DtoFavoritos()
	{
		this.codigo = 0;
	}

	public DtoFavoritos(int codigo) {
		super();
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}	
}

package Vo;

import java.util.List;

import business.GeneroBusiness;
import dto.DtoGenero;

public class GeneroVO {
	private List<DtoGenero> generos;
	
	public GeneroVO()
	{
		GeneroBusiness generoBusiness = new GeneroBusiness();
		
		try {
			List<DtoGenero> generos = generoBusiness.Listar();
			this.setGenero(generos);
		} catch (Exception e) {
			this.setGenero(null);
		}
	}

	public List<DtoGenero> getGeneros() {
		return generos;
	}

	public void setGenero(List<DtoGenero> generos) {
		this.generos = generos;
	}
	
}

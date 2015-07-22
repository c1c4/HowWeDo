package business;

import java.util.List;

import dao.FavoritosDAO;
import dto.DtoFavoritos;

public class FavoritosBusiness {

	private FavoritosDAO daoFavoritos = new FavoritosDAO();

	public Boolean Incluir(DtoFavoritos favoritosDTO) throws Exception {
		try
		{
			daoFavoritos.Incluir(favoritosDTO);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public Boolean Excluir(int codigo) throws Exception {
		try
		{
			return daoFavoritos.Excluir(codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

	public DtoFavoritos ObterUltimoId() throws Exception {
		try
		{
			return daoFavoritos.ObterUltimoId();
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

	public DtoFavoritos Buscar(int codigo) throws Exception {
		try
		{
			return daoFavoritos.BuscarFavoritos(codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
}






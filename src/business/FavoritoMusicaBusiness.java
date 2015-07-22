package business;

import java.util.List;

import dao.FavoritoMusicaDAO;
import dto.DtoFavoritoMusica;

public class FavoritoMusicaBusiness {

	private FavoritoMusicaDAO daoFavoritoMusica = new FavoritoMusicaDAO();

	public Boolean Incluir(DtoFavoritoMusica favoritoMusicaDTO) throws Exception {
		try
		{
			daoFavoritoMusica.Incluir(favoritoMusicaDTO);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public Boolean Excluir(int codigo ) throws Exception {
		try
		{
			return daoFavoritoMusica.Excluir(codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

	public List<DtoFavoritoMusica> Listar(int codigo) throws Exception {
		try
		{
			return daoFavoritoMusica.Listar(codigo);
		}catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}

	public DtoFavoritoMusica BuscarRegistro(int codigo) throws Exception{
		try{
			return daoFavoritoMusica.BuscaRegistro(codigo);
		}catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}
}

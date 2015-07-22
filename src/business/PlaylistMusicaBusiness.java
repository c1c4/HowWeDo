package business;

import java.util.List;

import dao.DaoPlaylistMusica;
import dto.DtoFavoritoMusica;
import dto.DtoPlaylistMusica;

public class PlaylistMusicaBusiness {

	private DaoPlaylistMusica daoPlaylistMusica = new DaoPlaylistMusica();

	public Boolean Incluir(DtoPlaylistMusica dtoPlaylistMusica) throws Exception {
		try
		{
			daoPlaylistMusica.Incluir(dtoPlaylistMusica);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public Boolean Excluir(int codigo ) throws Exception {
		try
		{
			return daoPlaylistMusica.Excluir(codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
	public Boolean ExcluirPlaylistMusica(int codigo ) throws Exception {
		try
		{
			return daoPlaylistMusica.ExcluirPlaylistMusica(codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

	public List<DtoPlaylistMusica> Listar(int codigo) throws Exception {
		try
		{
			return daoPlaylistMusica.Listar(codigo);
		}catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}


}

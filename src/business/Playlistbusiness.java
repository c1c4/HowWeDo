package business;

import java.util.List;

import dao.PlaylistDAO;
import dto.DtoMusica;
import dto.DtoPlaylist;

public class Playlistbusiness {

	private PlaylistDAO daoPlaylist = new PlaylistDAO();



	public List<DtoPlaylist> Listar(int codigo) throws Exception {
		List<DtoPlaylist> playlist = null;

		try
		{
			if(!daoPlaylist.VerifiqueConexao())
				throw new Exception("Conexão não estabelecida");

			playlist = daoPlaylist.Listar();
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return playlist;
	}
	

	public Boolean Incluir(DtoPlaylist playlistDTO) throws Exception {
		try
		{
			daoPlaylist.Incluir(playlistDTO);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return true;
	}
	

	
	public Boolean Excluir(int codigo) throws Exception {
		try
		{
			return daoPlaylist.Excluir(codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
	public DtoPlaylist BuscaRegistro(int codigo) throws Exception {
		try
		{
			return daoPlaylist.BuscaRegistro(codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
		
		
	}
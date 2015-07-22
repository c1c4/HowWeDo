package business;

import dao.DaoArtista;
import dto.DtoArtista;

public class ArtistaBusiness {
	DaoArtista daoArtista = new DaoArtista();
	public DtoArtista Incluir(DtoArtista artistaDTO) throws Exception {
		try
		{
			return daoArtista.Incluir(artistaDTO);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
	public DtoArtista BuscaArtista(String nome) throws Exception {
		try
		{
			return daoArtista.BuscaRegistro(nome);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
}

package business;

import java.util.List;

import dao.DaoGenero;
import dto.DtoArtista;
import dto.DtoGenero;
import dto.UsuarioDTO;

public class GeneroBusiness {
	
	private DaoGenero generoDAO = new DaoGenero();
	private DtoGenero generoDTO;
	
	public Boolean Incluir(DtoGenero generoDTO) throws Exception {
		try
		{
			generoDAO.Incluir(generoDTO);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return true;
	}
	
	public List<DtoGenero> Listar() throws Exception {
		List<DtoGenero> generos = null;

		try
		{
			if(!generoDAO.VerifiqueConexao())
				throw new Exception("Conexão não estabelecida");

			generos = generoDAO.Listar();
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return generos;
	}
	
	public DtoGenero BuscaGenero(int codigo) throws Exception {
		try
		{
			return generoDAO.BuscarGenero(codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

}

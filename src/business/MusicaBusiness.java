package business;

import java.util.List;

import dao.DaoMusica;
import dto.DtoGenero;
import dto.DtoMusica;
import dto.UsuarioDTO;

public class MusicaBusiness {
	
	private DaoMusica daoMusica = new DaoMusica();
	
	public Boolean Incluir(DtoMusica musicasDTO) throws Exception {
		try
		{
			daoMusica.Incluir(musicasDTO);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return true;
	}
	
	public Boolean Excluir(int codigo) throws Exception {
		try
		{
			return daoMusica.Excluir(codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
	public Boolean Alterar(DtoMusica musicaDto) throws Exception {
		try
		{
			return daoMusica.Alterar(musicaDto);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
	public List<DtoMusica> Listar(int codigo) throws Exception {
		List<DtoMusica> musicas = null;

		try
		{
			if(!daoMusica.VerifiqueConexao())
				throw new Exception("Conexão não estabelecida");

			musicas = daoMusica.Listar(codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return musicas;
	}
	
	public DtoMusica BuscaRegistro(int codigo) throws Exception {
		try
		{
			return daoMusica.BuscaRegistro(codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
	public List<DtoMusica> Procurar(String parametro) throws Exception {
		List<DtoMusica> musicas = null;

		try
		{
			if(!daoMusica.VerifiqueConexao())
				throw new Exception("Conexão não estabelecida");

			musicas = daoMusica.Procurar(parametro);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return musicas;
	}
	
	public List<DtoMusica> ProcurarMeusArquivos(String parametro, int codigo) throws Exception {
		List<DtoMusica> musicas = null;

		try
		{
			if(!daoMusica.VerifiqueConexao())
				throw new Exception("Conexão não estabelecida");

			musicas = daoMusica.ProcurarMeusArquivos(parametro, codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return musicas;
	}
	
	

}

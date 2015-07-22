package business;

import java.util.List;

import dao.DaoHistorico;
import dto.DtoFavoritoMusica;
import dto.DtoHistorico;
import dto.DtoMusica;

public class HistoricoBusiness {
	private DaoHistorico daoHistorico = new DaoHistorico();

	public Boolean Incluir(DtoHistorico historicoDTO) throws Exception {
		try
		{
			daoHistorico.Incluir(historicoDTO);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return true;
	}
	
	public List<DtoHistorico> Listar(int codigo) throws Exception {
		List<DtoHistorico> historicos = null;

		try
		{
			if(!daoHistorico.VerifiqueConexao())
				throw new Exception("Conexão não estabelecida");

			historicos = daoHistorico.Listar(codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return historicos;
	}

}

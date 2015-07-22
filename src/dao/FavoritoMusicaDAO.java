package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.DtoFavoritoMusica;
import dto.DtoFavoritos;
import dto.DtoMusica;

public class FavoritoMusicaDAO {

	private Connection conexao = null;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;

	private String comandoSql = "";

	public Boolean VerifiqueConexao() throws Exception {
		try
		{
			conexao = DbConexao.getConexao();
			if(conexao == null)
				return false;
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return true;
	}
	
	public List<DtoFavoritoMusica> Listar() throws Exception {
		DtoFavoritoMusica favoritoMusicaDTO;
		List<DtoFavoritoMusica> favoritosMusica = new ArrayList<DtoFavoritoMusica>();

		try
		{
			if(!VerifiqueConexao())
				return favoritosMusica;
			Statement st = (Statement)conexao.createStatement();
			comandoSql =  "SELECT * FROM FAVORITOSMUSICA";
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				favoritoMusicaDTO = new DtoFavoritoMusica();
				favoritoMusicaDTO.setCodigo(rs.getInt("codigo"));
				favoritoMusicaDTO.setCodigoFavoritos((DtoFavoritos) rs.getObject("codigofavoritos"));
				favoritoMusicaDTO.setCodigoMusica((DtoMusica) rs.getObject("codigomusica"));

				favoritosMusica.add(favoritoMusicaDTO);
			}
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return favoritosMusica;
	}

	public DtoFavoritoMusica BuscaRegistro(int codigo) throws Exception, SQLException {
		DtoFavoritoMusica favoritoMusicaDTO = null;
		DtoFavoritos favoritosDTO = new DtoFavoritos();
		int codigoFavoritos;
		FavoritosDAO favoritoDAO = new FavoritosDAO();
		DtoMusica musicaDTO = new DtoMusica();
		int codigoMusica;
		DaoMusica musicaDAO = new DaoMusica();
		try
		{
			if(!VerifiqueConexao())
				return favoritoMusicaDTO;
			
			Statement st = (Statement)conexao.createStatement();
			comandoSql =  "SELECT * FROM FAVORITOSMUSICA WHERE CODIGOMUSICA = " + codigo;
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				favoritoMusicaDTO = new DtoFavoritoMusica();
				favoritoMusicaDTO.setCodigo(rs.getInt("codigo"));
				
				codigoFavoritos = rs.getInt("codigofavorito");
				if(codigoFavoritos > 0){
					favoritosDTO = favoritoDAO.BuscarFavoritos(codigoFavoritos);
				}
				favoritoMusicaDTO.setCodigoFavoritos(favoritosDTO);
				
				codigoMusica = rs.getInt("codigomusica");
				if(codigoMusica > 0){
					musicaDTO = musicaDAO.BuscaRegistro(codigoMusica);
				}
				favoritoMusicaDTO.setCodigoMusica(musicaDTO);
			}
		}
		catch(SQLException e)
		{
			throw new Exception(e.getMessage());
		}

		return favoritoMusicaDTO;
	}

	
	public Boolean Incluir(DtoFavoritoMusica favoritoMusicaDTO) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "INSERT INTO FAVORITOSMUSICA (CODIGOFAVORITO, CODIGOMUSICA) VALUES(?,?)";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setInt(1, favoritoMusicaDTO.getCodigoFavoritos().getCodigo());
			pst.setInt(2, favoritoMusicaDTO.getCodigoMusica().getCodigo());
			
			return (pst.executeUpdate() > 0 ? true : false);
		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível executar o comando " + comandoSql + ". ERRO: " + e);
		}
	}


	public Boolean Excluir(int codigo) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "DELETE FROM FAVORITOSMUSICA WHERE CODIGO = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setInt(1, codigo);
			return (pst.executeUpdate() > 0?true:false);

		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível executar o comando " + comandoSql + ". ERRO: " + e);
		}
	}
	
	public List<DtoFavoritoMusica> Listar(int codigo) throws Exception {
		DtoFavoritoMusica favoritoMusicaDTO;
		DtoFavoritos favoritosDTO = new DtoFavoritos();
		int codigoFavoritos;
		FavoritosDAO favoritoDAO = new FavoritosDAO();
		DtoMusica musicaDTO = new DtoMusica();
		int codigoMusica;
		DaoMusica musicaDAO = new DaoMusica();
		List<DtoFavoritoMusica> favoritosMusica = new ArrayList<DtoFavoritoMusica>();

		try
		{
			if(!VerifiqueConexao())
				return favoritosMusica;
			Statement st = (Statement)conexao.createStatement();
			comandoSql =  "SELECT * FROM FAVORITOSMUSICA WHERE CODIGOFAVORITO = " + codigo;
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				favoritoMusicaDTO = new DtoFavoritoMusica();
				favoritoMusicaDTO.setCodigo(rs.getInt("codigo"));
				
				codigoFavoritos = rs.getInt("codigofavorito");
				if(codigoFavoritos > 0){
					favoritosDTO = favoritoDAO.BuscarFavoritos(codigoFavoritos);
				}
				favoritoMusicaDTO.setCodigoFavoritos(favoritosDTO);
				
				codigoMusica = rs.getInt("codigomusica");
				if(codigoMusica > 0){
					musicaDTO = musicaDAO.BuscaRegistro(codigoMusica);
				}
				favoritoMusicaDTO.setCodigoMusica(musicaDTO);

				favoritosMusica.add(favoritoMusicaDTO);
			}
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return favoritosMusica;
	}
}

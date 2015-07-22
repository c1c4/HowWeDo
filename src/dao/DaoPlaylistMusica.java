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
import dto.DtoPlaylist;
import dto.DtoPlaylistMusica;

public class DaoPlaylistMusica {

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
	
	public List<DtoPlaylistMusica> Listar() throws Exception {
		DtoPlaylistMusica dtoPlaylistMusica;
		List<DtoPlaylistMusica> playlistMusica = new ArrayList<DtoPlaylistMusica>();

		try
		{
			if(!VerifiqueConexao())
				return playlistMusica;
			Statement st = (Statement)conexao.createStatement();
			comandoSql =  "SELECT * FROM PLAYLISTMUSICA";
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				dtoPlaylistMusica = new DtoPlaylistMusica();
				dtoPlaylistMusica.setCodigo(rs.getInt("codigo"));
				dtoPlaylistMusica.setCodigoPlaylist((DtoPlaylist) rs.getObject("codigoplaylist"));
				dtoPlaylistMusica.setCodigoMusica((DtoMusica) rs.getObject("codigomusica"));

				playlistMusica.add(dtoPlaylistMusica);
			}
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return playlistMusica;
	}

	public DtoPlaylistMusica BuscaRegistro(int codigo) throws Exception, SQLException {

		DtoPlaylistMusica dtoPlaylistMusica = null;
		try
		{
			if(!VerifiqueConexao())
				return dtoPlaylistMusica;
			
			Statement st = (Statement)conexao.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
			comandoSql =  "SELECT * FROM PLAYLISTMUSICA WHERE CODIGO = " + codigo;
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				dtoPlaylistMusica = new DtoPlaylistMusica();
				dtoPlaylistMusica.setCodigo(rs.getInt("codigo"));
				dtoPlaylistMusica.setCodigoPlaylist((DtoPlaylist) rs.getObject("codigoplaylist"));
				dtoPlaylistMusica.setCodigoMusica((DtoMusica) rs.getObject("codigomusica"));
			}
		}
		catch(SQLException e)
		{
			throw new Exception(e.getMessage());
		}

		return dtoPlaylistMusica;
	}

	
	public Boolean Incluir(DtoPlaylistMusica dtoPlaylistMusica) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "INSERT INTO PLAYLISTMUSICA (CODIGOPLAYLIST, CODIGOMUSICA) VALUES(?,?)";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setInt(1, dtoPlaylistMusica.getCodigoPlaylist().getCodigo());
			pst.setInt(2, dtoPlaylistMusica.getCodigoMusica().getCodigo());
			
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

			comandoSql = "DELETE FROM PLAYLISTMUSICA WHERE CODIGO = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setInt(1, codigo);
			return (pst.executeUpdate() > 0?true:false);

		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível executar o comando " + comandoSql + ". ERRO: " + e);
		}
	}
	
	public Boolean ExcluirPlaylistMusica(int codigo) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "DELETE FROM PLAYLISTMUSICA WHERE CODIGOPLAYLIST = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setInt(1, codigo);
			return (pst.executeUpdate() > 0?true:false);

		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível executar o comando " + comandoSql + ". ERRO: " + e);
		}
	}
	
	public List<DtoPlaylistMusica> Listar(int codigo) throws Exception {
		DtoPlaylistMusica dtoPlaylistMusica;
		DtoPlaylist dtoPlaylist = new DtoPlaylist();
		int codigoPlaylist;
		PlaylistDAO playlistDAO = new PlaylistDAO();
		DtoMusica musicaDTO = new DtoMusica();
		int codigoMusica;
		DaoMusica musicaDAO = new DaoMusica();
		List<DtoPlaylistMusica> playlistMusica = new ArrayList<DtoPlaylistMusica>();

		try
		{
			if(!VerifiqueConexao())
				return playlistMusica;
			Statement st = (Statement)conexao.createStatement();
			comandoSql =  "SELECT * FROM PLAYLISTMUSICA WHERE CODIGOPLAYLIST = " + codigo;
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				dtoPlaylistMusica = new DtoPlaylistMusica();
				dtoPlaylistMusica.setCodigo(rs.getInt("codigo"));
				
				codigoPlaylist = rs.getInt("codigoplaylist");
				if(codigoPlaylist > 0){
					dtoPlaylist = playlistDAO.BuscarPlaylist(codigoPlaylist);
				}
				dtoPlaylistMusica.setCodigoPlaylist(dtoPlaylist);
				
				codigoMusica = rs.getInt("codigomusica");
				if(codigoMusica > 0){
					musicaDTO = musicaDAO.BuscaRegistro(codigoMusica);
				}
				dtoPlaylistMusica.setCodigoMusica(musicaDTO);

				playlistMusica.add(dtoPlaylistMusica);
			}
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return playlistMusica;
	}
}

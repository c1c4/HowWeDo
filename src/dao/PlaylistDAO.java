package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.DtoPlaylist;

public class PlaylistDAO {

	private Connection conexao = null;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;

	private String comandoSql = "";
	
    ///////// Verificando Conexão com o Banco //////////

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
	
///////// Listando Playlist Cadastradas   //////////
	
	public List<DtoPlaylist> Listar() throws Exception {
		DtoPlaylist dtoPlaylist;
		List<DtoPlaylist> playlist = new ArrayList<DtoPlaylist>();


		try
		{
			if(!VerifiqueConexao())
				return playlist;

			Statement st = (Statement)conexao.createStatement();
			comandoSql =  "SELECT * FROM PLAYLIST ORDER BY NOME";
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				dtoPlaylist = new DtoPlaylist();
				dtoPlaylist.setCodigo(rs.getInt("codigo"));
				dtoPlaylist.setNome(rs.getString("nome"));

				playlist.add(dtoPlaylist);
			}
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return playlist;
	}
	
	public DtoPlaylist BuscaRegistro(int codigo) throws Exception, SQLException {

		DtoPlaylist dtoPlaylist = null;
		try
		{
			if(!VerifiqueConexao())
				return dtoPlaylist;

			Statement st = (Statement)conexao.createStatement();
			comandoSql =  "SELECT * FROM PLAYLIST WHERE CODIGO = " + codigo;
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				dtoPlaylist = new DtoPlaylist();
				dtoPlaylist.setCodigo(rs.getInt("codigo"));
				dtoPlaylist.setNome(rs.getString("nome"));
	
			}
		}
		catch(SQLException e)
		{
			throw new Exception(e.getMessage());
		}

		return dtoPlaylist;
	}


    //////////////////////// Incluindo Playlist /////////////////////////
	
	public Boolean Incluir(DtoPlaylist playlistDTO) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "INSERT INTO PLAYLIST(NOME, CODIGOUSUARIO) VALUES (?,?)";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setString(1, playlistDTO.getNome());
			pst.setInt(2, playlistDTO.getUsuario().getCodigo());
			
			return (pst.executeUpdate() > 0 ? true : false);

		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível executar o comando " + comandoSql + ". ERRO: " + e);
		}
	}

	public DtoPlaylist BuscarPlaylist(int codigo) throws Exception, SQLException{

		DtoPlaylist dtoPlaylist = null;

		try{
			if(!VerifiqueConexao())
				return dtoPlaylist;

			Statement st = (Statement)conexao.createStatement();
			comandoSql = "SELECT * FROM PLAYLIST WHERE CODIGO = " + codigo;
			rs = st.executeQuery(comandoSql);
			while (rs.next()) {

				dtoPlaylist = new DtoPlaylist();
				dtoPlaylist.setCodigo(rs.getInt("codigo"));
			}
		}
		catch (SQLException e)
		{
			throw new Exception(e.getMessage());
		}
		return dtoPlaylist;
	}


	
    //////////////// Excluindo Playlist /////////////////

	public Boolean Excluir(int codigo) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "DELETE FROM PLAYLIST WHERE CODIGO = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setInt(1, codigo);
			return (pst.executeUpdate() > 0?true:false);

		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível executar o comando " + comandoSql + ". ERRO: " + e);
		}
	}
	
	

}

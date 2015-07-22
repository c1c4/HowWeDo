package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.DtoFavoritos;
import dto.DtoGenero;
import dto.UsuarioDTO;

public class FavoritosDAO {

	private Connection conexao = null;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;
	DtoFavoritos dtoFavoritos = new DtoFavoritos();

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

    //////////////////////// Incluindo Favoritos ////////////////////////
	
	public Boolean Incluir(DtoFavoritos favoritosDTO) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;
			
			comandoSql = "INSERT INTO FAVORITOS VALUES ('')";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			
			return (pst.executeUpdate() > 0?true:false);
			
			
			

		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível executar o comando " + comandoSql + ". ERRO: " + e);
		}
	}

    //////////////// Excluindo Favoritos /////////////////

	public Boolean Excluir(int codigo) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "DELETE FROM FAVORITOS WHERE CODIGO = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setInt(1, codigo);
			return (pst.executeUpdate() > 0?true:false);

		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível executar o comando " + comandoSql + ". ERRO: " + e);
		}
	}
	
	public DtoFavoritos ObterUltimoId() throws Exception, SQLException {

		DtoFavoritos favoritosDTO = null;
		try
		{
			if(!VerifiqueConexao())
				return favoritosDTO;
			
			Statement st = (Statement)conexao.createStatement();
			comandoSql =  "SELECT * FROM FAVORITOS";
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
			favoritosDTO = new DtoFavoritos();
			favoritosDTO.setCodigo(rs.getInt("codigo"));
			}
			
		}
		catch(SQLException e)
		{
			throw new Exception(e.getMessage());
		}

		return favoritosDTO;
	}
	
	public DtoFavoritos BuscarFavoritos(int codigo) throws Exception, SQLException{

		DtoFavoritos dtoFavoritos = null;

		try{
			if(!VerifiqueConexao())
				return dtoFavoritos;

			Statement st = (Statement)conexao.createStatement();
			comandoSql = "SELECT * FROM FAVORITOS WHERE CODIGO = " + codigo;
			rs = st.executeQuery(comandoSql);
			while (rs.next()) {

				dtoFavoritos = new DtoFavoritos();
				dtoFavoritos.setCodigo(rs.getInt("codigo"));
			}
		}
		catch (SQLException e)
		{
			throw new Exception(e.getMessage());
		}
		return dtoFavoritos;
	}

}

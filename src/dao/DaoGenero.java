package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import dto.DtoGenero;

public class DaoGenero {

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

	public List <DtoGenero> Listar() throws Exception{
		DtoGenero dtoGenero;
		List <DtoGenero> genero = new ArrayList<DtoGenero>();


		try{
			if(!VerifiqueConexao())
				return genero;
			Statement st = (Statement)conexao.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
			comandoSql = "SELECT * FROM GENERO ORDER BY NOME";
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				dtoGenero = new DtoGenero();
				dtoGenero.setCodigo(rs.getInt("codigo"));
				dtoGenero.setNome(rs.getString("nome"));
				genero.add(dtoGenero);

			}

		}catch (SQLException e)
		{
			throw new Exception(e.getMessage());
		}
		return genero;
	}

	public DtoGenero BuscarGenero(int codigo) throws Exception, SQLException{

		DtoGenero dtoGenero = null;

		try{
			if(!VerifiqueConexao())
				return dtoGenero;

			Statement st = (Statement)conexao.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
			comandoSql = "SELECT * FROM GENERO WHERE CODIGO = " + codigo;
			rs = st.executeQuery(comandoSql);
			while (rs.next()) {

				dtoGenero = new DtoGenero();
				dtoGenero.setNome(rs.getString("nome"));
				dtoGenero.setCodigo(rs.getInt("codigo"));
			}
		}
		catch (SQLException e)
		{
			throw new Exception(e.getMessage());
		}
		return dtoGenero;
	}
	
	public Boolean Incluir(DtoGenero dtoGenero) throws Exception{

		try{

			if(!VerifiqueConexao())
				return false;

			comandoSql = "INSERT INTO GENERO(NOME) VALUES (?)";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setString(1, dtoGenero.getNome());
			return (pst.executeUpdate() > 0 ? true : false);

		}
		catch (Exception e){
			throw new Exception("Nao foi possivel incluir o genero músical" + comandoSql + ". ERRO: " + e);
		}
	}
	
	public Boolean Alterar(DtoGenero dtoGenero) throws Exception{

		try{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "UPDATE GENERO SET NOME=? WHERE CODIGO = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setString(1, dtoGenero.getNome());
			pst.setInt(2, dtoGenero.getCodigo());
			return (pst.executeUpdate() > 0 ? true : false);

		}catch (SQLException e)
		{
			throw new Exception("Não foi possível alterar o genero músical " + comandoSql + ". ERRO: " + e);
		}
	}
	
	public Boolean Excluir(int codigo) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "DELETE FROM GENERO WHERE CODIGO = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setInt(1, codigo);
			return (pst.executeUpdate() > 0?true:false);

		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível excluir o genero músical " + comandoSql + ". ERRO: " + e);
		}
	}
	
}

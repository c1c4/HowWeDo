package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.DtoArtista;

public class DaoArtista {

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

	public List <DtoArtista> Listar() throws Exception{
		DtoArtista dtoArtista;
		List <DtoArtista> artistas = new ArrayList<DtoArtista>();

		try{
			if(!VerifiqueConexao())
				return artistas;
			Statement st = (Statement)conexao.createStatement();
			comandoSql = "SELECT * FROM ARTISTA ORDER BY NOME";
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				dtoArtista = new DtoArtista();
				dtoArtista.setCodigo(rs.getInt("codigo"));
				dtoArtista.setNome(rs.getString("nome"));
				artistas.add(dtoArtista);

			}

		}catch (SQLException e)
		{
			throw new Exception(e.getMessage());
		}

		return artistas;
	} 


	public DtoArtista BuscaRegistro(String nome) throws Exception, SQLException{

		DtoArtista dtoArtista = null;

		try{
			if(!VerifiqueConexao())
				return dtoArtista;

			Statement st = (Statement)conexao.createStatement();
			comandoSql = "SELECT * FROM ARTISTA WHERE NOME = '" + nome +"'";
			rs = st.executeQuery(comandoSql);
			while (rs.next()) {

				dtoArtista = new DtoArtista();
				dtoArtista.setCodigo(rs.getInt("codigo"));
				dtoArtista.setNome(rs.getString("nome"));
			}
		}
		catch (SQLException e)
		{
			throw new Exception(e.getMessage());
		}
		return dtoArtista;
	}

	public DtoArtista Incluir(DtoArtista artistaDTO) throws Exception{
		try{

			if(!VerifiqueConexao()){
				return new DtoArtista();}
			
			DtoArtista artistaDtotemp = BuscaRegistro(artistaDTO.getNome());
			if(artistaDtotemp != null)
			{
				return artistaDtotemp;
			}

			comandoSql = "INSERT INTO ARTISTA(NOME) VALUES (?)";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setString(1, artistaDTO.getNome());
			if(pst.executeUpdate() == 0)
			{
				return new DtoArtista();
			}
		
			return BuscaRegistro(artistaDTO.getNome());
		}
		catch (Exception e){
			throw new Exception("Nao foi possivel incluir novo Artista" + comandoSql + ". ERRO: " + e);
		}
	}

	public Boolean Alterar(DtoArtista dtoArtista) throws Exception{

		try{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "UPDATE ARTISTA SET NOME=? WHERE CODIGO = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setString(1, dtoArtista.getNome());
			pst.setInt(2, dtoArtista.getCodigo());
			return (pst.executeUpdate() > 0 ? true : false);

		}catch (SQLException e)
		{
			throw new Exception("Não foi possível executar o comando " + comandoSql + ". ERRO: " + e);
		}
	}

	public Boolean Excluir(int codigo) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "DELETE FROM ARTISTA WHERE CODIGO = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setInt(1, codigo);
			return (pst.executeUpdate() > 0?true:false);

		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível executar o comando " + comandoSql + ". ERRO: " + e);
		}
	}
	
	public DtoArtista BuscaRegistro(int codigo) throws Exception, SQLException{

		DtoArtista dtoArtista = null;

		try{
			if(!VerifiqueConexao())
				return dtoArtista;

			Statement st = (Statement)conexao.createStatement();
			comandoSql = "SELECT * FROM ARTISTA WHERE CODIGO = " + codigo;
			rs = st.executeQuery(comandoSql);
			while (rs.next()) {

				dtoArtista = new DtoArtista();
				dtoArtista.setCodigo(rs.getInt("codigo"));
				dtoArtista.setNome(rs.getString("nome"));
			}
		}
		catch (SQLException e)
		{
			throw new Exception(e.getMessage());
		}
		return dtoArtista;
	}
}

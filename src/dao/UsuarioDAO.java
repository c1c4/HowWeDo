package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.DtoFavoritos;
import dto.UsuarioDTO;

public class UsuarioDAO {

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

	//////////////////////// Fazendo Login //////////////////////////

	public UsuarioDTO Login(UsuarioDTO usuarioDTO) throws Exception {
		UsuarioDTO dto = null;

		try
		{
			if(!VerifiqueConexao())
				return dto;

			st = (Statement)conexao.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
			comandoSql =  "SELECT * FROM USUARIO WHERE";
			comandoSql += " LOGIN = '" + usuarioDTO.getLogin() + "'";
			comandoSql += " AND SENHA = '" + usuarioDTO.getSenha() + "'";
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				if(rs.getString("login").equals(usuarioDTO.getLogin()) && rs.getString("senha").equals(usuarioDTO.getSenha()))
				{
					dto = new UsuarioDTO();
					dto.setCodigo(rs.getInt("codigo"));
					dto.setEmail(rs.getString("email"));
					dto.setLogin(rs.getString("login"));
					dto.setSenha(rs.getString("senha"));
					return dto;
				}
			}
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return dto;
	}

	////////////////////// Incluindo Usuário ///////////////////////

	public Boolean Incluir(UsuarioDTO usuarioDTO) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "INSERT INTO USUARIO(EMAIL,LOGIN,SENHA,CODIGOFAVORITO) VALUES (?,?,?,?)";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setString(1, usuarioDTO.getEmail());
			pst.setString(2, usuarioDTO.getLogin());
			pst.setString(3, usuarioDTO.getSenha());
			pst.setInt(4, usuarioDTO.getFavorito().getCodigo());
			return (pst.executeUpdate() > 0 ? true : false);

		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível executar o comando " + comandoSql + ". ERRO: " + e);
		}
	}

	////////////////////// Alterando usuário ///////////////////////

	public Boolean Alterar(UsuarioDTO usuarioDTO) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "UPDATE USUARIO SET EMAIL=?,SENHA=? WHERE CODIGO = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setString(1, usuarioDTO.getEmail());
			pst.setString(2, usuarioDTO.getSenha());
			pst.setInt(3, usuarioDTO.getCodigo());
			return (pst.executeUpdate() > 0 ? true : false);

		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível executar o comando " + comandoSql + ". ERRO: " + e);
		}
	}

	public UsuarioDTO BuscaRegistro(int codigo) throws Exception, SQLException {
		DtoFavoritos favoritosDTO = new DtoFavoritos();
		int codigoFavoritos;
		FavoritosDAO favoritosDAO = new FavoritosDAO();

		UsuarioDTO usuarioDTO = null;
		try
		{
			if(!VerifiqueConexao())
				return usuarioDTO;

			Statement st = (Statement)conexao.createStatement();
			comandoSql =  "SELECT * FROM USUARIO WHERE CODIGO = " + codigo;
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				usuarioDTO = new UsuarioDTO();
				usuarioDTO.setCodigo(rs.getInt("codigo"));
				usuarioDTO.setLogin(rs.getString("login"));
				usuarioDTO.setEmail(rs.getString("email"));

				codigoFavoritos = rs.getInt("codigofavorito");
				if(codigoFavoritos > 0)
				{
					favoritosDTO = favoritosDAO.BuscarFavoritos(codigoFavoritos);
				}
				usuarioDTO.setFavorito(favoritosDTO);
			}
		}
		catch(SQLException e)
		{
			throw new Exception(e.getMessage());
		}

		return usuarioDTO;
	}

	public List<UsuarioDTO> Listar() throws Exception {
		UsuarioDTO usuarioDTO;
		List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();

		try
		{
			if(!VerifiqueConexao())
				return usuarios;
			Statement st = (Statement)conexao.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
			comandoSql =  "SELECT * FROM USUARIO ORDER BY NOME";
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				usuarioDTO = new UsuarioDTO();
				usuarioDTO.setLogin(rs.getString("login"));
				usuarioDTO.setEmail(rs.getString("email"));

				usuarios.add(usuarioDTO);
			}
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return usuarios;
	}

	public UsuarioDTO BuscaRegistro(String login) throws Exception, SQLException {
		DtoFavoritos favoritosDTO = new DtoFavoritos();
		int codigoFavoritos;
		FavoritosDAO favoritosDAO = new FavoritosDAO();

		UsuarioDTO usuarioDTO = null;
		try
		{
			if(!VerifiqueConexao())
				return usuarioDTO;

			Statement st = (Statement)conexao.createStatement();
			comandoSql =  "SELECT * FROM USUARIO WHERE LOGIN = '" + login + "'";
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				usuarioDTO = new UsuarioDTO();
				usuarioDTO.setCodigo(rs.getInt("codigo"));
				usuarioDTO.setLogin(rs.getString("login"));
				usuarioDTO.setSenha(rs.getString("senha"));
				usuarioDTO.setEmail(rs.getString("EMAIL"));


				codigoFavoritos = rs.getInt("codigofavorito");
				if(codigoFavoritos > 0)
				{
					favoritosDTO = favoritosDAO.BuscarFavoritos(codigoFavoritos);
				}
				usuarioDTO.setFavorito(favoritosDTO);
			}
		}
		catch(SQLException e)
		{
			throw new Exception(e.getMessage());
		}

		return usuarioDTO;
	}
	
	public UsuarioDTO VerificaRegistro(String login, String email) throws Exception, SQLException {
		DtoFavoritos favoritosDTO = new DtoFavoritos();
		int codigoFavoritos;
		FavoritosDAO favoritosDAO = new FavoritosDAO();

		UsuarioDTO usuarioDTO = null;
		try
		{
			if(!VerifiqueConexao())
				return usuarioDTO;

			Statement st = (Statement)conexao.createStatement();
			comandoSql =  "SELECT * FROM USUARIO WHERE LOGIN = '" + login + "' OR EMAIL = '" +  email + "'";
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				usuarioDTO = new UsuarioDTO();
				usuarioDTO.setCodigo(rs.getInt("codigo"));
				usuarioDTO.setLogin(rs.getString("login"));
				usuarioDTO.setSenha(rs.getString("senha"));
				usuarioDTO.setEmail(rs.getString("EMAIL"));


				codigoFavoritos = rs.getInt("codigofavorito");
				if(codigoFavoritos > 0)
				{
					favoritosDTO = favoritosDAO.BuscarFavoritos(codigoFavoritos);
				}
				usuarioDTO.setFavorito(favoritosDTO);
			}
		}
		catch(SQLException e)
		{
			throw new Exception(e.getMessage());
		}

		return usuarioDTO;
	}

}

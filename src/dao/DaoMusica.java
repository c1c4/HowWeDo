package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.DtoArtista;
import dto.DtoGenero;
import dto.DtoMusica;
import dto.UsuarioDTO;


public class DaoMusica {

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

	public List <DtoMusica> Listar(int codigo) throws Exception{
		DtoMusica dtoMusica;
		DtoArtista artistaDTO = new DtoArtista();
		int codigoArtista;
		DaoArtista artistaDAO = new DaoArtista();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		int codigoUsuario;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		DtoGenero generoDTO = new DtoGenero();
		int codigoGenero;
		DaoGenero generoDAO = new DaoGenero();
		List <DtoMusica> musicas = new ArrayList<DtoMusica>();



		try{
			if(!VerifiqueConexao())
				return musicas;
			Statement st = (Statement)conexao.createStatement();
			comandoSql = "SELECT * FROM MUSICA WHERE CODIGOUSUARIO =" + codigo + "ORDER BY NOME";
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				dtoMusica = new DtoMusica();
				dtoMusica.setCodigo(rs.getInt("codigo"));
				dtoMusica.setMusica(rs.getString("musica"));
				dtoMusica.setNome(rs.getString("nome"));
				codigoUsuario = rs.getInt("codigousuario");
				if(codigoUsuario > 0)
				{
					usuarioDTO = usuarioDAO.BuscaRegistro(codigoUsuario);
				}
				dtoMusica.setUsuario(usuarioDTO);

				codigoArtista = rs.getInt("codigoartista");
				if(codigoArtista > 0)
				{
					artistaDTO = artistaDAO.BuscaRegistro(codigoArtista);
				}
				dtoMusica.setArtista(artistaDTO);

				codigoGenero = rs.getInt("codigogenero");
				if(codigoGenero > 0)
				{
					generoDTO = generoDAO.BuscarGenero(codigoGenero);
				}
				dtoMusica.setGenero(generoDTO);
				musicas.add(dtoMusica);

			}

		}catch (SQLException e)
		{
			throw new Exception(e.getMessage());
		}

		return musicas;
	}


	public DtoMusica BuscaRegistro(int codigo) throws Exception, SQLException{

		DtoMusica dtoMusica = null;
		DtoArtista artistaDTO = new DtoArtista();
		int codigoArtista;
		DaoArtista artistaDAO = new DaoArtista();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		int codigoUsuario;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		DtoGenero generoDTO = new DtoGenero();
		int codigoGenero;
		DaoGenero generoDAO = new DaoGenero();

		try{
			if(!VerifiqueConexao())
				return dtoMusica;

			Statement st = (Statement)conexao.createStatement();
			comandoSql = "SELECT * FROM MUSICA WHERE CODIGO = " + codigo;
			rs = st.executeQuery(comandoSql);
			while (rs.next()) {

				dtoMusica = new DtoMusica();
				dtoMusica.setCodigo(rs.getInt("codigo"));
				dtoMusica.setMusica(rs.getString("musica"));
				dtoMusica.setNome(rs.getString("nome"));
				codigoUsuario = rs.getInt("codigousuario");
				if(codigoUsuario > 0)
				{
					usuarioDTO = usuarioDAO.BuscaRegistro(codigoUsuario);
				}
				dtoMusica.setUsuario(usuarioDTO);

				codigoArtista = rs.getInt("codigoartista");
				if(codigoArtista > 0)
				{
					artistaDTO = artistaDAO.BuscaRegistro(codigoArtista);
				}
				dtoMusica.setArtista(artistaDTO);

				codigoGenero = rs.getInt("codigogenero");
				if(codigoGenero > 0)
				{
					generoDTO = generoDAO.BuscarGenero(codigoGenero);
				}
				dtoMusica.setGenero(generoDTO);
			}
		}
		catch (SQLException e)
		{
			throw new Exception(e.getMessage());
		}
		return dtoMusica;
	}

	public Boolean Incluir(DtoMusica dtoMusica) throws Exception{

		try{

			if(!VerifiqueConexao())
				return false;

			comandoSql = "INSERT INTO MUSICA(MUSICA, NOME, CODIGOARTISTA, CODIGOUSUARIO, CODIGOGENERO) VALUES (?,?,?,?,?)";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setString(1, dtoMusica.getMusica());
			pst.setString(2, dtoMusica.getNome());
			pst.setInt(3, dtoMusica.getArtista().getCodigo());
			pst.setInt(4, dtoMusica.getUsuario().getCodigo());
			pst.setInt(5, dtoMusica.getGenero().getCodigo());
			return (pst.executeUpdate() > 0 ? true : false);


		}
		catch (Exception e){
			throw new Exception("Nao foi possivel incluir música no banco" + comandoSql + ". ERRO: " + e);
		}
	}

	public Boolean Alterar(DtoMusica dtoMusica) throws Exception{

		try{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "UPDATE MUSICA SET NOME=?, CODIGOARTISTA=? WHERE CODIGO = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);			
			pst.setString(1, dtoMusica.getNome());
			pst.setInt(2, dtoMusica.getArtista().getCodigo());
			pst.setInt(3, dtoMusica.getCodigo());
			return (pst.executeUpdate() > 0 ? true : false);

		}catch (SQLException e)
		{
			throw new Exception("Não foi possível alterar a música " + comandoSql + ". ERRO: " + e);
		}
	}

	public Boolean Excluir(int codigo) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "DELETE FROM MUSICA WHERE CODIGO = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setInt(1, codigo);
			return (pst.executeUpdate() > 0?true:false);

		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível excluir a música " + comandoSql + ". ERRO: " + e);
		}
	}

	public List <DtoMusica> Procurar(String parametro) throws Exception{
		DtoMusica dtoMusica;
		DtoArtista artistaDTO = new DtoArtista();
		int codigoArtista;
		DaoArtista artistaDAO = new DaoArtista();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		int codigoUsuario;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		DtoGenero generoDTO = new DtoGenero();
		int codigoGenero;
		DaoGenero generoDAO = new DaoGenero();
		List <DtoMusica> musicas = new ArrayList<DtoMusica>();



		try{
			if(!VerifiqueConexao())
				return musicas;

			Statement st = (Statement)conexao.createStatement();
			comandoSql = "SELECT * FROM musica m " ;
			comandoSql +="INNER JOIN artista art ON m.codigoartista = art.codigo " ;
			comandoSql +="INNER JOIN genero g ON m.codigogenero = g.codigo " ;
		    comandoSql +="WHERE m.nome like '"+ parametro +"' OR ";
			comandoSql +="art.nome like '"+ parametro +"' OR ";
			comandoSql +="g.nome like '"+ parametro +"'";


			rs = st.executeQuery(comandoSql);

			while(rs.next())
			{
				dtoMusica = new DtoMusica();
				dtoMusica.setCodigo(rs.getInt("codigo"));
				dtoMusica.setMusica(rs.getString("musica"));
				dtoMusica.setNome(rs.getString("nome"));
				codigoUsuario = rs.getInt("codigousuario");
				if(codigoUsuario > 0)
				{
					usuarioDTO = usuarioDAO.BuscaRegistro(codigoUsuario);
				}
				dtoMusica.setUsuario(usuarioDTO);

				codigoArtista = rs.getInt("codigoartista");
				if(codigoArtista > 0)
				{
					artistaDTO = artistaDAO.BuscaRegistro(codigoArtista);
				}
				dtoMusica.setArtista(artistaDTO);

				codigoGenero = rs.getInt("codigogenero");
				if(codigoGenero > 0)
				{
					generoDTO = generoDAO.BuscarGenero(codigoGenero);
				}
				dtoMusica.setGenero(generoDTO);
				musicas.add(dtoMusica);

			}

		}catch (SQLException e)
		{
			throw new Exception(e.getMessage());
		}

		return musicas;
	}
	
	public List <DtoMusica> ProcurarMeusArquivos(String parametro, int codigo) throws Exception{
		DtoMusica dtoMusica;
		DtoArtista artistaDTO = new DtoArtista();
		int codigoArtista;
		DaoArtista artistaDAO = new DaoArtista();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		int codigoUsuario;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		DtoGenero generoDTO = new DtoGenero();
		int codigoGenero;
		DaoGenero generoDAO = new DaoGenero();
		List <DtoMusica> musicas = new ArrayList<DtoMusica>();



		try{
			if(!VerifiqueConexao())
				return musicas;

			Statement st = (Statement)conexao.createStatement();
			comandoSql = "SELECT * FROM musica m " ;
			comandoSql +="INNER JOIN artista art ON m.codigoartista = art.codigo " ;
			comandoSql +="INNER JOIN genero g ON m.codigogenero = g.codigo " ;
			comandoSql +="INNER JOIN usuario u ON m.codigousuario = u.codigo " ;
		    comandoSql +="WHERE m.nome like '"+ parametro +"' OR ";
			comandoSql +="art.nome like '"+ parametro +"' OR ";
			comandoSql +="g.nome like '"+ parametro +"' AND ";
			comandoSql +="u.codigo = " + codigo;
 

			rs = st.executeQuery(comandoSql);

			while(rs.next())
			{
				dtoMusica = new DtoMusica();
				dtoMusica.setCodigo(rs.getInt("codigo"));
				dtoMusica.setMusica(rs.getString("musica"));
				dtoMusica.setNome(rs.getString("nome"));
				codigoUsuario = rs.getInt("codigousuario");
				if(codigoUsuario > 0)
				{
					usuarioDTO = usuarioDAO.BuscaRegistro(codigoUsuario);
				}
				dtoMusica.setUsuario(usuarioDTO);

				codigoArtista = rs.getInt("codigoartista");
				if(codigoArtista > 0)
				{
					artistaDTO = artistaDAO.BuscaRegistro(codigoArtista);
				}
				dtoMusica.setArtista(artistaDTO);

				codigoGenero = rs.getInt("codigogenero");
				if(codigoGenero > 0)
				{
					generoDTO = generoDAO.BuscarGenero(codigoGenero);
				}
				dtoMusica.setGenero(generoDTO);
				musicas.add(dtoMusica);

			}

		}catch (SQLException e)
		{
			throw new Exception(e.getMessage());
		}

		return musicas;
	}
}

package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import business.MusicaBusiness;
import dto.DtoHistorico;
import dto.DtoMusica;
import dto.UsuarioDTO;


public class DaoHistorico {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Connection conexao = null;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;

	private String comandoSql = "";
	private Date dataHistorico;
	
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
	
	public List <DtoHistorico> Listar(int codigo) throws Exception{
		DtoHistorico dtoHistorico;
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		int codigoUsuario;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		DtoMusica musicaDTO = new DtoMusica();
		int codigoMusica;
		DaoMusica musicaDAO = new DaoMusica();
		List <DtoHistorico> historico = new ArrayList<DtoHistorico>();

		try{
			if(!VerifiqueConexao())
				return historico;
			Statement st = (Statement)conexao.createStatement();
			comandoSql = "SELECT * FROM HISTORICO WHERE CODIGOUSUARIO =" + codigo + "ORDER BY DATA ASC";
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				dtoHistorico = new DtoHistorico();
				dtoHistorico.setCodigo(rs.getInt("codigo"));
				dtoHistorico.setData(rs.getDate("data"));
				
				codigoUsuario = rs.getInt("codigousuario");
				if(codigoUsuario > 0)
				{
					usuarioDTO = usuarioDAO.BuscaRegistro(codigoUsuario);
				}
				
				codigoMusica = rs.getInt("codigomusica");				
				if(codigoMusica > 0)
				{
					musicaDTO = musicaDAO.BuscaRegistro(codigoMusica);
				}
				dtoHistorico.setCodigoMusica(musicaDTO);
				dtoHistorico.setCodigoUsuario(usuarioDTO);
				
				historico.add(dtoHistorico);

			}

		}catch (SQLException e)
		{
			throw new Exception(e.getMessage());
		}

		return historico;
	}
	
	public DtoHistorico BuscaRegistro(int codigo) throws Exception, SQLException{

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		int codigoUsuario;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		DtoMusica musicaDTO = new DtoMusica();
		int codigoMusica;
		DaoMusica musicaDAO = new DaoMusica();
		
		DtoHistorico historicoDTO = null;
		try
		{
			if(!VerifiqueConexao())
				return historicoDTO;
			
			Statement st = (Statement)conexao.createStatement();
			comandoSql =  "SELECT * FROM HISTORICO WHERE CODIGO = " + codigo;
			rs = st.executeQuery(comandoSql);
			while(rs.next())
			{
				historicoDTO = new DtoHistorico();
				historicoDTO.setCodigo(rs.getInt("codigo"));
				
				Date dataHistorico = rs.getDate("data");
				historicoDTO.setData(dataHistorico);

				codigoUsuario = rs.getInt("codigousuario");
				if(codigoUsuario > 0)
				{
					usuarioDTO = usuarioDAO.BuscaRegistro(codigoUsuario);
				}
				
				codigoMusica = rs.getInt("codigomusica");				
				if(codigoMusica > 0)
				{
					musicaDTO = musicaDAO.BuscaRegistro(codigoMusica);
				}
				historicoDTO.setCodigoMusica(musicaDTO);
				historicoDTO.setCodigoUsuario(usuarioDTO);
			}
		}
		catch(SQLException e)
		{
			throw new Exception(e.getMessage());
		}

		return historicoDTO;
	}
	
	public Boolean Incluir(DtoHistorico dtoHistorico) throws Exception{

		try{

			if(!VerifiqueConexao())
				return false;

			comandoSql = "INSERT INTO HISTORICO(DATA, CODIGOUSUARIO, CODIGOMUSICA) VALUES (?,?,?)";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setDate(1, dtoHistorico.getData());
			pst.setInt(2, dtoHistorico.getCodigoUsuario().getCodigo());
			pst.setInt(3, dtoHistorico.getCodigoMusica().getCodigo());
			return (pst.executeUpdate() > 0 ? true : false);
			
		}
		catch (Exception e){
			throw new Exception("Nao foi possivel registrar no historico " + comandoSql + ". ERRO: " + e);
		}
	}
	public Boolean Alterar(DtoHistorico dtoHistorico) throws Exception{

		try{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "UPDATE HISTORICO SET DATA=?, CODIGOUSUARIO=?, CODIGOMUSICA=? WHERE CODIGO = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);			
			pst.setObject(1, dtoHistorico.getData());
			pst.setObject(2, dtoHistorico.getCodigoUsuario());
			pst.setObject(3, dtoHistorico.getCodigoMusica());
			pst.setInt(4, dtoHistorico.getCodigo());
			return (pst.executeUpdate() > 0 ? true : false);

		}catch (SQLException e)
		{
			throw new Exception("Não foi possível alterar o historico " + comandoSql + ". ERRO: " + e);
		}
	}
	
	public Boolean Excluir(int codigo) throws Exception {
		try
		{
			if(!VerifiqueConexao())
				return false;

			comandoSql = "DELETE FROM HISTORICO WHERE CODIGOUSUARIO = ?";
			pst = (PreparedStatement)conexao.prepareStatement(comandoSql);
			pst.setInt(1, codigo);
			return (pst.executeUpdate() > 0?true:false);

		}
		catch (SQLException e)
		{
			throw new Exception("Não foi possível excluir O HISTORICO " + comandoSql + ". ERRO: " + e);
		}
	}
}

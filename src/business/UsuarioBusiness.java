package business;

import java.util.List;

import dao.UsuarioDAO;
import dto.UsuarioDTO;

public class UsuarioBusiness {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private UsuarioDTO usuarioDTO;

	public UsuarioDTO Login(String usuario, String senha) throws Exception {
		try
		{
			usuarioDTO = new UsuarioDTO(usuario,senha);
			return usuarioDAO.Login(usuarioDTO);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

	public List<UsuarioDTO> Listar() throws Exception {
		List<UsuarioDTO> usuarios = null;

		try
		{
			if(!usuarioDAO.VerifiqueConexao())
				throw new Exception("Conexão não estabelecida");

			usuarios = usuarioDAO.Listar();
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}

		return usuarios;
	}
	
	public UsuarioDTO BuscaRegistro(int codigo) throws Exception {
		try
		{
			return usuarioDAO.BuscaRegistro(codigo);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

	public Boolean Incluir(UsuarioDTO usuarioDTO) throws Exception {
		try
		{
			usuarioDAO.Incluir(usuarioDTO);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public Boolean Alterar(UsuarioDTO usuarioDTO) throws Exception {
		try
		{
			usuarioDAO.Alterar(usuarioDTO);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return true;
	}
	
	public UsuarioDTO BuscaRegistro(String login) throws Exception {
		try
		{
			return usuarioDAO.BuscaRegistro(login);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
	public UsuarioDTO VerificaRegistro(String login, String email) throws Exception {
		try
		{
			return usuarioDAO.VerificaRegistro(login, email);
		} catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
//	public Boolean Excluir(int codigo) throws Exception {
//		try
//		{
//			return usuarioDAO.Excluir(codigo);
//		} catch (Exception e)
//		{
//			throw new Exception(e.getMessage());
//		}
//	}
	
}

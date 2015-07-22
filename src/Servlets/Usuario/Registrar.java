package Servlets.Usuario;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.FavoritosBusiness;
import business.UsuarioBusiness;
import dto.DtoFavoritos;
import dto.DtoParametros;
import dto.UsuarioDTO;

/**
 * Servlet implementation class Registrar
 */
@WebServlet("/RegistrarUsuario")
public class Registrar extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DtoParametros.setParametros(config);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		UsuarioDTO usuarioDTO = null;
		DtoFavoritos favoritosDTO = null;

		Vector<String> vectorErros = new Vector<String>();

		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		if(vectorErros.size() > 0)
		{
			String[] stringErros = (String[])vectorErros.toArray(new String[vectorErros.size()]);
			request.setAttribute("erros", stringErros);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			favoritosDTO = new DtoFavoritos();
			FavoritosBusiness favoritosBusiness = new FavoritosBusiness();
			
			UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
			
			try{
				if(usuarioBusiness.VerificaRegistro(login, email) != null)
				{
					request.setAttribute("existeOk", false);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else if(favoritosBusiness.Incluir(favoritosDTO))
				{
					favoritosDTO = favoritosBusiness.ObterUltimoId();
					usuarioDTO = new UsuarioDTO(login, senha, email, favoritosDTO);
					
					if(usuarioBusiness.Incluir(usuarioDTO))
					{
						request.setAttribute("incluirOk", false);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
				}
			}
			catch(Exception e)
			{
				request.setAttribute ("javax.servlet.jsp.jspException", e);
				getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
			}
		}
	}
}

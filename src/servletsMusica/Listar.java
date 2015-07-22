package servletsMusica;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.UsuarioBusiness;
import dto.UsuarioDTO;

/**
 * Servlet implementation class ListarServlet
 */
@WebServlet("/ListarMusica")
public class Listar extends HttpServlet {
	private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sessao = request.getSession(true);
		
		try
		{
			List<UsuarioDTO> usuarios = usuarioBusiness.Listar();		
			sessao.setAttribute("listaUsuarios", usuarios);
			request.getRequestDispatcher("/Usuario/Index.jsp").forward(request, response);
		
		}
		catch(Exception e)
		{
			request.setAttribute ("javax.servlet.jsp.jspException", e);
		    getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
		}
		
	}

	
}

package Servlets.Usuario;

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
@WebServlet("/ListarUsuarios")
public class Listar extends HttpServlet {
	private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sessao = request.getSession(true);
		
		try
		{
			List<UsuarioDTO> usuarios = usuarioBusiness.Listar();
			
			/*UsuarioDTO usuarioDTO1 = new UsuarioDTO(1,"nome1","login1","");
			UsuarioDTO usuarioDTO2 = new UsuarioDTO(2,"nome2","login2","");
			UsuarioDTO usuarioDTO3 = new UsuarioDTO(3,"nome3","login3","");
			UsuarioDTO usuarioDTO4 = new UsuarioDTO(4,"nome4","login4","");
			
			List<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
			usuarios.add(usuarioDTO1);
			usuarios.add(usuarioDTO2);
			usuarios.add(usuarioDTO3);
			usuarios.add(usuarioDTO4);
			*/
		
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

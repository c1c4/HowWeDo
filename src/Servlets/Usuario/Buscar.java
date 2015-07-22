package Servlets.Usuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.UsuarioBusiness;
import dto.UsuarioDTO;

/**
 * Servlet implementation class Buscar
 */
@WebServlet("/BuscarUsuario")
public class Buscar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String codigo = request.getParameter("codigo");
		UsuarioBusiness usuarioBusiness = new UsuarioBusiness();

		try{
			UsuarioDTO usuarioDTO = usuarioBusiness.BuscaRegistro(Integer.parseInt(codigo));
			
			/*TESTE
			UsuarioDTO usuarioDTO = new UsuarioDTO(1,"NOME1","LOGIN1","SENHA1");
			*/
			
			request.setAttribute("usuarioDTO", usuarioDTO);
			if(acao.equals("Alterar"))
				request.getRequestDispatcher("/Usuario/Alterar.jsp").forward(request, response);
			else
				request.getRequestDispatcher("/Usuario/Excluir.jsp").forward(request, response);
		}
		catch(Exception e)
		{
			request.setAttribute ("javax.servlet.jsp.jspException", e);
			getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
		}
	}

}

package servletsGenero;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.GeneroBusiness;
import dto.DtoGenero;
import dto.DtoParametros;

/**
 * Servlet implementation class Gravar
 */
@WebServlet("/IncluirGenero")
public class Incluir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DtoGenero generoDTO = null;
		
		Vector<String> vectorErros = new Vector<String>();

		int codigo = 0;
		String acao = request.getParameter("acao");
		String genero = request.getParameter("genero");
		
		if(genero == null || genero.isEmpty())
			vectorErros.add("Genero deve ser informado");
		
		String proximaPagina = "";

		if(vectorErros.size() > 0)
		{
			String[] stringErros = (String[])vectorErros.toArray(new String[vectorErros.size()]);
			request.setAttribute("erros", stringErros);
			request.getRequestDispatcher(proximaPagina).forward(request, response);
		}
		else
		{
			generoDTO = new DtoGenero(genero);
			GeneroBusiness generoBusiness = new GeneroBusiness();
			try{
				
				if(acao.equals("Inclusao"))
				{
					generoBusiness.Incluir(generoDTO);
					request.getRequestDispatcher("genero.jsp").forward(request, response);
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

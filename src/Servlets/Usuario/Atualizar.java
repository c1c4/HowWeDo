package Servlets.Usuario;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.u;
import business.FavoritosBusiness;
import business.UsuarioBusiness;
import dto.DtoFavoritos;
import dto.UsuarioDTO;

/**
 * Servlet implementation class Gravar
 */
@WebServlet("/AtualizarUsuario")
public class Atualizar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioBusiness usuarioBusiness = new UsuarioBusiness();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sessao = request.getSession();
		UsuarioDTO uDTO = (UsuarioDTO)sessao.getAttribute("usuarioLogado");
		
		sessao.getAttribute("alterarOk");
		sessao.getAttribute("errorsOk");
		
		UsuarioDTO usuarioDTO = null;
		DtoFavoritos favoritosDTO = null;

		Vector<String> vectorErros = new Vector<String>();

		int codigo = 0;
		String acaoEmail = request.getParameter("AlterarEmail");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String acaoSenha = request.getParameter("AlterarSenha");

		if(acaoEmail == null){
			acaoEmail = "";}
		if(acaoSenha == null){
			acaoSenha = "";}

		codigo = uDTO.getCodigo();

		if(email == null || email.isEmpty() && senha == null || senha.isEmpty()){
			sessao.setAttribute("errorsOk", true);
			response.sendRedirect("configuracoes.jsp");
		}
		else{
			favoritosDTO = new DtoFavoritos(codigo);
			FavoritosBusiness favoritosBusiness = new FavoritosBusiness();
			try{

				if(acaoEmail.equals("Alterar Email"))
				{
					usuarioDTO = new UsuarioDTO(codigo, uDTO.getSenha(), email);
					if(usuarioBusiness.Alterar(usuarioDTO))
					{
						sessao.setAttribute("alterarOk", true);
						response.sendRedirect("configuracoes.jsp");
					}
				}
				else if(acaoSenha.equals("Alterar Senha"))
				{
					usuarioDTO = new UsuarioDTO(codigo, senha, uDTO.getEmail());
					if(usuarioBusiness.Alterar(usuarioDTO))
					{
						sessao.setAttribute("alterarOk", true);
						response.sendRedirect("configuracoes.jsp");
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


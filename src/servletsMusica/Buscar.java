package servletsMusica;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.MusicaBusiness;
import business.UsuarioBusiness;
import dto.DtoMusica;
import dto.UsuarioDTO;

/**
 * Servlet implementation class Buscar
 */
@WebServlet("/BuscarMusica")
public class Buscar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessao = request.getSession();

		String acao = request.getParameter("acao");
		String codigo = request.getParameter("codigo");
		MusicaBusiness musicaBusiness = new MusicaBusiness();
		UsuarioDTO uDTO = (UsuarioDTO)sessao.getAttribute("usuarioLogado");

		List<DtoMusica> musicas;
		try {
			musicas = musicaBusiness.Listar(uDTO.getCodigo());
			sessao.setAttribute("listaMusica", musicas);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try{
			if (codigo != null){
				DtoMusica musicaDTO = musicaBusiness.BuscaRegistro(Integer.parseInt(codigo));			
				request.setAttribute("musicaDTO", musicaDTO);
				//				sessao.setAttribute("musicaSelecionada", musicaDTO);
			}
			if(acao.equals("Alterar")){
				request.getRequestDispatcher("meusarquivosalterar.jsp").forward(request, response);}
			else if(acao.equals("Excluir")){
				request.getRequestDispatcher("/AtualizarMusica").forward(request, response);}
			else if(acao.equals("procurar")){
				String parametro = request.getParameter("parametro");
				List<DtoMusica> musicasGeral = musicaBusiness.Procurar(parametro);
				sessao.setAttribute("listaMusicaGeral", musicasGeral);
				request.getRequestDispatcher("procurar.jsp").forward(request, response);}
			else if(acao.equals("meusArquivosProcurar")){
				String parametroMusicaUsuario = request.getParameter("parametroMusicaUsuario");
				List<DtoMusica> musicasUsuario = musicaBusiness.ProcurarMeusArquivos(parametroMusicaUsuario, uDTO.getCodigo());
				sessao.setAttribute("listaMusicaUsuario", musicasUsuario);
				request.getRequestDispatcher("meusarquivos.jsp").forward(request, response);}
		}
		catch(Exception e)
		{
			request.setAttribute ("javax.servlet.jsp.jspException", e);
			getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
		}
	}
}

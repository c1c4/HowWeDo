package Servlets.Login;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.FavoritoMusicaBusiness;
import business.HistoricoBusiness;
import business.MusicaBusiness;
import business.Playlistbusiness;
import business.UsuarioBusiness;
import dto.DtoFavoritoMusica;
import dto.DtoHistorico;
import dto.DtoMusica;
import dto.DtoParametros;
import dto.DtoPlaylist;
import dto.UsuarioDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
	private UsuarioDTO usuarioDTO = new UsuarioDTO();
	private MusicaBusiness musicaBusiness = new MusicaBusiness();
	private FavoritoMusicaBusiness fbm = new FavoritoMusicaBusiness();
	private HistoricoBusiness hm = new HistoricoBusiness();
	private Playlistbusiness playlistBusiness = new Playlistbusiness();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		DtoParametros.setParametros(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");


		HttpSession sessao = request.getSession(true);

		try
		{
			usuarioDTO = usuarioBusiness.Login(usuario, senha);
			UsuarioDTO usuarioDTO1 = new UsuarioDTO();
		    usuarioDTO1 = usuarioBusiness.BuscaRegistro(usuario);

			request.setAttribute("usuarioDTO", usuarioDTO1);

			/* TESTE
			UsuarioDTO usuarioDTO = new UsuarioDTO(1,"NOME1","LOGIN1","SENHA1");
			 */

			if(usuarioDTO != null)
			{
				sessao.setMaxInactiveInterval(600);
				sessao.setAttribute("usuarioLogado", usuarioDTO1);
				List<DtoMusica> musicas = musicaBusiness.Listar(usuarioDTO1.getCodigo());
				sessao.setAttribute("listaMusica", musicas);
				List<DtoFavoritoMusica> favoritosMusicas = fbm.Listar(usuarioDTO1.getFavorito().getCodigo());
				sessao.setAttribute("listaFavoritos", favoritosMusicas);
				List<DtoHistorico> historicoMusicas = hm.Listar(usuarioDTO1.getCodigo());
				sessao.setAttribute("historico", historicoMusicas);
				List<DtoPlaylist> playlist = playlistBusiness.Listar(usuarioDTO1.getCodigo());
				sessao.setAttribute("listaPlaylist", playlist);
				request.getRequestDispatcher("iframe.jsp").forward(request, response);
			}
			else
			{
				sessao.setAttribute("loginOk", false);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		catch(Exception e)
		{
			request.setAttribute ("javax.servlet.jsp.jspException", e);
			getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
		}
	}
}

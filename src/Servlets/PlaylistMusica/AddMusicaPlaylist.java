package Servlets.PlaylistMusica;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.MusicaBusiness;
import business.PlaylistMusicaBusiness;
import business.Playlistbusiness;
import dto.DtoMusica;
import dto.DtoPlaylist;
import dto.DtoPlaylistMusica;
import dto.UsuarioDTO;

@WebServlet("/AdicionarMusicaPlaylist")
public class AddMusicaPlaylist extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession();
		UsuarioDTO uDTO = (UsuarioDTO) sessao.getAttribute("usuarioLogado");

		
		String acao = request.getParameter("acao");
		
		
		String codigoMusica = request.getParameter("CodigoMusica");
		String codigoPlaylist = request.getParameter("codigoPlaylist");
		String codigo = request.getParameter("codigo");
		

		
		if (codigoMusica == null)
			codigoMusica = "0";

		if (codigoPlaylist == null)
			codigoPlaylist = "0";
		if(codigo == null)
			codigo = "0";

		DtoMusica mDTO = new DtoMusica();
		DtoPlaylist pDTO = new DtoPlaylist();

		Playlistbusiness pb = new Playlistbusiness();
		MusicaBusiness mb = new MusicaBusiness();

		PlaylistMusicaBusiness pmb = new PlaylistMusicaBusiness();
		 
		/*if(!acao.equals("Incluir")){*/
		
		try {
				switch (acao) {
				case "Incluir":
					mDTO = mb.BuscaRegistro(Integer.parseInt(codigoMusica));
					pDTO = pb.BuscaRegistro(Integer.parseInt(codigoPlaylist));
					DtoPlaylistMusica pmDTOIncluir = new DtoPlaylistMusica(Integer.parseInt(codigo), pDTO, mDTO);
					pmb.Incluir(pmDTOIncluir);
					List<DtoPlaylistMusica> playlistMusicas = pmb.Listar(pDTO.getCodigo());
					sessao.setAttribute("listaPlaylistMusica", playlistMusicas);
					request.getRequestDispatcher("/PlaylistMusica/Listar.jsp").forward(request, response);
					break;
			case "Excluir":
				pmb.Excluir(Integer.parseInt(codigo));
				playlistMusicas = pmb.Listar(Integer.parseInt(codigoPlaylist));
				sessao.setAttribute("listaPlaylistMusica", playlistMusicas);
				request.getRequestDispatcher("playlistmusicas.jsp").forward(request, response);
				break;
			case "Listar":
				playlistMusicas = pmb.Listar(Integer.parseInt(codigoPlaylist));
				sessao.setAttribute("listaPlaylistMusica", playlistMusicas);
				request.getRequestDispatcher("playlistmusicas.jsp").forward(request, response);
				break;			
				}
				
				
		} catch (Exception e) {
			request.setAttribute("javax.servlet.jsp.jspException", e);
			getServletConfig().getServletContext()
					.getRequestDispatcher(e.toString())
					.forward(request, response);
		
		}
		}
	}
  


package Servlets.Playlist;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.ArtistaBusiness;
import business.MusicaBusiness;
import business.PlaylistMusicaBusiness;
import business.Playlistbusiness;
import dto.DtoArtista;
import dto.DtoMusica;
import dto.DtoPlaylist;
import dto.UsuarioDTO;




@WebServlet("/AtualizarPlaylist")
public class Atualizar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String proximaPagina = "";
		DtoPlaylist playlistDTO = null;
		
		Vector<String> vectorErros = new Vector<String>();
		int codigo = 0;
		String nome = null;
		
		String acao = request.getParameter("acao");
		nome = request.getParameter("nome");
		
		
		DtoPlaylist playlistDTOExclusao = (DtoPlaylist) request.getAttribute("playlistDTO");
	
		HttpSession sessao = request.getSession();
		UsuarioDTO uDTO = (UsuarioDTO)sessao.getAttribute("usuarioLogado");
		
		
		
		
		if(!acao.equals("Incluir"))
		{
			codigo = Integer.parseInt(request.getParameter("codigoPlaylist"));
			nome = request.getParameter("nome");

		}

		if(!acao.equals("Excluir"))
		{
			if(nome == null || nome.isEmpty())
				vectorErros.add("Nome deve ser informado");
		}

		if(vectorErros.size() > 0)
		{
			String[] stringErros = (String[])vectorErros.toArray(new String[vectorErros.size()]);
			request.setAttribute("erros", stringErros);
			request.getRequestDispatcher(proximaPagina).forward(request, response);
		}
		if(acao.equals("Incluir"))
		{
			UsuarioDTO usuarioDTO = new UsuarioDTO();


			playlistDTO = new DtoPlaylist(codigo,nome,uDTO);

			Playlistbusiness playlistBusiness = new Playlistbusiness();
			try{
				
				
				
					playlistBusiness.Incluir(playlistDTO);
					List<DtoPlaylist> playlist = playlistBusiness.Listar(uDTO.getCodigo());
					sessao.setAttribute("listaPlaylist", playlist);
				request.getRequestDispatcher("/playlist.jsp").forward(request, response);
			 
				
			} 
			catch(Exception e)
			{
				request.setAttribute ("javax.servlet.jsp.jspException", e);
				getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
			}
		}
		if(acao.equals("Excluir"))
		{
			UsuarioDTO usuarioDTO = new UsuarioDTO();


			playlistDTO = new DtoPlaylist(codigo,nome,uDTO);

			Playlistbusiness playlistBusiness = new Playlistbusiness();
			PlaylistMusicaBusiness plmb = new PlaylistMusicaBusiness();
			try{
				    plmb.ExcluirPlaylistMusica(codigo);
					playlistBusiness.Excluir(codigo);
					List<DtoPlaylist> playlist = playlistBusiness.Listar(uDTO.getCodigo());
					sessao.setAttribute("listaPlaylist", playlist);
				request.getRequestDispatcher("/playlist.jsp").forward(request, response);
			} 
			catch(Exception e)
			{
				request.setAttribute ("javax.servlet.jsp.jspException", e);
				getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
			}
		}
		
	}

}

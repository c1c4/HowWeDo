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

import business.MusicaBusiness;
import business.Playlistbusiness;
import business.UsuarioBusiness;
import dto.DtoMusica;
import dto.DtoPlaylist;
import dto.UsuarioDTO;


@WebServlet("/ListarPlaylist")
public class Listar extends HttpServlet {
	private UsuarioBusiness usuarioBusiness = new UsuarioBusiness();

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
		
		
			
            
    /* if(!acao.equals("Musica")){*/
        	    
    	  
        	   
			try{
				
				UsuarioDTO usuarioDTO = new UsuarioDTO();
	               Playlistbusiness playlistBusiness = new Playlistbusiness();
	               MusicaBusiness musicaBusiness = new MusicaBusiness();
				
				
	               String codigoMusica =  request.getParameter("CodigoMusica"); request.setAttribute("CodigoMusica", codigoMusica);

	              
	               
					List<DtoPlaylist> playlist = playlistBusiness.Listar(uDTO.getCodigo());
					sessao.setAttribute("listaPlaylist", playlist);
				request.getRequestDispatcher("/Playlist/IncluirMusicaPlaylist.jsp").forward(request, response);
				
				
			}
			catch(Exception e)
			{
				request.setAttribute ("javax.servlet.jsp.jspException", e);
				getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
		 /*}*/
      }
   }
}
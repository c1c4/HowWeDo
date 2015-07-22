package servletsFavoritosMusica;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.net.aso.f;
import business.FavoritoMusicaBusiness;
import business.FavoritosBusiness;
import business.MusicaBusiness;
import dto.DtoFavoritoMusica;
import dto.DtoFavoritos;
import dto.DtoMusica;
import dto.UsuarioDTO;

@WebServlet("/CreateDestroyFavoritosMusica")
public class CreateDestroyFavoritosMusica extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession();
		UsuarioDTO uDTO = (UsuarioDTO)sessao.getAttribute("usuarioLogado");

		String acao = request.getParameter("acao");
		String codigoMusica = request.getParameter("codigoMusica");
		String codigo = request.getParameter("codigo");
		
		if(codigo == null)
			codigo = "0";
		if(codigoMusica == null)
			codigoMusica = "0";

		DtoMusica mDTO = new DtoMusica();
		DtoFavoritos fDTO = new DtoFavoritos();

		FavoritosBusiness fb = new FavoritosBusiness();
		MusicaBusiness mb = new MusicaBusiness();

		FavoritoMusicaBusiness fmb = new FavoritoMusicaBusiness();
		try {
			switch (acao) {
			case "Inclusao":
				fDTO =fb.Buscar(uDTO.getFavorito().getCodigo());
				mDTO = mb.BuscaRegistro(Integer.parseInt(codigoMusica));
				DtoFavoritoMusica fmDTOIncluir = new DtoFavoritoMusica(fDTO, mDTO);
				fmb.Incluir(fmDTOIncluir);
				List<DtoFavoritoMusica> favoritosMusicas = fmb.Listar(uDTO.getFavorito().getCodigo());
				sessao.setAttribute("listaFavoritos", favoritosMusicas);
				request.getRequestDispatcher("procurar.jsp").forward(request, response);
				break;

			case "Excluir":
				fmb.Excluir(Integer.parseInt(codigo));
				favoritosMusicas = fmb.Listar(uDTO.getFavorito().getCodigo());
				sessao.setAttribute("listaFavoritos", favoritosMusicas);
				request.getRequestDispatcher("favoritos.jsp").forward(request, response);
				break;
			}
			
		} catch (Exception e) {
			request.setAttribute ("javax.servlet.jsp.jspException", e);
			getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
		}
	}

}

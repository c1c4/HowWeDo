package servletsMusica;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import business.ArtistaBusiness;
import business.FavoritoMusicaBusiness;
import business.FavoritosBusiness;
import business.GeneroBusiness;
import business.MusicaBusiness;
import dto.DtoArtista;
import dto.DtoFavoritoMusica;
import dto.DtoFavoritos;
import dto.DtoGenero;
import dto.DtoMusica;
import dto.UsuarioDTO;

/**
 * Servlet implementation class Atualizar
 */
@WebServlet("/AtualizarMusica")
public class Atualizar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String proximaPagina = "";
		DtoMusica musicaDTO = null;
		Vector<String> vectorErros = new Vector<String>();
		DtoArtista artistaDTO= null;
		DtoGenero generoDTO = new DtoGenero();
		String genero = null;
		String artista = null;
		String caminho = null;
		String name = null;
		String nomeDiretorio = null;   
		String separador = java.io.File.separator;   
		String nomeCaminhoNovo = null;

		int codigo = 0;
		String acao = null;
		String nome = null;

		acao = request.getParameter("acao");

		DtoMusica musicaDTOExclusao = (DtoMusica) request.getAttribute("musicaDTO");

		HttpSession sessao = request.getSession();
		UsuarioDTO uDTO = (UsuarioDTO)sessao.getAttribute("usuarioLogado");
		sessao.getAttribute("alterarOk");
		sessao.getAttribute("excluirOk");
		sessao.getAttribute("existeFavOk");

		if(ServletFileUpload.isMultipartContent(request)){
			try {

				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for(FileItem item : multiparts){
					if(item.isFormField()){
						if(item.getFieldName().equals("acao")){
							acao = item.getString();

						}
						if (item.getFieldName().equals("genero")) {  
							genero = item.getString();
							generoDTO = new DtoGenero();
							GeneroBusiness gb = new GeneroBusiness();
							try {
								generoDTO = gb.BuscaGenero(Integer.parseInt(genero));  
								String genero1 = generoDTO.getNome();

							} catch (Exception e) {
								// TODO Auto-generated catch block
								request.setAttribute ("javax.servlet.jsp.jspException", e);
								getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
							}  
						}
						if (item.getFieldName().equals("nome")) {  
							nome = item.getString();

						}
						if(item.getFieldName().equals("artista")){
							artista = item.getString();

						}
					}
					if(!item.isFormField()){

						name = new File(item.getName()).getName();
						try {       
							nomeDiretorio = "C:\\Users\\Renê\\Desktop\\prints\\musicas" + separador + generoDTO.getNome();   
							if (!new File(nomeDiretorio).exists()) { // Verifica se o diretório existe.   
								(new File(nomeDiretorio)).mkdir();   // Cria o diretório
							}   
						} catch (Exception e) {   
							request.setAttribute ("javax.servlet.jsp.jspException", e);
							getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
						}
						nomeCaminhoNovo = nomeDiretorio + separador + name;
						item.write( new File(nomeDiretorio + separador + name));

					}
				}
				//File uploaded successfull
			} catch (Exception ex) {
				request.setAttribute ("javax.servlet.jsp.jspException", ex);
				getServletConfig().getServletContext().getRequestDispatcher(ex.toString()).forward(request,response);
			}  
		}

		if(!acao.equals("Inclusao"))
		{
			codigo = Integer.parseInt(request.getParameter("codigo"));
			artista = request.getParameter("artista");
			nome = request.getParameter("nome");

		}

		if(!acao.equals("Excluir"))
		{
			if(nome == null || nome.isEmpty())
				nome = "Nome da Música Desconhecida";

			if(artista == null || artista.isEmpty())
				artista = "Artista Desconhecido";
		}

		if(vectorErros.size() > 0)
		{
			String[] stringErros = (String[])vectorErros.toArray(new String[vectorErros.size()]);
			request.setAttribute("erros", stringErros);
			request.getRequestDispatcher(proximaPagina).forward(request, response);
		}
		else
		{
			UsuarioDTO usuarioDTO = new UsuarioDTO();

			artistaDTO = new DtoArtista(artista);
			ArtistaBusiness artistaBusiness = new ArtistaBusiness();
			try {
				artistaDTO = artistaBusiness.Incluir(artistaDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute ("javax.servlet.jsp.jspException", e);
				getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
			}

			musicaDTO = new DtoMusica(codigo,nomeCaminhoNovo,nome,artistaDTO,uDTO,generoDTO);

			MusicaBusiness musicaBusiness = new MusicaBusiness();
			try{
				Boolean retornoAtualizacao = false;
				switch(acao)
				{
				case "Inclusao": retornoAtualizacao = musicaBusiness.Incluir(musicaDTO); break;
				case "Alteracao":
					retornoAtualizacao = musicaBusiness.Alterar(musicaDTO);
					sessao.setAttribute("alterarOk", true);
					break;
				case "Excluir": 
					FavoritoMusicaBusiness fmb = new FavoritoMusicaBusiness();
					
					DtoFavoritoMusica fav = fmb.BuscarRegistro(musicaDTOExclusao.getCodigo());
					if(fav != null){
						sessao.setAttribute("existeFavOk", true);
					}else{
					sessao.setAttribute("excluirOk", true);
					retornoAtualizacao = musicaBusiness.Excluir(musicaDTOExclusao.getCodigo());
					}
					break;
				}
				List<DtoMusica> musicas = musicaBusiness.Listar(uDTO.getCodigo());
				sessao.setAttribute("listaMusica", musicas);
				request.getRequestDispatcher("meusarquivos.jsp").forward(request, response);
			}
			catch(Exception e)
			{
				request.setAttribute ("javax.servlet.jsp.jspException", e);
				getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
			}
		}
	}

}



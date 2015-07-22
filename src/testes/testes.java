package testes;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import business.GeneroBusiness;
import dto.DtoArtista;
import dto.DtoGenero;

/**
 * Servlet implementation class testes
 */
@WebServlet("/testes")
public class testes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nomeDiretorio = null;  
		DtoGenero generoDTO = new DtoGenero();
		String separador = java.io.File.separator;
		String genero1 = null;
		String name = null;
		
		if(ServletFileUpload.isMultipartContent(request)){
			try {

				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for(FileItem item : multiparts){
					if(item.isFormField()){
						if(item.getFieldName().equals("acao")){
							String acao = item.getString();
							System.out.println(acao);
						}
						if (item.getFieldName().equals("genero")) {  
			                String genero = item.getString();
			                generoDTO = new DtoGenero();
			                 GeneroBusiness gb = new GeneroBusiness();
			 			    try {
			 					generoDTO = gb.BuscaGenero(Integer.parseInt(genero));  
			 					genero1 = generoDTO.getNome();
			 					System.out.println(genero1);
			 				} catch (Exception e) {
			 					// TODO Auto-generated catch block
			 					request.setAttribute ("javax.servlet.jsp.jspException", e);
			 					getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
			 				}  
						}
						if (item.getFieldName().equals("nome")) {  
							String nome = item.getString();
							System.out.println(nome);
						}
						if(item.getFieldName().equals("artista")){
							String artista = item.getString();
							System.out.println(artista);
						}
					}
					if(!item.isFormField()){

						 name = new File(item.getName()).getName();
						 try {       
								nomeDiretorio = "C:\\Users\\Felipe\\Documents\\ProjetosRepo" + separador + genero1;   
								if (!new File(nomeDiretorio).exists()) { // Verifica se o diretório existe.   
									(new File(nomeDiretorio)).mkdir();   // Cria o diretório
								}   
							} catch (Exception e) {   
								request.setAttribute ("javax.servlet.jsp.jspException", e);
								getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
							}
							item.write( new File(nomeDiretorio + separador + name));

					}
				}
				//File uploaded successfull
			} catch (Exception ex) {
						
				request.setAttribute("message", "File Upload Failed due to " + ex);

			}         



		}else{

			request.setAttribute("message",
					"Sorry this Servlet only handles file upload request");

		}

		request.getRequestDispatcher("/Login/Login.jsp").forward(request, response);

	}

}

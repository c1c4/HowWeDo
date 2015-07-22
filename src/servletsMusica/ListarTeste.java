package servletsMusica;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.MusicaBusiness;
import dto.DtoMusica;

@WebServlet("/ListarTeste")
public class ListarTeste extends HttpServlet {
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

//		String fileName = (String) request.getParameter("file");
//		if (fileName == null || fileName.equals(""))
//			throw new ServletException("Invalid or non-existent file parameter in SendMp3 servlet.");
//
//		if (fileName.indexOf(".mp3") == -1)
//			fileName = fileName + ".mp3";
//
//		String mp3Dir = getServletContext( ).getInitParameter("mp3-dir");
//
//		if (mp3Dir == null || mp3Dir.equals(""))
//
//			throw new ServletException(
//
//					"Invalid or non-existent mp3-Dir context-param.");

		ServletOutputStream stream = null;

		BufferedInputStream buf = null;
		DtoMusica dtm = new DtoMusica();

		try{
			
			stream = response.getOutputStream( );
			
			String codigo = request.getParameter("codigo");	
			String acao = request.getParameter("acao");	
			
					
			MusicaBusiness mb = new MusicaBusiness();
			try {
				if(acao.equals("Buscar"))
					
					
				dtm = mb.BuscaRegistro(Integer.parseInt(codigo));
	          
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	
			
			File mp3 = new File(dtm.getMusica());
			
			

			//set response headers

			response.setContentType("audio/mpeg");

			response.addHeader(

					"Content-Disposition","attachment  filename=" + "" );
			
		

			response.setContentLength( (int) mp3.length( ) );

			FileInputStream input = new FileInputStream(mp3);

			buf = new BufferedInputStream(input);

			int readBytes = 0;

			//read from the file; write to the ServletOutputStream

			while((readBytes = buf.read( )) != -1)
				
				stream.write(readBytes);
			
			
			
			
			

		} catch (IOException ioe){

			throw new ServletException(ioe.getMessage( ));

		} finally {

			//close the input/output streams

			if(stream != null)
				stream.close( );

			if(buf != null)
				buf.close( );
		}

	} //doGet

	public void doPost(HttpServletRequest request,

			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

	}

}
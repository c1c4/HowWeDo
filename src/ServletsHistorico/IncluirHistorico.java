package ServletsHistorico;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.HistoricoBusiness;
import business.MusicaBusiness;
import dto.DtoHistorico;
import dto.DtoMusica;
import dto.UsuarioDTO;

/**
 * Servlet implementation class IncluirHistorico
 */
@WebServlet("/IncluirHistorico")
public class IncluirHistorico extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession();
		UsuarioDTO uDTO = (UsuarioDTO)sessao.getAttribute("usuarioLogado");
		DtoMusica mDTO = new DtoMusica();
		MusicaBusiness mb = new MusicaBusiness();

		String acao = request.getParameter("incluir");
		String codigoMusica = request.getParameter("codigoMusica");
		String jsp = request.getParameter("jsp");
		Date data = new Date();
		java.sql.Date dataSql = new java.sql.Date(data.getTime());
		
		try {
			mDTO = mb.BuscaRegistro(Integer.parseInt(codigoMusica));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HistoricoBusiness hb = new HistoricoBusiness();

		DtoHistorico hDTO = new DtoHistorico(dataSql, mDTO, uDTO);

		try {
			hb.Incluir(hDTO);
			List<DtoHistorico> historicoMusicas = hb.Listar(uDTO.getCodigo());
			sessao.setAttribute("historico", historicoMusicas);
			request.getRequestDispatcher(jsp).forward(request, response);
		} catch (Exception e) {
			request.setAttribute ("javax.servlet.jsp.jspException", e);
			getServletConfig().getServletContext().getRequestDispatcher(e.toString()).forward(request,response);
		}

	}

}

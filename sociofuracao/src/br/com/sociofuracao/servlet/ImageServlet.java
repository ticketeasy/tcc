package br.com.sociofuracao.servlet;

import java.io.IOException;

import javax.activation.DataSource;
import javax.ejb.EJB;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sociofuracao.dao.EventoDAO;
import br.com.sociofuracao.model.Evento;

@WebServlet("/eventoimage/*")
public class ImageServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7179736852894820210L;
	@EJB
	private EventoDAO eventoDAO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get ID from request.
        String id = request.getParameter("id");

        // Check if ID is supplied to the request.
        if (id == null) {
            // Do your thing if the ID is not supplied to the request.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Lookup Image by ImageId in database.
        // Do your "SELECT * FROM Image WHERE ImageID" thing.
        Evento evento = eventoDAO.findById(Long.decode(id));

        // Check if image is actually retrieved from database.
        if (evento == null) {
            // Do your thing if the image does not exist in database.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        
        DataSource dataSource = new ByteArrayDataSource(evento.getImagem(), "image/jpeg");

        // Init servlet response.
        response.reset();
        response.setContentType(dataSource.getContentType());
        response.setContentLength(evento.getImagem().length);

        // Write image content to response.
        response.getOutputStream().write(evento.getImagem());
    }

}

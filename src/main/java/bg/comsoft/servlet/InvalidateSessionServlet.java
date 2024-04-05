package bg.comsoft.servlet;

import io.quarkus.logging.Log;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/invalidatesession")
public class InvalidateSessionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
		Log.infof("Session id %s invalidated. %s", request.getSession().getId());
		request.getSession().invalidate();
	}
}
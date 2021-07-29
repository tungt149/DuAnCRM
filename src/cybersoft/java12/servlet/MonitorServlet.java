package cybersoft.java12.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.dbconnection.MySqlConection;
import cybersoft.java12.util.ServletConst;
import cybersoft.java12.util.UrlConst;

@WebServlet(name = ServletConst.MONITOR, urlPatterns = { UrlConst.HEALTH})
public class MonitorServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
		case UrlConst.HEALTH: {
			if (MySqlConection.getConnection() != null) {
				resp.getWriter().append("Database connection has been entablished successfully !");
			} else {
				resp.getWriter().append("Database connection has been entablished unsuccessfully !");
			}
			break;
		}

		}
	}

}

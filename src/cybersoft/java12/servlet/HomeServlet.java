package cybersoft.java12.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.util.JspConst;
import cybersoft.java12.util.ServletConst;
import cybersoft.java12.util.UrlConst;

@WebServlet(name = ServletConst.HOME, urlPatterns = UrlConst.HOME)
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = -5747914875620363540L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspConst.HOME).forward(req, resp);
	}
}

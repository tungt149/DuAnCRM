package cybersoft.java12.servlet;
import cybersoft.java12.service.AuthService;
import cybersoft.java12.util.JspConst;
import cybersoft.java12.util.ServletConst;
import cybersoft.java12.util.UrlConst;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = ServletConst.AUTH, urlPatterns = {
		UrlConst.AUTH_LOGIN,
		UrlConst.AUTH_LOGOUT,
		UrlConst.AUTH_FORGOT_PASSWORD,
		UrlConst.AUTH_SIGNUP
})
public class AuthServlet extends HttpServlet {
	
	private AuthService service;
	
	@Override
	public void init() throws ServletException {
		service = new AuthService();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.AUTH_LOGIN: 
			// kiem tra cookie - email
			Cookie[] cookies = req.getCookies();
			int cookiesCount = cookies == null ? 0 : cookies.length;
			for(int i = 0; i < cookiesCount; i++)
				if(cookies[i].getName().equals("email"))
					req.setAttribute("email", cookies[i].getValue());
			
			//Kiem tra user da dang nhap hay chua?
			String status = String.valueOf(req.getSession().getAttribute("status"));
			if(!status.equals("null"))
				resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
			else
				req.getRequestDispatcher(JspConst.AUTH_LOGIN)
					.forward(req, resp);
			break;
		case UrlConst.AUTH_LOGOUT: 
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
			break;	
		default:
			throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.AUTH_LOGIN: 
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String remember = req.getParameter("rememberUsername");
			
			boolean isLogin = true;
			// cookies demo
			if(remember != null) {
				Cookie cookie = new Cookie("email", email);
				cookie.setMaxAge(60*60*24*30);
				resp.addCookie(cookie);
			}
			
			System.out.printf("Email: %s, Remember: %s\n", email, remember);
					
			// logic dang nhap
			
			if(email == null || password == null)
				isLogin = false;
			else if(!service.login(email, password))
				isLogin = false;
			
			if(isLogin) {
				req.getSession().setAttribute("status", "Logged in successfully.");
				req.getSession().setAttribute("email", email);
				resp.sendRedirect(req.getContextPath() + UrlConst.HOME);
			} else
				resp.sendRedirect(req.getContextPath() + UrlConst.AUTH_LOGIN);
			break;
		case UrlConst.AUTH_LOGOUT: 
			
			break;	
		default:
			throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
		}
	}


}



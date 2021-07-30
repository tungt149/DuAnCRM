package cybersoft.java12.servlet;

import cybersoft.java12.dto.RoleDTO;
import cybersoft.java12.dto.UserDTO;
import cybersoft.java12.model.Role;
import cybersoft.java12.model.User;
import cybersoft.java12.service.RoleService;
import cybersoft.java12.service.UserService;
import cybersoft.java12.util.JspConst;
import cybersoft.java12.util.UrlConst;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "userServlet", urlPatterns = { UrlConst.USER_DASHBOARD, UrlConst.USER_PROFILE, UrlConst.USER_ADD,
		UrlConst.USER_UPDATE, UrlConst.USER_DELETE })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service;
	private RoleService roleService;

	@Override
	public void init() throws ServletException {
		service = new UserService();
		roleService = new RoleService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.USER_DASHBOARD:
			getUserDashboard(req, resp);
			break;
		case UrlConst.USER_PROFILE:
			getUserProfile(req, resp);
			break;
		case UrlConst.USER_ADD:
			getUserAdd(req, resp);
			break;
		case UrlConst.USER_UPDATE:
			getUserUpdate(req, resp);
			break;
		case UrlConst.USER_DELETE:
			getUserDelete(req, resp);
			break;
		}
	}

	private void getUserDashboard(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		List<User> users = service.findAll();

		if (users != null && !users.isEmpty())
			req.setAttribute("users", users);

		req.getRequestDispatcher(JspConst.USER_DASHBOARD).forward(req, resp);
	}

	private void getUserProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspConst.USER_PROFILE).forward(req, resp);
	}

	private void getUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspConst.USER_ADD).forward(req, resp);
	}

	private void getUserDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		service.deleteById(id);
		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);

	}

	private void getUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		User user = this.service.findUserById(id);
		List<Role> roles = this.roleService.findAll();

		if (roles != null && !roles.isEmpty())
			req.setAttribute("roles", roles);
		if (user != null)
			req.setAttribute("user", new UserDTO(user));
		req.getRequestDispatcher(JspConst.USER_UPDATE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.USER_DASHBOARD:

			break;
		case UrlConst.USER_PROFILE:

			break;
		case UrlConst.USER_ADD:
			postUserAdd(req, resp);
			break;
		case UrlConst.USER_UPDATE:
			postUserUpdate(req, resp);
			break;
		case UrlConst.USER_DELETE:

			break;
		}
	}

	private void postUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		List<Role> roles = this.roleService.findAll();
		if (roles != null && !roles.isEmpty())
			req.setAttribute("roles", roles);

		UserDTO dto = extractDtoFromReq(req);
		service.update(dto);
		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);
	}

	private void postUserAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Role> roles = this.roleService.findAll();
		if (roles != null && !roles.isEmpty());
		UserDTO dto = extractDtoFromReq(req);
		service.add(dto);
		resp.sendRedirect(req.getContextPath() + UrlConst.USER_DASHBOARD);

	}

	private UserDTO extractDtoFromReq(HttpServletRequest req) {
		int id = -1;
		if (req.getParameter("id") != null) {
			id = Integer.parseInt(req.getParameter("id"));
		}
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		int roleId = Integer.parseInt(req.getParameter("roleId"));
		return new UserDTO(id, email, password, name, address, phone, roleId);
	}

}

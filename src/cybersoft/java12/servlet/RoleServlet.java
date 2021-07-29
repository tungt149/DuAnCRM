package cybersoft.java12.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cybersoft.java12.dto.RoleDTO;

import cybersoft.java12.model.Role;
import cybersoft.java12.service.RoleService;
import cybersoft.java12.util.JspConst;
import cybersoft.java12.util.UrlConst;
@WebServlet(name = "roleServlet", urlPatterns = {
		UrlConst.ROLE_ADD,
		UrlConst.ROLE_DASHBOARD,
		UrlConst.ROLE_DELETE,
		UrlConst.ROLE_UPDATE
})
public class RoleServlet extends HttpServlet{
	RoleService service;
	
	@Override
	public void init() throws ServletException {
		service = new RoleService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		switch(servletPath) {
			case UrlConst.ROLE_DASHBOARD:{
				getRoleDashBoard(req,resp);
				break;
			}
			
			case UrlConst.ROLE_UPDATE:{
				getRoleUpdate(req,resp);
				break;
			}
			
			case UrlConst.ROLE_ADD:{
				getRoleAdd(req,resp);
				break;
			}
			
			case UrlConst.ROLE_DELETE: {
				getRoleDelete(req,resp);
			}
			
		}
	}
	private void getRoleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		service.deleteById(id);
		resp.sendRedirect(req.getContextPath() + UrlConst.ROLE_DASHBOARD);
		
	}

	private void getRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspConst.ROLE_ADD).forward(req, resp);	
	}


	private void getRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("update servlet: " +id);
		RoleDTO roleDTO = null;
		try {
			roleDTO = service.findRoleById(id);
			System.out.println("update servlet: userDTO " + roleDTO.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("roleDTO", roleDTO);
		req.getRequestDispatcher(JspConst.ROLE_UPDATE).forward(req, resp);
		
	}


	private void getRoleDashBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Role> roles = service.findAll();
		if(roles != null && !roles.isEmpty()) {
			req.setAttribute("roles", roles);
		}
		req.getRequestDispatcher(JspConst.ROLE_DASHBOARD).forward(req, resp);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		switch(servletPath) {
			case UrlConst.ROLE_UPDATE:{
				postRoleUpdate(req, resp);
				break;
			}
			case UrlConst.ROLE_ADD:{
				System.out.println("ROLE_ADD");
				postRoleAdd(req,resp);
				break;
			}
		}
	}

	private void postRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setName(name);
		roleDTO.setDescription(description);
		System.out.println("servlet add: " + roleDTO.getName());
		service.add(roleDTO);
		resp.sendRedirect(req.getContextPath() + UrlConst.ROLE_DASHBOARD);
	}


	private void postRoleUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		RoleDTO roleDTO = new RoleDTO(id, name, description);
		service.update(roleDTO, id);
		resp.sendRedirect(req.getContextPath() + UrlConst.ROLE_DASHBOARD);
	}
}


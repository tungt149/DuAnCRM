package cybersoft.java12.servlet;

import cybersoft.java12.dto.ProjectDTO;
import cybersoft.java12.dto.TaskDTO;
import cybersoft.java12.dto.UserDTO;
import cybersoft.java12.model.Task;
import cybersoft.java12.model.User;
import cybersoft.java12.service.TaskService;

import cybersoft.java12.util.JspConst;
import cybersoft.java12.util.UrlConst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = { UrlConst.TASK_ADD, UrlConst.TASK_DASHBOARD, UrlConst.TASK_DELETE, UrlConst.TASK_UPDATE })
public class TaskServlet extends HttpServlet {
	private TaskService taskService;

	@Override
	public void init() throws ServletException {
		taskService = new TaskService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.TASK_ADD: {
			try {
				getTaskAdd(req, resp);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			break;
		}
		case UrlConst.TASK_UPDATE: {
			try {
				getTaskUpdate(req, resp);
			} catch (ServletException | IOException | SQLException e) {

				e.printStackTrace();
			}
			break;

		}
		case UrlConst.TASK_DELETE:
			try {
				getdeleteTask(req, resp);
			} catch (IOException | SQLException e) {

				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	private void getTaskUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, SQLException {
		int taskID = Integer.parseInt(req.getParameter("taskID"));
		int projectID = Integer.parseInt(req.getParameter("projectID"));
		Task task = this.taskService.findTaskById(taskID);
		List<UserDTO> assignees = this.taskService.findMembersByProjectID(projectID);
		if (assignees != null && !assignees.isEmpty())
			req.setAttribute("assignees", assignees);
		if (task != null)
			req.setAttribute("task", task);
		req.setAttribute("projectID", projectID);
		req.getRequestDispatcher(JspConst.TASK_UPDATE).forward(req, resp);
	}

	private void getdeleteTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		int taskID = Integer.parseInt(req.getParameter("taskID"));
		taskService.deleteTaskById(taskID);
		int projectID = Integer.parseInt(req.getParameter("projectID"));
		taskService.deleteTaskById(projectID);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DETAIL + "?id=" + projectID);

	}

	private void getTaskAdd(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		int projectID = Integer.parseInt(req.getParameter("projectID"));
		List<UserDTO> assignees = this.taskService.findMembersByProjectID(projectID);
		if (assignees != null && !assignees.isEmpty())
			req.setAttribute("assignees", assignees);
		req.setAttribute("projectID", projectID);
		req.getRequestDispatcher(JspConst.TASK_ADD).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlConst.TASK_ADD: {
			try {
				postTaskAdd(req, resp);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			break;
		}
		case UrlConst.TASK_UPDATE: {
			try {
				postTaskUpdate(req, resp);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		default:
			break;
		}
	}

	private void postTaskUpdate(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		TaskDTO dto = new TaskDTO();
		dto.setId(Integer.parseInt(req.getParameter("id")));
		dto.setName(req.getParameter("name"));
		dto.setDescription(req.getParameter("description"));
		dto.setStartDate(req.getParameter("startDate"));
		dto.setEndDate(req.getParameter("endDate"));
		dto.setUserID(Integer.parseInt(req.getParameter("assigneeID")));
		dto.setProjectID(Integer.parseInt(req.getParameter("projectID")));
		dto.setStatusID(Integer.parseInt(req.getParameter("statusID")));
		taskService.update(dto);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DETAIL + "?id=" + dto.getProjectID());
	}

	private void postTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		TaskDTO dto = new TaskDTO();
		dto.setName(req.getParameter("name"));
		dto.setDescription(req.getParameter("description"));
		dto.setStartDate(req.getParameter("startDate"));
		dto.setEndDate(req.getParameter("endDate"));
		dto.setUserID(Integer.parseInt(req.getParameter("assigneeID")));
		dto.setStatusID(Integer.parseInt(req.getParameter("statusID")));
		dto.setProjectID(Integer.parseInt(req.getParameter("projectID")));
		taskService.add(dto);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DETAIL + "?id=" + dto.getProjectID());
	}

}

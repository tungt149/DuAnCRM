package cybersoft.java12.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.dto.ProjectDTO;
import cybersoft.java12.model.Member;
import cybersoft.java12.model.Project;
import cybersoft.java12.model.Task;
import cybersoft.java12.model.User;
import cybersoft.java12.service.ProjectService;
import cybersoft.java12.service.TaskService;
import cybersoft.java12.service.UserService;
import cybersoft.java12.util.JspConst;
import cybersoft.java12.util.UrlConst;

@WebServlet(urlPatterns = {
        UrlConst.PROJECT_DASHBOARD, UrlConst.PROJECT_DETAIL, UrlConst.PROJECT_ADD,
        UrlConst.PROJECT_UPDATE, UrlConst.PROJECT_DELETE, UrlConst.PROJECT_STAFF,
        UrlConst.PROJECT_STAFF_ADD, UrlConst.PROJECT_STAFF_REMOVE
})
public class ProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProjectService service;
    private UserService userService;
    private TaskService taskService;

    @Override
    public void init() throws ServletException {
        service = new ProjectService();
        userService = new UserService();
        taskService = new TaskService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlConst.PROJECT_DASHBOARD:
                try {
                    getProjectDashBoard(req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case UrlConst.PROJECT_DETAIL:
                try {
                    getProjectDetail(req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case UrlConst.PROJECT_ADD:
                getProjectAdd(req, resp);
                break;
            case UrlConst.PROJECT_UPDATE:
                try {
                    getProjectUpdate(req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case UrlConst.PROJECT_DELETE:
                try {
                    getProjectDelete(req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case UrlConst.PROJECT_STAFF:

                break;
            case UrlConst.PROJECT_STAFF_ADD:
                try {
                    getProjectStaffAdd(req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case UrlConst.PROJECT_STAFF_REMOVE:
                try {
                    getProjectStaffRemove(req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                break;
        }

    }

    private void getProjectStaffRemove(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int projectID = Integer.parseInt(req.getParameter("projectID"));
        int userID = Integer.parseInt(req.getParameter("userID"));
        service.removeMemberInProject(projectID, userID);
        resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DETAIL + "?id=" + projectID);
        
    }

    private void getProjectDetail(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ProjectDTO dto = this.service.findProjectById(id);
        Project project = service.convertDtoToProject(dto);
        List<Member> members = this.userService.findMembersByProjectId(id);
        List<Task> tasks = this.taskService.findTasksByProjectId(id);
        if(project != null)
            req.setAttribute("project", project);
        if(members != null && !members.isEmpty())
            req.setAttribute("members", members);
        if(tasks != null && !tasks.isEmpty())
            req.setAttribute("tasks", tasks);
        req.getRequestDispatcher(JspConst.PROJECT_DETAIL).forward(req, resp);
    }

    private void getProjectStaffAdd(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ProjectDTO dto = service.findProjectById(id);
        Project project = this.service.convertDtoToProject(dto);
        List<User> users = this.userService.findAll();
        if (project != null)
            req.setAttribute("project", project);
        if(users != null && !users.isEmpty())
            req.setAttribute("users", users);
        req.getRequestDispatcher(JspConst.PROJECT_STAFF_ADD).forward(req, resp);
    }

    private void getProjectUpdate(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        ProjectDTO dto = this.service.findProjectById(id);
        Project project = this.service.convertDtoToProject(dto);
        if (project != null)
            req.setAttribute("project", project);
        req.getRequestDispatcher(JspConst.PROJECT_UPDATE).forward(req, resp);
    }

    private void getProjectAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspConst.PROJECT_ADD).forward(req, resp);
    }

    private void getProjectDashBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        List<ProjectDTO> projectdtos = service.findAll();
        List<Project> projects = service.convertDtosToProjects(projectdtos);
        List<User> users = userService.findAll();
        if (projects != null && !projects.isEmpty())
            req.setAttribute("projects", projects);
        if(users != null && !users.isEmpty())
            req.setAttribute("users", users);
        req.getRequestDispatcher(JspConst.PROJECT_DASHBOARD).forward(req, resp);
    }

    private void getProjectDelete(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteById(id);
        resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlConst.PROJECT_DASHBOARD:

                break;
            case UrlConst.PROJECT_DETAIL:

                break;
            case UrlConst.PROJECT_ADD:
                try {
                    postProjectAdd(req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case UrlConst.PROJECT_UPDATE:
                try {
                    postProjectUpdate(req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case UrlConst.PROJECT_DELETE:

                break;
            case UrlConst.PROJECT_STAFF:

                break;
            case UrlConst.PROJECT_STAFF_ADD:
                try {
                    postProjectStaffAdd(req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case UrlConst.PROJECT_STAFF_REMOVE:

                break;
            default:

                break;
        }
    }

    
    private void postProjectStaffAdd(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int projectID = Integer.parseInt(req.getParameter("projectID"));
        int memberID = Integer.parseInt(req.getParameter("memberID"));
        String roleDesc = req.getParameter("description");
        String joinDate = req.getParameter("joinDate");
        service.addMemberToProject(projectID, memberID, roleDesc, joinDate);
        ProjectDTO dto = service.findProjectById(projectID);
        Project project = this.service.convertDtoToProject(dto);
        List<User> users = this.userService.findAll();
        if (project != null)
            req.setAttribute("project", project);
        if(users != null && !users.isEmpty())
            req.setAttribute("users", users);
        req.getRequestDispatcher(JspConst.PROJECT_STAFF_ADD).forward(req, resp);
    }

    private void postProjectUpdate(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		ProjectDTO dto = new ProjectDTO();
		dto.setId(Integer.parseInt(req.getParameter("id")));
		dto.setName(req.getParameter("name"));
		dto.setDescription(req.getParameter("description"));
		dto.setStartDate(req.getParameter("startdate"));
		dto.setEndDate(req.getParameter("enddate"));
		service.update(dto);
		resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
	}

	private void postProjectAdd(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        ProjectDTO dto = extractDtoFromReq(req);
        service.add(dto);
        resp.sendRedirect(req.getContextPath() + UrlConst.PROJECT_DASHBOARD);
    }

    private ProjectDTO extractDtoFromReq(HttpServletRequest req) throws SQLException {
        int id = -1;
        if (req.getParameter("id") != null) {
            id = Integer.parseInt(req.getParameter("id"));
        }
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String startDate = req.getParameter("startdate");
        String endDate = req.getParameter("enddate");
        User currentUser = service.findUserByEmail(String.valueOf(req.getSession().getAttribute("email")));

        return new ProjectDTO(id, name, description, startDate, endDate, currentUser.getId());
    }

}

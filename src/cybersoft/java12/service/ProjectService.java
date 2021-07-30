package cybersoft.java12.service;

import cybersoft.java12.dao.ProjectDao;
import cybersoft.java12.dao.UserDao;
import cybersoft.java12.dto.ProjectDTO;
import cybersoft.java12.model.Project;
import cybersoft.java12.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    private ProjectDao projectDao;
    private UserDao userDao;

    public  ProjectService(){
        projectDao = new ProjectDao();
        userDao = new UserDao();
    }

    public List<ProjectDTO> findAll() throws SQLException {
        return projectDao.findAll();
    }

    public User findUserByEmail(String email) throws SQLException {
        return projectDao.findUserByEmail(email);
    }

    public void add(ProjectDTO dto) throws SQLException {
        projectDao.add(dto);
    }

    public List<Project> convertDtosToProjects(List<ProjectDTO> projectdtos) throws SQLException {
        List<Project> projects = new ArrayList<>();
        for (ProjectDTO dto : projectdtos){
            Project project = new Project();
            project.setId(dto.getId());
            project.setName(dto.getName());
            project.setDescription(dto.getDescription());
            project.setStartDate(dto.getStartDate());
            project.setEndDate(dto.getEndDate());
            User owner = userDao.findUserById(dto.getOwnerId());
            project.setOwner(owner);

            projects.add(project);
        }
        return  projects;
    }

    public  Project convertDtoToProject(ProjectDTO dto) throws SQLException {
            Project project = new Project();
            project.setId(dto.getId());
            project.setName(dto.getName());
            project.setDescription(dto.getDescription());
            project.setStartDate(dto.getStartDate());
            project.setEndDate(dto.getEndDate());
            User owner = userDao.findUserById(dto.getOwnerId());
            project.setOwner(owner);

        return  project;
    }

    public void deleteById(int id) throws SQLException {
        projectDao.deleteById(id);
    }

    public void update(ProjectDTO dto) throws SQLException {
        projectDao.update(dto);
    }

    public ProjectDTO findProjectById(int id) throws SQLException {
        return projectDao.findProjectById(id);
    }

    public void addMemberToProject(int projectID, int memberID, String roleDesc, String joinDate) throws SQLException {
        projectDao.addMemberToProject(projectID, memberID, roleDesc, joinDate);
    }

    public void removeMemberInProject(int projectID, int userID) throws SQLException {
    	//Check member co dang lam 1 task nao do ko?
    	if(!userDao.isMemberAssignedAnyTaskInProject(projectID, userID)) {
    		projectDao.removeMemberInProject(projectID, userID);
    	}
    }
}

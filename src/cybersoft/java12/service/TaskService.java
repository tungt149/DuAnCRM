package cybersoft.java12.service;

import cybersoft.java12.dao.TaskDao;

import cybersoft.java12.dto.TaskDTO;
import cybersoft.java12.dto.UserDTO;

import cybersoft.java12.model.Task;

import java.sql.SQLException;

import java.util.List;

public class TaskService {
	private TaskDao taskDao;

	public TaskService() {
		taskDao = new TaskDao();

	}

	public List<UserDTO> findMembersByProjectID(int projectID) throws SQLException {
		List<UserDTO> assignees = null;
		assignees = this.taskDao.findMembersByProjectID(projectID);
		return assignees;

	}

	public void add(TaskDTO dto) throws SQLException {
		taskDao.add(dto);
	}

	public List<Task> findTasksByProjectId(int id) throws SQLException {
		List<Task> tasks = null;
		tasks = this.taskDao.findTasksByProjectId(id);
		return tasks;
	}

	public void deleteTaskById(int id) throws SQLException {
		taskDao.deleteTaskById(id);

	}

	public void update(TaskDTO dto) throws SQLException {
		taskDao.update(dto);

	}

	public Task findTaskById(int id) throws SQLException {
		return taskDao.findTaskById(id);
	}

}

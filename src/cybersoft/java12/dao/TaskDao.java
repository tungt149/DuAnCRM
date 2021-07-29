package cybersoft.java12.dao;

import cybersoft.java12.dbconnection.MySqlConection;

import cybersoft.java12.dto.TaskDTO;
import cybersoft.java12.dto.UserDTO;

import cybersoft.java12.model.Status;
import cybersoft.java12.model.Task;
import cybersoft.java12.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class TaskDao {

	public List<UserDTO> findMembersByProjectID(int projectID) throws SQLException {
		List<UserDTO> users = new ArrayList<>();

		Connection connection = MySqlConection.getConnection();
		String query = "SELECT  u.id as id, u.name as name, u.email as email, u.phone as phone, u.role_id as role_id "
				+ "FROM user u, project_user pu " + "WHERE u.id = pu.user_id and pu.project_id = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, projectID);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UserDTO dto = new UserDTO();
				dto.setId(resultSet.getInt("id"));
				dto.setName(resultSet.getString("name"));
				dto.setName(resultSet.getString("email"));
				dto.setPhone(resultSet.getString("phone"));
				dto.setRoleId(resultSet.getInt("role_id"));
				users.add(dto);
			}
			return users;
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public void add(TaskDTO dto) throws SQLException {
		String query = "INSERT INTO task(name, description, start_date, end_date, project_id, user_id, status_id)"
				+ "VALUES(?,?,?,?,?,?,?)";

		Connection connection = MySqlConection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, dto.getName());
			statement.setNString(2, dto.getDescription());
			statement.setDate(3, java.sql.Date.valueOf(dto.getStartDate()));
			statement.setDate(4, java.sql.Date.valueOf(dto.getEndDate()));
			statement.setInt(5, dto.getProjectID());
			statement.setInt(6, dto.getUserID());
			statement.setInt(7, dto.getStatusID());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public List<Task> findTasksByProjectId(int id) throws SQLException {
		List<Task> tasks = new ArrayList<>();

		Connection connection = MySqlConection.getConnection();
		String query = "SELECT u.id as userid, u.name as username, t.id as taskid, t.name as taskname,"
				+ " t.description, t.start_date, t.end_date, t.description, t.status_id, s.id as statusid, s.name as statusname"
				+ " FROM user u, task t, status s WHERE t.project_id = ? and t.user_id = u.id and s.id = t.status_id";

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Task task = new Task();
				task.setId(resultSet.getInt("taskid"));
				task.setName(resultSet.getString("taskname"));
				task.setDescription(resultSet.getString("description"));
				task.setStartDate(resultSet.getString("start_date"));
				task.setEndDate(resultSet.getString("end_date"));
				User user = new User();
				user.setId(resultSet.getInt("userid"));
				user.setName(resultSet.getString("username"));
				task.setUser(user);
				Status status = new Status();
				status.setId(resultSet.getInt("statusid"));
				status.setName(resultSet.getString("statusname"));
				task.setStatus(status);
				tasks.add(task);
			}
			return tasks;
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public void deleteTaskById(int id) throws SQLException {
		String query = "DELETE FROM task WHERE id=?";
		Connection connection = MySqlConection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

	}


	public void update(TaskDTO dto) throws SQLException {
		String query = "UPDATE task SET name = ?,description = ?,start_date = ?,end_date = ?,user_id = ?,status_id =?,project_id=? WHERE id = ?";

		Connection connection = MySqlConection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setNString(1, dto.getName());
			statement.setNString(2, dto.getDescription());
			statement.setNString(3, dto.getStartDate());
			statement.setNString(4, dto.getEndDate());
			statement.setInt(5, dto.getUserID());
			statement.setInt(6, dto.getStatusID());
			statement.setInt(7,dto.getProjectID());
			statement.setInt(8, dto.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	
		
	}

	public Task findTaskById(int id) throws SQLException {
		Task task = new Task();
		String query="SELECT t.id as id, t.name as name, t.description as description, t.start_date as start_date, t.end_date as end_date,\n"
				+ "				s.id as statusid,s.name as statusname,\n"
				+ "				u.id as userid,u.name as username\n"
				+ "				FROM  task t, status s,user u\n"
				+ "				 WHERE  t.user_id =u.id and t.status_id=s.id and t.id=?";
		Connection connection = MySqlConection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setDescription(resultSet.getString("description"));
				task.setStartDate(resultSet.getString("start_date"));
				task.setEndDate(resultSet.getString("end_date"));
				User user = new User();
				user.setId(resultSet.getInt("userid"));
				user.setName(resultSet.getString("username"));
				task.setUser(user);
				Status status = new Status();
				status.setId(resultSet.getInt("statusid"));
				status.setName(resultSet.getString("statusname"));
				task.setStatus(status);
			}
			return task;
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
		return null;
	}

	
}

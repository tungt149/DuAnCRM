package cybersoft.java12.dao;

import cybersoft.java12.dbconnection.MySqlConection;
import cybersoft.java12.dto.ProjectDTO;

import cybersoft.java12.model.Role;
import cybersoft.java12.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {

	public List<ProjectDTO> findAll() throws SQLException {
		List<ProjectDTO> projects = new ArrayList<>();

		Connection connection = MySqlConection.getConnection();
		String query = "SELECT p.id, p.name, p.description, p.start_date, p.end_date, p.owner, u.email, u.password, u.name, u.phone, u.role_id "
				+ " FROM project as p, user as u WHERE p.owner = u.id";

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ProjectDTO dto = new ProjectDTO();
				dto.setId(resultSet.getInt("id"));
				dto.setName(resultSet.getString("name"));
				dto.setDescription(resultSet.getString("description"));
				dto.setStartDate(resultSet.getString("start_date"));
				dto.setEndDate(resultSet.getString("end_date"));
				dto.setOwnerId(resultSet.getInt("owner"));
				projects.add(dto);
			}
			return projects;
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return null;
	}

	public void add(ProjectDTO dto) throws SQLException {
		String query = "INSERT INTO project(name, description, start_date, end_date, owner)" + "VALUES(?,?,?,?,?)";

		Connection connection = MySqlConection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setNString(1, dto.getName());
			statement.setNString(2, dto.getDescription());
			statement.setDate(3, java.sql.Date.valueOf(dto.getStartDate()));
			statement.setDate(4, java.sql.Date.valueOf(dto.getEndDate()));
			statement.setInt(5, dto.getOwnerId());
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public User findUserByEmail(String email) throws SQLException {
		User user = new User();
		Role role = new Role();
		String query = "SELECT u.id as id, u.name as name, u.password as password, u.email as email, u.address as address,"
				+ "u.phone as phone, r.id as role_id, r.name as role_name, r.description as role_description "
				+ "FROM user u, role r WHERE u.role_id = r.id and u.email = ?";

		Connection connection = MySqlConection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setAddress(resultSet.getString("address"));
				user.setPassword(resultSet.getString("password"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));

				role.setId(resultSet.getInt("role_id"));
				role.setName(resultSet.getString("role_name"));
				role.setDescription(resultSet.getString("role_description"));

				user.setRole(role);
			}
			return user;

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;

	}

	public void deleteById(int id) throws SQLException {
		String query = "DELETE FROM project WHERE id = ?";
		Connection connection = MySqlConection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public void update(ProjectDTO dto) throws SQLException {
		String query = "UPDATE project SET name = ?, description= ?, start_date= ?, end_date = ? WHERE id = ?";

		Connection connection = MySqlConection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setNString(1, dto.getName());
			statement.setNString(2, dto.getDescription());
			statement.setNString(3, dto.getStartDate());
			statement.setNString(4, dto.getEndDate());
			statement.setInt(5, dto.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public ProjectDTO findProjectById(int id) throws SQLException {
		ProjectDTO dto = new ProjectDTO();
		String query = "SELECT * from project WHERE id = ?";
		Connection connection = MySqlConection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				dto.setId(resultSet.getInt("id"));
				dto.setName(resultSet.getString("name"));
				dto.setDescription(resultSet.getString("description"));
				dto.setStartDate(resultSet.getString("start_date"));
				dto.setEndDate(resultSet.getString("end_date"));
				dto.setOwnerId(resultSet.getInt("owner"));
			}
			return dto;

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return null;
	}

	public void addMemberToProject(int projectID, int memberID, String roleDesc, String joinDate) throws SQLException {
		String query = "INSERT INTO project_user(project_id, user_id, join_date, role_description)" + "VALUES(?,?,?,?)";

		Connection connection = MySqlConection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, projectID);
			statement.setInt(2, memberID);
			statement.setDate(3, java.sql.Date.valueOf(joinDate));
			statement.setString(4, roleDesc);
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public void removeMemberInProject(int projectID, int userID) throws SQLException {
		String query = "DELETE FROM project_user WHERE  project_id = ? and  user_id = ?";
		Connection connection = MySqlConection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, projectID);
			statement.setInt(2, userID);
			

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	

}

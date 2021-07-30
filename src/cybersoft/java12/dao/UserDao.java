package cybersoft.java12.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.dbconnection.MySqlConection;
import cybersoft.java12.dto.UserDTO;
import cybersoft.java12.model.Member;
import cybersoft.java12.model.Role;
import cybersoft.java12.model.User;

public class UserDao {
	public List<User> findAll() throws SQLException {
		List<User> users = new LinkedList<>();
		List<Role> roles = new ArrayList<>();

		Connection connection = MySqlConection.getConnection();
		String query = "SELECT u.id as id, u.name as name, u.email as email, "
				+ "u.phone as phone, r.id as role_id, r.name as role_name, r.description as role_description "
				+ "FROM user u, role r WHERE u.role_id = r.id";

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				User user = new User();

				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));

				int roleId = resultSet.getInt("role_id");
				Role role = getRoleFromList(roles, roleId);

				if (role == null) {
					role = new Role();
					role.setId(resultSet.getInt("role_id"));
					role.setName(resultSet.getString("role_name"));
					role.setDescription(resultSet.getString("role_description"));

					roles.add(role);
				}

				user.setRole(role);

				users.add(user);
			}

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return users;
	}

	public void deleteById(int id) throws SQLException {
		String query = "DELETE FROM user WHERE id = ?";
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

	private Role getRoleFromList(List<Role> roles, int roleId) {
		for (Role role : roles)
			if (role.getId() == roleId)
				return role;
		return null;
	}

	public void add(UserDTO dto) throws SQLException {
		String query = "INSERT INTO user(email, password, name, address, phone, role_id)" + "VALUES(?,?,?,?,?,?)";

		Connection connection = MySqlConection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setNString(1, dto.getEmail());
			statement.setNString(2, dto.getPassword());
			statement.setNString(3, dto.getName());
			statement.setNString(4, dto.getAddress());
			statement.setNString(5, dto.getPhone());
			statement.setInt(6, dto.getRoleId());

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	public User findUserById(int id) throws SQLException {
		User user = new User();
		Role role = new Role();
		String query = "SELECT u.id as id, u.name as name, u.password as password, u.email as email, u.address as address,"
				+ "u.phone as phone, r.id as role_id, r.name as role_name, r.description as role_description "
				+ "FROM user u, role r WHERE u.role_id = r.id and u.id = ?";

		Connection connection = MySqlConection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
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
 
	public void update(UserDTO dto) throws SQLException {
		String query = "UPDATE user SET email = ?, name= ?, phone = ?, address = ?, role_id = ? WHERE id = ?";

		Connection connection = MySqlConection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setNString(1, dto.getEmail());
			statement.setNString(2, dto.getName());
			statement.setNString(3, dto.getPhone());
			statement.setNString(4, dto.getAddress());
			statement.setInt(5, dto.getRoleId());
			statement.setInt(6, dto.getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		
	}


    public List<Member> findMembersByProjectId(int id) throws SQLException {
		List<Member> members = new LinkedList<>();

		Connection connection = MySqlConection.getConnection();
		String query = "SELECT u.name, pu.user_id, pu.role_description, pu.join_date, pu.project_id FROM user u, project_user pu WHERE u.id = pu.user_id and project_id = ?";

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Member member = new Member();

				member.setProjectID(resultSet.getInt("project_id"));
				member.setUserID(resultSet.getInt("user_id"));
				member.setName(resultSet.getString("name"));
				member.setRoleDescription(resultSet.getString("role_description"));
				member.setJoinDate(resultSet.getString("join_date"));

				members.add(member);
			}

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return members;
    }

	public boolean isMemberAssignedAnyTaskInProject(int projectID, int userID) throws SQLException {
		String query = "SELECT * FROM task WHERE user_id = ? and project_id = ?";

		Connection connection = MySqlConection.getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userID);
			statement.setInt(2, projectID);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			return false;

		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return false;
	}
}

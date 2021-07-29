package cybersoft.java12.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cybersoft.java12.dbconnection.MySqlConection;
import cybersoft.java12.dto.RoleDTO;
import cybersoft.java12.model.Role;



public class RoleDao {
	public List<Role> findAll() throws SQLException{
		List<Role> roles = new ArrayList<Role>();
		Connection connection = MySqlConection.getConnection();
		String query = "SELECT id, name, description FROM crm.role;";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				roles.add(role);
			}
			return roles;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return roles;
		
	}
	
	public void updateByID(RoleDTO dto, int id) throws SQLException {
		String query = "UPDATE crm.role SET name = ?, description = ? WHERE id = ?;";
		Connection connection = MySqlConection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, dto.getName());
			statement.setString(2, dto.getDescription());
			statement.setInt(3, id);
			statement.executeUpdate();
			System.out.println("Update successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
		}	
	}
	
	public Role findRoleByID(int id) throws SQLException {
		Role role = new Role();
		Connection connection = MySqlConection.getConnection();
		String query = "SELECT * FROM role WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				role.setId(id);
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getNString("description"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return role;
	}
	
	public void add(RoleDTO roleDTO) throws SQLException {
		String query = "INSERT INTO role (name, description)"
				+ "VALUES(?,?)";
		Connection connection = MySqlConection.getConnection();
		try {
			PreparedStatement statement	= connection.prepareStatement(query);
			statement.setNString(1, roleDTO.getName());
			statement.setNString(2, roleDTO.getDescription());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
		}
	}
	
	public void deleteById(int id) throws SQLException {
		String query = "DELETE FROM role WHERE id =?";
		Connection connection = MySqlConection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			System.out.print("Delete role successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
	}
	
}

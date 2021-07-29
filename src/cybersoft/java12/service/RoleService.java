package cybersoft.java12.service;

import java.sql.SQLException;
import java.util.List;

import cybersoft.java12.dao.RoleDao;
import cybersoft.java12.dto.RoleDTO;
import cybersoft.java12.model.Role;

public class RoleService {
	private RoleDao dao;

	public RoleService() {
		dao = new RoleDao();
	}

	public void deleteById(int id) {
		try {
			dao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public RoleDTO findRoleById(int id) throws SQLException {
		Role role = dao.findRoleByID(id);
		RoleDTO roleDTO = new RoleDTO(role);
		if(role != null) {
			return roleDTO;
		}
		System.out.println("Role == null");
		return null;
	}

	public List<Role> findAll() {
		List<Role> roles = null;
		try {
			roles = dao.findAll();
			System.out.println("Service: " + roles.get(0).getName() + " " + roles.get(0).getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roles;
	}

	public void update(RoleDTO roleDTO, int id) {
		try {
			dao.updateByID(roleDTO, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(RoleDTO roleDTO) {
		try {
			System.out.println("roleService: " + roleDTO.getName());
			dao.add(roleDTO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

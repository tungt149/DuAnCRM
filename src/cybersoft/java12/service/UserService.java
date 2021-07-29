package cybersoft.java12.service;

import java.sql.SQLException;
import java.util.List;

import cybersoft.java12.dto.UserDTO;
import cybersoft.java12.model.Member;
import org.mindrot.jbcrypt.BCrypt;

import cybersoft.java12.dao.UserDao;
import cybersoft.java12.model.User;

public class UserService {

	private UserDao dao;

	public UserService() {
		dao = new UserDao();
	}

	public List<User> findAll() {
		List<User> users = null;
		try {
			users = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public void deleteById(int id) {
		try {
			dao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void add(UserDTO dto) {
		String hashedPwd = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hashedPwd);

		try {
			dao.add(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User findUserById(int id) {
		User user = new User();
		try {
			user = dao.findUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void update(UserDTO dto) {
		try {
			dao.update(dto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

    public List<Member> findMembersByProjectId(int id) {
		List<Member> members = null;
		try {
			members = dao.findMembersByProjectId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
    }
}

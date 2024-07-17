package service;

import java.util.List;

import javax.persistence.Persistence;

import application.OopsException;
import dao.UserDao;
import model.User;

public class UserService {
	private UserDao userDao;

	public UserService() {
		try {
			userDao = new UserDao(Persistence.createEntityManagerFactory("App"));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void addUser(User newUser) {
		userDao.create(newUser);
	}

	public void updateUser(User updatedUser) {
		userDao.update(updatedUser);
	}

	public List<User> getAllUsers() {
		return userDao.findAll();
	}	

	/// for login
	
	public User findUser(String user, String pass) throws OopsException {
		List<User> users = userDao.find(user);
		if (users.size() == 0) {
			throw new OopsException("Oops! User not found!");
		}
		User u = users.get(0);

		if (!pass.equals(u.getPassword())) {
			throw new OopsException("Oops! Password does not match");
		}
		return u;
	}
	

	public User addUser(String user, String pass, String first, String last) throws OopsException {
		List<User> users = userDao.find(user);
		if (user == null || user.isEmpty()) {
	        throw new OopsException("Oops! Please provide a username!");
	    }
		
		if (!users.isEmpty()) {
			throw new OopsException("Oops! User already exists!");
		} 
		
		User newUser = new User(user, pass, first, last);
		userDao.create(newUser);
		return newUser;
	}
}

package rs.codecentric.dao;

import rs.codecentric.entity.User;

/**
 * 
 * @author Adriana Martinovic
 * 
 */
public interface ILoginDAO {

	public User login(String username, String password);

}

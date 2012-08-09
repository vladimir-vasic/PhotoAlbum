package rs.codecentric.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.codecentric.entity.User;

/**
 * 
 * @author Adriana Martinovic
 * 
 */
@Repository("loginService")
@Transactional
public class LoginDAO implements ILoginDAO {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public User login(String username, String password) {
		User retVal = null;
		Session session = sessionFactory.getCurrentSession();
		
		String hql = "FROM User u WHERE u.userName = :username AND u.userPassword = :password";
		List<User> userList = session.createQuery(hql)
				.setParameter("username", username)
				.setParameter("password", password).list();
		if (userList != null && !userList.isEmpty()) {
			retVal = userList.get(0);
		}
		return retVal;
	}

}

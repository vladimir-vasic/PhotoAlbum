/**
 * 
 */
package rs.codecentric.rest.api;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.codecentric.entity.User;
import rs.codecentric.exception.BusinessErrorCode;
import rs.codecentric.exception.BusinessException;

/**
 * @author vladimir.vasic@codecentric.de
 *
 */
//This will make easier to autowired
@Repository("UserAPI")
//Default is read only
@Transactional
public class UserAPI implements IUserAPI, Serializable {

	private static final long serialVersionUID = -4016449895747878062L;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public User loadUserByUsernameAndPassword(String userName, String userPassword) throws BusinessException {
		User retVal = null;
		try {
			log.info("Loading user by username and password...");
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM User pa WHERE pa.userName = :userName AND pa.userPassword = :userPassword";
			List<User> userList = session.createQuery(hql)
					.setParameter("userName", userName)
					.setParameter("userPassword", userPassword)
					.list();
			if (userList != null && !userList.isEmpty()) {
				 retVal = userList.get(0);
				 log.info("User seccessfully loaded.");
			}
		} catch (Exception exc) {
			log.error(MessageFormatter.format("COULD NOT LOAD USER WITH USERNAME: {} !!!", userName).getMessage(), exc);
			throw new BusinessException(BusinessErrorCode.API_ERROR_LOADING_USER, exc); 
		}
		return retVal;
	}

}

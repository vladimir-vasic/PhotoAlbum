/**
 * 
 */
package rs.codecentric.rest.api;

import rs.codecentric.entity.User;
import rs.codecentric.exception.BusinessException;

/**
 * @author vladimir.vasic@codecentric.de
 *
 */
public interface IUserAPI {
	public User loadUserByUsernameAndPassword(String userName, String userPassword) throws BusinessException;
}

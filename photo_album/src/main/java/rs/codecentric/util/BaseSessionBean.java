/**
 * 
 */
package rs.codecentric.util;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author vladimir.vasic@codecentric.de
 * 
 */
public abstract class BaseSessionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -7296562243876158341L;

	@PersistenceContext(name = "PictureManagerPU", unitName = "PictureManagerPU")
	protected EntityManager em;

	protected void persist(ImmutableEntity entity) {
		em.persist(entity);
		em.flush();
	}

	protected <T extends MutableEntity> T merge(T entity) {
		T retVal = em.merge(entity);
		em.flush();
		return retVal;
	}
}

/**
 * 
 */
package rs.codecentric.rest.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.codecentric.entity.Picture;
import rs.codecentric.entity.PictureAlbum;
import rs.codecentric.entity.User;
import rs.codecentric.exception.BusinessErrorCode;
import rs.codecentric.exception.BusinessException;
import rs.codecentric.util.rest.RestUtil;

/**
 * @author vladimir.vasic@codecentric.de
 * 
 */
//This will make easier to autowired
@Repository("PictureAlbumAPI")
//Default is read only
@Transactional
public class PictureAlbumAPI implements IPictureAlbumAPI, Serializable {

	private static final long serialVersionUID = 6779038739296151642L;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Picture> getAllPictures() throws BusinessException {
		List<Picture> retVal = null;
		try {
			log.info("Getting list of all pictures");
			Session session = sessionFactory.getCurrentSession();
			Query getAllPicturesQuery = session.getNamedQuery("Picture.getAll4User");
			getAllPicturesQuery.setParameter("userId", 1L);
			List<Picture> pictureList = getAllPicturesQuery.list();
			if (pictureList != null && pictureList.size() > 0) {
				retVal = pictureList;
				log.info("Picture list fetched seccessfully.");
			}
			else {
				log.info("There is no picture list for user with userId: 1.");
			}
		} catch (Exception exc) {
			log.error("COULD NOT FATCH ALL PICTURES !!!", exc);
			throw new BusinessException(BusinessErrorCode.API_ERROR_GETTING_ALL_PICTURES, "Could not retrieve all pictures", exc);
		}
		return retVal;
	}

	public Boolean createPicture(String pictureName) throws BusinessException {
		Boolean retVal = Boolean.FALSE;
		try {
			log.info("Creating picture");
			Session session = sessionFactory.getCurrentSession();
			User user = loadUserByUserId(1L);
			PictureAlbum pictureAlbum = user.getUserAlbums().get(0);
			Picture picture = new Picture();
			picture.setPictureAlbum(pictureAlbum);
			picture.setName(pictureName);
			picture.setContent(RestUtil.readImageByteStream(pictureName));
			picture.setCreateDateTime(new Date());
			session.save(picture);
			List<Picture> pictureList = new ArrayList<Picture>();
			pictureList.add(picture);
			user.getUserAlbums().get(0).setAlbumPictures(pictureList);
			session.save(user);
			log.info("Picture created seccessfully.");
			retVal = Boolean.TRUE;
		} catch (Exception exc) {
			throw new BusinessException(BusinessErrorCode.API_ERROR_CREATING_PICTURE, "Could not create picture", exc);
		}
		return retVal;
	}
	
	@SuppressWarnings("unchecked")
	public List<PictureAlbum> getAllPictureAlbums4User(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		List<PictureAlbum> retVal = null;
		String hql = "from PictureAlbum pa where pa.albumOwner.userId = :userId";
		retVal = session.createQuery(hql).setParameter("userId", userId).list();
		return retVal;
	}
	
	public User loadUserByUserId(Long userId) throws BusinessException {
		User retVal = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			retVal = (User)session.get(User.class, userId);
		} catch (Exception exc) {
			throw new BusinessException(BusinessErrorCode.API_ERROR_LOADING_USER, "Could not load user", exc);
		}
		return retVal;
	}
	
}

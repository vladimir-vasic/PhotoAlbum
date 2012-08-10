package rs.codecentric.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rs.codecentric.entity.Picture;
import rs.codecentric.entity.PictureAlbum;
import rs.codecentric.entity.User;
import rs.codecentric.util.PhotoAlbumUtil;

//This will make easier to autowired
@Repository("pictureAlbumService")
// Default is read only
@Transactional
public class PictureAlbumDAO implements IPictureAlbumDAO {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Boolean createNewUser(String userName, String userPassword, String userEmail) {
		log.info(MessageFormatter.arrayFormat("Creating new user with params: userName - {} | userPassword - {} | userEmail - {}",new Object[]{userName, userPassword, userEmail}).getMessage());
		Session session = sessionFactory.getCurrentSession();
		Boolean retVal = Boolean.FALSE;
		User user = new User();
		user.setUserName(userName);
		user.setUserPassword(userPassword);
		user.setUserEmail(userEmail);
		user.setCreateDateTime(new Date());
		session.save(user);
		retVal = Boolean.TRUE;
		return retVal;
	}

	public Boolean createAlbum4User(User albumOwner, String albumName) {
		Session session = sessionFactory.getCurrentSession();
		Boolean retVal = Boolean.FALSE;
		PictureAlbum pictureAlbum = new PictureAlbum();
		pictureAlbum.setAlbumOwner(albumOwner);
		session.save(pictureAlbum);
		retVal = Boolean.TRUE;
		return retVal;
	}

	public Boolean addPictureToAlbum(PictureAlbum pictureAlbum) {
		Boolean retVal = Boolean.FALSE;
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Picture> albumPicturesList = new ArrayList<Picture>();
			Picture picture = new Picture();
			picture.setName("fb.jpg");
			picture.setContent(PhotoAlbumUtil.readImageByteStream("fb.jpg"));
			albumPicturesList.add(picture);
			pictureAlbum.setAlbumPictures(albumPicturesList);
			session.save(pictureAlbum);
			retVal = Boolean.TRUE;
		} catch (HibernateException exc) {
			log.error("ERROR WHILE ADDING PICTURE TO ALBUM", exc);
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

	@SuppressWarnings("unchecked")
	public User loadUserByUsrPwd(String userName, String userPassword) {
		User retVal = null;
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User pa WHERE pa.userName = :userName AND pa.userPassword = :userPassword";
		List<User> userList = session.createQuery(hql)
				.setParameter("userName", userName)
				.setParameter("userPassword", userPassword)
				.list();
		if (userList != null && !userList.isEmpty()) {
			retVal = userList.get(0);
		}
		return retVal;
	}

}

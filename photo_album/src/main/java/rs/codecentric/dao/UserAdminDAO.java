package rs.codecentric.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
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
public class UserAdminDAO implements IUserAdminDAO {

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

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		List<User> retVal = null;
		Session session = sessionFactory.getCurrentSession();
		String hql = "from User u";
		retVal = session.createQuery(hql).list();
		return retVal;
	}
	
	public User loadUserById(Long userId) {
		User retVal = null;
		Session session = sessionFactory.getCurrentSession();
		retVal = (User) session.get(User.class, userId);
		return retVal;
	}

	public Boolean updateUser(User user) {
		Boolean retVal = Boolean.FALSE;
		try {
			log.info("Updating user...");
			Session session = sessionFactory.getCurrentSession();
			session.merge(user);//update(user);
			retVal = Boolean.TRUE;
		} catch (HibernateException exc) {
			log.error("ERROR WHILE UPDATING USER", exc);
		}
		return retVal;
	}

	public Boolean deleteUser(User user) {
		Boolean retVal = Boolean.FALSE;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(user);
			retVal = Boolean.TRUE;
		} catch (HibernateException exc) {
			log.error("ERROR WHILE DELETING USER", exc);
		}
		return retVal;
	}

	public PictureAlbum loadPictureAlbumById(Long pictureAlbumId) {
		PictureAlbum retVal = null;
		Session session = sessionFactory.getCurrentSession();
		retVal = (PictureAlbum) session.get(PictureAlbum.class, pictureAlbumId);
		return retVal;
	}

	public Boolean updatePictureAlbum(PictureAlbum pictureAlbum) {
		Boolean retVal = Boolean.FALSE;
		try {
			log.info("Updating picture album...");
			Session session = sessionFactory.getCurrentSession();
			session.update(pictureAlbum);
			retVal = Boolean.TRUE;
		} catch (HibernateException exc) {
			log.error("ERROR WHILE UPDATING PICTURE ALBUM", exc);
		}
		return retVal;
	}
	
	public Boolean deletePictureAlbumById(PictureAlbum pictureAlbum) {
		Boolean retVal = Boolean.FALSE;
		try {
			log.info("Deleting picture album...");
			Session session = sessionFactory.getCurrentSession();
			session.delete(pictureAlbum);
			retVal = Boolean.TRUE;
		} catch (HibernateException exc) {
			log.error("ERROR WHILE DELETING PICTURE ALBUM", exc);
		}
		return retVal;
	}

	@SuppressWarnings("unchecked")
	public List<User> getPosibleFriends(Long userId) {
		List<User> retVal = null;
		Session session = sessionFactory.getCurrentSession();
		Query select = session.getNamedQuery("User.findPosibleFriends");
		select.setParameter("userId", userId);
		select.setParameter("curUserId", userId);
		retVal = select.list();
		return retVal;
	}

	@SuppressWarnings("unchecked")
	public Picture getPictureByName(String pictureName) {
		Picture retVal = null;
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Picture p WHERE p.name = :pictureName";
		List<Picture> pictureList = session.createQuery(hql)
				.setParameter("pictureName", pictureName)
				.list();
		if (pictureList != null && !pictureList.isEmpty()) {
			retVal = pictureList.get(0);
		}
		return retVal;
	}

	public Picture getPictureById(Long pictureId) {
		Picture retVal = null;
		Session session = sessionFactory.getCurrentSession();
		retVal = (Picture) session.get(Picture.class, pictureId);
		return retVal;
	}
}

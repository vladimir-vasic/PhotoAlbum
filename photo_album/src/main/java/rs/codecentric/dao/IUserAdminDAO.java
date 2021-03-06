package rs.codecentric.dao;

import java.util.List;

import rs.codecentric.entity.Picture;
import rs.codecentric.entity.PictureAlbum;
import rs.codecentric.entity.User;

public interface IUserAdminDAO {
	
	public Boolean createNewUser(String userName, String userPassword, String userEmail);
	
	public Boolean createAlbum4User(User albumOwner, String albumName);
	
	public Boolean addPictureToAlbum(PictureAlbum pictureAlbum);
	
	public List<PictureAlbum> getAllPictureAlbums4User(Long userId);
	
	public User loadUserByUsrPwd(String userName, String userPassword);
	
	public List<User> getAllUsers();
	
	public User loadUserById(Long userId);
	
	public Boolean updateUser(User user);
	
	public Boolean deleteUser(User user);
	
	public PictureAlbum loadPictureAlbumById(Long pictureAlbumId);
	
	public Boolean updatePictureAlbum(PictureAlbum pictureAlbum);
	
	public Boolean deletePictureAlbumById(PictureAlbum pictureAlbum);
	
	public List<User> getPosibleFriends(Long userId);
	
	public Picture getPictureByName(String pictureName);
	
	public Picture getPictureById(Long pictureId);
}

package rs.codecentric.dao;

import java.util.List;

import rs.codecentric.entity.PictureAlbum;
import rs.codecentric.entity.User;

public interface IPictureAlbumDAO {
	
	public Boolean createNewUser(String userName, String userPassword, String userEmail);
	
	public Boolean createAlbum4User(User albumOwner, String albumName);
	
	public Boolean addPictureToAlbum(PictureAlbum pictureAlbum);
	
	public List<PictureAlbum> getAllPictureAlbums4User(Long userId);
	
	public User loadUserByUsrPwd(String userName, String userPassword);
}

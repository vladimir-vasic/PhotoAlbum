/**
 * 
 */
package rs.codecentric.dto;

import java.util.ArrayList;

import rs.codecentric.entity.Picture;
import rs.codecentric.entity.PictureAlbum;

/**
 * @author vladimir.vasic@codecentric.de
 *
 */
public class UserPictures4Display {

	private PictureAlbum userPictureAlbum;
	private ArrayList<Picture> friendsPictures;
	
	/**
	 * @param userPictureAlbum
	 * @param friendsPictures
	 */
	public UserPictures4Display(PictureAlbum userPictureAlbum, ArrayList<Picture> friendsPictures) {
		this.userPictureAlbum = userPictureAlbum;
		this.friendsPictures = friendsPictures;
	}
	
	// getters-setters
	public PictureAlbum getUserPictureAlbum() {
		return userPictureAlbum;
	}
	public void setUserPictureAlbum(PictureAlbum userPictureAlbum) {
		this.userPictureAlbum = userPictureAlbum;
	}
	public ArrayList<Picture> getFriendsPictures() {
		return friendsPictures;
	}
	public void setFriendsPictures(ArrayList<Picture> friendsPictures) {
		this.friendsPictures = friendsPictures;
	}
	
}

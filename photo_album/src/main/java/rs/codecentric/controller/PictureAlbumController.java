/**
 * 
 */
package rs.codecentric.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.jboss.resteasy.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import rs.codecentric.dao.IUserAdminDAO;
import rs.codecentric.dto.UserPictures4Display;
import rs.codecentric.entity.Picture;
import rs.codecentric.entity.PictureAlbum;
import rs.codecentric.entity.User;

/**
 * @author vladimir.vasic@codecentric.de
 * 
 */
@Controller
@RequestMapping("/{userId}")
@SessionAttributes("PictureAlbum")
public class PictureAlbumController {

	@Autowired
	IUserAdminDAO userService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/allUserPhotoAlbums.htm", method = RequestMethod.POST)
	public String showAllUserPhotoAlbumsForm(@PathVariable Long userId, Model model) {
		log.info("Displays all user photo albums page");
		log.info("User photo albums displayed seccessfully.");
		return "viewAllUserPhotoAlbums";
	}

	@RequestMapping(value = "/allUserPhotoAlbums.htm", method = RequestMethod.GET)
	public String showAllUserPhotoAlbumsFormFromEditPhotoAlbum(@PathVariable Long userId, Model model) {
		log.info("Displays all user photo albums page");
		log.info("User photo albums displayed seccessfully.");
		return "viewAllUserPhotoAlbums";
	}

	@RequestMapping(value = "/addUserPhotoAlbum.htm", method = RequestMethod.GET)
	public String showAddUserPhotoAlbumForm(Model model) {
		log.info("Displays add user photo albums page");
		PictureAlbum pictureAlbum = new PictureAlbum();
		model.addAttribute("PictureAlbum", pictureAlbum);
		return "addPhotoAlbum";
	}

	@RequestMapping(value = "/addUserPhotoAlbum.htm", method = RequestMethod.POST)
	public String showUserPhotoAlbumAddedForm(@PathVariable Long userId, @ModelAttribute("PictureAlbum") PictureAlbum pictureAlbum) {
		log.info("Displays user photo album added page");
		User tmpUser = userService.loadUserById(userId);
		pictureAlbum.setAlbumOwner(tmpUser);
		pictureAlbum.setCreateDateTime(new Date());
		tmpUser.getUserAlbums().add(pictureAlbum);
		userService.updateUser(tmpUser);
		log.info("Photo album added seccessfully.");
		return "userPhotoAlbumAdded";
	}

	@RequestMapping(value = "/updateUserPhotoAlbum.htm", method = RequestMethod.GET)
	public String showUpdateUserPhotoAlbumForm(@PathVariable Long userId, @RequestParam(value = "albumId", required = true) Long albumId, Model model) {
		log.info("Displays update user photo albums page");
		PictureAlbum pictureAlbum = userService.loadPictureAlbumById(albumId);
		if (pictureAlbum.getAlbumPictures() == null || pictureAlbum.getAlbumPictures().isEmpty()) {
			pictureAlbum.setAlbumPictures(new ArrayList<Picture>());
		}
		ArrayList<Picture> friendsPictures = new ArrayList<Picture>();
		User user = userService.loadUserById(userId);
		if (user.getFriends() != null && !user.getFriends().isEmpty()) {
			Iterator<User> iterator = user.getFriends().iterator();
			while (iterator.hasNext()) {
				User friend = iterator.next();
				if (friend.getUserAlbums() != null && !friend.getUserAlbums().isEmpty()) {
					for (PictureAlbum picAl : friend.getUserAlbums()) {
						if (picAl.getAlbumPictures() != null && !picAl.getAlbumPictures().isEmpty()) {
							for (Picture pic : picAl.getAlbumPictures()) {
								friendsPictures.add(pic);
							}
						}
					}
				}
			}
		}
		UserPictures4Display userPictures4Display = new UserPictures4Display(pictureAlbum, friendsPictures);
		model.addAttribute("UserPictures4Display", userPictures4Display);
		return "editPhotoAlbum";
	}

	@RequestMapping(value = "/deleteUserPhotoAlbum.htm", method = RequestMethod.GET)
	public String showDeleteUserPhotoAlbumForm(@PathVariable Long userId, @RequestParam(value = "albumId", required = true) Long albumId, Model model) {
		log.info("Displays delete user photo albums page");
		PictureAlbum pictureAlbum = userService.loadPictureAlbumById(albumId);
		model.addAttribute("PictureAlbum", pictureAlbum);
		return "deletePhotoAlbum";
	}

	@RequestMapping(value = "/deleteUserPhotoAlbum.htm", method = RequestMethod.POST)
	public String showDeletedUserPhotoAlbumForm(@PathVariable Long userId, @ModelAttribute("PictureAlbum") PictureAlbum pictureAlbum) {
		log.info("Displays delete user photo albums page");
		User tmpUser = userService.loadUserById(userId);
		if (tmpUser.getUserAlbums() != null && tmpUser.getUserAlbums().size() > 0) {
			for (int idx = 0; idx < tmpUser.getUserAlbums().size(); idx++) {
				if (tmpUser.getUserAlbums().get(idx).getAlbumId().compareTo(pictureAlbum.getAlbumId()) == 0) {
					tmpUser.getUserAlbums().remove(idx);
					break;
				}
			}
			if (userService.updateUser(tmpUser)) {
				log.info("Photo album deleted seccessfully.");
			} else {
				log.info("PHOTO ALBUM NOT DELETED!!!");
			}
		}
		return "photoAlbumDeleted";
	}

	@RequestMapping(value = "/getPictureContent.htm", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPictureContent(@RequestParam(value = "pictureId", required = true) Long pictureId) throws IOException {
		Picture picture = userService.getPictureById(pictureId);
		if (picture != null) {
			byte[] decodedPictureContent = Base64.decode(picture.getContent());
			HttpHeaders responseHeaders = new HttpHeaders();
			return new ResponseEntity<byte[]>(decodedPictureContent, responseHeaders, HttpStatus.OK);
		} else {
			return new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
	}

}

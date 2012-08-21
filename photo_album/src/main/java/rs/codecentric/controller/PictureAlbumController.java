/**
 * 
 */
package rs.codecentric.controller;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import rs.codecentric.dao.IUserAdminDAO;
import rs.codecentric.entity.Picture;
import rs.codecentric.entity.PictureAlbum;
import rs.codecentric.entity.User;

/**
 * @author vladimir.vasic@codecentric.de
 * 
 */
@Controller
@RequestMapping("/rest/{userId}")
@SessionAttributes("PictureAlbum")
public class PictureAlbumController {

	@Autowired
	IUserAdminDAO userService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/allUserPhotoAlbums", method = RequestMethod.POST)
	public String showAllUserPhotoAlbumsForm(@PathVariable Long userId, Model model) {
		log.info("Displays all user photo albums page");
		log.info("User photo albums displayed seccessfully.");
		return "viewAllUserPhotoAlbums";
	}

	@RequestMapping(value = "/addUserPhotoAlbum", method = RequestMethod.GET)
	public String showAddUserPhotoAlbumForm(Model model) {
		log.info("Displays add user photo albums page");
		PictureAlbum pictureAlbum = new PictureAlbum();
		model.addAttribute("PictureAlbum", pictureAlbum);
		return "addPhotoAlbum";
	}

	@RequestMapping(value = "/addUserPhotoAlbum", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/updateUserPhotoAlbum", method = RequestMethod.GET)
	public String showUpdateUserPhotoAlbumForm(@PathVariable Long userId, @RequestParam(value = "albumId", required = true) Long albumId, Model model) {
		log.info("Displays update user photo albums page");
		PictureAlbum pictureAlbum = userService.loadPictureAlbumById(albumId);
		if (pictureAlbum.getAlbumPictures() == null || pictureAlbum.getAlbumPictures().isEmpty()) {
			pictureAlbum.setAlbumPictures(new ArrayList<Picture>());
		}
		model.addAttribute("PictureAlbum", pictureAlbum);
		return "editPhotoAlbum";
	}

	@RequestMapping(value = "/deleteUserPhotoAlbum", method = RequestMethod.GET)
	public String showDeleteUserPhotoAlbumForm(@PathVariable Long userId, @RequestParam(value = "albumId", required = true) Long albumId, Model model) {
		log.info("Displays delete user photo albums page");
		PictureAlbum pictureAlbum = userService.loadPictureAlbumById(albumId);
		model.addAttribute("PictureAlbum", pictureAlbum);
		return "deletePhotoAlbum";
	}

	@RequestMapping(value = "/deleteUserPhotoAlbum", method = RequestMethod.POST)
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
	
}

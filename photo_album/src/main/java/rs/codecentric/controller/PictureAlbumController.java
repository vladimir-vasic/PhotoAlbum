/**
 * 
 */
package rs.codecentric.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import rs.codecentric.dao.IUserAdminDAO;
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
}

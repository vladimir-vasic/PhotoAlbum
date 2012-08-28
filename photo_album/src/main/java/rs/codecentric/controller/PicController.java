/**
 * 
 */
package rs.codecentric.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.jboss.resteasy.util.Base64;
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
import rs.codecentric.dto.UploadItem;
import rs.codecentric.entity.Picture;
import rs.codecentric.entity.PictureAlbum;
import rs.codecentric.entity.User;

/**
 * @author vladimir.vasic@codecentric.de
 * 
 */
@Controller
@RequestMapping("/rest/{userId}/updatePhotoAlbum/{albumId}")
@SessionAttributes("UploadItem")
public class PicController {

	@Autowired
	IUserAdminDAO userService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/addPictureToPhotoAlbum", method = RequestMethod.GET)
	public String showAddPictureToPhotoAlbumForm(Model model) {
		log.info("Displays add picture to photo albums page");
		UploadItem uploadItem = new UploadItem();
		model.addAttribute("UploadItem", uploadItem);
		return "addPictureToAlbum";
	}

	@RequestMapping(value = "/addPictureToPhotoAlbum", method = RequestMethod.POST)
	public String showNewPictureAddedToPhotoAlbumForm(@PathVariable Long userId, @PathVariable Long albumId, @ModelAttribute("UploadItem") UploadItem uploadItem, Model model) {
		log.info("Displays new picture added to photo albums page");
		if (uploadItem.getFileData().getName() != null && uploadItem.getFileData().getName().trim().length() > 0) {
			PictureAlbum pictureAlbum = userService.loadPictureAlbumById(albumId);
			if (pictureAlbum.getAlbumPictures() == null || pictureAlbum.getAlbumPictures().isEmpty()) {
				pictureAlbum.setAlbumPictures(new ArrayList<Picture>());
			}

			Picture picture = new Picture();
			picture.setName(uploadItem.getFileData().getOriginalFilename());
			byte[] encodedPicture = Base64.encodeBytesToBytes(uploadItem.getFileData().getBytes());
			picture.setContent(encodedPicture);
			picture.setCreateDateTime(new Date());
			picture.setPictureAlbum(pictureAlbum);
			pictureAlbum.getAlbumPictures().add(picture);
			if (userService.updatePictureAlbum(pictureAlbum)) {
				log.info("Picture album updated seccessfully.");
			} else {
				log.info("PICTURE ALBUM NOT UPDATED!!!");
			}
		}
		PictureAlbum pictureAlbum = userService.loadPictureAlbumById(albumId);
		model.addAttribute("PictureAlbum", pictureAlbum);
		return "editPhotoAlbum";
	}

	@RequestMapping(value = "/getFriendsPictures", method = RequestMethod.GET)
	public byte[] getFriendsPictures(@PathVariable Long userId) {
		byte[] retVal = null;
		User user = userService.loadUserById(userId);
		Iterator<User> it = user.getFriends().iterator();
		while (it.hasNext()) {
			User value = (User) it.next();
			if (value.getUserAlbums() != null && !value.getUserAlbums().isEmpty()) {
				for (PictureAlbum picAl : value.getUserAlbums()) {
					if (picAl.getAlbumPictures() != null && !picAl.getAlbumPictures().isEmpty()) {
						for (Picture pic : picAl.getAlbumPictures()) {
							retVal = pic.getContent();
							log.info("*** IMA SLIKA: {}", picAl.getAlbumPictures().size());
						}
					}
				}
			}
		}
		return retVal;
	}

}

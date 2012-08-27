/**
 * 
 */
package rs.codecentric.controller;

import java.util.ArrayList;
import java.util.Date;

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
			if(pictureAlbum.getAlbumPictures() == null || pictureAlbum.getAlbumPictures().isEmpty()) {
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
}

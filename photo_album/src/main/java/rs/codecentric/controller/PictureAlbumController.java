package rs.codecentric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import rs.codecentric.dao.IPictureAlbumDAO;
import rs.codecentric.entity.PictureAlbum;
import rs.codecentric.entity.User;

@Controller
@RequestMapping("/rest")
public class PictureAlbumController {

	@Autowired
	IPictureAlbumDAO albumService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/useradmin", method = RequestMethod.GET)
	public String showUserAdminForm(ModelMap model) {
		log.info("Displays user admin page");
		return "userAdminPage";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String showNewUserSubscribeForm(ModelMap model) {
		log.info("Displays new user submit page");
		model.addAttribute("User", new User());
		return "addNewUser";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String createNewUser(@ModelAttribute("User") User user) {
		log.info(MessageFormatter.arrayFormat("Trying to crete new user with params: userName - {} | userPassword - {} | userEmail - {}", new Object[] { user.getUserName(), user.getUserPassword(), user.getUserEmail() }).getMessage());
		Boolean result = albumService.createNewUser(user.getUserName(), user.getUserPassword(), user.getUserEmail());
		;
		log.info("New user created seccessfully.");
		return "newUserAdded";
	}

	// @RequestMapping(value="/user", method = RequestMethod.POST)
	// public Response createNewUserJson(
	// @RequestParam(value="userName", required=true) String userName,
	// @RequestParam(value="userPassword", required=true) String userPassword,
	// @RequestParam(value="userEmail", required=true) String userEmail) {
	// log.info(MessageFormatter.arrayFormat("Trying to crete new user with params: userName - {} | userPassword - {} | userEmail - {}",new
	// Object[]{userName, userPassword, userEmail}).getMessage());
	// Boolean result = albumService.createNewUser(userName, userPassword,
	// userEmail);;
	// log.info("New user created seccessfully.");
	// return RestUtil.getResponseOkJSon("", result);
	// }

	@RequestMapping(value = "/album/{userId}", method = RequestMethod.GET)
	public ModelAndView getAllAlbums4User(@PathVariable String userId, ModelMap model) {
		Long userIdTmp;
		try {
			userIdTmp = Long.parseLong(userId);
		} catch (NumberFormatException exc) {
			log.error(MessageFormatter.format("Invalid userId format: {}", userId).getMessage(), exc);
			return null;
		}
		List<PictureAlbum> albumList = albumService.getAllPictureAlbums4User(userIdTmp);
		ModelAndView mav = new ModelAndView("getAllAlbums4User", BindingResult.MODEL_KEY_PREFIX + "albumsAttribute", albumList);
		return mav;
	}

	@RequestMapping(value = "/user/{userName}/{userPassword}", method = RequestMethod.GET)
	public String loadUserByUsrPwd(@PathVariable String userName, @PathVariable String userPassword, ModelMap model) {
		log.info("Trying to load user by username and password");
		User result = albumService.loadUserByUsrPwd(userName, userPassword);
		model.addAttribute("userName", result.getUserName());
		return "list";

	}

}
package rs.codecentric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import rs.codecentric.dao.IUserAdminDAO;
import rs.codecentric.entity.PictureAlbum;
import rs.codecentric.entity.User;

@Controller
@RequestMapping("/rest")
public class UserAdminController {

	@Autowired
	IUserAdminDAO userService;

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
		userService.createNewUser(user.getUserName(), user.getUserPassword(), user.getUserEmail());
		log.info("New user created seccessfully.");
		return "newUserAdded";
	}

	@RequestMapping(value = "/viewallusers", method = RequestMethod.GET)
	public String showAllUserForm(ModelMap model) {
		log.info("Displays all users for editing in list box");
		List<User> userList = userService.getAllUsers();
		model.addAttribute("userList", userList);
		return "viewAllUsers";
	}
	
	@RequestMapping(value = "/edituser", method = RequestMethod.GET)
	public String showEditUserForm(@RequestParam(value="userId", required=true) Long userId, Model model) {
		log.info("Displays user edit page");
		User user = userService.loadUserById(userId);
		model.addAttribute("User", user);
		return "editUser";
	}
	
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public String showUpdatedUserForm(@RequestParam(value="userId", required=true) Long userId,
			@ModelAttribute("User") User user) {
		log.info("Displays updated user page");
		userService.updateUser(user);
		log.info("User updated seccessfully.");
		return "userUpdated";
	}
	
	@RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
	public String showDeleteUserForm(@RequestParam(value="userId", required=true) Long userId, Model model) {
		log.info("Displays user delete page");
		User user = userService.loadUserById(userId);
		model.addAttribute("User", user);
		return "deleteUser";
	}
	
	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
	public String showDeletedUserForm(@RequestParam(value="userId", required=true) Long userId,
			@ModelAttribute("User") User user) {
		log.info("Displays deleted user page");
		userService.deleteUser(user);
		log.info("User deleted seccessfully.");
		return "userDeleted";
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
		List<PictureAlbum> albumList = userService.getAllPictureAlbums4User(userIdTmp);
		ModelAndView mav = new ModelAndView("getAllAlbums4User", BindingResult.MODEL_KEY_PREFIX + "albumsAttribute", albumList);
		return mav;
	}

	@RequestMapping(value = "/user/{userName}/{userPassword}", method = RequestMethod.GET)
	public String loadUserByUsrPwd(@PathVariable String userName, @PathVariable String userPassword, ModelMap model) {
		log.info("Trying to load user by username and password");
		User result = userService.loadUserByUsrPwd(userName, userPassword);
		model.addAttribute("userName", result.getUserName());
		return "list";

	}

}
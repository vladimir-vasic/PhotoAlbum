package rs.codecentric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import rs.codecentric.dao.IUserAdminDAO;
import rs.codecentric.entity.User;

@Controller
@SessionAttributes("User")
public class UserAdminController {

	@Autowired
	IUserAdminDAO userService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/userAdmin.htm", method = RequestMethod.GET)
	public String showUserAdminForm(ModelMap model) {
		log.info("Displays user admin page");
		log.info("Displays all users for editing in list box");
		List<User> userList = userService.getAllUsers();
		model.addAttribute("userList", userList);
		return "viewAllUsers";
	}

	@RequestMapping(value = "/newUser.htm", method = RequestMethod.GET)
	public String showNewUserSubscribeForm(ModelMap model) {
		log.info("Displays new user submit page");
		model.addAttribute("User", new User());
		return "newUser";
	}

	@RequestMapping(value = "/newUser.htm", method = RequestMethod.POST)
	public String createNewUser(@ModelAttribute("User") User user) {
		log.info(MessageFormatter.arrayFormat("Trying to crete new user with params: userName - {} | userPassword - {} | userEmail - {}",
				new Object[] { user.getUserName(), user.getUserPassword(), user.getUserEmail() }).getMessage());
		userService.createNewUser(user.getUserName(), user.getUserPassword(), user.getUserEmail());
		log.info("New user created seccessfully.");
		return "redirect:redirect.htm?msg=userAdded";
	}

	@RequestMapping(value = "/viewAllUsers.htm", method = RequestMethod.GET)
	public String showAllUserForm(ModelMap model) {
		log.info("Displays all users for editing in list box");
		List<User> userList = userService.getAllUsers();
		model.addAttribute("userList", userList);
		return "viewAllUsers";
	}

	@RequestMapping(value = "/editUser.htm", method = RequestMethod.GET)
	public String showEditUserForm(@RequestParam(value = "userId", required = true) Long userId, Model model) {
		log.info("Displays user edit page");
		User user = userService.loadUserById(userId);
		model.addAttribute("User", user);
		return "editUser";
	}

	@RequestMapping(value = "/editMyUser.htm", method = RequestMethod.GET)
	public String editMyUser() {
		log.info("Displays user edit page");
		return "editUser";
	}

	@RequestMapping(value = "/editUser.htm", method = RequestMethod.POST)
	public String showUpdatedUserForm(@RequestParam(value = "userId", required = true) Long userId, @ModelAttribute("User") User user) {
		log.info("Displays updated user page");
		userService.updateUser(user);
		log.info("User updated seccessfully.");
		return "redirect:redirect.htm?msg=userUpdated";
	}

	@RequestMapping(value = "/deleteUser.htm", method = RequestMethod.GET)
	public String showDeleteUserForm(@RequestParam(value = "userId", required = true) Long userId, Model model) {
		log.info("Displays user delete page");
		User user = userService.loadUserById(userId);
		model.addAttribute("User", user);
		return "deleteUser";
	}

	@RequestMapping(value = "/deleteUser.htm", method = RequestMethod.POST)
	public String showDeletedUserForm(@ModelAttribute("User") User user) {
		log.info("Displays deleted user page");
		userService.deleteUser(user);
		log.info("User deleted seccessfully.");
		return "redirect:redirect.htm?msg=userDeleted";
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

}

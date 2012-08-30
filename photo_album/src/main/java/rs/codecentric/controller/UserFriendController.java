/**
 * 
 */
package rs.codecentric.controller;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import rs.codecentric.dao.ILoginDAO;
import rs.codecentric.dao.IUserAdminDAO;
import rs.codecentric.entity.User;

/**
 * @author vladimir.vasic@codecentric.de
 * 
 */
@Controller
// @RequestMapping("/{userId}")
@SessionAttributes("User")
public class UserFriendController {

	@Autowired
	IUserAdminDAO userService;
	@Autowired
	ILoginDAO loginService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/addFriend.htm", method = RequestMethod.GET)
	public String showAddFriendForm(Model model) {
		log.info("Displays add friend page");
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = loginService.getUserByUsername(userName);
		log.info("USER ID " + user.getUserId());
		List<User> userList = userService.getPosibleFriends(user.getUserId());
		model.addAttribute("userList", userList);
		return "addFriend";
	}

	@RequestMapping(value = "/addFriend.htm", params = "selUserId", method = RequestMethod.GET)
	public String showFriendAddedForm(@ModelAttribute("User") User user, @RequestParam(value = "selUserId", required = true) Long selUserId) {
		log.info("Displays friend added page");
		User userFriend = userService.loadUserById(selUserId);
		log.info("USER ID " + user.getUserId());
		log.info("USER FRIEND ID " + userFriend.getUserId());
		log.info("*** USER: {}", user.getUserName());
		log.info("*** FRIEND: {}", userFriend.getUserName());
		if (user.getFriends() == null || user.getFriends().isEmpty()) {
			user.setFriends(new HashSet<User>());
		}
		user.getFriends().add(userFriend);
		userService.updateUser(user);
		return "redirect:redirect.htm?msg=friendAdded";
	}
}

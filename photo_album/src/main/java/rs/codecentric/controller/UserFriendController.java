/**
 * 
 */
package rs.codecentric.controller;

import java.util.HashSet;
import java.util.List;

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
import rs.codecentric.entity.User;

/**
 * @author vladimir.vasic@codecentric.de
 *
 */
@Controller
@RequestMapping("/rest/{userId}")
@SessionAttributes("User")
public class UserFriendController {

	@Autowired
	IUserAdminDAO userService;
	
private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/addFriend", method = RequestMethod.POST)
	public String showAddFriendForm(@PathVariable Long userId, @ModelAttribute("User") User user, Model model) {
		log.info("Displays add friend page");
		List<User> userList = userService.getPosibleFriends(userId);
		model.addAttribute("userList", userList);
		return "addFriend";
	}
	
	@RequestMapping(value = "/friendAdded", method = RequestMethod.POST)
	public String showFriendAddedForm(@PathVariable Long userId, @RequestParam(value = "selUserId", required = true) Long selUserId) {
		log.info("Displays friend added page");
		User user = userService.loadUserById(userId);
		User userFriend = userService.loadUserById(selUserId);
		if (user.getFriends() == null || user.getFriends().isEmpty()) {
			user.setFriends(new HashSet<User>());
		}
		user.getFriends().add(userFriend);
		userService.updateUser(user);
		return "friendAdded";
	}
}

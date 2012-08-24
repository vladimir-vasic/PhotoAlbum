package rs.codecentric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @SessionAttributes("User")
public class LoginController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/login.htm")
	public void login() {
		log.info("+++++++++++++++LOGIN METHOD++++++++++++++++++");
	}
	/*
	 * @Autowired ILoginDAO loginService;
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public
	 * String onGet(@SuppressWarnings("rawtypes") Map model) { User user = new
	 * User(); model.put("User", user); return "login"; }
	 * 
	 * @RequestMapping(value = "/login", method = RequestMethod.POST) public
	 * String onSubmit(@ModelAttribute("User") User user, BindingResult result,
	 * ModelMap model) { // String userName = "username"; // String password =
	 * "password"; // if (result.hasErrors()) { // return "loginMsg"; // } //
	 * login = (User) model.get("login"); // if
	 * (!login.getUserName().equals(userName) // ||
	 * !login.getUserPassword().equals(password)) { // return "loginMsg"; // }
	 * // model.put("login", login);
	 * 
	 * System.out.println("user from the form" + user.getUserName());
	 * 
	 * User u = loginService.login(user.getUserName(), user.getUserPassword());
	 * System.out.println("USER------------" + u.getUserName());
	 * model.addAttribute("User", u); return "index";
	 * 
	 * }
	 */

}

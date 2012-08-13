package rs.codecentric.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.codecentric.dao.ILoginDAO;
import rs.codecentric.entity.User;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	ILoginDAO loginService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String onGet(@SuppressWarnings("rawtypes") Map model) {
		User user = new User();
		model.put("User", user);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("User") User user,
			BindingResult result, ModelMap model) {
		// String userName = "username";
		// String password = "password";
		// if (result.hasErrors()) {
		// return "loginMsg";
		// }
		// login = (User) model.get("login");
		// if (!login.getUserName().equals(userName)
		// || !login.getUserPassword().equals(password)) {
		// return "loginMsg";
		// }
		// model.put("login", login);

		System.out.println("user from the form" + user.getUserName());

		User u = loginService.login(user.getUserName(), user.getUserPassword());
		System.out.println("USER------------" + u.getUserName());
		model.addAttribute("User", u);
		return "loginMsg";
	}

}

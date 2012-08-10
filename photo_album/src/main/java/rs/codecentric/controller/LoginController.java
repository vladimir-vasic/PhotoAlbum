package rs.codecentric.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import rs.codecentric.entity.User;

@Controller
@RequestMapping("login.html")
@SessionAttributes("login")
public class LoginController {

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(@SuppressWarnings("rawtypes") Map model) {
		User login = new User();
		model.put("login", login);
		return "login";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute("login") User login,
			BindingResult result, @SuppressWarnings("rawtypes") Map model) {
		String userName = "username";
		String password = "password";
		if (result.hasErrors()) {
			return "login";
		}
		login = (User) model.get("login");
		if (!login.getUserName().equals(userName)
				|| !login.getUserPassword().equals(password)) {
			return "login";
		}
		model.put("login", login);
		return "login";
	}

}

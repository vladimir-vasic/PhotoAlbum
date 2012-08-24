package rs.codecentric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.codecentric.dao.ILoginDAO;

@Controller
public class IndexController {

	@Autowired
	ILoginDAO loginService;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String onGet(ModelMap model) {
		// User u = loginService.login(user.getUserName(),
		// user.getUserPassword());
		// String name = u.getUserName();
		//
		// model.addAttribute("userName", name);

		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = user.getUsername();

		model.addAttribute("userName", name);

		log.info("+++++++++++++++INDEX++++++++++++++++++");

		return "index";
	}

}

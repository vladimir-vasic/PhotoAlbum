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

		User u = (User) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String userName = u.getUsername();

		rs.codecentric.entity.User user = loginService
				.getUserByUsername(userName);
		model.addAttribute("user", user);

		log.info("+++++++++++++++INDEX++++++++++++++++++");

		return "index";
	}

}

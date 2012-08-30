package rs.codecentric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import rs.codecentric.dao.ILoginDAO;
import rs.codecentric.entity.User;

@Controller
@SessionAttributes("User")
public class IndexController {

	@Autowired
	ILoginDAO loginService;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String onGet(ModelMap model) {

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = loginService.getUserByUsername(userName);
		model.addAttribute("User", user);

		log.info("+++++++++++++++INDEX++++++++++++++++++ " + user.getUserId() + ", " + user.getUserName());

		return "index";
	}

}

package rs.codecentric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/login.htm")
	public void login() {
		log.info("+++++++++++++++LOGIN METHOD++++++++++++++++++");
	}

}

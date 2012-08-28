package rs.codecentric.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/redirect.htm")
public class RedirectController {

	/**
	 * First time we get request to /redirect.htm page with parameter msg, this
	 * method will be called.
	 * 
	 * @param msg
	 *            String - message from what page user came from. We need it to
	 *            know what message to write to page.
	 * @param model
	 *            ModelMap - used to save all data we need at jsp pages.
	 * @return new ModelMap.
	 */
	@RequestMapping(method = RequestMethod.GET, params = "msg")
	public ModelAndView onGet(@RequestParam(value = "msg") String msg,
			ModelMap model) {
		model.addAttribute("key", msg);
		return new ModelAndView();
	}

}

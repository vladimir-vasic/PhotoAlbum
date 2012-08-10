/**
 * 
 */
package rs.codecentric.rest.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.codecentric.entity.User;
import rs.codecentric.exception.BusinessException;
import rs.codecentric.rest.api.IUserAPI;
import rs.codecentric.util.rest.RestUtil;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * @author vladimir.vasic@codecentric.de
 *
 */
@Controller
@RequestMapping("/rest")
public class UserController {

	@Autowired
	IUserAPI userAPI;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(headers = "Accept=application/json", value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> loadUserByUsernameAndPassword(
			@RequestHeader(value = "Authorization", required = false) String serverUsername,
			@RequestBody String jsonText) {
		try {
			log.info("*** SERVER AUTHORIZATION: Basic Q09ERUNFTlRSSUM6");
			log.info("*** serverUsername: ".concat(serverUsername));
//			if (RestUtil.companyExists(getCompanyCode())) {
				log.info("Trying to load user...");
				
				JsonParser parser = new JsonParser();
				List<String> missingMembers;
				try {
					missingMembers = RestUtil.getMissingMembers(jsonText, new String[] { "userName", "userPassword" });
				} catch (JsonSyntaxException jse) {
					log.error("Invalid json string for loading user.", jse);
					return new ResponseEntity<String>("Invalid json string for loading user !!!", HttpStatus.BAD_REQUEST);
				}
				if (!missingMembers.isEmpty()) {
					return new ResponseEntity<String>(MessageFormatter.arrayFormat("Invalid Json object. Missing object params are: {}", missingMembers.toArray()).getMessage(), HttpStatus.BAD_REQUEST);
				}
				JsonObject params;
				try {
					params = parser.parse(jsonText).getAsJsonObject();
				} catch (JsonSyntaxException jse) {
					log.error("Invalid json string for creating picture.", jse);
					return new ResponseEntity<String>("Invalid json string for creating picture !!!", HttpStatus.BAD_REQUEST);
				}
				String userName = params.get("userName").getAsString();
				String userPassword = params.get("userPassword").getAsString();
				
				User user = userAPI.loadUserByUsernameAndPassword(userName, userPassword);
				
//				HttpHeaders responseHeaders = new HttpHeaders();
//				responseHeaders.set("MyResponseHeader", "MyValue");
				return new ResponseEntity<String>(RestUtil.toJSon(user), HttpStatus.OK);
//			}
//			else {
//				return new ResponseEntity<String>("Authorization required!", HttpStatus.UNAUTHORIZED);
//			}
		} catch (BusinessException exc) {
			log.error("COULD NOT LOAD USER !!!", exc);
			return new ResponseEntity<String>("Unexpected error while loading user !!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

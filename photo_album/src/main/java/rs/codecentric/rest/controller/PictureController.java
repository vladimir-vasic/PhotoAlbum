/**
 * 
 */
package rs.codecentric.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.codecentric.entity.Picture;
import rs.codecentric.exception.BusinessException;
import rs.codecentric.rest.api.IPictureAlbumAPI;
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
public class PictureController {

	@Autowired
	IPictureAlbumAPI albumAPI;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	
	// TEST COMMIT
	@RequestMapping(value = "/pictures/all", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getAllPictures(@RequestHeader(value = "Authorization", required = false) String serverUsername) {
		try {
			log.info("*** SERVER AUTHORIZATION: Basic Q09ERUNFTlRSSUM6");
			log.info("*** serverUsername: ".concat(serverUsername));
//			if (RestUtil.companyExists(getCompanyCode())) {
				log.info("Trying to get all pictures...");
				List<Picture> pictureList = albumAPI.getAllPictures();
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("MyResponseHeader", "MyValue");
				return new ResponseEntity<String>(RestUtil.pictureListToJson(pictureList), responseHeaders, HttpStatus.OK);
//			}
//			else {
//				return new ResponseEntity<String>("Authorization required!", HttpStatus.UNAUTHORIZED);
//			}
		} catch (BusinessException exc) {
			log.error("COULD NOT RETRIEVE ALL PICTURES !!!", exc);
			return new ResponseEntity<String>("Unexpected error while getting all pictures !!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(headers = "Accept=application/json", value = "/pictures/create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> createPicture(@RequestHeader(value = "Authorization", required = false) String serverUsername,
			@RequestBody String jsonText) {
		log.info("*** SERVER AUTHORIZATION: Basic Q09ERUNFTlRSSUM6");
		log.info("*** serverUsername: ".concat(serverUsername));
		log.info("*** JSON TEXT: ".concat(jsonText));
		log.info("Trying to create picture...");
		JsonParser parser = new JsonParser();
		List<String> missingMembers;
		try {
			missingMembers = RestUtil.getMissingMembers(jsonText, new String[] { "name" });
		} catch (JsonSyntaxException jse) {
			log.error("Invalid json string for creating picture.", jse);
			return new ResponseEntity<String>("Invalid json string for creating picture !!!", HttpStatus.BAD_REQUEST);
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
		String name = params.get("name").getAsString();
		try {
			Boolean result = albumAPI.createPicture(name);
			if (result) {
				return new ResponseEntity<String>(RestUtil.toJSon(result), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("COULD NOT CREATE PICTURE", HttpStatus.BAD_REQUEST);
			}
		} catch (BusinessException exc) {
			log.error("Error while creating picture.", exc);
			return new ResponseEntity<String>(MessageFormatter.format("Unexpected error (code: {}) while creating picture.", exc.getErrorCode()).getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see rs.codecentric.rest.Picture.IPictureController#loadPictureByName(java.lang.String)
	 */
	public ResponseEntity<String> loadPictureByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rs.codecentric.rest.Picture.IPictureController#loadPictureById(java.lang.String)
	 */
	public ResponseEntity<String> loadPictureById(String pictureId) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see rs.codecentric.rest.Picture.IPictureController#updatePicture(java.lang.String, java.lang.String)
	 */
	public ResponseEntity<String> updatePicture(String pictureId, String jsonText) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see rs.codecentric.rest.Picture.IPictureController#deletePicture(java.lang.String)
	 */
	public ResponseEntity<String> deletePicture(String pictureId) {
		// TODO Auto-generated method stub
		return null;
	}

}

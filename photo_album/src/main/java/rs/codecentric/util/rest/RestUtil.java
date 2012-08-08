/**
 * 
 */
package rs.codecentric.util.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * @author vladimir.vasic@codecentric.de
 * 
 */
public final class RestUtil {

	public static String toJSon(Object obj) {
		if (obj == null) {
			return "";
		}
		if (obj instanceof String) {
			return (String) obj;
		}
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	public static Response getResponseJSon(String resource, Status status, Object result) {
		if (resource != null && resource.trim().length() > 0) {
			return Response.status(status).entity(addResourceNameToJson(resource, toJSon(result))).build();
		} else {
			return Response.status(status).entity(toJSon(result)).build();
		}
	}

	public static String addResourceNameToJson(String resource, String jsonText) {
		String jsonContent;
		if (jsonText == null) {
			jsonContent = "[]";
		} else {
			if (jsonText.trim().length() < 1) {
				jsonContent = "[]";
			} else {
				jsonContent = jsonText;
			}
		}
		return "{\"" + resource + "\":" + jsonContent + "}";
	}

	public static Response getResponseOkJSon(String resource, Object result) {
		return getResponseJSon(resource, Response.Status.OK, result);
	}

	public static List<String> jsonContainsFields(Map<Object, Object> jsonMap, String[] fields) {
		List<String> retVal = new ArrayList<String>();
		for (String field : fields) {
			if (!jsonMap.containsKey(field)) {
				retVal.add(field);
			}
		}
		return retVal;
	}

	/**
	 * This method returns the list of json members that are missing in json
	 * string from the <code>members</code> list.
	 * 
	 * @param jsonTxt
	 *            json string.
	 * @param members
	 *            members to check.
	 * @return missing members, or empty list if nonthing is missing.
	 * @throws JsonSyntaxException
	 *             is thrown if <code>jsonTxt</code> parsing fails.
	 */
	public static List<String> getMissingMembers(String jsonTxt, String[] members) throws JsonSyntaxException {
		List<String> retVal = new ArrayList<String>();
		JsonParser jsonParser = new JsonParser();
		JsonObject jsonObject = jsonParser.parse(jsonTxt).getAsJsonObject();
		for (String member : members) {
			if (jsonObject.get(member) == null) {
				retVal.add(member);
			}
		}
		return retVal;
	}

	/**
	 * This method returns the list of members that are missing in json object
	 * from the <code>members</code> list.
	 * 
	 * @param jsonObject
	 *            json object that needs to be checked.
	 * @param members
	 *            members to check.
	 * @return missing members.
	 */
	public static List<String> getMissingMembers(JsonObject jsonObject, String[] members) {
		List<String> retVal = new ArrayList<String>();
		for (String member : members) {
			if (!jsonObject.has(member)) {
				retVal.add(member);
			}
		}
		return retVal;
	}

//	/**
//	 * This method checks whether all <code>members</code> are present in
//	 * <code>jsonString</code>, and if all are present null is returned,
//	 * otherwise response with status {@link Response.Status#BAD_REQUEST} with
//	 * the string containing missing members, or invalid json string message.
//	 * 
//	 * @param jsonString
//	 *            json string to check.
//	 * @param members
//	 *            array of mandatory members.
//	 * @return <code>null</code> if all members are present, otherwise response
//	 *         with status {@link Response.Status#BAD_REQUEST} and list of
//	 *         missing members, or invalid json string message.
//	 */
//	public static Response checkJsonMembers(String jsonString, String[] members) {
//		try {
//			List<String> missing = getMissingMembers(jsonString, members);
//			if (missing.isEmpty()) {
//				return null;
//			}
//			String message = MessageFormatter.format("{}", missing).getMessage();
//			return Response.status(Response.Status.BAD_REQUEST).entity(getJsonDesktopError(BusinessErrorCode.API_ERROR_MISSING_MANDATORY_MEMBERS, message)).build();
//		} catch (JsonSyntaxException jse) {
//			return Response.status(Response.Status.BAD_REQUEST).entity("Invalid json string.").build();
//		}
//	}
}

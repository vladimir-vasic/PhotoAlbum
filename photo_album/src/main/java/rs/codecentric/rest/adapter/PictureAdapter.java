/**
 * 
 */
package rs.codecentric.rest.adapter;

import java.lang.reflect.Type;

import rs.codecentric.entity.Picture;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author vladimir.vasic@codecentric.de
 *
 */
public class PictureAdapter implements JsonSerializer<Picture> {

    public JsonElement serialize(Picture picture, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pictureId", picture.getPictureId());
        jsonObject.addProperty("name", picture.getName());
        jsonObject.addProperty("createdDateTime", picture.getCreateDateTime().toString());
        jsonObject.addProperty("createdDateTime", new String(picture.getContent()));
        return jsonObject;      
    }
}

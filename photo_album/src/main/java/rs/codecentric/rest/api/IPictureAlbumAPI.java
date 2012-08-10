/**
 * 
 */
package rs.codecentric.rest.api;

import java.util.List;

import rs.codecentric.entity.Picture;
import rs.codecentric.exception.BusinessException;

/**
 * @author vladimir.vasic@codecentric.de
 *
 */
public interface IPictureAlbumAPI {
	public List<Picture> getAllPictures() throws BusinessException;
	
	public Boolean createPicture(String pictureName) throws BusinessException;
}

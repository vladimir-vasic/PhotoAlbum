/**
 * 
 */
package rs.codecentric.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.jboss.resteasy.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vladimir.vasic@codecentric.de
 * 
 */
public final class PhotoAlbumUtil {

	final static Logger log = LoggerFactory.getLogger(PhotoAlbumUtil.class);

	public static byte[] readImageByteStream(String imageName) {
		byte[] retVal = null;
		log.info("Reading in binary file named : {}", imageName);
		File file = new File("D:\\Documents\\Images\\".concat(imageName));
		log.info("File size: {}", file.length());
		retVal = new byte[(int) file.length()];
		try {
			InputStream input = null;
			try {
				int totalBytesRead = 0;
				input = new BufferedInputStream(new FileInputStream(file));
				while (totalBytesRead < retVal.length) {
					int bytesRemaining = retVal.length - totalBytesRead;
					// input.read() returns -1, 0, or more :
					int bytesRead = input.read(retVal, totalBytesRead, bytesRemaining);
					if (bytesRead > 0) {
						totalBytesRead = totalBytesRead + bytesRead;
					}
				}
				log.info("Num bytes read: {}", totalBytesRead);
			} finally {
				log.info("Closing input stream.");
				input.close();
			}
		} catch (FileNotFoundException ex) {
			log.error("File not found.");
		} catch (IOException ex) {
			log.error(ex.getLocalizedMessage());
		}
		String tmp = null;
		tmp = Base64.encodeBytes(retVal);
		return tmp.getBytes();
	}

}

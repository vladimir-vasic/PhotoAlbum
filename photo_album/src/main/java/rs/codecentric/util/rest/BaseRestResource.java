/**
 * 
 */
package rs.codecentric.util.rest;

import java.io.IOException;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.HttpHeaders;

import org.jboss.resteasy.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author vladimir.vasic@codecentric.de
 *
 */
public class BaseRestResource implements IBaseRestResource {

    @HeaderParam(HttpHeaders.AUTHORIZATION)
    private String encodedCompanyCode;
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public String getCompanyCode() {
        if (encodedCompanyCode == null) {
            return null;
        }
        try {
            String field = encodedCompanyCode.trim();
            field = field.substring(field.lastIndexOf(" "));
            field = field.replaceAll(" ", "");
            byte[] bytes = Base64.decode(field.getBytes());
            String decodedCompanyCode = new String(bytes);
            decodedCompanyCode = decodedCompanyCode.substring(0, decodedCompanyCode.lastIndexOf(":"));
            return decodedCompanyCode;
        } catch (IOException ex) {
            log.error("Error decoding company code.", ex);
            return null;
        }
    }

	
}

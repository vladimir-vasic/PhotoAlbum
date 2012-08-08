/**
 * 
 */
package rs.codecentric.util;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author vladimir.vasic@codecentric.de
 *
 */
@MappedSuperclass
public class ImmutableEntity implements Serializable {

    
	private static final long serialVersionUID = -5940567991988103559L;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_datetime")
    private Date createdDateTime;

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @PrePersist
    protected void prePersist() {
        if (createdDateTime == null) {
            createdDateTime = new Date();
        }
    }
}


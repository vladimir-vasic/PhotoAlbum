/**
 * 
 */
package rs.codecentric.util;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * @author vladimir.vasic@codecentric.de
 *
 */
@MappedSuperclass
public class MutableEntity extends ImmutableEntity implements Serializable {

	private static final long serialVersionUID = 2821345962673824309L;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_datetime")
    private Date updatedDateTime;
    
    @Version
    @Column(name = "version", nullable = false)
    private long version;

    public Date getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Date updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @PreUpdate
    protected void preUpdate() {
        updatedDateTime = new Date();
    }
}


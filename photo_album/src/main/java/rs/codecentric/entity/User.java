/**
 * 
 */
package rs.codecentric.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author vladimir.vasic@codecentric.de
 * 
 */
@Entity
@Table(name = "user")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u ORDER BY u.userId") })
public class User implements Serializable {

	private static final long serialVersionUID = -7181112174818154537L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false)
	private Long userId;

	@Column(name = "user_name", unique = true, nullable = false)
	private String userName;

	@Column(name = "user_password", unique = true, nullable = false)
	private String userPassword;

	@Column(name = "user_email", unique = true, nullable = false)
	private String userEmail;

	@Column(name = "user_lib", unique = true)
	private String userLib;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User> friends;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<PictureAlbum> userAlbums;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_datetime")
	private Date createDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_datetime")
	private Date updateDateTime;
	
	// getters setters
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<PictureAlbum> getUserAlbums() {
		return userAlbums;
	}

	public void setUserAlbums(List<PictureAlbum> userAlbums) {
		this.userAlbums = userAlbums;
	}

	public String getUserLib() {
		return userLib;
	}

	public void setUserLib(String userLib) {
		this.userLib = userLib;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail=" + userEmail + "]";
	}

}

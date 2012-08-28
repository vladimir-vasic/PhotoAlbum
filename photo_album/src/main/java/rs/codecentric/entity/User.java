/**
 * 
 */
package rs.codecentric.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
	
	@ManyToMany(cascade={CascadeType.ALL})
	@LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name="user_friend", joinColumns={@JoinColumn(name="user_id")}, inverseJoinColumns={@JoinColumn(name="friend_id")})
    private Set<User> users = new HashSet<User>();
 
    @ManyToMany(mappedBy="users")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<User> friends = new HashSet<User>();

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail=" + userEmail + "]";
	}

}

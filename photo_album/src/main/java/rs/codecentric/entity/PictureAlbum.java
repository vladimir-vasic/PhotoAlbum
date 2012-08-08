/**
 * 
 */
package rs.codecentric.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import rs.codecentric.util.MutableEntity;

/**
 * @author vladimir.vasic@codecentric.de
 * 
 */
@Entity
@Table(name = "picture_album")
@NamedQueries({ @NamedQuery(name = "PictureAlbum.findAll4User", query = "SELECT pa FROM PictureAlbum pa WHERE pa.albumOwner.userId = :userId ORDER BY pa.albumId") })
public class PictureAlbum extends MutableEntity implements Serializable {

	private static final long serialVersionUID = 1500088580950795040L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "album_id", unique = true, nullable = false)
	private Long albumId;

	@Column(name = "album_name", nullable = false)
	private String albumName;

	@OneToMany
	private List<Picture> albumPictures;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User albumOwner;

	// getters setters
	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public List<Picture> getAlbumPictures() {
		return albumPictures;
	}

	public void setAlbumPictures(List<Picture> albumPictures) {
		this.albumPictures = albumPictures;
	}

	public User getAlbumOwner() {
		return albumOwner;
	}

	public void setAlbumOwner(User albumOwner) {
		this.albumOwner = albumOwner;
	}

	@Override
	public String toString() {
		return "PictureAlbum [albumId=" + albumId + ", albumName=" + albumName + "albumOwner=" + albumOwner.getUserName() + "]";
	}

}

package rs.codecentric.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import rs.codecentric.util.MutableEntity;

/**
 * @author vladimir.vasic@codecentric.de
 * 
 */
@Entity
@Table(name = "picture")
@NamedQueries({ 
	@NamedQuery(name = "Picture.findAll", query = "SELECT p FROM Picture p ORDER BY p.pictureId"), 
	@NamedQuery(name = "Picture.getByName", query = "SELECT p FROM Picture p WHERE p.name = :name ORDER BY p.pictureId"),
	@NamedQuery(name = "Picture.getById", query = "SELECT p FROM Picture p WHERE p.pictureId = :pictureId"),
	@NamedQuery(name = "Picture.getAll4User", query = "SELECT p FROM Picture p JOIN p.pictureAlbum pa WHERE pa.albumOwner.userId = :userId ORDER BY p.pictureId")
})
public class Picture extends MutableEntity implements Serializable {

	private static final long serialVersionUID = 6957425219334485686L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "picture_id", unique = true, nullable = false)
	private Long pictureId;

	@Column(name = "name", length = 256, nullable = false)
	@NotNull
	private String name;

	@Column(name = "content")
	@Lob
	private byte[] content;

	@ManyToOne
	@JoinColumn(name = "album_id")
	private PictureAlbum pictureAlbum;

	// getters setters
	public Long getPictureId() {
		return pictureId;
	}

	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public PictureAlbum getPictureAlbum() {
		return pictureAlbum;
	}

	public void setPictureAlbum(PictureAlbum pictureAlbum) {
		this.pictureAlbum = pictureAlbum;
	}

	@Override
	public String toString() {
		return "Picture [pictureId=" + pictureId + ", name=" + name + ", pictureAlbum=" + pictureAlbum.getAlbumName() + "]";
	}

}

package czj.ssh.model;

/**
 * Address entity. @author MyEclipse Persistence Tools
 */

public class Address implements java.io.Serializable {

	// Fields

	private Integer aid;
	private User user;
	private String address;
	private String zipCode;
	private String note;

	// Constructors

	/** default constructor */
	public Address() {
	}

	/** full constructor */
	public Address(User user, String address, String zipCode, String note) {
		this.user = user;
		this.address = address;
		this.zipCode = zipCode;
		this.note = note;
	}

	// Property accessors

	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
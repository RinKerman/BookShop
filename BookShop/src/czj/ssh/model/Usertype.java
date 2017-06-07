package czj.ssh.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Usertype entity. @author MyEclipse Persistence Tools
 */

public class Usertype implements java.io.Serializable {

	// Fields

	private Integer utId;
	private String utype;
	private String note;
	private Set users = new HashSet();

	// Constructors

	/** default constructor */
	public Usertype() {
	}

	/** full constructor */
	public Usertype(String utype, String note, Set users) {
		this.utype = utype;
		this.note = note;
		this.users = users;
	}

	// Property accessors

	public Integer getUtId() {
		return this.utId;
	}

	public void setUtId(Integer utId) {
		this.utId = utId;
	}

	public String getUtype() {
		return this.utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}
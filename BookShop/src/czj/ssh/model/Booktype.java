package czj.ssh.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Booktype entity. @author MyEclipse Persistence Tools
 */

public class Booktype implements java.io.Serializable {

	// Fields

	private Integer tid;
	private String btype;
	private String note;
	private Set books = new HashSet();

	// Constructors

	/** default constructor */
	public Booktype() {
	}

	/** full constructor */
	public Booktype(String btype, String note, Set books) {
		this.btype = btype;
		this.note = note;
		this.books = books;
	}

	// Property accessors

	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getBtype() {
		return this.btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set getBooks() {
		return this.books;
	}

	public void setBooks(Set books) {
		this.books = books;
	}	
	
}
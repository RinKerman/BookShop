package czj.ssh.model;

/**
 * Recommend entity. @author MyEclipse Persistence Tools
 */

public class Recommend implements java.io.Serializable {

	// Fields

	private Integer rid;
	private Book book;
	private String note;

	// Constructors

	/** default constructor */
	public Recommend() {
	}

	/** full constructor */
	public Recommend(Book book, String note) {
		this.book = book;
		this.note = note;
	}

	// Property accessors

	public Integer getRid() {
		return this.rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
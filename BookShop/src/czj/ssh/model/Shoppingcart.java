package czj.ssh.model;

/**
 * Shoppingcart entity. @author MyEclipse Persistence Tools
 */

public class Shoppingcart implements java.io.Serializable {

	// Fields

	private Integer sid;
	private Book book;
	private User user;
	private Integer quantity;
	private String note;

	// Constructors

	/** default constructor */
	public Shoppingcart() {
	}

	/** full constructor */
	public Shoppingcart(Book book, User user, Integer quantity, String note) {
		this.book = book;
		this.user = user;
		this.quantity = quantity;
		this.note = note;
	}

	// Property accessors

	public Shoppingcart(Book book, User user, Integer quantity) {
		super();
		this.book = book;
		this.user = user;
		this.quantity = quantity;
	}

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
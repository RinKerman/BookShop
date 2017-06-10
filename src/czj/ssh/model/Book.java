package czj.ssh.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Book entity. @author MyEclipse Persistence Tools
 */

public class Book implements java.io.Serializable {

	// Fields

	private Integer bid;
	private Booktype booktype;
	private String title;
	private String author;
	private String press;
	private String introduction;
	private Date addDate;
	private String picture;
	private float price;
	private Integer salesAmount;
	private Integer stockNumber;
	private Integer deleteFlag;
	private Integer recommendFlag;
	private String note;
	private Set recommends = new HashSet(0);
	private Set orderdetails = new HashSet(0);
	private Set shoppingcarts = new HashSet(0);
	private Set comments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Book() {
	}

	/** full constructor */
	public Book(Booktype booktype, String title, String author, String press,
			String introduction, Date addDate, String picture, float price,
			Integer salesAmount, Integer stockNumber, Integer deleteFlag,
			Integer recommendFlag, String note, Set recommends,
			Set orderdetails, Set shoppingcarts, Set comments) {
		this.booktype = booktype;
		this.title = title;
		this.author = author;
		this.press = press;
		this.introduction = introduction;
		this.addDate = addDate;
		this.picture = picture;
		this.price = price;
		this.salesAmount = salesAmount;
		this.stockNumber = stockNumber;
		this.deleteFlag = deleteFlag;
		this.recommendFlag = recommendFlag;
		this.note = note;
		this.recommends = recommends;
		this.orderdetails = orderdetails;
		this.shoppingcarts = shoppingcarts;
		this.comments = comments;
	}

	// Property accessors

	public Integer getBid() {
		return this.bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Booktype getBooktype() {
		return this.booktype;
	}

	public void setBooktype(Booktype booktype) {
		this.booktype = booktype;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return this.press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getSalesAmount() {
		return this.salesAmount;
	}

	public void setSalesAmount(Integer salesAmount) {
		this.salesAmount = salesAmount;
	}

	public Integer getStockNumber() {
		return this.stockNumber;
	}

	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getRecommendFlag() {
		return this.recommendFlag;
	}

	public void setRecommendFlag(Integer recommendFlag) {
		this.recommendFlag = recommendFlag;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set getRecommends() {
		return this.recommends;
	}

	public void setRecommends(Set recommends) {
		this.recommends = recommends;
	}

	public Set getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(Set orderdetails) {
		this.orderdetails = orderdetails;
	}

	public Set getShoppingcarts() {
		return this.shoppingcarts;
	}

	public void setShoppingcarts(Set shoppingcarts) {
		this.shoppingcarts = shoppingcarts;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

}
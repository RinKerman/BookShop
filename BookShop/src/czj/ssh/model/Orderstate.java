package czj.ssh.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Orderstate entity. @author MyEclipse Persistence Tools
 */

public class Orderstate implements java.io.Serializable {

	// Fields

	private Integer osId;
	private String orderState;
	private String note;
	private Set orders = new HashSet();

	// Constructors

	/** default constructor */
	public Orderstate() {
	}

	/** full constructor */
	public Orderstate(String orderState, String note, Set orders) {
		this.orderState = orderState;
		this.note = note;
		this.orders = orders;
	}

	// Property accessors

	public Integer getOsId() {
		return this.osId;
	}

	public void setOsId(Integer osId) {
		this.osId = osId;
	}

	public String getOrderState() {
		return this.orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set getOrders() {
		return this.orders;
	}

	public void setOrders(Set orders) {
		this.orders = orders;
	}

}
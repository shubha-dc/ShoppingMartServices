package com.infostretch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "order_details")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer id;

	@ManyToOne
	private Product product;

	@NotBlank
	@Size(max = 20)
	private String quantity;

	@OneToOne
	private Address address;

	

	@NotBlank
	private String totalAmount;

	@ManyToOne
	private User user;

	public OrderDetails() {

	}

	public OrderDetails(Integer id, @NotBlank @Size(max = 20) String quantity, @NotBlank String totalAmount) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", quantity=" + quantity + ", totalAmount=" + totalAmount + "]";
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

package com.infostretch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private Integer id;

	@NotBlank
	@Column(name = "product_name")
	private String productname;

	@NotBlank
	private String brand;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "product_id")
	private int productId;

	@Column(name = "quantity")
	private int quantity;

	@Column
	private double price;

	public Cart(int userId, Integer productId, int quantity, double price) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public Cart() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", productname=" + productname + ", brand=" + brand + ", userId=" + userId
				+ ", productId=" + productId + ", quantity=" + quantity + ", price=" + price + "]";
	}



}

package com.infostretch.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer id;

	@NotBlank
	@Size(max = 20)
	@Column(name = "product_name")
	private String productname;

	@NotBlank
	@Size(max = 20)
	private String brand;

	@NotBlank
	private String quantity;

	@ManyToOne
	private User user;

	@NotBlank
	@Size(max = 20)
	private Double price;

	@NotBlank
	@Size(max = 150)
	private String description;

	@NotBlank
	@Size(max = 50)
	@Column(name = "image_filename")
	private String imageFileName;

	public Product() {

	}

	public Product(Integer id, @NotBlank @Size(max = 20) String productname, @NotBlank @Size(max = 20) String brand,
			@NotBlank String quantity, User User, @NotBlank @Size(max = 20) Double price,
			@NotBlank @Size(max = 150) String description, @NotBlank @Size(max = 50) String imageFileName) {
		super();
		this.id = id;
		this.productname = productname;
		this.brand = brand;
		this.quantity = quantity;
		this.user = User;
		this.price = price;
		this.description = description;
		this.imageFileName = imageFileName;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User User) {
		this.user = User;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productname=" + productname + ", brand=" + brand + ", quantity=" + quantity
				+ ", User=" + user + ", price=" + price + ", description=" + description + ", imageFileName="
				+ imageFileName + "]";
	}

}

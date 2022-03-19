package com.infostretch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "local_warehouse")
public class LocalWareHouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Product product;

	private String reorderLevel;

	private String maximumLevel;

	public LocalWareHouse(Integer id, @NotBlank Product product, String reorderLevel, String maximumLevel) {
		super();
		this.id = id;
		this.product = product;
		this.reorderLevel = reorderLevel;
		this.maximumLevel = maximumLevel;
	}

	public LocalWareHouse() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(String reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public String getMaximumLevel() {
		return maximumLevel;
	}

	public void setMaximumLevel(String maximumLevel) {
		this.maximumLevel = maximumLevel;
	}

	@Override
	public String toString() {
		return "CentralWareHouse [id=" + id + ", product=" + product + ", reorderLevel=" + reorderLevel
				+ ", maximumLevel=" + maximumLevel + "]";
	}

}

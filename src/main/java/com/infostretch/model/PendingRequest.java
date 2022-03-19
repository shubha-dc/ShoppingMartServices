package com.infostretch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pendingRequests")
public class PendingRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Product product;

	public PendingRequest(Integer id, Product product) {
		super();
		this.id = id;
		this.product = product;
	}
	
	public PendingRequest() {
		
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

	@Override
	public String toString() {
		return "PendingRequest [id=" + id + ", product=" + product + "]";
	}
	
	
	
	
	
	
	
}

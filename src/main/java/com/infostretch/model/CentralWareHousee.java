package com.infostretch.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "central_warehouse")
public class CentralWareHousee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany
	private List<LocalWareHouse> localWareHouse;

	private String reorderLevel;

	private String maximumLevel;

	public CentralWareHousee(Integer id, List<LocalWareHouse> centralWareHouse, String reorderLevel,
			String maximumLevel) {
		super();
		this.id = id;
		this.localWareHouse = centralWareHouse;
		this.reorderLevel = reorderLevel;
		this.maximumLevel = maximumLevel;
	}

	public CentralWareHousee() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<LocalWareHouse> getCentralWareHouse() {
		return localWareHouse;
	}

	public void setCentralWareHouse(List<LocalWareHouse> centralWareHouse) {
		this.localWareHouse = centralWareHouse;
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
		return "LocalWareHouse [id=" + id + ", centralWareHouse=" + localWareHouse + ", reorderLevel=" + reorderLevel
				+ ", maximumLevel=" + maximumLevel + "]";
	}

}

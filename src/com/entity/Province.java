package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 省份
 * @author Caviar
 *
 */
@Entity
@Table(name="province")
public class Province {
	@Id
	@GeneratedValue 
	private int id;//省份id
	private String name;//省份名称
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

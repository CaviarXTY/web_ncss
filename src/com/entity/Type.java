package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 项目分类
 * @author Caviar
 *
 */
@Entity
@Table(name="type")
public class Type {
	@Id
	@GeneratedValue 
	private int id;//类型id
	private String name;//类型名称
	
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

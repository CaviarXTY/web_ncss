package com.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户注册公司
 * @author Caviar
 *
 */
@Entity
@Table(name="company")
public class Company {
	@Id
	@GeneratedValue 
	private int id;//注册公司id
	private String name;//注册公司名称
	private String legalperson;//法人代表
	private int capital;//注册资金
	private Date date;//注册时间
	private String address;//注册公司所在地
	private int credit;//统一社会信用代码
	private String shareholder;//股权结构（股东1：所占股份 ：股东2 ：所占股份）
	private boolean is_transition;//是否高校科研成果转化
	
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
	public String getLegalperson() {
		return legalperson;
	}
	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}
	public int getCapital() {
		return capital;
	}
	public void setCapital(int capital) {
		this.capital = capital;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getShareholder() {
		return shareholder;
	}
	public void setShareholder(String shareholder) {
		this.shareholder = shareholder;
	}
	public boolean isIs_transition() {
		return is_transition;
	}
	public void setIs_transition(boolean is_transition) {
		this.is_transition = is_transition;
	}
}

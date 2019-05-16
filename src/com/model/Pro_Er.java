package com.model;

import java.util.List;

import com.entity.Project;

public class Pro_Er {
	
	private String name;//项目名称
	private String orgName;//项目负责人名称
	private List<String> memList;//项目负责人名称
	private String data;//项目建立日期
	
	public Pro_Er(Project pro) {
		this.name = pro.getName();
		this.orgName = pro.getOrgName();
		this.memList = pro.getMembersName();
		this.data = pro.getDate();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<String> getMemList() {
		return memList;
	}

	public void setMemList(List<String> memList) {
		this.memList = memList;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}

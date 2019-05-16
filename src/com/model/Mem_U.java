package com.model;

import com.entity.User;

public class Mem_U {
	private String name;
	private String school;
	
	public Mem_U(User u) {
		this.name = u.getName();
		this.school = u.getSchool();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
}

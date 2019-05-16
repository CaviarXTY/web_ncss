package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.util.NameUtil;

/**
 * 管理员
 * @author Caviar
 *
 */
@Entity//注解为hibernate实体
@Table(name="admin") //注解对应的表名
public class Admin {
	@Id	//注解主键
	@GeneratedValue	//id生成策略  默认auto 相当于hibernate的native - 自增字段
	private int id;//管理员id
	private String username;//管理员登录账号
	private String password;//管理员密码
	private String name;//管理员姓名
	private String organization;//管理员所属组织
	private String photo;//管理员头像
	private int province;//管理员所属省份
	private int permissions;//管理员权限
	
	/**
	 * 获取省份
	 * @return
	 */
	public String getProvinceName() {
		return NameUtil.getProvince(this.province);
	} 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getPermissions() {
		return permissions;
	}
	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}
	
}

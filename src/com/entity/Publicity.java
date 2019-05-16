package com.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 宣传栏
 * @author Caviar
 *
 */
@Entity
@Table(name="publicity")
public class Publicity {
	@Id
	private int id;//宣传栏位
	private String img;//宣传图
	private String title;//宣传标题
	private String data;//发布日期
	private String subtitle;//副标题
	private int information;//动态信息id
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return data;
	}
	public void setDate(String data) {
		this.data = data;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public int getInformation() {
		return information;
	}
	public void setInformation(int information) {
		this.information = information;
	}
}

package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.util.NameUtil;

/**
 * 大赛动态信息
 * @author Caviar
 *
 */
@Entity
@Table(name="information")
public class Information {
	@Id
	@GeneratedValue
	private int id;//动态信息id
	private String title;//动态信息标题
	private String date;//信息发布日期
	private String content;//文章内容
	private int province;//所属省份id
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}

}

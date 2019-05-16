package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.util.NameUtil;
import com.util.StringUtil;

/**
 * 企业
 * @author Caviar
 *
 */
@Entity
@Table(name="enterprise") 
public class Enterprise {
	@Id
	@GeneratedValue 
	private int id;//企业id
	private String name;//企业名称
	private String photo;//企业家头像
	private String entrepreneurs;//企业家@企业家id
	private String personal;//企业家个人简介
	private String address;//企业所在地
	private int capital;//企业资金规模
	private String introduce;//企业简介
	private String invest;//企业投资案例
	private int type;//企业类型
	private int province;//企业地区
	
	/**
	 * 获取企业家名称
	 * @return
	 */
//	public String getErName() {
//		return StringUtil.getName(entrepreneurs);
//	}
	
	/**
	 * 获取类型
	 * @return
	 */
	public String getTypeName() {
		return NameUtil.getType(this.type);
	}
	
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
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEntrepreneurs() {
		return entrepreneurs;
	}
	public void setEntrepreneurs(String entrepreneurs) {
		this.entrepreneurs = entrepreneurs;
	}
	public String getPersonal() {
		return personal;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCapital() {
		return capital;
	}
	public void setCapital(int capital) {
		this.capital = capital;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getInvest() {
		return invest;
	}
	public void setInvest(String invest) {
		this.invest = invest;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	
}

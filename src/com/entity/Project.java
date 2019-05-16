package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.util.NameUtil;
import com.util.StringUtil;

/**
 * 大赛项目
 * @author Caviar
 *
 */
@Entity
@Table(name="project")
public class Project {
	
	/**********************************Data**********************************/
	
	@Id
	@GeneratedValue
	private int id;//项目id
	private String name;//项目名称
	private String logo;//项目logo
	private int province;//项目所属省份id
	private int type;//项目类型id
	private String orginators;//项目负责人1：id
	private String members;//项目成员1：id ;成员2 ：id
	private String presentation;//项目概述
	private String plan;//项目计划书URL
	private String date;//项目建立日期
	private int sponsorship;//项目赞助资金
	private int support;//项目获得支持数
	private String appraise;//评选状况
	private String ispublic;//是否公开
	
	/*********************************GetSet*********************************/
	
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
	
	/**
	 * 获取成员
	 * @return
	 */
	public List<String> getMembersName() {
		return StringUtil.getNameArray(this.members);
	}
	
	/**
	 * 显示成员
	 * @return
	 */
	public String getMemString() {
		String str = "";
		List<String> list = getMembersName();
		if(list != null && list.size() > 0) {
			System.out.println("getMemString():"+StringUtil.getName(orginators));
			for(String s : list) {
				str += s + " ";
			}
		}
		
		return str;
	}
	
	/**
	 * 添加成员
	 * @param id
	 * @param name
	 * @return
	 */
	public Project addMembers(int id, String name){
		List<String> list = getMembersName();
		if(list!=null) {
			for(String str : list) {
				if(str.equals(name)) {
					return this;
				}
			}
		}
		this.members += name + "@" + id + ";"; 
		return this;
	}
	
	/**
	 * 获取项目负责人
	 * @return
	 */
	public String getOrgName() {
		return StringUtil.getName(orginators);
	}
	
	

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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getOrginators() {
		return orginators;
	}

	public void setOrginators(String orginators) {
		this.orginators = orginators;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSponsorship() {
		return sponsorship;
	}

	public void setSponsorship(int sponsorship) {
		this.sponsorship = sponsorship;
	}

	public int getSupport() {
		return support;
	}

	public void setSupport(int support) {
		this.support = support;
	}

	public String getAppraise() {
		return appraise;
	}

	public void setAppraise(String appraise) {
		this.appraise = appraise;
	}

	public String getIspublic() {
		return ispublic;
	}

	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}
	
}

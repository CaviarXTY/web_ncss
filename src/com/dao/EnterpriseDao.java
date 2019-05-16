package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Enterprise;

@Repository
public class EnterpriseDao extends BaseDao{
	
	/**
	 * 通过id查找
	 * @return 
	 */
	public Enterprise getById(int id) {
		return getSession().createQuery("from Enterprise where id=:id", Enterprise.class)
				.setParameter("id", id).uniqueResult();
	}
	
	/**
	 * 通过name查找
	 * @return
	 */
	public Enterprise getByName(String name) {
		return getSession().createQuery("from Enterprise where name=:name", Enterprise.class)
				.setParameter("name", name).uniqueResult();
	}
	
	/**
	 * 获取列表
	 * @return
	 */
	public List<Enterprise> getList(int page, int rows){
		return getSession().createQuery("from Enterprise", Enterprise.class)
				.setFirstResult(rows*(page-1)).setMaxResults(rows).list();
	}
	
	/**
	 * 获取总数
	 * @return
	 */
	public long getTotal() {
		return getSession().createQuery("select count(*) from Enterprise", Long.class).uniqueResult();
	}
	
	/**
	 * 通过名称、类型、省份获取列表
	 * @param name
	 * @param typeid
	 * @param provinceid
	 * @param page
	 * @param size
	 * @return
	 */
//	public List<Enterprise> getListByForm(String name,int typeid, int provinceid, int page, int size){
//		return getSession().createQuery("from Enterprise where name like :name and type=:typeid order and province=:provinceid by id desc", Enterprise.class)
//				.setParameter("name", "%"+name+"%")
//				.setParameter("typeid",typeid)
//				.setParameter("provinceid",provinceid)
//				.setFirstResult((page-1)*size).setMaxResults(size).list();
//	}
	
	/**
	 * 通过名称获取列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Enterprise> getListByName(String name, int page, int size){
		return getSession().createQuery("from Enterprise where name like :name order by id desc", Enterprise.class)
				.setParameter("name", "%"+name+"%").setFirstResult((page-1)*size).setMaxResults(size).list();
	}
	
	/**
	 * 通过名称获取总数
	 * @param name
	 * @return
	 */
	public long getTotalByName(String name) {
		return (Long) getSession().createQuery("select count(*) from Enterprise where name like :name")
				.setParameter("name", "%"+name+"%").uniqueResult();
	}
	
	/**
	 * 通过类型获取列表
	 * @param typeid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Enterprise> getListByType(int type, int page, int size){
		return getSession().createQuery("from Enterprise where type=:type", Enterprise.class)
				.setParameter("type", type).setFirstResult((page-1)*size).setMaxResults(size).list();
	}
	
	/**
	 * 通过类型获取总数
	 * @param typeid
	 * @return
	 */
	public long getTotalByType(int type){
		return (Long) getSession().createQuery("select count(*) from Enterprise where type=:type")
				.setParameter("type", type).uniqueResult();
	}
	
	/**
	 * 通过省份获取列表
	 * @param provinceid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Enterprise> getListByProv(int province, int page, int size){
		return getSession().createQuery("from Enterprise where province=:province", Enterprise.class)
				.setParameter("province", province).setFirstResult((page-1)*size).setMaxResults(size).list();
	}
	
	/**
	 * 通过省份获取总数
	 * @param provinceid
	 * @return
	 */
	public long getTotalByProv(int province){
		return (Long) getSession().createQuery("select count(*) from Enterprise where province=:province")
				.setParameter("province", province).uniqueResult();
	}
}

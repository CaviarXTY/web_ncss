package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Project;

/**
 * 项目dao
 * @author Caviar
 *
 */
@Repository
public class ProjectDao extends BaseDao{

	/**
	 * 通过id查找
	 * @return 
	 */
	public Project getById(int id) {
		return getSession().get(Project.class, id);
	}
	
	/**
	 * 通过用户名查找项目
	 * @param name
	 * @return
	 */
	public Project getByName(String name) {
		return getSession().createQuery("from Project where name=:name",Project.class)
				.setParameter("name",name).uniqueResult();
	}
	
	
	/**
	 * 获取列表
	 * @return
	 */
	public List<Project> getList(int page, int rows){
		return getSession().createQuery("from Project", Project.class)
				.setFirstResult(rows*(page-1)).setMaxResults(rows).list();
	}
	
	/**
	 * 获取总数
	 * @return
	 */
	public long getTotal() {
		return getSession().createQuery("select count(*) from Project", Long.class).uniqueResult();
	}
	
	/**
	 * 通过名称获取列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Project> getListByName(String name, int page, int size){
		return getSession().createQuery("from Project where name like :name order by id desc", Project.class)
				.setParameter("name", "%"+name+"%").setFirstResult((page-1)*size).setMaxResults(size).list();
	}
	
	/**
	 * 通过名称获取总数
	 * @param name
	 * @return
	 */
	public long getTotalByName(String name) {
		return (Long) getSession().createQuery("select count(*) from Project where name like :name")
				.setParameter("name", "%"+name+"%").uniqueResult();
	}
	
	/**
	 * 通过类型获取列表
	 * @param typeid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Project> getListByType(int type, int page, int size){
		return getSession().createQuery("from Project where type=:type", Project.class)
				.setParameter("type", type).setFirstResult((page-1)*size).setMaxResults(size).list();
	}
	
	/**
	 * 通过类型获取总数
	 * @param typeid
	 * @return
	 */
	public long getTotalByType(int type){
		return (Long) getSession().createQuery("select count(*) from Project where type=:type")
				.setParameter("type", type).uniqueResult();
	}
	
	/**
	 * 通过省份获取列表
	 * @param provinceid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Project> getListByProv(int province, int page, int size){
		return getSession().createQuery("from Project where province=:province", Project.class)
				.setParameter("province", province).setFirstResult((page-1)*size).setMaxResults(size).list();
	}
	
	/**
	 * 通过省份获取总数
	 * @param provinceid
	 * @return
	 */
	public long getTotalByProv(int province){
		return (Long) getSession().createQuery("select count(*) from Project where province=:province")
				.setParameter("province", province).uniqueResult();
	}
}

package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Information;

/**
 * 大赛动态dao
 * @author Caviar
 *
 */
@Repository
public class InformationDao extends BaseDao{
	
	/**
	 * 通过id查找
	 * @return 
	 */
	public Information getById(int id) {
		return getSession().createQuery("from Information where id=:id", Information.class)
				.setParameter("id", id).uniqueResult();
	}
	
	/**
	 * 通过名称获取列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Information> getListByName(String name, int page, int size){
		return getSession().createQuery("from Information where name like :name order by id desc",Information.class)
				.setParameter("name", "%"+name+"%").setFirstResult((page-1)*size).setMaxResults(size).list();
	}

	/**
	 * 通过名称获取总数
	 * @param name
	 * @return
	 */
	public long getTotalByName(String name) {
		return (Long) getSession().createQuery("select count(*) from Information where name like :name")
				.setParameter("name", "%"+name+"%").uniqueResult();
	}

	
	/**
	 * 获取列表
	 * @return
	 */
	public List<Information> getList(int page, int rows){
		return getSession().createQuery("from Information", Information.class)
				.setFirstResult(rows*(page-1)).setMaxResults(rows).list();
	}
	
	/**
	 * 获取总数
	 * @return
	 */
	public long getTotal() {
		return getSession().createQuery("select count(*) from Information", Long.class).uniqueResult();
	}
}

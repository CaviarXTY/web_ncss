package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Company;

/**
 * 用户注册公司
 * @author Caviar
 *
 */
@Repository
public class CompanyDao extends BaseDao{
	
	/**
	 * 通过id查找
	 * @return 
	 */
	public Company getById(int id) {
		return getSession().createQuery("from Company where id=:id", Company.class)
				.setParameter("id", id).uniqueResult();
	}
	
	/**
	 * 获取列表
	 * @return
	 */
	public List<Company> getList(int page, int rows){
		return getSession().createQuery("from Company", Company.class)
				.setFirstResult(rows*(page-1)).setMaxResults(rows).list();
	}
	
	/**
	 * 获取总数
	 * @return
	 */
	public long getTotal() {
		return getSession().createQuery("select count(*) from Company", Long.class).uniqueResult();
	}
}

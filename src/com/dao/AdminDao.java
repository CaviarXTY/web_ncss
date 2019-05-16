package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Admin;

/**
 * 管理员Dao
 * @author Caviar
 *
 */
@Repository // 注册dao层bean等同于@Component
public class AdminDao extends BaseDao{
	/**
	 * 通过id查找
	 * @return 
	 */
	public Admin getById(int id) {
		return getSession().createQuery("from Admin where id=:id", Admin.class)
				.setParameter("id", id).uniqueResult();
	}
	
	/**
	 * 通过username和password查找
	 * @return
	 */
	public Admin getByIdAndPassword(String username, String password) {
		return getSession().createQuery("from Admin where username=:username and password=:password", Admin.class)
				.setParameter("username", username).setParameter("password", password).uniqueResult();
	}
	
	/**
	 * 通过username查找
	 * @param username
	 * @return
	 */
	public Admin getByUserName(String username) {
		return getSession().createQuery("from Admin where username=:username", Admin.class)
				.setParameter("username", username).uniqueResult();
	}
	
	/**
	 * 通过名称获取列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Admin> getListByName(String name, int page, int size){
		return getSession().createQuery("from Admin where name like :name order by id desc",Admin.class)
				.setParameter("name", "%"+name+"%").setFirstResult((page-1)*size).setMaxResults(size).list();
	}

	/**
	 * 通过名称获取总数
	 * @param name
	 * @return
	 */
	public long getTotalByName(String name) {
		return (Long) getSession().createQuery("select count(*) from Admin where name like :name")
				.setParameter("name", "%"+name+"%").uniqueResult();
	}

	
	/**
	 * 获取列表
	 * @return
	 */
	public List<Admin> getList(int page, int rows){
		return getSession().createQuery("from Admin", Admin.class)
				.setFirstResult(rows*(page-1)).setMaxResults(rows).list();
	}
	
	/**
	 * 获取总数
	 * @return
	 */
	public long getTotal() {
		return getSession().createQuery("select count(*) from Admin", Long.class).uniqueResult();
	}
}

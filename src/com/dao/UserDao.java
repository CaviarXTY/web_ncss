package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public class UserDao extends BaseDao{
	
	/**
	 * 通过id查找
	 * @return 
	 */
	public User getById(int id) {
		return getSession().createQuery("from User where id=:id", User.class)
				.setParameter("id", id).uniqueResult();
	}
	
	/**
	 * 通过用户名查找用户
	 * @return 无记录返回null
	 */
	public User getByPhone(String phone) {
		return getSession().createQuery("from User where phone=:phone", User.class)
				.setParameter("phone", phone).uniqueResult();
	}
	
	/**
	 * 通过phoneNum和password查找
	 * @return
	 */
	public User getByIdAndPassword(String phone, String password) {
		return getSession().createQuery("from User where phone=:phone and password=:password", User.class)
				.setParameter("phone", phone).setParameter("password", password).uniqueResult();
	}
	
	/**
	 * 通过名称获取列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public List<User> getListByName(String name, int page, int size){
		return getSession().createQuery("from User where name like :name order by id desc",User.class)
				.setParameter("name", "%"+name+"%").setFirstResult((page-1)*size).setMaxResults(size).list();
	}

	/**
	 * 通过名称获取总数
	 * @param name
	 * @return
	 */
	public long getTotalByName(String name) {
		return (Long) getSession().createQuery("select count(*) from User where name like :name")
				.setParameter("name", "%"+name+"%").uniqueResult();
	}

	
	/**
	 * 获取列表
	 * @return
	 */
	public List<User> getList(int page, int rows){
		return getSession().createQuery("from User", User.class)
				.setFirstResult(rows*(page-1)).setMaxResults(rows).list();
	}
	
	/**
	 * 获取总数
	 * @return
	 */
	public long getTotal() {
		return getSession().createQuery("select count(*) from User", Long.class).uniqueResult();
	}
}

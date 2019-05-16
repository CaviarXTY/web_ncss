package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Entrepreneur;

/**
 * 企业家Dao
 * @author Caviar
 *
 */
@Repository // 注册dao层bean等同于@Component
public class EntrepreneurDao extends BaseDao {

	/**
	 * 通过id查找
	 * @return 
	 */
	public Entrepreneur getById(int id) {
		return getSession().createQuery("from Entrepreneur where id=:id", Entrepreneur.class)
				.setParameter("id", id).uniqueResult();
	}
	
	/**
	 * 通过用户名查找企业家
	 * @return 无记录返回null
	 */
	public Entrepreneur getByUsername(String username){
		return getSession().createQuery("from Entrepreneur where username=:username", Entrepreneur.class)
				.setParameter("username", username).uniqueResult();
	}
	
	/**
	 * 通过username和password查找
	 * @return
	 */
	public Entrepreneur getByIdAndPassword(String username, String password) {
		return getSession().createQuery("from Entrepreneur where username=:username and password=:password", Entrepreneur.class)
				.setParameter("username", username).setParameter("password", password).uniqueResult();
	}
	
	/**
	 * 通过名称获取列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Entrepreneur> getListByName(String name, int page, int size){
		return getSession().createQuery("from Entrepreneur where name like :name order by id desc",Entrepreneur.class)
				.setParameter("name", "%"+name+"%").setFirstResult((page-1)*size).setMaxResults(size).list();
	}

	/**
	 * 通过名称获取总数
	 * @param name
	 * @return
	 */
	public long getTotalByName(String name) {
		return (Long) getSession().createQuery("select count(*) from Entrepreneur where name like :name")
				.setParameter("name", "%"+name+"%").uniqueResult();
	}
	
	/**
	 * 获取列表
	 * @return
	 */
	public List<Entrepreneur> getList(int page, int rows){
		return getSession().createQuery("from Entrepreneur", Entrepreneur.class)
				.setFirstResult(rows*(page-1)).setMaxResults(rows).list();
	}
	
	/**
	 * 获取总数
	 * @return
	 */
	public long getTotal() {
		return getSession().createQuery("select count(*) from Entrepreneur", Long.class).uniqueResult();
	}
}

package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Province;


/**
 * 省份dao
 * @author Caviar
 *
 */
@Repository
public class ProvinceDao extends BaseDao{

	/**
	 * 通过id查找
	 * @return 
	 */
	public Province getById(int id) {
		return getSession().createQuery("from Province where id=:id", Province.class)
				.setParameter("id", id).uniqueResult();
	}
	
	/**
	 * 获取列表
	 * @return
	 */
	public List<Province> getList(){
		//return getSession().createQuery("from Province order by id desc", Province.class).list();
		return getSession().createQuery("from Province", Province.class).list();
	}
	
}

package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Publicity;

/**
 * 宣传栏dao
 * @author Caviar
 *
 */
@Repository
public class PublicityDao extends BaseDao{

	/**
	 * 获取列表
	 * @return
	 */
	public List<Publicity> getList() {
		return getSession().createQuery("from Publicity order by id", Publicity.class).list();
	}
	
}

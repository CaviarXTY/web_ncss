package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Type;

/**
 * 类型dao
 * @author Caviar
 *
 */
@Repository
public class TypeDao extends BaseDao{
	
	/**
	 * 获取列表
	 * @return
	 */
	public List<Type> getList() {
		//return getSession().createQuery("from Type order by id desc", Type.class).list();
		return getSession().createQuery("from Type", Type.class).list();
	}
}

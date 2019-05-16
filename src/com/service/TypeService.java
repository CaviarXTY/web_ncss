package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.TypeDao;
import com.entity.Type;

/**
 * 类型服务
 * @author Caviar
 *
 */
@Service
@Transactional
public class TypeService {
	
	@Resource	
	private TypeDao typeDao;
	
	/**
	 * 获取列表
	 * @return
	 */
	public List<Type> getList(){
		return typeDao.getList();
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public Type get(int id) {
		return typeDao.get(Type.class, id);
	}
}

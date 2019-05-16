package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PublicityDao;
import com.entity.Publicity;


/**
 * 宣传栏服务
 */
@Service
@Transactional
public class PublicityService {
	
	@Resource
	private PublicityDao publicityDao;
	
	/**
	 * 获取列表
	 * @return
	 */
	public List<Publicity> getList(){
		return publicityDao.getList();
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public Publicity get(int id) {
		return publicityDao.get(Publicity.class, id);
	}
	
	/**
	 * 更新
	 * @param top
	 */
	public boolean update(Publicity publicity) {
		return publicityDao.update(publicity);
	}
}

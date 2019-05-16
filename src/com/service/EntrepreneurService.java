package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.EntrepreneurDao;
import com.entity.Entrepreneur;
import com.util.SafeUtil;

/**
 * 企业家服务
 * @author Caviar
 */
@Service// 注解为service层spring管理bean
@Transactional// 注解此类所有方法加入spring事务, 具体设置默认
public class EntrepreneurService {
	
	@Resource//spring注入类对象
	private EntrepreneurDao entrepreneurDao;
	
	/**
	 * 验证企业家密码
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean verifyEntrepreneurs(String username,String password) {
		//System.out.println(entrepreneurDao.getByIdAndPassword(username, SafeUtil.encode(password)));
		return entrepreneurDao.getByIdAndPassword(username, SafeUtil.encode(password)) != null;
	}
	
	/**
	 * 验证企业家是否存在
	 * @param username
	 * @return
	 */
	public boolean isExist(String username) {
		return entrepreneurDao.getByUsername(username) != null;
	}
	
	/**
	 * 验证企业家是否存在
	 * @param id
	 * @return
	 */
	public boolean isExist(int id) {
		return entrepreneurDao.get(Entrepreneur.class, id) != null;
	}
	
	/**
	 * 增加
	 * @param entrepreneurs
	 * @return
	 */
	public boolean add(Entrepreneur entrepreneur){
		entrepreneur.setPassword(SafeUtil.encode(entrepreneur.getPassword()));//加密
		return entrepreneurDao.save(entrepreneur) > 0;
	}
	
	/**
	 * 通过id获取
	 * @param entrepreneurs_id
	 * @return
	 */
	public Entrepreneur get(int id){
		return entrepreneurDao.get(Entrepreneur.class, id);
	}
	
	/**
	 * 通过usename获取
	 * @param usename
	 * @return
	 */
	public Entrepreneur get(String usename){
		return entrepreneurDao.getByUsername(usename);
	}
	
	/**
	 * 更新企业家数据
	 * @param entrepreneurs
	 */
	public boolean update(Entrepreneur entrepreneur) {
		return entrepreneurDao.update(entrepreneur);
	}

	/**
	 * 删除企业家数据
	 * @param entrepreneurs
	 */
	public boolean delete(Entrepreneur entrepreneur) {
		return entrepreneurDao.delete(entrepreneur);
	}
	
	/**
	 * 通过名称获取列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Entrepreneur> getListByName(String name, int page, int size){
		return entrepreneurDao.getListByName(name, page, size);
	}
	
	/**
	 * 通过名称获取总数
	 * @param name
	 * @return
	 */
	public long getTotalByName(String name) {
		return entrepreneurDao.getTotalByName(name);
	}


	
	/**
	 * 列表获取
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Entrepreneur> getList(int page, int rows) {
		return entrepreneurDao.getList(page, rows);
	}
	
	/**
	 * 总数
	 * @return
	 */
	public long getTotal() {
		return entrepreneurDao.getTotal();
	}

}

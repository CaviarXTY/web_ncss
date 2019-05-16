package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.InformationDao;
import com.entity.Information;

/**
 * 动态信息服务
 * @author Caviar
 *
 */
@Service
@Transactional
public class InformationService {
	
	@Resource
	private InformationDao informationDao;
	
	/**
	 * 动态信息是否存在
	 * @param id
	 * @return
	 */
	public boolean isExist(int id) {
		return informationDao.get(Information.class, id) != null;
	}
	
	/**
	 * 通过id获取
	 * @param id
	 * @return
	 */
	public Information get(int id) {
		return informationDao.get(Information.class, id);
	}
	
	/**
	 * 添加
	 * @param information
	 * @return
	 */
	public boolean add(Information information) {
		return informationDao.save(information) > 0;
	}
	
	/**
	 * 删除数据
	 * @param information
	 */
	public boolean delete(Information information) {
		return informationDao.delete(information);
	}
	
	/**
	 * 更新数据
	 * @param information
	 */
	public boolean update(Information information) {
		return informationDao.update(information);
	}

	/**
	 * 通过名称获取列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Information> getListByName(String name, int page, int size){
		return informationDao.getListByName(name, page, size);
	}
	
	/**
	 * 通过名称获取总数
	 * @param name
	 * @return
	 */
	public long getTotalByName(String name) {
		return informationDao.getTotalByName(name);
	}

	
	/**
	 * 列表获取
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Information> getList(int page, int rows) {
		return informationDao.getList(page, rows);
	}
	
	/**
	 * 总数
	 * @return
	 */
	public long getTotal() {
		return informationDao.getTotal();
	}
}

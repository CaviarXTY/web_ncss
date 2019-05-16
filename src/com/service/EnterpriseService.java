package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.EnterpriseDao;
import com.entity.Enterprise;

/**
 * 企业服务
 * @author Caviar
 *
 */
@Service
@Transactional
public class EnterpriseService {
	
	@Resource
	private EnterpriseDao enterpriseDao;
	
	/**
	 * 通过id验证企业是否存在
	 * @param id
	 * @return
	 */
	public boolean isExist(int id) {
		return enterpriseDao.get(Enterprise.class, id) != null;
	}
	
	/**
	 * 验证企业是否存在
	 * @param name
	 * @return
	 */
	public boolean isExist(String name) {
		return enterpriseDao.getByName(name) != null;
	}
	
	/**
	 * 通过id获取
	 * @param id
	 * @return
	 */
	public Enterprise get(int id){
		return enterpriseDao.get(Enterprise.class, id);
	}
	
	/**
	 * 通过name获取
	 * @param enterpriseName
	 * @return
	 */
	public Enterprise get(String enterpriseName) {
		return enterpriseDao.getByName(enterpriseName);
	}
	
	/**
	 * 添加
	 * @param enterprise
	 * @return
	 */
	public boolean add(Enterprise enterprise) {
		return enterpriseDao.save(enterprise) > 0;
	}
	
	/**
	 * 更新企业数据
	 * @param enterprise
	 * @return
	 */
	public boolean update(Enterprise enterprise) {
		return enterpriseDao.update(enterprise);
	}
	
	/**
	 * 删除数据
	 * @param enterprise
	 */
	public boolean delete(Enterprise enterprise) {
		return enterpriseDao.delete(enterprise);
	}

	
	/**
	 * 获取列表
	 * @return
	 */
	public List<Enterprise> getList(int page, int rows){
		return enterpriseDao.getList(page, rows);
	}
	
	/**
	 * 获取总数
	 * @return
	 */
	public long getTotal() {
		return enterpriseDao.getTotal();
	}
	
	/**
	 * 通过名称获取列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Enterprise> getListByName(String name, int page, int size){
		return enterpriseDao.getListByName(name, page, size);
	}
	
	/**
	 * 通过名称获取总数
	 * @param name
	 * @return
	 */
	public long getTotalByName(String name) {
		return enterpriseDao.getTotalByName(name);
	}
	
	/**
	 * 通过类型获取列表
	 * @param typeid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Enterprise> getListByType(int typeid, int page, int size){
		return enterpriseDao.getListByType(typeid, page, size);
	}
	
	/**
	 * 通过类型获取总数
	 * @param typeid
	 * @return
	 */
	public long getTotalByType(int typeid){
		return enterpriseDao.getTotalByType(typeid);
	}
	
	/**
	 * 通过省份获取列表
	 * @param provinceid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Enterprise> getListByProv(int provinceid, int page, int size){
		return enterpriseDao.getListByProv(provinceid, page, size);
	}
	
	/**
	 * 通过省份获取总数
	 * @param provinceid
	 * @return
	 */
	public long getTotalByProv(int provinceid){
		return enterpriseDao.getTotalByProv(provinceid);
	}
}

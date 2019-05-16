package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProjectDao;
import com.entity.Project;

/**
 * 项目服务
 * @author Caviar
 */
@Service
@Transactional
public class ProjectService {
	
	@Resource//spring注入类对象
	private ProjectDao projectDao;
	
	/**
	 * 通过id验证项目是否存在
	 * @param id
	 * @return
	 */
	public boolean isExist(int id) {
		return projectDao.get(Project.class,id) != null;
	}
	
	/**
	 * 通过项目名称验证项目是否存在
	 * @param id
	 * @return
	 */
	public boolean isExist(String name) {
		return projectDao.getByName(name) != null;
	}
	
	/**
	 * 通过id获取
	 * @param id
	 * @return
	 */
	public Project get(int id){
		return projectDao.get(Project.class, id);
	}
	
	/**
	 * 通过项目名称获取
	 * @param name
	 * @return
	 */
	public Project get(String name) {
		return projectDao.getByName(name);
	}
	
	
	/**
	 * 增加
	 * @param project
	 * @return
	 */
	public boolean add(Project project) {
		return projectDao.save(project) > 0;
	}
	
	/**
	 * 删除数据
	 * @param project
	 */
	public boolean delete(Project project) {
		return projectDao.delete(project);
	}

	/**
	 * 更新用户数据
	 * @param project
	 */
	public boolean update(Project project) {
		return projectDao.update(project);
	}
	
	/**
	 * 获取列表
	 * @return
	 */
	public List<Project> getList(int page, int rows){
		return projectDao.getList(page, rows);
	}
	
	/**
	 * 获取总数
	 * @return
	 */
	public long getTotal() {
		return projectDao.getTotal();
	}
	
	/**
	 * 通过名称获取列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Project> getListByName(String name, int page, int size){
		return projectDao.getListByName(name, page, size);
	}
	
	/**
	 * 通过名称获取总数
	 * @param name
	 * @return
	 */
	public long getTotalByName(String name) {
		return projectDao.getTotalByName(name);
	}
	
	/**
	 * 通过类型获取列表
	 * @param typeid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Project> getListByType(int typeid, int page, int size){
		return projectDao.getListByType(typeid, page, size);
	}
	
	/**
	 * 通过类型获取总数
	 * @param typeid
	 * @return
	 */
	public long getTotalByType(int typeid){
		return projectDao.getTotalByType(typeid);
	}
	
	/**
	 * 通过省份获取列表
	 * @param provinceid
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Project> getListByProv(int provinceid, int page, int size){
		return projectDao.getListByProv(provinceid, page, size);
	}
	
	/**
	 * 通过省份获取总数
	 * @param provinceid
	 * @return
	 */
	public long getTotalByProv(int provinceid){
		return projectDao.getTotalByProv(provinceid);
	}
	
}

package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entity.User;
import com.dao.UserDao;
import com.util.SafeUtil;

/**
 * 用户服务
 * @author Caviar
 *
 */
@Service
@Transactional
public class UserService {

	@Resource//spring注入类对象
	private UserDao userDao;
	
	/**
	 * 验证用户密码
	 * @param phone
	 * @param password
	 * @return
	 */
	public boolean checkUser(String phone,String password) {
		return userDao.getByIdAndPassword(phone, SafeUtil.encode(password)) != null;
	}
	
	/**
	 * 用户是否存在
	 * @param phone
	 * @return 
	 */
	public boolean isExist(String phone) {
		return userDao.getByPhone(phone) != null;
	}
	
	/**
	 * 用户是否存在
	 * @param phone
	 * @return 
	 */
	public boolean isExist(int id) {
		return userDao.get(User.class, id) != null;
	}
	
	
	/**
	 * 增加
	 * @param user
	 * @return
	 */
	public boolean add(User user){
		user.setPassword(SafeUtil.encode(user.getPassword()));
		return userDao.save(user) > 0;
	}
	
	/**
	 * 通过id获取
	 * @param id
	 * @return
	 */
	public User get(int id){
		return userDao.get(User.class, id);
	}
	
	/**
	 * 通过phone获取
	 * @param phone
	 * @return
	 */
	public User get(String phone){
		return userDao.getByPhone(phone);
	}
	
	/**
	 * 更新用户数据
	 * @param user
	 */
	public boolean update(User user) {
		return userDao.update(user);
	}

	/**
	 * 删除用户数据
	 * @param entrepreneurs
	 */
	public boolean delete(User user) {
		return userDao.delete(user);
	}
	
	/**
	 * 通过名称获取列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public List<User> getListByName(String name, int page, int size){
		return userDao.getListByName(name, page, size);
	}
	
	/**
	 * 通过名称获取总数
	 * @param name
	 * @return
	 */
	public long getTotalByName(String name) {
		return userDao.getTotalByName(name);
	}

	
	/**
	 * 列表获取
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<User> getList(int page, int rows) {
		return userDao.getList(page, rows);
	}
	
	/**
	 * 总数
	 * @return
	 */
	public long getTotal() {
		return userDao.getTotal();
	}
}

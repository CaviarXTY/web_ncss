package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AdminDao;
import com.entity.Admin;
import com.util.SafeUtil;

/**
 * 管理员服务
 * @author Caviar
 *
 */
@Service// 注解为service层spring管理bean
@Transactional// 注解此类所有方法加入spring事务, 具体设置默认
public class AdminService {

	@Resource//spring注入类对象
	private AdminDao admindao;
	
	/**
	 * 验证管理员密码
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean verifyAdmin(String username,String password) {
		//System.out.println(admindao.getByIdAndPassword(username, SafeUtil.encode(password)));
		return admindao.getByIdAndPassword(username, SafeUtil.encode(password)) != null;
	}
	
//	/**
//	 * 管理员是否存在
//	 * @param entrepreneurs_id
//	 * @return 
//	 */
//	public boolean isExist(int id) {
//		return admindao.getById(id) != null;
//	}
//	
	/**
	 * 增加
	 * @param entrepreneurs
	 * @return
	 */
	public boolean add(Admin admin){
		admin.setPassword(SafeUtil.encode(admin.getPassword()));
		return admindao.save(admin) > 0;
	}
	
	/**
	 * 通过id获取
	 * @param id
	 * @return
	 */
	public Admin get(int id){
		return admindao.get(Admin.class, id);
	}
	
	/**
	 * 通过usenaem获取
	 * @param entrepreneurs_id
	 * @return
	 */
	public Admin get(String usename){
		return admindao.getByUserName(usename);
	}
	
	/**
	 * 更新管理员数据
	 * @param entrepreneurs
	 */
	public boolean update(Admin admin) {
		return admindao.update(admin);
	}

	/**
	 * 删除管理员数据
	 * @param entrepreneurs
	 */
	public boolean delete(Admin admin) {
		return admindao.delete(admin);
	}
	
	/**
	 * 通过名称获取列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	public List<Admin> getListByName(String name, int page, int size){
		return admindao.getListByName(name, page, size);
	}
	
	/**
	 * 通过名称获取总数
	 * @param name
	 * @return
	 */
	public long getTotalByName(String name) {
		return admindao.getTotalByName(name);
	}
	
	/**
	 * 列表获取
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Admin> getList(int page, int rows) {
		return admindao.getList(page, rows);
	}
	
	/**
	 * 总数
	 * @return
	 */
	public long getTotal() {
		return admindao.getTotal();
	}
}

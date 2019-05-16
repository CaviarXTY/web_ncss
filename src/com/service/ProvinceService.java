package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProvinceDao;
import com.entity.Province;

/**
 * 省份服务
 * @author Caviar
 *
 */
@Service
@Transactional
public class ProvinceService {
	
	@Resource	
	private ProvinceDao provinceDao;
	
	/**
	 * 获取列表
	 * @return
	 */
	public List<Province> getList(){
		return provinceDao.getList();
	}
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public Province get(int id) {
		return provinceDao.get(Province.class, id);
	}
}
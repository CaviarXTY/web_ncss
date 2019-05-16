//package com.service;
//
//import java.text.ParseException;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.dao.NewsDao;
//import com.entity.News;
//
///**
// * 消息服务
// * @author Caviar
// *
// */
//@Service
//@Transactional
//public class NewsService {
//	
//	@Resource
//	private NewsDao newsDao;
//	
//	public News send(String value) throws ParseException {
//		return newsDao.send(value);
//	}
//}

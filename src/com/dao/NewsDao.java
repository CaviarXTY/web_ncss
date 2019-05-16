//package com.dao;
//
//import java.text.ParseException;
//import java.util.List;
//
//import org.springframework.stereotype.Repository;
//
//import com.entity.News;
//import com.entity.Session;
//
///**
// * 消息Dao
// * @author Caviar
// *
// */
//@Repository
//public class NewsDao {
//	
//	/**
//	 * 获取消息列
//	 * @param se
//	 * @return
//	 */
//	public List<News> getList(Session se){
//		return se.getNewsList();
//	}
//	
//	public News send(String value) throws ParseException {
//		return new News(value);
//	}
//	
//}

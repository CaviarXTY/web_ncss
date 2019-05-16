package com.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.News;
import com.entity.Session;
import com.util.StringUtil;

/**
 * 会话Dao
 * @author Caviar
 *
 */
@Repository
public class SessionDao {
	//name@1#0.2019-1-1 11:11:11.<a href="">接受</a>,1.2019-2-2 12:12:12.value2;name2@2#0.2019-2-1 11:11:11.<a href="">接受</a>,1.2019-1-1 12:12:12.value2;
	
	/**
	 * 新建一个对话
	 * @param name
	 * @param userid
	 * @param news
	 * @return
	 */
	public Session newSession(String objectname, int objectid, News news) {
		System.out.println("SessionDao:newSession:start");//测试
		return new Session(objectname,objectid,news);
	}
	
	
	/**
	 * 将数组转换成Session对象集合
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public List<Session> getList(String value) throws ParseException{
		System.out.println("SessionDao:getList:value="+value);
		if(value != null) {
			List<Session> list = new ArrayList<Session>();
			String[] strA = StringUtil.getUnit(value);//分割每个会话
			for(String str : strA) {
				list.add(new Session(str));
			}
			Session re;
			//冒泡排序
			for(int i = 0; i < list.size();i++) {
				for(int j = 1;j < list.size();j++) {
					if(list.get(i).after(list.get(j))) {
						//替换
						re = list.get(i); 			//re=a
						list.set(j,list.get(i));	//b = a;
						list.set(i,re);				//a = re;
					}
				}
				//添加序号
				re = list.get(i);
				re.setId(i);
				list.set(i,re);
			}
			//测试
			for(int i = 0; i < list.size(); i++) {
				System.out.println("SessionDao:getList:return["+ i +"]=" + (list.get(i)).toString());
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 将会话集合转换成字符串
	 * 结果：	"session;session2"
	 * @param list
	 * @return
	 */
	public String toString(List<Session> list) {
		System.out.println("SessionDao:toString:start");//测试
		String strR = "";
		for(Session s : list) {
			strR = strR + s.toString();
		}
		System.out.println("SessionDao:toString:return="+strR);//测试
		return strR;
	}
}

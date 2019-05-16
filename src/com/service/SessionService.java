package com.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.SessionDao;
import com.entity.News;
import com.entity.Session;
import com.entity.User;
import com.util.StringUtil;

/**
 * 会话服务
 * @author Caviar
 *
 */
@Service
@Transactional
public class SessionService {

	@Resource
	private SessionDao sessionDao;
	
	/**
	 * 解析String获取List集合
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public List<Session> getList(String value) throws ParseException{
		//System.out.println("SessionService:getList:start");//测试
		return value!=null ? sessionDao.getList(value) : null;
	}
	
	/**
	 * 将会话集合List转化成String
	 * @param list
	 * @return 
	 */
	public String sessionListToString(List<Session> list) {
		String str ="";
		if(list.size() > 0) {
			for(Session se : list) {
				str += se.toString();
			}
		}
		return str;
	}
	
	/**
	 * 判断List中是否有与该对象的会话存在
	 * @param userName 会话对象名
	 * @param userNews 被检查的用户的news内容
	 * @return
	 * @throws ParseException
	 */
	public boolean isExist(List<Session> list, String userName) {
		if(list != null) {
			for(Session se : list) {
				if((se.getUserName()).equals(userName)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断自己记录中是否有与该对象的会话存在
	 * @param userName 会话对象名
	 * @param userNews 被检查的用户的news内容
	 * @return
	 * @throws ParseException
	 */
	public boolean isExist(String userName, String userNews) throws ParseException {
		//System.out.println("SessionService:isExist:value="+value);
		if(!StringUtil.isNull(userNews)) {//输入内容不为空			
			return isExist(sessionDao.getList(userNews),userName);
//			List<Session> list = sessionDao.getList(userNews);
//			for(Session se : list) {
//				if((se.getUserName()).equals(userName)) {
//					return true;
//				}
//			}
		}
		return false;
	}
	
	//public String getSessionUser(List<Session> Sessionlist)
	
	/**
	 * 判断记录中是否有某信息
	 * @param Sessionlist 检查的会话集合Sessionlist
	 * @param objectname 对话的用户
	 * @param value 判断的信息
	 * @return
	 */
	public boolean isValue(List<Session> Sessionlist, String objectname, String value) {
		if(!StringUtil.isNull(value) && isExist(Sessionlist,objectname)) {//输入内容不为空,存在与对象的会话
			for(Session se : Sessionlist) {
				if((se.getUserName()).equals(objectname)) {
					List<News> newsList = se.getNewsList();
					for(News ne : newsList) {
						if(ne.getValue().contains(value)) {//判断是否存在某段字符
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断用户记录中是否已发送邀请
	 * @param send
	 * @param get
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public boolean isSend(User send, User get, String value) throws ParseException {
		if(StringUtil.isNull(value)) {
			List<Session> sessionList = getList(send.getNews());
			if(isValue(sessionList, get.getName(), value)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 新建发送信息，将信息写入发送者的会话中,返回修改后的字符串
	 * @param send
	 * @param read
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public String sendNews(User send, User get, String value) throws ParseException {
		//System.out.println("SessionService:sendNews:value="+ send+":" + get+":" +value);//测试
		List<Session> sendList;
		News s = new News(value, true);//建立发送消息
		if(send.getNews() != null &&  !send.getNews().isEmpty()) {
			//将消息添加入发送者的list
			sendList = sessionDao.getList(send.getNews());
			if(isExist(get.getName(), send.getNews())) {
				//查询已有会话
				for(int i = 0; i < sendList.size(); i++) {
					if((sendList.get(i)).getUserName().equals(get.getName())) {
						//将发送的news添加进会话
						sendList.set(i, (sendList.get(i)).addNews(s));
						return sessionListToString(sendList);
					}
				}
			}	
		}else {
			//新建一个发送者会话组
			sendList = new ArrayList<Session>();
		}
		//没有当前用户的对话,建立发送者的会话
		//System.out.println("SessionService:sendNews:s="+s.toString());
		Session se = sessionDao.newSession(get.getName(), get.getId(), s);
		sendList.add(se);//并添加
		//System.out.println("############SessionService:sendNews:s="+sendList.toString());
		return sessionListToString(sendList);
	}
	
	/**
	 * 新建获取信息，将信息写入接受者的会话中,返回修改后的字符串
	 * @param send
	 * @param get
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public String getNews(User send, User get, String value) throws ParseException {
		//System.out.println("**********SessionService:getNews:value="+value);
		List<Session> readList;
		News r = new News(value, false);//建立未读消息
		if(get.getNews() != null &&  !get.getNews().isEmpty()) {
			readList = sessionDao.getList(get.getNews());
			//将消息加入接收list
			//System.out.println("**********SessionService:getNews:send.getNews()="+send.getNews());
			if(!isExist(send.getName(), get.getNews())){
				//查询需要的会话
				for(int i = 0; i < readList.size(); i++) {
					if((readList.get(i)).getUserName() == send.getName()) {
						//将发送的news添加进会话
						readList.set(i, (readList.get(i)).addNews(r));
						return sessionListToString(readList);
					}
				}
			}
		}else {
			//新建接收者会话组
			readList = new ArrayList<Session>();
		}
		//没有当前用户的对话,在接受者建立会话
		readList.add(sessionDao.newSession(send.getName(), send.getId(), r));
		return sessionListToString(readList);
	}
	
	/**
	 * 更改会话记录
	 * @param user
	 * @param object
	 * @param value
	 * @param newValue
	 * @return
	 * @throws ParseException
	 */
	public String updateSessionList(User user, User object, String value, String newValue) throws ParseException {
		List<Session> userList= getList(user.getNews());//获取用户会话sessionList
		if(isExist(userList,object.getName())) {//判断是否有该对话
			if(isValue(userList, object.getName(), value)) {//判断是否有该消息
				Session se = null;//建立新session存放newslist
				List<News> newsList = null;//建立新newslist存放news
				News ne = null;//建立新news存放value
				for(int i = 0; i < userList.size(); i++) {
					if(userList.get(i).getUserName().equals(object.getName())) {//找到与该对象对话session
						se = userList.get(i);
						newsList = se.getNewsList();//建立新集合存放新newlist
						for(int j = 0; j < newsList.size(); j++) {
							if(newsList.get(j).getValue().contains(value)) {//找到该信息
								//修改信息
								ne = newsList.get(j);
								ne.setValue(newValue);
								ne.setDate(new Date());
								newsList.set(j,ne);//将news存放入newslist
								break;
							}
						}
						se.setNewsList(newsList);//保存修改后的新newslist进session
						userList.set(i, se);//保存新的sessionList
						return sessionListToString(userList);//返回toString后更新的SessionList
					}
				}
			}
		}
		return sessionListToString(userList);//返回toString后的原SessionList
	}
	
	
	
	
}

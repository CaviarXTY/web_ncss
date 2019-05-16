package com.entity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.util.DateUtil;
import com.util.StringUtil;

public class Session {
	private int id;//会话id
	private List<News> newsList;//对话记录
	private String userName;//对话用户名
	private int userId;//对话用户id
	
	private int num;//对话数
	private String value;//最新对话内容
	private Date date;//最新日期
	private String ymd;//格式化后日期
	private boolean isread;//是否有新消息
	
	/**
	 * 生成新对话对象
	 * @param name
	 * @param userid
	 * @param news
	 */
	public Session(String name, int userid, News news) {
		System.out.println("Session:Session(0):name=" + name + ",id=" + id + ",news=" + news.toString());//测试
		this.id = -1;
		List<News> list = new ArrayList<News>();
		list.add(news);
		this.newsList=list;
		this.userName = name;
		this.userId = userid;
		
		this.num = newsList.size();
		this.value = news.getValue();
		this.date = news.getDate();
		this.ymd = DateUtil.YMD.format(this.date);
		this.isread = news.getType() == News.READ;
		System.out.println("Session:Session(0):return="+ this.toString());//测试
	}
	
	/**
	 * 通过传入信息分解成Session信息
	 * value规范    "name@id#type.date.value,type1.date2.value3"...
	 * @param value
	 * @throws ParseException 
	 */
	public Session(String value) throws ParseException {
		//value规范    "name@id#type.date.value,type1.date2.value3"...
		System.out.println("Session:Session(1):value="+value);//测试
		if(value != null) {
			String[] strA = StringUtil.getUnitBy(value,"#");
			this.id = -1;
			this.userId = StringUtil.getId(strA[0]);
			this.userName = StringUtil.getName(strA[0]);
			this.newsList = getList(strA[1]);
			this.num = newsList.size();
			this.value = (newsList.get(num-1)).getValue();
			this.date = (newsList.get(num-1)).getDate();
			this.isread = (newsList.get(num-1)).getType() == News.READ; 
			System.out.println("Session:Session(1):return="+ this.toString());//测试
		}
	}
	
	public Session addNews(News news) {
		this.newsList.add(news);
		return this;
	}
	
	/**
	 * 将字符串转换为历史记录
	 * value规范    "type.date.value,type1.date2.value3"...
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public ArrayList<News> getList(String value) throws ParseException{
		//value规范    "type.date.value,type1.date2.value3"...
		System.out.println("Session:getList:value="+value);//测试
		if(value!=null) {
			ArrayList<News> list = new ArrayList<News>();
			if(value.indexOf(",") != -1) {
				String[] strA = StringUtil.getUnitBy(value,",");//获得"type.date.value"
				for(String str : strA) {
					list.add(new News(str));
				}
			}else {
				list.add(new News(value));
			}
			//测试
			for(int i = 0; i < list.size(); i++) {
				System.out.println("StringUtil:getUnitBy:return["+ i +"]=" + (list.get(i)).toString());
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 将会话对象转换成字符串
	 * 结果：	"name@id#type.date.value,type1.date2.value3" + ... + ";"
	 */
	public String toString() {
		//结果：	name@id#type.date.value,type1.date2.value3;
		System.out.println("Session:toString:start");//测试
		String strList = "";
		int size = newsList.size();
		if(size > 0) {
			for(int i = 0; i < size; i++) {
				strList += (newsList.get(i)).toString();
				if(i != size - 1) {
					strList += ",";
				}
			}
		}

//		for(News news : newsList) {
//			strList = strList + news.toString() + ",";
//		}
		String str = this.userName+ "@" + userId + "#" + strList + ";";
		//System.out.println("@@@@@@@@Session:toString:return="+str);//测试
		return str;
	}
	
	/**
	 * 返回比较新的会话
	 * @param se
	 * @return
	 */
	public boolean after(Session se) {
		if(this.date.after(se.date)) {
			return true;
		}else {
			return false;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getYmd() {
		return ymd;
	}

	public void setYmd(String ymd) {
		this.ymd = ymd;
	}

	public boolean isIsread() {
		return isread;
	}

	public void setIsread(boolean isread) {
		this.isread = isread;
	}

}

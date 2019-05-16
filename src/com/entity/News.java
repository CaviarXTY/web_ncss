package com.entity;

import java.text.ParseException;
import java.util.Date;

import com.util.DateUtil;

public class News {
	public static final int SEND = 0;
	public static final int READ = 1;
	public static final int GET = 2;	

	private int type;//是否发送还是接受
	private Date date;//时间
	private String value;//内容
	
	public News() {
		this.type = SEND;
		this.date = new Date();
		this.value = "";
	}
	
	public News(String value) throws ParseException {
		toNews(value);
	}
	
	public News(String value, boolean issend){
		this.type = issend ? 0 : 1;
		this.date = new Date();
		this.value = value;
	}
	
//	public void sendNews(String value) {
//		this.type = SEND;
//		this.date = new Date();
//		this.value = value;
//	}
	
//	public News(String date, String value,int type) throws ParseException {
//		this.type = type;
//		this.date = DateUtil.YMDHMS.parse(date);
//		this.value = value;
//	}
	
	public void isRead() {
		this.type = GET;
	}
	
	/**
	 * 将消息转换成字符串
	 */
	public String toString() {
		System.out.println("News:toString:start");
		//type.date.value
		String str = this.type +"&" + DateUtil.YMDHMS.format(this.date) + "&" + this.value;
		System.out.println("News:toString:return="+str);
		return str;
	}
	
	/**
	 * 将字符串转换成消息
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public News toNews(String str) throws ParseException {
		System.out.println("News:toNews:value="+str);
		String[] s = str.split("&");
		if(s.length == 3) {
			this.type = Integer.parseInt(s[0]);
			this.date = DateUtil.YMDHMS.parse(s[1]);
			this.value = s[2];
		}
		return this;
	}
	
	/**
	 * 返回比较新的消息
	 * @param news
	 * @return
	 */
	public News after(News news) {
		System.out.println("News:after:start");
		if(this.date.after(news.date)) {
			return this;
		}else {
			return news;
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}

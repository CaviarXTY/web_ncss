package com.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	 
		/*
		 * 发送邮件工具类
		 * @to 收件人  code 激活码
		 */
		public static void sendMail(InternetAddress[] internetAddress,String tit,String content) throws Exception{
			Properties properties = new Properties();
			properties.put("mail.transport.protocol", "smtp");// 连接协议
			properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
			//properties.put("mail.smtp.host", "smtp.exmail.qq.com");// 企业邮箱主机名
			properties.put("mail.smtp.port", 465);// 端口号
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
			properties.put("mail.debug", "false");// 设置是否显示debug信息 true 会在控制台显示相关信息
			// 得到回话对象
			Session session = Session.getInstance(properties);
			
			// 获取邮件对象
			Message message = new MimeMessage(session);
			// 设置发件人邮箱地址
			message.setFrom(new InternetAddress("489308936@qq.com","[毕设]大学生创新创业服务网--Caviar", "UTF-8"));
			// 设置收件人邮箱地址
			message.setRecipients(Message.RecipientType.TO,internetAddress);
			// message.setRecipient(Message.RecipientType.TO, new
			// InternetAddress("xxx@qq.com"));//一个收件人
			// 设置邮件标题
			message.setSubject(tit);
			// 设置邮件内容
			message.setText(content);
			
			
			// 得到邮差对象
			Transport transport = session.getTransport();
			// 连接自己的邮箱账户
			transport.connect("489308936@qq.com", "sjfddueymyztbjfb");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
			//transport.connect("xutianyu@xutianyu.com", "mbasnk");
			// 发送邮件
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		
}

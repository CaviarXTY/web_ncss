package com.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;	

public class QcloudSmsUtil {

	// 短信应用SDK AppID
	public static int appid = 1400194256; // 1400开头
	// 短信应用SDK AppKey
	public static String appkey = "48ad507c6ec8146ea18c7d7698b9d9e5";
	// 短信模板ID，需要在短信应用中申请
	public static int templateId = 306394; //模板ID需要在短信控制台中申请,templateId7839对应的内容是"您的验证码是: {1}"
	// 签名内容
	public static String smsSign = "许天瑜个人空间";
	
	/**
	 * 发送短信
	 * @param code 发送的验证码
	 * @param nationCode 手机地区号
	 * @param phone 手机号码
	 */
	public static void Send(String code,String nationCode, String phone) {
		try {
		    String[] params = {code};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
		    SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
		    SmsSingleSenderResult result = ssender.sendWithParam(nationCode, phone,
		        templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
		    System.out.println(result);
		} catch (HTTPException e) {
		    // HTTP响应码错误
		    e.printStackTrace();
		} catch (JSONException e) {
		    // json解析错误
		    e.printStackTrace();
		} catch (IOException e) {
		    // 网络IO错误
		    e.printStackTrace();
		}
	}
	
	/**
	 * 发送短信
	 * @param code 发送的验证码
	 * @param nationCode 手机地区号
	 * @param phone 手机号码
	 */
	public static void Send2(int temp, String code,String nationCode, String phone) {
		try {
		    String[] params = {code};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
		    SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
		    SmsSingleSenderResult result = ssender.sendWithParam(nationCode, phone,
		    		temp, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
		    System.out.println(result);
		} catch (HTTPException e) {
		    // HTTP响应码错误
		    e.printStackTrace();
		} catch (JSONException e) {
		    // json解析错误
		    e.printStackTrace();
		} catch (IOException e) {
		    // 网络IO错误
		    e.printStackTrace();
		}
	}
	
}

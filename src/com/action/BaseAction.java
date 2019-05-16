package com.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * action基类, 继承自ActionSupport类  
 * 为子类提供struts2 map类型session对象
 * 提供分页工具变量等
 */
public class BaseAction extends ActionSupport implements SessionAware,ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	protected static final String USER = "user";
	protected static final String ENP = "entrepreneur";
	protected static final String ADM = "admin";
	protected static final String ERR = "error";
	
	protected int adNum = 4;	//轮播图数量
	protected int page = 1;
	protected String pageTool;
	protected Map<String, Object> session;
	protected HttpServletRequest servletRequest;

	
	@Override//重写自父接口SessionAware的方法,有容器自行调用并给session对象赋值
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	//用于在子类中获取session对象
	public Map<String, Object> getSession(){
		return session;
	}

	public int getAdNum() {
		return adNum;
	}

	public void setAdNum(int adNum) {
		this.adNum = adNum;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getPageTool() {
		return pageTool;
	}

	public void setPageTool(String pageTool) {
		this.pageTool = pageTool;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	/**
	 * 返回响应数据
	 * @param msg
	 */
	protected void sendResponseMsg(String msg) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.addHeader("Cache-Control", "must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setDateHeader("Expires", 0);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(msg);
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	/**
	 * 返回有且仅有一个的登录状态
	 * @return
	 */
	public String checkLogin() {
		if(getSession().get(USER) != null 
				&& getSession().get(ENP) == null && getSession().get(ADM) == null) {
			return USER;
		}else if(getSession().get(ENP) != null 
				&& getSession().get(USER) == null && getSession().get(ADM) == null){
			return ENP;
		}else if(getSession().get(ADM) != null 
				&& getSession().get(USER) == null && getSession().get(ENP) == null){
			return ADM;
		}else if(getSession().get(USER) == null 
				&& getSession().get(ENP) == null && getSession().get(ADM) == null) {
			return null;
		}else {
			logoutAll();
			getServletRequest().setAttribute("message", "发生错误，请重新登录");//弹窗提示	
			return ERR;
		}
	}
	
	/**
	 * 清除所有登录状态
	 */
	public void logoutAll() {
		getSession().remove(USER);
		getSession().remove(ENP);
		getSession().remove(ADM);
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 */
//	public boolean isNull(String str) {
//		if(str == null || str.isEmpty()) {
//			return true;
//		}
//		return false;
//	}
}

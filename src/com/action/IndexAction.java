package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.entity.Information;
import com.entity.Project;
import com.entity.Publicity;
import com.service.InformationService;
import com.service.PublicityService;
import com.util.MailUtil;
import com.util.QcloudSmsUtil;
import com.service.ProjectService;//测试

@Namespace("/index")
@Results({
	@Result(name="index",location="/index/index.jsp"),
	@Result(name="login",location="/index/login.jsp"),
	@Result(name="information",location="/index/information.jsp"),
	@Result(name="xiaoyu",location="/index/xiaoyu.jsp"),
})
@SuppressWarnings("serial")
public class IndexAction extends BaseAction{
	
	/**********************************Data**********************************/
	
	//数据
	private int flag; // 页面标记
	private int id;//信息跳转页面
	private Information information;//文章


	//集合
	private List<Publicity> publicityList;//宣传列
	
	//服务
	@Resource
	private PublicityService publicityService;//宣传栏服务
	@Resource
	private InformationService informationService;//动态文章服务
	
	//test
	private String name;
	private String pw;
	@Resource
	private ProjectService projectService;//动态文章服务
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	@Action("test")
	public void test() throws IOException {

		//ajax
//		//得到httpservlet里的response
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		/*
//		 * 判断用户名和密码是否正确
//		 * 这里只是做测试，用户名和密码是伪造的。
//		 */
//		List<Project> projectList = projectService.getList(page, 10);
////		for(Project p :projectList) {
////			System.out.println("p="+p.getIspublic());
////		}
//		String jsonStr =JSON.toJSONString(projectList);
//		System.out.println(name+"@"+pw);
//		System.out.println("list="+jsonStr);
//		//out.print("{\"mess\":\"success\"}");//返回也是一个json对象
//		out.print(jsonStr);//返回也是一个json对象
//		//关闭资源
//		out.close();
		
		//邮箱
//		String tit="这是标题";
//		String content="这是内容部分";
//		try {
//			InternetAddress[] internetAddress = new InternetAddress[] {
//					new InternetAddress("1148427828@qq.com")};
//			MailUtil.sendMail(internetAddress,tit,content);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		//短信
		QcloudSmsUtil.Send("1234", "86", "13760788985");
	}
	
	
	/*********************************Action*********************************/
	
	/**
	 * 首页
	 * @return
	 */
	@Action("index")
	public String index(){
		publicityList = publicityService.getList();
		//Collections.reverse(publicityList);
		return "index";
	}
		
	/**
	 * 登录页
	 * @return
	 */
	@Action("login")
	public String login(){
		if(checkLogin()==null) {
			return "login";
		}else {
			return index();
		}
	}
	
	/**
	 * 跳转信息页
	 * @return
	 */
	@Action("information")
	public String information(){
		if(informationService.isExist(id)) {
			information = informationService.get(id);
			return "information";
		}else {
			getServletRequest().setAttribute("message", "该文章已过期");//弹窗提示
			return index();
		}
	}
	
	@Action("xiaoyu")
	public String xiaoyu() {
		return "xiaoyu";
	}
	
	@Action("send")
	public void send() {
			QcloudSmsUtil.Send("520", "86", "13760788985");
			QcloudSmsUtil.Send("王小雨最可爱", "86", "15170977726");
	}
	
	@Action("badsend")
	public void badsend() {
			QcloudSmsUtil.Send("88", "86", "13760788985");
	}
	
		
	/*********************************GetSet*********************************/

	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Information getInformation() {
		return information;
	}
	public void setInformation(Information information) {
		this.information = information;
	}
	public List<Publicity> getPublicityList() {
		return publicityList;
	}
	public void setPublicityList(List<Publicity> publicityList) {
		this.publicityList = publicityList;
	}
	public PublicityService getPublicityService() {
		return publicityService;
	}
	public void setPublicityService(PublicityService publicityService) {
		this.publicityService = publicityService;
	}
	public InformationService getInformationService() {
		return informationService;
	}
	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}

}

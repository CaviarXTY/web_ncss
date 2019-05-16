package com.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.News;
import com.entity.Project;
import com.entity.Province;
import com.entity.Publicity;
import com.entity.Session;
import com.entity.Type;
import com.entity.User;
import com.model.Mem_U;
import com.service.ProjectService;
import com.service.ProvinceService;
import com.service.PublicityService;
import com.service.SessionService;
import com.service.TypeService;
import com.service.UserService;
import com.util.CodeUtil;
import com.util.DateUtil;
import com.util.MailUtil;
import com.util.QcloudSmsUtil;
import com.util.SafeUtil;
import com.util.StringUtil;
import com.util.UploadUtil;

@Namespace("/index")
@Results({
	@Result(name="index",location="/index/index.jsp"),
	@Result(name="login",location="/index/login.jsp"),
	@Result(name="register",location="/index/register.jsp"),
	@Result(name="user",location="/index/user.jsp"),
	@Result(name="newproject",location="/index/newproject.jsp"),
	@Result(name="findpw",location="/index/findpw.jsp"),
	@Result(name="refindpw",type="redirect",location="findpw.action?flag=-1"),
})
@SuppressWarnings("serial")
public class UserAction extends BaseAction{
	
	/**********************************Data**********************************/
	//页面内容
	private int flag; // 页面标记
	private User user; // 用户
	
	private String userid;//存放邀请对象名
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	private String username;//存放邀请对象名
	private String userphone;//存放邀请对象电话
	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	private Project project; // 项目
	private String orgName;//项目负责人名称
	private int pid;//存放项目id
	private String phone;//注册-手机验证
	
	//密码操作
	private static String code;//****存放验证码，无get、set方法
	private String checkcode;//用户提交的验证码
	private String newpassword;//存放新密码
	//上传图片
	private File img; // 获取上传图片
	private String imgFileName; // 获取上传图片名称
	//上传文件
	private File plan; // 获取上传文件
	private String planFileName; // 获取上传文件名称
	//集合
	private List<Project> projectList;//项目集合
	private List<Publicity> publicityList;//宣传栏集合
	private List<Type> typeList; // 类型集合
	private List<Province> provinceList; // 省份集合
	private List<Session> sessionList;//会话集合
	private List<News> newsList;//消息集合
	//private List<String> membersList;//项目成员列
	private List<Mem_U> memList;//项目成员列
	//服务
	@Resource
	private UserService userService;//用户服务
	@Resource
	private ProjectService projectService;//项目服务
	@Resource
	private TypeService typeService; // 类型服务
	@Resource
	private ProvinceService provinceService; // 省份服务
	@Resource
	private PublicityService publicityService;//宣传栏服务
	@Resource
	private SessionService sessionService;//会话服务
	
	/*********************************Action*********************************/

	/**
	 * 用户注册
	 * @return
	 */
	@Action("register")
	public String register() {
		if(flag==-1) {
			flag = 5; // 注册页面
			return "register";
		}
//		System.out.println(checkcode);
		if(checkcode.equals(code)) {
			String password = user.getPassword();
			userService.add(user);//写入数据库
			user.setPassword(password);//自动登录填写密码（转码后）
			return userlogin(); // 注册成功后转去登录
		}else {
			getServletRequest().setAttribute("message", "验证码错误");//返回错误
//			getSession().put(USER, user);//保存登录状态
			return "register";
		}
	}
	
	/**
	 * 检查手机号是否已存在
	 */
	@Action("checkExist")
	public void checkExist() {
		try {
			//得到httpservlet里的response
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(userService.isExist(user.getPhone())) {
				out.print("{\"meg\":\"true\"}");
			}else {
				out.print("{\"meg\":\"false\"}");
			}
			out.close();//关闭资源
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送短信验证码
	 */
	@Action("recode")
	public void recode() {
		try {
			//得到httpservlet里的response
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
//			System.out.println("phone="+user.getPhone());
//			out.print("{\"meg\":\"success\",\"phone\":\""+user.getPhone()+"\"}");
			code = CodeUtil.wordCode(4);
			System.out.println("UserAction:短信验证码:"+code);//测试
			QcloudSmsUtil.Send(code+"", "86", user.getPhone());
			out.print("{\"meg\":\"success\"}");
			out.close();//关闭资源
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 用户登录
	 * @return
	 */
	@Action("userlogin")
	public String userlogin() {
		publicityList = publicityService.getList();//加载主页内容
		if(checkLogin() == null) {//判断是否有登录状态
			if(userService.checkUser(user.getPhone(), user.getPassword())){
				//System.out.println("UserAction:登录成功");//测试
				getSession().put(USER, userService.get(user.getPhone()));//保存登录状态
				return "index";
			} else {
				getServletRequest().setAttribute("message", "用户名或密码错误!");//返回错误
				return "login";
			}
		}else {
			getServletRequest().setAttribute("message", "请先注销当前已登陆用户");//弹窗提示
			return "index";
		}
	}
	
	/**
	 * 用户注销
	 * @return
	 */
	@Action("userlogout")
	public String userlogout() {
		publicityList = publicityService.getList();//加载主页内容
		getSession().remove(USER);
		return "index";
	}
	
	/**
	 * 找回密码
	 * @return
	 */
	@Action("findpw")
	public String findpw() {
		//System.out.println("UserAction测试:findpw-flag="+flag);//测试
		if(flag==-1) {
			flag = 0;
			return "findpw";
		}else if(flag==1) {
			//提交手机号
			if(userService.isExist(user.getPhone())) {
				return "findpw";
			}else {
				flag = 0;
				getServletRequest().setAttribute("message", "用户不存在");//弹窗提示
				return "findpw";
			}
		}else if(flag==2) {
			//选择短信验证
			getServletRequest().setAttribute("message", "已发送至手机");//弹窗提示
			code = CodeUtil.wordCode(4);
			QcloudSmsUtil.Send(code, "86", user.getPhone());
			System.out.println("UserAction:短信验证码:"+code);//测试
			return "findpw";
		}else if(flag==3) {
			//选择邮箱验证
			String tit="邮件验证";
			code = CodeUtil.wordCode(4);
			String content="您的用户正在找回密码，验证码为"+code+",若不是本人操作请忽略";
			String email = userService.get(user.getPhone()).getEmail();
			try {
				InternetAddress[] internetAddress = new InternetAddress[] {
						new InternetAddress(email)};//接受邮箱
				MailUtil.sendMail(internetAddress,tit,content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = 1;
				getServletRequest().setAttribute("message", "发送失败");//弹窗提示
				return "findpw";
			}
			getServletRequest().setAttribute("message", "已发送至邮箱"+email);//弹窗提示
			System.out.println("UserAction:邮箱验证码:"+code);//测试
			
			return "findpw";
		}else if(flag==4) {
			//提交短信验证码
//			System.out.println("checkcode="+checkcode+",code="+code);
			if(!checkcode.equals(code)) {
				getServletRequest().setAttribute("message", "验证码错误");//弹窗提示
				flag=2;
			}else getServletRequest().setAttribute("message", "验证码成功");//弹窗提示
			return "findpw";
		}else if(flag==5) {
			//提交邮箱验证码
			if(!checkcode.equals(code)) {
				getServletRequest().setAttribute("message", "验证码错误");//弹窗提示
				flag=3;
			}else getServletRequest().setAttribute("message", "验证码成功");//弹窗提示
			return "findpw";
		}else if(flag==123) {
			//提交密码重置
//			System.out.println("UserAction测试:findpw-user.phone="+user.getPhone()+",user.password="+user.getPassword());//测试
			User reUser = userService.get(user.getPhone());// 获取数据库中用户信息
			reUser.setPassword(SafeUtil.encode(user.getPassword()));// 整合转码新密码
			userService.update(reUser);//更新信息
			getServletRequest().setAttribute("message", "修改成功，请登录");// 弹窗提示
		}
		else {
			//错误操作
			getServletRequest().setAttribute("message", "error:请重新提交");// 弹窗提示
			return "findpw";
		}
		publicityList = publicityService.getList();//加载主页内容
		return "index";
	}
	
	/**
	 * 用户主页
	 * @return
	 */
	@Action("user")
	public String user() {//检查登录状态
		//System.out.println("UserAction:useriID = " + ((User)getSession().get("user")).getId());
		return project();
	}
	
	/**
	 * 查看自己的项目
	 * @return
	 */
	@Action("project")
	public String project() {
		//检查登录状态
		if(checkLogin() == USER && ((User)getSession().get(USER)).getId() != 0) {
			//导入信息
			user = userService.get(((User)getSession().get(USER)).getId());//获取信息
			if(projectService.isExist(user.getProject())) {
				project = projectService.get(user.getProject());//获取项目内容
				//System.out.println("UserAction:project:project.id="+project.getId());
//				membersList = project.getMembersName();//获取成员数列
				orgName = StringUtil.getName(project.getOrginators());
				if(!StringUtil.isNull(project.getMembers())){
					List<Integer> memIdList = StringUtil.getIdArray(project.getMembers());
					memList = new ArrayList<Mem_U>();
					for(int id : memIdList) {
						memList.add(new Mem_U(userService.get(id)));
					}
				}	
			}
			flag = 0;
			return "user";
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
	}
	
	/**
	 * 创建项目
	 * @return
	 */
	@Action("newproject")
	public String newproject() {
		if(checkLogin()==USER && ((User)getSession().get(USER)).getId() != 0) {//检查登录状态
			typeList = typeService.getList();
			provinceList = provinceService.getList();
			if(flag==-1) {
				flag = 8; //项目申请页面
				return "newproject";
			}
			if(projectService.isExist(project.getName())) {
				getServletRequest().setAttribute("message", "项目名已被占用");
				return "newproject";
			}else{
				user = userService.get(((User)getSession().get("user")).getId());//获取用户信息
			    //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//设置格式
			    project.setDate(DateUtil.YMD.format(new Date()));//保存日期
			    project.setOrginators(user.getName()+"@"+user.getId());
				project.setAppraise("待审核");
				project.setLogo(UploadUtil.fileUpload(img, imgFileName, "file/logo", project.getName()));
				project.setPlan(UploadUtil.fileUpload(plan, planFileName, "file/plan", project.getName()));
				projectService.add(project);//项目写入数据库
				project = projectService.get(project.getName());//获取项目id
				user.setProject(project.getId());//写入用户关联
				userService.update(user);//更新用户信息
				getSession().put(USER, user);//保存登录状态
			}
			return user(); // 转会用户界面
		}else {
			getServletRequest().setAttribute("message", "error:请重新登陆");//弹窗提示
			publicityList = publicityService.getList();//加载主页内容
			return "index";
		}
	}

	/**
	 * 查看消息
	 * @return
	 */
	@Action("news")
	public String news() throws ParseException {
		//检查登录状态
		if(checkLogin() == USER && ((User)getSession().get(USER)).getId() != 0) {
			user = userService.get(((User)getSession().get(USER)).getId());//获取信息
			if(!StringUtil.isNull(user.getNews())) {
				sessionList = sessionService.getList(user.getNews());
			}
			//user = (User)getSession().get("user");
			flag=1;
			return "user";
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
	}
	
	/**
	 * 发送成员邀请
	 * @return
	 * @throws ParseException
	 */
	@Action("invite")
	public String invite() throws ParseException{
		//检查登录状态
		if(checkLogin() == USER && ((User)getSession().get(USER)).getId() != 0) {
			//判断邀请者是否已注册账号
			if(userService.isExist(userphone)) {
				User get = userService.get(userphone);//获取接受者信息
				if(!get.getPhone().equals(userphone)) {
					flag=0;
					getServletRequest().setAttribute("message", "输入信息不正确");//弹窗提示
					return user();
				}
				flag=1;
				//获取对话者用户信息及用户记录
				user = userService.get(((User)getSession().get(USER)).getId());//获取发送者信息
				project = projectService.get(user.getProject());
				//判断是否已是项目成员
				if(!StringUtil.isNull(project.getMembers())) {
					List<String> members = project.getMembersName();
					for(String str : members) {
						if(str.equals(username)) {
							getServletRequest().setAttribute("message", "对方已是项目成员");//弹窗提示
							flag=0;
							return user();
						}
					}
				}
				//判断是否已经邀请
				if(sessionService.isSend(user, get, project.getName()+"</a>项目负责人"+user.getName()+"邀请您成为该项目团队成员。")) {
					getServletRequest().setAttribute("message", "请勿重复邀请，请等待对方接受");//弹窗提示
					return news();
				}
				//准备会话
				//System.out.println("UserAction:invite:准备会话");
				String inviteValue = project.getName() + "项目负责人" + user.getName() 
					+ "邀请您成为该项目团队成员。(待接受)";//生成消息语句
				//接收者内容
				String getValue = "<a href='loadproject.action?pid="+project.getId()+"'>"
					+ project.getName() + "</a>项目负责人" + user.getName()
					+ "邀请您成为该项目团队成员。(待    <a href='accept.action?userid="+user.getId()
					+"'>接受</a>)";//生成消息语句
				user.setNews(sessionService.sendNews(user, get, inviteValue));//生成发送者记录
				//System.out.println("!!!!!!!!!!!UserAction:invite:user.getNews="+user.getNews());
				get.setNews(sessionService.getNews(user, get, getValue));//生成接收者信息
				//保存
				//System.out.println("UserAction:invite:保存");
				userService.update(user);//保存发送者记录
				userService.update(get);//保存接收者记录
				//更新页面信息
				//System.out.println("UserAction:invite:更新页面信息");
				//sessionList = sessionService.getList(user.getNews());
				getServletRequest().setAttribute("message", "已发送邀请");//弹窗提示
			}else {
				flag=0;
				getServletRequest().setAttribute("message", "该手机号味注册服务网账号");//弹窗提示
				return user();
			}
			return news();
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
	}
	
	/**
	 * 接受邀请
	 * @return
	 * @throws ParseException
	 */
	@Action("accept")
	public String accept() throws ParseException {
		//检查登录状态
		if(checkLogin() == USER && ((User)getSession().get(USER)).getId() != 0) {	
			flag=1;
			user = userService.get(((User)getSession().get(USER)).getId());//获取用户信息
			if(!StringUtil.isNull(userid)) {//检查数据
				User invite = userService.get(Integer.valueOf(userid).intValue());//获取邀请者信息
				project = projectService.get(invite.getProject());//获取项目信息
				if(invite.getProject()!= 0 && projectService.isExist(invite.getProject())) {//检查项目数据
					//添加成员信息
					project = projectService.get(invite.getProject());//获取项目对象
					project.addMembers(user.getId(), user.getName());//添加成员信息
					projectService.update(project);//更新数据库信息
					//旧内容
					String userValue = project.getName() + "</a>项目负责人" + invite.getName() + "邀请您成为该项目团队成员。";
					String inviteValue = project.getName() + "项目负责人" + invite.getName() + "邀请您成为该项目团队成员。";
					//新内容
					String newUserValue = "<a href='loadproject.action?pid="+project.getId()+"'>"
							+ project.getName() + "</a>项目负责人"+user.getName()
							+"邀请您成为该项目团队成员。(已接受)";//生成消息语句
					String newInviteValue = project.getName() + "项目负责人"+user.getName()
							+"邀请您成为该项目团队成员。(已接受)";//生成消息语句
					//更新会话内容
					user.setNews(sessionService.updateSessionList(user,invite,userValue,newUserValue));
					invite.setNews(sessionService.updateSessionList(invite,user,inviteValue,newInviteValue));
					userService.update(user);
					userService.update(invite);
				}
				getServletRequest().setAttribute("message", "成功加入该项目");//弹窗提示
			}else {
				getServletRequest().setAttribute("message", "该邀请已过期");//弹窗提示
			}
			return news();
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
	}
	
	/**
	 * 查看信息
	 * @return
	 */
	@Action("set")
	public String set() {
		//System.out.println("UserAction:set:user.id="+((User)getSession().get("user")).getId());
		//检查登录状态
		if(checkLogin() == USER && ((User)getSession().get(USER)).getId() != 0) {
			user = userService.get(((User)getSession().get(USER)).getId());//获取信息
			flag=2;
			return "user";
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
	}
	
	/**
	 * 更换头像
	 * @return
	 */
	@Action("userimg")
	public String userimg() {
		//检查登录状态
		if(checkLogin() == USER && ((User)getSession().get(USER)).getId() != 0) {
			user = userService.get(((User)getSession().get(USER)).getId());//获取信息
			user.setPhoto(UploadUtil.fileUpload(img, imgFileName, "file/photo", "user_"+user.getId()));
			userService.update(user);
			getSession().put(USER, user);//保存登录状态
			getServletRequest().setAttribute("message", "更换成功");//弹窗提示
			flag=2;
			return set();
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
	}
	
	/**
	 * 修改用户密码
	 * @return
	 */
	@Action("rePassword")
	public String rePassword() {
		//检查登录状态
		if(checkLogin() == USER && ((User)getSession().get(USER)).getId() != 0) {
			User reuser = userService.get(((User)getSession().get(USER)).getId());//获取信息
			//判断输入的旧密码是否正确
			if(userService.checkUser(reuser.getPhone(), user.getPassword())) {
				reuser.setPassword(SafeUtil.encode(newpassword));
				userService.update(reuser);
				getSession().put(USER, reuser);//保存登录状态
				getServletRequest().setAttribute("message", "修改成功");//弹窗提示
				flag=2;
			}else {
				getServletRequest().setAttribute("message", "原密码错误");//弹窗提示
				flag=2;
			}
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
		return set();
	}
	
	/**
	 * 修改用户邮箱
	 * @return
	 */
	@Action("reEmail")
	public String reEmail() {
		//检查登录状态
		if(checkLogin() == USER && ((User)getSession().get(USER)).getId() != 0) {
			User reuser = userService.get(((User)getSession().get(USER)).getId());//获取信息
			if(!StringUtil.isNull(user.getEmail())) {
				if(checkcode.equals(code)){
					reuser.setEmail(user.getEmail());
					userService.update(reuser);
					getSession().put(USER, reuser);//保存登录状态
					getServletRequest().setAttribute("message", "修改成功");//弹窗提
					flag=2;
				}else getServletRequest().setAttribute("message", "验证码错误");//弹窗提示
			}
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
		return set();
	}
	
	@Action("uEmailcode")
	public void uEmailcode() {
		try {
			//得到httpservlet里的response
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			code = CodeUtil.wordCode(4);
			System.out.println("EntrepreneurAction:邮箱验证码:"+code);//测试
			//选择邮箱验证
			String tit="邮件验证";
			code = CodeUtil.wordCode(4);
			String content="您的用户正在更改邮箱，验证码为"+code+",若不是本人操作请忽略";
			String email = user.getEmail();
			try {
				InternetAddress[] internetAddress = new InternetAddress[] {
						new InternetAddress(email)};//接受邮箱
				MailUtil.sendMail(internetAddress,tit,content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = 1;
				getServletRequest().setAttribute("message", "发送失败");//弹窗提示
			}
			//QcloudSmsUtil.Send(code+"", "86", user.getPhone());
			
			out.print("{\"meg\":\"success\"}");
			out.close();//关闭资源
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改用户信息
	 * @return
	 */
	@Action("userReset")
	public String userReset() {
		//System.out.println("UserAction:userReset:user.id="+((User)getSession().get("user")).getId());
		//检查登录状态
		if(checkLogin() == USER && ((User)getSession().get(USER)).getId() != 0) {
			User reuser = userService.get(((User)getSession().get(USER)).getId());//获取信息
			//修改
			reuser.setEducation(user.getEducation());
			reuser.setSchool(user.getSchool());
			reuser.setAdmission(user.getAdmission());
			reuser.setGraduation(user.getGraduation());
			reuser.setSpecialty(user.getSpecialty());
			user = reuser;//输入信息
			getSession().put(USER, user);//保存登录状态
			userService.update(reuser);
			flag=2;
			return set();
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
	}

	/*********************************GetSet*********************************/
	
	public int getFlag() {
		return flag;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static String getCode() {
		return code;
	}

	public static void setCode(String code) {
		UserAction.code = code;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public File getPlan() {
		return plan;
	}

	public void setPlan(File plan) {
		this.plan = plan;
	}

	public String getPlanFileName() {
		return planFileName;
	}

	public void setPlanFileName(String planFileName) {
		this.planFileName = planFileName;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public List<Publicity> getPublicityList() {
		return publicityList;
	}

	public void setPublicityList(List<Publicity> publicityList) {
		this.publicityList = publicityList;
	}

	public List<Type> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Type> typeList) {
		this.typeList = typeList;
	}

	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	public List<Session> getSessionList() {
		return sessionList;
	}

	public void setSessionList(List<Session> sessionList) {
		this.sessionList = sessionList;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
	

	public List<Mem_U> getMemList() {
		return memList;
	}

	public void setMemList(List<Mem_U> memList) {
		this.memList = memList;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public TypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

	public ProvinceService getProvinceService() {
		return provinceService;
	}

	public void setProvinceService(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}

	public PublicityService getPublicityService() {
		return publicityService;
	}

	public void setPublicityService(PublicityService publicityService) {
		this.publicityService = publicityService;
	}

	public SessionService getSessionService() {
		return sessionService;
	}

	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

}

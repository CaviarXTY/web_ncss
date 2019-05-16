package com.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.Enterprise;
import com.entity.Entrepreneur;
import com.entity.Project;
import com.entity.Province;
import com.entity.Publicity;
import com.entity.Session;
import com.entity.Type;
import com.model.Pro_Er;
import com.service.EnterpriseService;
import com.service.EntrepreneurService;
import com.service.ProjectService;
import com.service.ProvinceService;
import com.service.PublicityService;
import com.service.SessionService;
import com.service.TypeService;
import com.util.CodeUtil;
import com.util.MailUtil;
import com.util.QcloudSmsUtil;
import com.util.SafeUtil;
import com.util.StringUtil;
import com.util.UploadUtil;

@Namespace("/index")
@Results({
	@Result(name="success",location="/index/successTest.jsp"),
	@Result(name="index",location="/index/index.jsp"),
	@Result(name="reindex",type="redirect",location="index.action"),
	@Result(name="login",location="/index/login.jsp"),
	@Result(name="findpw",location="/index/findpw.jsp"),
	@Result(name="en",location="/index/entrepreneur.jsp"),
	@Result(name="reSearch",type="redirect",location="infPro.action?id=${project.id}")
})
@SuppressWarnings("serial")//忽略可序列化的类上缺少 serialVersionUID 定义时的警告
public class EntrepreneurAction extends BaseAction{
	
	/**********************************Data**********************************/
	
	//页面内容
	private int flag; // 页面标记
	private Entrepreneur entrepreneur;//企业家数据
	private Enterprise en;//企业数据
	private Type type;//类型
	private Province province;//省份
	private int pid;//项目id
	private Project project;//
	//密码操作
	private static String code;//****存放验证码，无get、set方法
	private String checkcode;//用户提交的验证码
	private String newpassword;//存放新密码
	//上传图片
	private File img; // 获取上传图片
	private String imgFileName; // 获取上传图片名称
	//集合
	private List<Publicity> publicityList;//宣传栏集合
	private List<Pro_Er> proList;//项目集合
	private List<String> projectNameList;//关注项目集合
	private List<Session> sessionList;//会话集合
	private List<Type> typeList;	// 类型列
	private List<Province> provinceList;	// 省份列
	//服务
	@Resource
	private EntrepreneurService entrepreneurService;//企业家服务
	@Resource
	private EnterpriseService enService;//企业服务
	@Resource
	private PublicityService publicityService;//宣传栏服务
	@Resource
	private SessionService sessionService;//会话服务
	@Resource
	private TypeService typeService;	// 类型服务
	@Resource
	private ProvinceService provinceService;	// 省份服务
	@Resource
	private ProjectService projectService;	// 项目服务
	
	
	/*********************************Action*********************************/
	
	/**
	 * 登录
	 * @return
	 */
	@Action("enlogin")
	public String enlogin(){
		//System.out.println(entrepreneur.getUsername()+":"+entrepreneur.getPassword());
		publicityList = publicityService.getList();//加载主页内容
		if(checkLogin() == null) {//判断是否有登录状态
			if(entrepreneurService.verifyEntrepreneurs(entrepreneur.getUsername(), entrepreneur.getPassword())){
				//System.out.println("登录成功");
				getSession().put(ENP, entrepreneurService.get(entrepreneur.getUsername()));
				return "index";
			} else {
				getServletRequest().setAttribute("message", "账号或密码错误!");
				return "login";
			}
		}else {
			getServletRequest().setAttribute("message", "请先注销当前已登陆用户");//弹窗提示
			publicityList = publicityService.getList();//加载主页内容
			return "index";
		}
	}
	
	/**
	 * 注销
	 * @return
	 */
	@Action("enlogout")
	public String enlogout() {
		getSession().remove(ENP);
		publicityList = publicityService.getList();//加载主页内容
		return "index";
	}
	
	/**
	 * 找回密码
	 * @return
	 */
	@Action("enfindpw")
	public String enfindpw() {
		{
			//System.out.println("UserAction测试:findpw-flag="+flag);//测试
			if(flag==-2) {
				flag = 8;
				return "findpw";
			}else if(flag==6) {
				//提交账号
				if(entrepreneurService.isExist(entrepreneur.getUsername())) {
					//发送邮箱验证码
					getServletRequest().setAttribute("message", "已发送至邮箱");//弹窗提示
					code = CodeUtil.wordCode(4);
					System.out.println("Entrepreneurn:邮箱验证码:"+code);//测试
					return "findpw";
				}else {
					flag = 0;
					getServletRequest().setAttribute("message", "企业用户不存在");//弹窗提示
					return "findpw";
				}
			}else if(flag==7) {
				//提交邮箱验证码
				if(!checkcode.equals(code)) {
					getServletRequest().setAttribute("message", "验证码错误");//弹窗提示
					flag=6;
				}
				return "findpw";
			}else if(flag==456) {
				//提交密码重置
				System.out.println("EnAction测试:findpw-en.username="+entrepreneur.getUsername()
						+",en.password="+entrepreneur.getPassword());//测试
				Entrepreneur reEntrepreneur = entrepreneurService.get(entrepreneur.getUsername());// 获取数据库中用户信息
				reEntrepreneur.setPassword(SafeUtil.encode(entrepreneur.getPassword()));// 整合转码新密码
				entrepreneurService.update(reEntrepreneur);//更新信息
				getServletRequest().setAttribute("message", "修改成功，请登录");// 弹窗提示
			}else {
				//错误操作
				getServletRequest().setAttribute("message", "error:请重新提交");// 弹窗提示
				return "findpw";
			}
			publicityList = publicityService.getList();//加载主页内容
			return "index";
		}
	}
	
	/**
	 * 企业主页
	 * @return
	 */
	@Action("entrepreneur")
	public String entrepreneur() {
		return enproject();
	}
	
	/**
	 * 查看关注的项目
	 * @return
	 */
	@Action("enproject")
	public String enproject() {
		//检查登录状态
		if(checkLogin() == ENP && ((Entrepreneur)getSession().get(ENP)).getId() != 0) {
			entrepreneur = entrepreneurService.get(((Entrepreneur)getSession().get(ENP)).getId());
			//projectNameList = StringUtil.getNameArray(entrepreneur.getFocus());
			if(!StringUtil.isNull(entrepreneur.getFocus())) {
				List<Integer> proIdList = StringUtil.getIdArray(entrepreneur.getFocus());
				proList = new ArrayList<Pro_Er>();
				for(int id : proIdList) {
					proList.add(new Pro_Er(projectService.get(id)));
				}
			}
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
		flag = 0;
		return "en";
	}
	
	/**
	 * 添加项目关注
	 * @return
	 */
	@Action("enfocus")
	public String enfocus() {
		if(checkLogin() == ENP && ((Entrepreneur)getSession().get(ENP)).getId() != 0) {
			entrepreneur = entrepreneurService.get(((Entrepreneur)getSession().get(ENP)).getId());
			//该项目id是否有效
			if(projectService.isExist(pid)) {
				project = projectService.get(pid);
				if(!StringUtil.isNull(entrepreneur.getFocus())) {//判断是否为空
					//判断是否已关注
					projectNameList = StringUtil.getNameArray(entrepreneur.getFocus());
					if(projectNameList.size()>0) {
						//判断是否已关注
						for(String str : projectNameList) {
							if(str.equals(project.getName())) {
								getServletRequest().setAttribute("message", "已关注该项目");//弹窗提示
								return "reSearch";
							}
						}
					}	
				}
				entrepreneur.setFocus(entrepreneur.getFocus() + project.getName() + "@" + project.getId() + ";");//存放关注信息;
				entrepreneurService.update(entrepreneur);//更新数据库
				getSession().put(ENP,entrepreneur);//更新信息
			}else{
				getServletRequest().setAttribute("message", "项目已失效");//弹窗提示
			}
		}else{
			getServletRequest().setAttribute("message", "请先登陆");//弹窗提示
			return "login";
		}	
		return "reSearch";
	}
	
	/**
	 * 查看消息
	 * @return
	 */
	@Action("ennews")
	public String ennews() throws ParseException {
		//检查登录状态
		if(checkLogin() == ENP && ((Entrepreneur)getSession().get(ENP)).getId() != 0) {
			entrepreneur = entrepreneurService.get(((Entrepreneur)getSession().get(ENP)).getId());
			if(!StringUtil.isNull(entrepreneur.getNews())) {
				sessionList = sessionService.getList(entrepreneur.getNews());
			}
			flag=1;
			return "en";
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
	}
	
	/**
	 * 查看信息
	 * @return
	 */
	@Action("enset")
	public String enset() {
		//检查登录状态
		if(checkLogin() == ENP && ((Entrepreneur)getSession().get(ENP)).getId() != 0) {
			entrepreneur = entrepreneurService.get(((Entrepreneur)getSession().get(ENP)).getId());//获取企业家
			en = enService.get(entrepreneur.getEntreprise());//获取企业数据
			//获取省、类型列表
			typeList = typeService.getList();
			provinceList = provinceService.getList();
			if(en != null) {//有企业数据数据时
				type = typeList.get(en.getType());//获取类型名
				province = provinceList.get(en.getProvince());//获取省名
			}
			flag=2;
			return "en";
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
	}
	
	/**
	 * 更换头像
	 * @return
	 */
	@Action("enimg")
	public String enimg() {
		//检查登录状态
		if(checkLogin() == ENP && ((Entrepreneur)getSession().get(ENP)).getId() != 0) {
			//获取
			entrepreneur = entrepreneurService.get(((Entrepreneur)getSession().get(ENP)).getId());
			en = enService.get(entrepreneur.getId());
			//数据
			entrepreneur.setPhoto(UploadUtil.fileUpload(img, imgFileName, "file/photo", "en_"+entrepreneur.getId()));
			en.setPhoto(entrepreneur.getPhoto());
			//更新
			entrepreneurService.update(entrepreneur);
			enService.update(en);
			//显示最新头像
			getSession().put(ENP,entrepreneur);
			getServletRequest().setAttribute("message", "更换成功");//弹窗提示
			flag=2;
			return enset();
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
	}
	
	/**
	 * 更换密码
	 * @return
	 */
	@Action("enRePw")
	public String enRePw() {
		//检查登录状态
		if(checkLogin() == ENP && ((Entrepreneur)getSession().get(ENP)).getId() != 0) {
			flag=2;
			//检查登录状态
			Entrepreneur reEntrepreneur = entrepreneurService.get(((Entrepreneur)getSession().get(ENP)).getId());
			//判断输入的旧密码是否正确
			if(entrepreneurService.verifyEntrepreneurs(reEntrepreneur.getUsername(), entrepreneur.getPassword())) {
				reEntrepreneur.setPassword(SafeUtil.encode(newpassword));
				entrepreneurService.update(reEntrepreneur);
				getSession().put(ENP, reEntrepreneur);//保存登录状态
				getServletRequest().setAttribute("message", "修改成功");//弹窗提示
			}else {
				getServletRequest().setAttribute("message", "原密码错误");//弹窗提示
			}
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
		return enset();
	}

	/**
	 * 修改邮箱
	 * @return
	 */
	@Action("enEmail")
	public String enEmail() {
		//检查登录状态
		if(checkLogin() == ENP && ((Entrepreneur)getSession().get(ENP)).getId() != 0) {
			Entrepreneur reEntrepreneur = entrepreneurService.get(((Entrepreneur)getSession().get(ENP)).getId());
			if(!StringUtil.isNull(entrepreneur.getEmail())&&!StringUtil.isNull(entrepreneur.getPassword())) {
				if(checkcode.equals(code)){
					reEntrepreneur.setEmail(entrepreneur.getEmail());
					entrepreneurService.update(reEntrepreneur);
					getSession().put(ENP, reEntrepreneur);//保存登录状态
					getServletRequest().setAttribute("message", "修改成功");//弹窗提示
				}else getServletRequest().setAttribute("message", "验证码错误");//弹窗提示
			}
			flag=2;
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
		return enset();
		//1148427828@qq.com
	}
	
	/**
	 * 发送验证码
	 */
	@Action("enEmailcode")
	public void enEmailcode() {
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
			String email = entrepreneur.getEmail();
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
		//1148427828@qq.com
	}
	
	/**
	 * 修改企业信息
	 * @return
	 */
	@Action("enReset")
	public String enReset() {
		//检查登录状态
		if(checkLogin() == ENP && ((Entrepreneur)getSession().get(ENP)).getId() != 0) {
			flag=2;
			//获取企业家数据
			entrepreneur = entrepreneurService.get(((Entrepreneur)getSession().get(ENP)).getId());
			//查询是否已有企业家数据
			if(entrepreneur.getEntreprise() != 0) {
				Enterprise reEn = enService.get(entrepreneur.getEntreprise());
				en.setId(reEn.getId());
				en.setEntrepreneurs(reEn.getEntrepreneurs());
				en.setInvest(reEn.getInvest());
				//更新已有数据
				enService.update(en);
			}else {
				//创建新企业
				if(!enService.isExist(en.getName())) {//查询企业名是否存在
					//添加数据入数据库
					en.setEntrepreneurs(entrepreneur.getName());
					enService.add(en);
					//获取新企业id
					en = enService.get(en.getName());
					//更新企业家关联
					entrepreneur.setEntreprise(en.getId());//修改
					entrepreneurService.update(entrepreneur);//写入数据
					getSession().put(ENP, entrepreneur);//保存登录状态
					getServletRequest().setAttribute("message", "修改成功");//弹窗提示
				}else {
					getServletRequest().setAttribute("message", "该企业名已被占用");//弹窗提示
				}
			}
		}else{
			getServletRequest().setAttribute("message", "登录超时，请重新登陆");//弹窗提示
			return "login";
		}
		return enset();
	}

	
	/*********************************GetSet*********************************/
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Entrepreneur getEntrepreneur() {
		return entrepreneur;
	}
	public void setEntrepreneur(Entrepreneur entrepreneur) {
		this.entrepreneur = entrepreneur;
	}
	public Enterprise getEn() {
		return en;
	}
	public void setEn(Enterprise en) {
		this.en = en;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
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
	public List<Publicity> getPublicityList() {
		return publicityList;
	}
	public void setPublicityList(List<Publicity> publicityList) {
		this.publicityList = publicityList;
	}
	public List<String> getProjectNameList() {
		return projectNameList;
	}
	public void setProjectNameList(List<String> projectNameList) {
		this.projectNameList = projectNameList;
	}
	public List<Session> getSessionList() {
		return sessionList;
	}
	public void setSessionList(List<Session> sessionList) {
		this.sessionList = sessionList;
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
	public EntrepreneurService getEntrepreneurService() {
		return entrepreneurService;
	}
	public void setEntrepreneurService(EntrepreneurService entrepreneurService) {
		this.entrepreneurService = entrepreneurService;
	}
	public EnterpriseService getEnService() {
		return enService;
	}
	public void setEnService(EnterpriseService enService) {
		this.enService = enService;
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
	public ProjectService getProjectService() {
		return projectService;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public static String getCode() {
		return code;
	}

	public static void setCode(String code) {
		EntrepreneurAction.code = code;
	}

	public List<Pro_Er> getProList() {
		return proList;
	}

	public void setProList(List<Pro_Er> proList) {
		this.proList = proList;
	}
}

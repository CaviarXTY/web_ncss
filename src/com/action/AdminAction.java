package com.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.entity.Admin;
import com.entity.Enterprise;
import com.entity.Entrepreneur;
import com.entity.Information;
import com.entity.Project;
import com.entity.Province;
import com.entity.Publicity;
import com.entity.Type;
import com.entity.User;

import com.service.AdminService;
import com.service.EnterpriseService;
import com.service.EntrepreneurService;
import com.service.InformationService;
import com.service.ProjectService;
import com.service.ProvinceService;
import com.service.PublicityService;
import com.service.TypeService;
import com.service.UserService;
import com.util.PageUtil;
import com.util.StringUtil;
import com.util.UploadUtil;

@Namespace("/admin")
@Results({
	@Result(name="admin",location="/admin/admin.jsp"),
	@Result(name="login",location="/admin/login.jsp"),
	@Result(name="list",location="/admin/list.jsp"),
	
	@Result(name="adminAdd",location="/admin/add_admin.jsp"),
	@Result(name="enAdd",location="/admin/add_en.jsp"),
	@Result(name="erAdd",location="/admin/add_er.jsp"),
	@Result(name="infAdd",location="/admin/add_inf.jsp"),

})
@SuppressWarnings("serial")//忽略可序列化的类上缺少 serialVersionUID 定义时的警告
public class AdminAction extends BaseAction{
	
	
	/**********************************Data**********************************/
	
	private static final int rows = 10;//列表行数
	private static final int AAM = 1;
	private static final int EAM = 2;
	private static final int IAM = 3;
	private static final int PAM = 4;
	
	private Admin admin;				//1 管理员		-AAM-0
	private Enterprise en;				//2 企业 		-EAM-1-0
	private Entrepreneur er;			//3 企业家		-EAM-1-0
	private Information inf;			//4 动态文章	-IAM-2-0
	private Publicity pub;				//5 宣传栏		-IAM-2-0
	private Project pro;				//6 项目		-PAM-3-2-1-0
	private User user;					//7 用户		-PAM-3-2-1-0
	
	private int flag;//页记录码
	private String value;//记录搜索内容
	//上传图片
	private File img; // 获取上传图片
	private String imgFileName; // 获取上传图片名称
	//集合
	private List<Admin> adminList;//管理员集合
	private List<Enterprise> enList;//企业集合
	private List<Entrepreneur> erList;//企业家集合
	private List<Information> infList;//动态文章集合
	private List<Project> proList;//项目集合
	private List<Publicity> pubList;//宣传栏集合
	private List<User> userList;//用户集合
	private List<Type> typeList; // 类型集合
	private List<Province> provinceList; // 省份集合
	
	//服务
	@Resource
	private AdminService adminService;//管理员服务
	@Resource
	private EnterpriseService enService;//企业服务
	@Resource
	private EntrepreneurService erService;//企业家服务
	@Resource
	private InformationService infService;//动态文章服务
	@Resource
	private ProjectService proService;//项目服务
	@Resource
	private PublicityService pubService;//宣传栏服务
	@Resource
	private UserService userService;//用户服务
	@Resource
	private TypeService typeService; // 类型服务
	@Resource
	private ProvinceService provinceService; // 省份服务
	
	
	/*********************************Action*********************************/
	

	/**
	 * 登录状态及权限检查
	 * @param admin
	 * @param p
	 * @return
	 */
	public boolean checkPerm(Admin admin,int p) {
		if(checkLogin()==ADM && admin.getId() != 0) {
			if(adminService.get(admin.getId()).getPermissions() == p) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 后台首页
	 * @return
	 */
	@Action("admin")
	public String admin() {
		flag=0;
		admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
		return "admin";
	}
	
	/**
	 * 管理员登录
	 * @return
	 */
	@Action("amlogin")
	public String login() {
		if(adminService.verifyAdmin(admin.getUsername(),admin.getPassword())){
			getSession().put(ADM, adminService.get(admin.getUsername()));//保存登录session
			return admin();//跳转管理主页
		}else{
			getServletRequest().setAttribute("message", "账号或密码错误!");
			return "login";
		}	
	}
	
	/**
	 * 管理员注销
	 * @return
	 */
	@Action("amlogout")
	public String logout() {
		getSession().remove(ADM);
		return "login";
	}
	
	/**
	 * 管理员列表
	 * @return
	 */
	@Action("adminList")
	public String adminList() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() == AAM) {//检查权限
				flag = 1;//管理员
				adminList = adminService.getList(page, rows);
				pageTool = PageUtil.getPageTool(servletRequest, adminService.getTotal(), page, rows);
				return "list";
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return admin();
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 管理员添加
	 * @return
	 */
	@Action("adminAdd")
	public String adminAdd() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			Admin adm = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(adm.getPermissions() == AAM) {//检查权限
				if(flag==-1) {
					flag = 1;//管理员
					provinceList = provinceService.getList();
					return "adminAdd";
				}
				flag = 1;//管理员
				if(admin!=null) {
					adminService.add(admin);//添加数据
					getServletRequest().setAttribute("message", "添加成功");
					return adminList();
				}else {
					getServletRequest().setAttribute("message", "添加失败");
					return "adminAdd";
				}
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}	
	}
	
	/**
	 * 搜索管理员
	 * @return
	 */
	@Action("adminSearch")
	public String adminSearch() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			Admin adm = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(adm.getPermissions() == AAM) {//检查权限
				flag = 1;//管理员
				if(StringUtil.isNull(value)) {
					flag = 1;//管理员
					adminList = adminService.getListByName(value,page,rows);
					pageTool = PageUtil.getPageTool(servletRequest, adminService.getTotalByName(value), page, rows);
					return "admin";
				}else {
					getServletRequest().setAttribute("message", "搜索失败");
					return adminList();
				}
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
		
	/**
	 * 修改管理员权限
	 * @return
	 */
	@Action("adminLever")
	public String adminLever() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			Admin adm = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(adm.getPermissions() == AAM) {//检查权限
				flag = 1;//管理员
				if(admin.getId()!=0 && admin.getPermissions()!=0) {
					Admin readmin = adminService.get(admin.getId());
					readmin.setPermissions(admin.getPermissions());
					adminService.update(readmin);//更新数据
					getServletRequest().setAttribute("message", "更改成功");
				}else {
					getServletRequest().setAttribute("message", "更改失败");
				}
				return adminList();
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}	
	}
	
	/**
	 * 删除管理员
	 * @return
	 */
	@Action("adminDel")
 	public String adminDel() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			Admin adm = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(adm.getPermissions() == AAM) {//检查权限
				flag = 1;//管理员
				if(admin.getId()!=0) {
					adminService.delete(admin);
					getServletRequest().setAttribute("message", "删除成功");
				}else {
					getServletRequest().setAttribute("message", "删除失败");
				}
				return adminList();
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 更新管理员信息
	 * @return
	 */
	@Action("adminSet")
	public String adminSet() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			flag = 1;//管理员
			if(admin!=null) {
				adminService.update(admin);//更新数据
				getServletRequest().setAttribute("message", "更改成功");
			}else {
				getServletRequest().setAttribute("message", "更改失败");
			}
			return "admin";
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}	
	}
	
	/**
	 * 更换管理员头像
	 * @return
	 */
	@Action("adminImg")
	public String adminImg() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			if(admin!=null) {
				admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
				admin.setPhoto(UploadUtil.fileUpload(img, imgFileName, "file/photo", "admin_"+admin.getId()));
				adminService.update(admin);//更新数据
				getServletRequest().setAttribute("message", "更改成功");
			}else {
				getServletRequest().setAttribute("message", "更改失败");
			}
			return "admin";
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 企业列表
	 * @return
	 */
	@Action("enList")
	public String enList() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= EAM) {//检查权限
				flag = 2;//企业
				enList = enService.getList(page,rows);
				pageTool = PageUtil.getPageTool(servletRequest, enService.getTotal(), page, rows);
				return "list";
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 企业添加
	 * @return
	 */
	@Action("enAdd")
	public String enAdd() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= EAM) {//检查权限
				if(flag==-1) {
					flag = 2;//企业
					typeList = typeService.getList();// 类型集合
					provinceList = provinceService.getList();// 省份集合
					return "enAdd";
				}
				flag = 2;//企业
				if(en!=null) {
					enService.add(en);//添加数据
					getServletRequest().setAttribute("message", "添加成功");
					return enList();
				}else {
					getServletRequest().setAttribute("message", "添加失败");
					return "enAdd";
				}
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 企业删除
	 * @return
	 */
	@Action("enDel")
	public String enDel() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= EAM) {//检查权限
				flag = 2;//企业
				if(en.getId()!=0) {
					enService.delete(en);
					getServletRequest().setAttribute("message", "删除成功");
				}else {
					getServletRequest().setAttribute("message", "删除失败");
				}
				return enList();
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
		
	/**
	 * 更新企业信息
	 * @return
	 */
	@Action("enSet")
	public String enSet() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= EAM) {//检查权限
				if(flag==-1) {
					flag = 2;//企业
					return "enSet";
				}
				flag = 2;//企业
				if(en!=null) {
					enService.update(en);//更新数据
					getServletRequest().setAttribute("message", "更改成功");
				}else {
					getServletRequest().setAttribute("message", "更改失败");
				}
				return "enSet";
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}	
	}
	
	/**
	 * 搜索企业
	 * @return
	 */
	@Action("enSearch")
	public String enSearch() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= PAM) {//检查权限
				flag = 2;//企业
				if(StringUtil.isNull(value)) {
					flag = 2;//企业
					enList = enService.getListByName(value,page,rows);
					pageTool = PageUtil.getPageTool(servletRequest, enService.getTotalByName(value), page, rows);
					return "admin";
				}else {
					getServletRequest().setAttribute("message", "搜索失败");
					return enList();
				}
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 企业家列表
	 * @return
	 */
	@Action("erList")
	public String erList() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= EAM) {//检查权限
				flag = 3;//企业家
				erList = erService.getList(page,rows);
				pageTool = PageUtil.getPageTool(servletRequest, erService.getTotal(), page, rows);
				return "list";
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 企业家添加
	 * @return
	 */
	@Action("erAdd")
	public String erAdd() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= EAM) {//检查权限
				if(flag==-1) {
					flag = 3;//企业家
					return "erAdd";
				}
				flag = 3;//企业家
				if(er!=null) {
					erService.add(er);//添加数据
					getServletRequest().setAttribute("message", "添加成功");
					return erList();
				}else {
					getServletRequest().setAttribute("message", "添加失败");
					return "erAdd";
				}
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
		
	/**
	 * 企业家删除
	 * @return
	 */
	@Action("erDel")
	public String erDel() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= EAM) {//检查权限
				flag = 3;//企业
				if(er.getId()!=0) {
					erService.delete(er);
					getServletRequest().setAttribute("message", "删除成功");
				}else {
					getServletRequest().setAttribute("message", "删除失败");
				}
				return erList();
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 更新企业家信息
	 * @return
	 */
	@Action("erSet")
	public String erSet() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= EAM) {//检查权限
				if(flag==-1) {
					flag = 3;//企业家
					return "erSet";
				}
				flag = 3;//企业家
				if(er!=null) {
					erService.update(er);//更新数据
					getServletRequest().setAttribute("message", "更改成功");
				}else {
					getServletRequest().setAttribute("message", "更改失败");
				}
				return "erSet";
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}	
	}
	
	/**
	 * 搜索企业家
	 * @return
	 */
	@Action("erSearch")
	public String erSearch() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= PAM) {//检查权限
				flag = 3;//企业家
				if(StringUtil.isNull(value)) {
					flag = 3;//企业家
					erList = erService.getListByName(value,page,rows);
					pageTool = PageUtil.getPageTool(servletRequest, erService.getTotalByName(value), page, rows);
					return "admin";
				}else {
					getServletRequest().setAttribute("message", "搜索失败");
					return erList();
				}
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 动态文章列表
	 * @return
	 */
	@Action("infList")
	public String infList() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() == IAM || admin.getPermissions() == AAM) {//检查权限
				flag = 4;//动态文章
				infList = infService.getList(page,rows);
				pageTool = PageUtil.getPageTool(servletRequest, infService.getTotal(), page, rows);
				return "list";
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 动态文章添加
	 * @return
	 */
	@Action("infAdd")
	public String infAdd() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() == IAM || admin.getPermissions() == AAM) {//检查权限
				if(flag==-1) {
					flag = 4;//动态文章
					provinceList = provinceService.getList();// 省份集合
					return "infAdd";
				}
				flag = 4;//动态文章
				if(inf!=null) {
					infService.add(inf);//添加数据
					getServletRequest().setAttribute("message", "添加成功");
					return infList();
				}else {
					getServletRequest().setAttribute("message", "添加失败");
					return "infAdd";
				}
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 动态文章删除
	 * @return
	 */
	@Action("infDel")
	public String infDel() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() == IAM || admin.getPermissions() == AAM) {//检查权限
				flag = 3;//企业
				if(inf.getId()!=0) {
					infService.delete(inf);
					getServletRequest().setAttribute("message", "删除成功");
				}else {
					getServletRequest().setAttribute("message", "删除失败");
				}
				return infList();
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 更新动态文章信息
	 * @return
	 */
	@Action("infSet")
	public String infSet() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() == IAM || admin.getPermissions() == AAM) {//检查权限
				if(flag==-1) {
					flag = 4;//动态文章
					return "infSet";
				}
				flag = 4;//动态文章
				if(inf!=null) {
					infService.update(inf);//更新数据
					getServletRequest().setAttribute("message", "更改成功");
				}else {
					getServletRequest().setAttribute("message", "更改失败");
				}
				return "infSet";
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}	
	}

	/**
	 * 搜索动态文章
	 * @return
	 */
	@Action("infSearch")
	public String infSearch() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= PAM) {//检查权限
				flag = 4;//动态文章
				if(StringUtil.isNull(value)) {
					flag = 4;//动态文章
					infList = infService.getListByName(value,page,rows);
					pageTool = PageUtil.getPageTool(servletRequest, infService.getTotalByName(value), page, rows);
					return "admin";
				}else {
					getServletRequest().setAttribute("message", "搜索失败");
					return infList();
				}
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
		
	/**
	 * 宣传栏列表
	 * @return
	 */
	@Action("pubList")
	public String pubList() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() == IAM || admin.getPermissions() == AAM) {//检查权限
				flag = 5;//宣传栏
				pubList = pubService.getList();
				return "list";
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 更新宣传栏信息
	 * @return
	 */
	@Action("pubSet")
	public String pubSet() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() == IAM || admin.getPermissions() == AAM) {//检查权限
				flag = 5;//宣传栏
				if(pub!=null && inf.getId()!=0) {
					inf = infService.get(inf.getId());
					pub.setImg(UploadUtil.fileUpload(img, imgFileName, "img", "inf_"+inf.getId()));
					pub.setDate(inf.getDate());
					pub.setInformation(inf.getId());
					pubService.update(pub);//更新数据
					getServletRequest().setAttribute("message", "更改成功");
				}else {
					getServletRequest().setAttribute("message", "更改失败");
				}
				return pubList();
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}	
	}
		
	/**
	 * 项目列表
	 * @return
	 */
	@Action("proList")
	public String proList() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= PAM) {//检查权限
				flag = 6;//项目
				proList = proService.getList(page,rows);
				pageTool = PageUtil.getPageTool(servletRequest, proService.getTotal(), page, rows);
				return "list";
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 项目添加
	 * @return
	 */
	@Action("proAdd")
	public String proAdd() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= PAM) {//检查权限
				if(flag==-1) {
					flag = 6;//项目
					return "proAdd";
				}
				flag = 6;//项目
				if(pro!=null) {
					proService.add(pro);//添加数据
					getServletRequest().setAttribute("message", "添加成功");
					return proList();
				}else {
					getServletRequest().setAttribute("message", "添加失败");
					return "proAdd";
				}
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 项目删除
	 * @return
	 */
	@Action("proDel")
	public String proDel() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() == AAM) {//检查权限
				flag = 3;//企业
				if(pro.getId()!=0) {
					proService.delete(pro);
					getServletRequest().setAttribute("message", "删除成功");
				}else {
					getServletRequest().setAttribute("message", "删除失败");
				}
				return proList();
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 更新项目信息
	 * @return
	 */
	@Action("proSet")
	public String proSet() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= PAM) {//检查权限
				if(flag==-1) {
					flag = 6;//项目
					return "infSet";
				}
				flag = 6;//项目
				if(pro!=null) {
					proService.update(pro);//更新数据
					getServletRequest().setAttribute("message", "更改成功");
				}else {
					getServletRequest().setAttribute("message", "更改失败");
				}
				return "proSet";
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}	
	}
	
	/**
	 * 搜索项目
	 * @return
	 */
	@Action("proSearch")
	public String proSearch() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= PAM) {//检查权限
				flag = 6;//项目
				if(StringUtil.isNull(value)) {
					flag = 6;//项目
					proList = proService.getListByName(value,page,rows);
					pageTool = PageUtil.getPageTool(servletRequest, proService.getTotalByName(value), page, rows);
					return "list";
				}else {
					getServletRequest().setAttribute("message", "搜索失败");
					return proList();
				}
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}	
	
	/**
	 * 用户列表
	 * @return
	 */
	@Action("userList")
	public String userList() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= IAM) {//检查权限
				flag = 7;//用户
				userList = userService.getList(page,rows);
				pageTool = PageUtil.getPageTool(servletRequest, userService.getTotal(), page, rows);
				return "list";
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}
	
	/**
	 * 用户添加
	 * @return
	 */
	@Action("userAdd")
	public String userAdd() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() == AAM) {//检查权限
				if(flag==-1) {
					flag = 7;//用户
					return "userAdd";
				}
				flag = 6;//项目
				if(user!=null) {
					userService.add(user);//添加数据
					getServletRequest().setAttribute("message", "添加成功");
					return userList();//返回列
				}else {
					getServletRequest().setAttribute("message", "添加失败");
					return "userAdd";
				}
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}

	/**
	 * 用户删除
	 * @return
	 */
	@Action("userDel")
	public String userDel() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() == AAM) {//检查权限
				flag = 3;//企业
				if(user.getId()!=0) {
					userService.delete(user);
					getServletRequest().setAttribute("message", "删除成功");
				}else {
					getServletRequest().setAttribute("message", "删除失败");
				}
				return userList();
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}

	/**
	 * 更新用户信息
	 * @return
	 */
	@Action("userSet")
	public String userSet() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= PAM) {//检查权限
				if(flag==-1) {
					flag = 7;//用户
					return "infSet";
				}
				flag = 7;//用户
				if(user!=null) {
					userService.update(user);//更新数据
					getServletRequest().setAttribute("message", "更改成功");
				}else {
					getServletRequest().setAttribute("message", "更改失败");
				}
				return "userSet";
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}	
	}

	/**
	 * 搜索用户
	 * @return
	 */
	@Action("userSearch")
	public String userSearch() {
		//检查登录状态
		if(checkLogin() == ADM && ((Admin)getSession().get(ADM)).getId() != 0) {
			admin = adminService.get(((Admin)getSession().get(ADM)).getId());//获取登录管理员信息
			if(admin.getPermissions() <= PAM) {//检查权限
				flag = 7;//用户
				if(StringUtil.isNull(value)) {
					flag = 7;//用户
					userList = userService.getListByName(value,page,rows);
					pageTool = PageUtil.getPageTool(servletRequest, userService.getTotalByName(value), page, rows);
					return "list";
				}else {
					getServletRequest().setAttribute("message", "搜索失败");
					return userList();
				} 
			}else {
				getServletRequest().setAttribute("message", "无权限操作");
				return "admin";
			}
		}else{
			getServletRequest().setAttribute("message", "登录已失效，请重新登录");
			return "login";
		}
	}

	
	
	/*********************************GetSet*********************************/
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Enterprise getEn() {
		return en;
	}

	public void setEn(Enterprise en) {
		this.en = en;
	}

	public Entrepreneur getEr() {
		return er;
	}

	public void setEr(Entrepreneur er) {
		this.er = er;
	}

	public Information getInf() {
		return inf;
	}

	public void setInf(Information inf) {
		this.inf = inf;
	}

	public Publicity getPub() {
		return pub;
	}

	public void setPub(Publicity pub) {
		this.pub = pub;
	}

	public Project getPro() {
		return pro;
	}

	public void setPro(Project pro) {
		this.pro = pro;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public List<Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}

	public List<Enterprise> getEnList() {
		return enList;
	}

	public void setEnList(List<Enterprise> enList) {
		this.enList = enList;
	}

	public List<Entrepreneur> getErList() {
		return erList;
	}

	public void setErList(List<Entrepreneur> erList) {
		this.erList = erList;
	}

	public List<Information> getInfList() {
		return infList;
	}

	public void setInfList(List<Information> infList) {
		this.infList = infList;
	}

	public List<Project> getProList() {
		return proList;
	}

	public void setProList(List<Project> proList) {
		this.proList = proList;
	}

	public List<Publicity> getPubList() {
		return pubList;
	}

	public void setPubList(List<Publicity> pubList) {
		this.pubList = pubList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public EnterpriseService getEnService() {
		return enService;
	}

	public void setEnService(EnterpriseService enService) {
		this.enService = enService;
	}

	public EntrepreneurService getErService() {
		return erService;
	}

	public void setErService(EntrepreneurService erService) {
		this.erService = erService;
	}

	public InformationService getInfService() {
		return infService;
	}

	public void setInfService(InformationService infService) {
		this.infService = infService;
	}

	public ProjectService getProService() {
		return proService;
	}

	public void setProService(ProjectService proService) {
		this.proService = proService;
	}

	public PublicityService getPubService() {
		return pubService;
	}

	public void setPubService(PublicityService pubService) {
		this.pubService = pubService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public static int getRows() {
		return rows;
	}

	public static int getAam() {
		return AAM;
	}

	public static int getEam() {
		return EAM;
	}

	public static int getIam() {
		return IAM;
	}

	public static int getPam() {
		return PAM;
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
}

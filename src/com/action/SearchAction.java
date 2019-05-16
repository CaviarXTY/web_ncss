package com.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.entity.Enterprise;
import com.entity.Entrepreneur;
import com.entity.Project;
import com.entity.Province;
import com.entity.Type;
import com.service.EnterpriseService;
import com.service.EntrepreneurService;
import com.service.ProjectService;
import com.service.ProvinceService;
import com.service.TypeService;
import com.util.PageUtil;
import com.util.StringUtil;

@Namespace("/index")
@Results({ 
	@Result(name = "index", location = "/index/index.jsp"),
	@Result(name = "results", location = "/index/results.jsp"), 
	@Result(name = "reSearchPo",type="redirect",location = "searchPo.action?flag=-1"), 
	@Result(name = "reSearchEn",type="redirect",location = "searchEn.action?flag=-1"), 
	@Result(name = "inf", location = "/index/inf.jsp"), 
})
@SuppressWarnings("serial")
public class SearchAction extends BaseAction {
	
	/**********************************Data**********************************/
	
	// 数据
	private static final int rows = 10; // 默认每页数量
	private int flag;	// 页面标记
	private String value;	// 搜索内容
	private int typeid;	// 类型、搜索限定类型
	private int provinceid;	// 省份、搜索限定区域
	private String reAction;//是否列出项目
	private Project project;//项目
	private Enterprise en;//企业
	private int id;//信息跳转页面
	private Entrepreneur entrepreneur;//企业家
	private boolean isfocus;//记录是否已关注
	
	// 集合
	private List<Type> typeList;	// 类型列
	private List<Province> provinceList;	// 省份列
	private List<Project> projectList;	// 项目列
	private List<Enterprise> enterpriseList;	// 企业列
	// 服务
	@Resource
	private TypeService typeService;	// 类型服务
	@Resource
	private ProvinceService provinceService;	// 省份服务
	@Resource
	private ProjectService projectService;	// 项目服务
	@Resource
	private EnterpriseService enService;	// 企业服务
	@Resource
	private EntrepreneurService entrepreneurService;
	
	
	/*********************************Action*********************************/
	
	/**
	 * 项目搜索
	 * 
	 * @return
	 */
	@Action("searchPo")
	public String searchPo() {
		reAction = "searchPo";//记录页面
		typeList = typeService.getList();
		provinceList = provinceService.getList();
		if (flag == -1) {
			//全部查询
			System.out.println("SearchAction:项目全局查询");//测试
			projectList = projectService.getList(page, rows);
			pageTool = PageUtil.getPageTool(servletRequest, projectService.getTotal(), page, rows);
		}else if(flag == 1 && !StringUtil.isNull(value)) {
			//项目名查询
			System.out.println("SearchAction:项目名查询value="+value);//测试
			projectList = projectService.getListByName(value, page, rows);
			pageTool = PageUtil.getPageTool(servletRequest, projectService.getTotalByName(value), page, rows);
		}else if(flag == 2 && typeid != 0) {
			//项目类型查询
			System.out.println("SearchAction:项目类型查询typeid="+typeid);//测试
			projectList = projectService.getListByType(typeid, page, rows);
			pageTool = PageUtil.getPageTool(servletRequest, projectService.getTotalByType(typeid), page, rows);
		}else if(flag == 3 && provinceid != 0) {
			//项目省份查询
			System.out.println("SearchAction:项目省份查询provinceid="+provinceid);//测试
			projectList = projectService.getListByProv(provinceid, page, rows);
			pageTool = PageUtil.getPageTool(servletRequest, projectService.getTotalByProv(provinceid), page, rows);
		}else{
			//容错查询
			System.out.println("SearchAction:error-flag="+flag+",value="+value+",typeid="+typeid+",provinceid="+provinceid);//测试
			return "reSearchPo";
		}
		flag = 0; //显示无条件搜索
		return "results";
	}
	
	/**
	 * 企业搜索
	 * @return
	 */
	@Action("searchEn")
	public String searchEn() {
		reAction = "searchEn";//记录页面
		typeList = typeService.getList();
		provinceList = provinceService.getList();
		if (flag == -1) {
			//全部查询
			System.out.println("SearchAction:企业全局查询");//测试
			enterpriseList = enService.getList(page, rows);
			pageTool = PageUtil.getPageTool(servletRequest, enService.getTotal(), page, rows);
		}else if(flag == 1 && !StringUtil.isNull(value)) {
			//项目名查询
			System.out.println("SearchAction:企业名查询value="+value);//测试
			enterpriseList = enService.getListByName(value, page, rows);
			pageTool = PageUtil.getPageTool(servletRequest, enService.getTotalByName(value), page, rows);
		}else if(flag == 2 && typeid != 0) {
			//项目类型查询
			System.out.println("SearchAction:企业类型查询typeid="+typeid);//测试
			enterpriseList = enService.getListByProv(provinceid, page, rows);
			pageTool = PageUtil.getPageTool(servletRequest, enService.getTotalByProv(provinceid), page, rows);
		}else if(flag == 3 && provinceid != 0) {
			//项目省份查询
			System.out.println("SearchAction:企业省份查询provinceid="+provinceid);//测试
			enterpriseList = enService.getListByProv(provinceid, page, rows);
			pageTool = PageUtil.getPageTool(servletRequest, enService.getTotalByProv(provinceid), page, rows);
		}else{
			//容错
			System.out.println("SearchAction:error-flag="+flag+",value="+value+",typeid="+typeid+",provinceid="+provinceid);//测试
			return "reSearchPo";
		}
		flag = 0; //显示无条件搜索
		return "results";
	}
	
	/**
	 * 跳转项目介绍页
	 * @return
	 */
	@Action("infPro")
	public String infPro() {
		//该项目id是否有效
		if(projectService.isExist(id)) {
			project = projectService.get(id);//获取项目信息
			project.setOrginators(StringUtil.getName(project.getOrginators()));
			//判断是否有企业家登录,判断是否已关注
			if(checkLogin() == ENP && ((Entrepreneur)getSession().get(ENP)).getId() != 0) {
				isfocus = false;
				//获取企业家信息
				entrepreneur = entrepreneurService.get(((Entrepreneur)getSession().get(ENP)).getId());
				if(!StringUtil.isNull(entrepreneur.getFocus())) {//判断是否为空
					//判断是否已关注
					List<String> projectNameList = StringUtil.getNameArray(entrepreneur.getFocus());
					if(projectNameList.size()>0) {
						//判断是否已关注
						for(String str : projectNameList) {
							if(str.equals(project.getName())) {
								isfocus = true;//已关注
							}
						}
					}
				}
			}
			return "inf";
		}
		getServletRequest().setAttribute("message", "项目已失效");//弹窗提示
		return "reSearchPo";
	}
	
	/**
	 * 跳转企业介绍页
	 * @return
	 */
	@Action("infEnt")
	public String infEnt() {
		if(enService.isExist(id)) {
			en = enService.get(id);
			return "inf";
		}else {
			getServletRequest().setAttribute("message", "该企业不可查");//弹窗提示
			return "reSearchEn";
		}
	}

	/*********************************GetSet*********************************/
	
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

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public int getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	public String getReAction() {
		return reAction;
	}

	public void setReAction(String reAction) {
		this.reAction = reAction;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Enterprise getEn() {
		return en;
	}

	public void setEn(Enterprise en) {
		this.en = en;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Entrepreneur getEntrepreneur() {
		return entrepreneur;
	}

	public void setEntrepreneur(Entrepreneur entrepreneur) {
		this.entrepreneur = entrepreneur;
	}

	public boolean isIsfocus() {
		return isfocus;
	}

	public void setIsfocus(boolean isfocus) {
		this.isfocus = isfocus;
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

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public List<Enterprise> getEnterpriseList() {
		return enterpriseList;
	}

	public void setEnterpriseList(List<Enterprise> enterpriseList) {
		this.enterpriseList = enterpriseList;
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

	public EnterpriseService getEnService() {
		return enService;
	}

	public void setEnService(EnterpriseService enService) {
		this.enService = enService;
	}

	public EntrepreneurService getEntrepreneurService() {
		return entrepreneurService;
	}

	public void setEntrepreneurService(EntrepreneurService entrepreneurService) {
		this.entrepreneurService = entrepreneurService;
	}

	public static int getRows() {
		return rows;
	}
}

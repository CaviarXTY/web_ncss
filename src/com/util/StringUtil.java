package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文字处理
 * @author Caviar
 *
 */
public class StringUtil {
	
	//记录人员	Name@Id;Name@Id;		-->		Map(id,name) 
	//发送的消息	s.Time=Value&Name.Id;	-->		News
	//接受的消息	g.Time=Value&Name.Id;	-->		News
	//未读的消息	r.Time=Value&Name.Id;	-->		News
	/**
	 * 判断字符串是否为空
	 * @param str
	 */
	public static boolean isNull(String str) {
		if(str == null || str.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 将字符串中所有的用户名返回
	 * @param value
	 * @return
	 */
	public static List<String> getNameArray(String value) {
		if(!isNull(value)) {
			if(value.indexOf(";") != -1 && value.indexOf("@") != -1) {
				List<String> list = new ArrayList<String>();
				String[] strA = getUnit(value);
				//遍历拆分Id和用户名
				for(String str : strA) {
					if(str.split("@").length==2) {
						//添加(Id,Name)
						list.add(getName(str));
					}
				}
				return list;
			}
		}
		return null;
	}
	
	/**
	 * 将字符串中所有的id返回
	 * @param value
	 * @return
	 */
	public static List<Integer> getIdArray(String value) {
		if(!isNull(value)) {
			if(value.indexOf(";") != -1 && value.indexOf("@") != -1) {
				List<Integer> list = new ArrayList<Integer>();
				String[] strA = getUnit(value);
				//遍历拆分Id和用户名
				for(String str : strA) {
					if(str.split("@").length==2) {
						//添加(Id,Name)
						list.add(getId(str));
					}
				}
				return list;
			}
		}
		return null;
	}
	
	/**
	 * 返回字符串中所有的id和name
	 * @param value
	 * @return
	 */
	public static Map<Integer, String> getIdAndNameByList(String value){
		//记录人员	Name@Id;Name@Id;	-->		Map(id,name) 
//		System.out.println("StringUtil:getIdAndNameByList:value="+value);//测试
		if(value.indexOf(";") != -1 && value.indexOf("@") != -1) {
			Map<Integer, String> map = new HashMap<Integer,String>();
			//是否有结果
			if(getUnit(value)!= null) {
				int num = 0;
				String[] strA = getUnit(value);
				//遍历拆分Id和用户名
				for(String str : strA) {
					if(str.split("@").length==2) {
						//添加(Id,Name)
						map.put(getId(str), getName(str));
						num++;
					}
				}
				//判断是否有数据
				if(num>0) {
//					System.out.println("StringUtil:getIdAndNameByList:return=map");//测试
					return map;
				}
			}
		}
//		System.out.println("StringUtil:getIdAndNameByList:return=null");//测试
		return null;
	}
	
	/**
	 * 将字符串分割成部分
	 * @param value
	 * @param s
	 * @return
	 */
	public static String[] getUnitBy(String value,String s) {
//		System.out.println("StringUtil:getUnitBy:value="+value);//测试
		if(value.indexOf(s) != -1) {
			String[] unit = value.split(s);
			if(unit != null && unit.length > 0) {
//				//测试
//				for(int i = 0; i < unit.length; i++) {
//					System.out.println("StringUtil:getUnitBy:return["+ i +"]=" + unit[i]);
//				}
				return unit;
			}
		}
//		System.out.println("StringUtil:getUnitBy:return=null");//测试
		return null;
	}
	
	/**
	 * 获取；分割的单位
	 * @param value
	 * @return
	 */
	public static String[] getUnit(String value) {
		return getUnitBy(value,";");
	}
	
	/**
	 * 获取字符串中的name
	 * @param value
	 * @return
	 */
	public static String getName(String value) {
//		System.out.println("StringUtil:getName:value="+value);//测试
		if(value.indexOf("@") != -1) {
			String[] strA = value.split("@");
			if(strA != null && strA.length==2) {
				//System.out.println("StringUtil:getName:retun="+strA[0]);//测试
				//添加(Id,Name)
				return strA[0];
			}
		}
		//System.out.println("StringUtil:getName:retun=null");//测试
		return null;
	}
	
	/**
	 * 获取字符串中的id
	 * @param value
	 * @return
	 */
	public static int getId(String value) {
//		System.out.println("StringUtil:getId:value="+value);//测试
		if(value.indexOf("@") != -1) {
			String[] strA = value.split("@");
			if(strA != null && strA.length==2) {
				//添加(Id,Name)
//				System.out.println("StringUtil:getName:retun="+strA[1]);//测试
				return Integer.parseInt(strA[1]);
			}
		}
//		System.out.println("StringUtil:getName:retun=0");//测试
		return 0;
	}


}

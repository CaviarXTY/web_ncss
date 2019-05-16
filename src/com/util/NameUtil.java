package com.util;

public class NameUtil {
	
	public static String getType(int id) {
		switch(id){
			case 1 : return "农、林、牧、渔业";
			case 2 : return "采矿业";
			case 3 : return "制造业";
			case 4 : return "水、电、热力、燃气生产及供应";
			case 5 : return "建筑业";
			case 6 : return "批发和零售业";
			case 7 : return "交通运输、仓储和物流业";
			case 8 : return "住宿和餐饮业";
			case 9 : return "信息技术服务业";
			case 10 : return "金融业";
			case 11 : return "房地产业";
			case 12 : return "租赁和商务服务业";
			case 13 : return "科学技术服务业";
			case 14 : return "水利、环境和公共设施管理";
			case 15 : return "居民服务、修理和其他服务业";
			case 16 : return " 教育";
			case 17 : return "医疗和社会工作";
			case 18 : return "文化、体育和娱乐业";

			default: return "";
		}
	}
	
	public static String getProvince(int id) {
		switch(id){
			case 1 : return "北京市";
			case 2 : return "天津市";
			case 3 : return "河北省";
			case 4 : return "山西省";
			case 5 : return "内蒙古";
			case 6 : return "辽宁省";
			case 7 : return "吉林省";
			case 8 : return "黑龙江省";
			case 9 : return "江苏省";
			case 10 : return "浙江省";
			case 11 : return "安徽省";
			case 12 : return "福建省";
			case 13 : return "江西省";
			case 14 : return "山东省";
			case 15 : return "河南省";
			case 16 : return "湖北省";
			case 17 : return "湖南省";
			case 18 : return "广东省";
			case 19 : return "广西";
			case 20 : return "海南省";
			case 21 : return "重庆市";
			case 22 : return "四川省";
			case 23 : return "贵州省";
			case 24 : return "云南省";
			case 25 : return "西藏";
			case 26 : return "陕西省";
			case 27 : return "甘肃省";
			case 28 : return "青海省";
			case 29 : return "宁夏";
			case 30 : return "新疆";
			case 31 : return "台湾";
			case 32 : return "香港";
			case 33 : return "澳门";
			case 34 : return "新疆生产建设兵团";
			case 35 : return "国外";
			
			default: return "";
		}
	}
}

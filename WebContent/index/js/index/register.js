	function test(){
		console.log("test");
	}

	var $form = $("#register");
	var agree = document.getElementById("agree");
	var countdown = 60;

	var sw = {
		phone : false,
		code : false,
		pw : false,
		repw : false,
		name : false,
		card : false,
		email : false,
		sug : false,
	}

	/*
		检查输入框是否为空
	*/
	function checkNull(elem){
		if(elem.val()==''){
			elem.next().html("此项不能为空");//null
			return false;
		}else{
			elem.next().html("");//not null
			return true;
		}
	}

	/*
		检查手机格式、ajax请求是否已存在
		手机号为11位，1开头，第二位为3、4、5、7、8，
	*/
	function checkPhone(id){
		var phone = $("#"+id);
		if(checkNull(phone)){//是否为空提示
			var ret = /^1[34578]\d{9}$/;
			if(ret.test(phone.val())){
				checkExist(phone);
				return true;
			}else phone.next().html("输入的手机号格式有误");			
		}
		return false;
	}

	/*
		发送ajax请求，检查手机号是否已注册
	*/
	function checkExist(elem){
		var phone = elem.serialize();
		console.log("数据序列化:"+phone);//test
		$.ajax({
			url: "checkExist.action",
			type: "post",
			dataType: "json",//返回的类型必须为json
			data: phone,//数据为序列化之后的。
			success: function(data){
				//data.mess是返回的json对象里面的值(在action里面设置)
				console.log("data:");//test
				console.log(data);//test
				if(data.meg=="true"){
					console.log("success:"+data);//test
					sw['phone'] = false;
					elem.next().html("该手机号已注册账号");
				}else{
					sw['phone'] = true;
					elem.next().html("该手机号可注册");
				}
			}
		});
	}

	/*
		检查验证码格式
	*/
	function checkCode(id){
		var code = $("#"+id);
		if(checkNull(code)){//是否为空提示
			sw[id] = true;
			return true;
		}
		return false;
	}

	/*
		检查密码格式
		密码为6~10位字母、数字、下划线，第一个字符必须是字母
	*/
	function checkPw(id){
		var elem = $("#"+id);
		if(checkNull(elem)){//是否为空提示
			var ret = /^[a-zA-Z][\w]{5,9}$/;
			if(!ret.test(elem.val())){
				elem.next().html("密码须为6~10位字母、数字、下划线，第一个字符必须是字母");
				return false;
			}
			sw[id] = true;
			return true;
		}
		return false;
	}

	/*
		检查两次密码是否相同
	*/
	function checkRePw(pwid,repwid){
		var pw = $("#"+pwid);
		var repw = $("#"+repwid);
		if(checkNull(repw)){//是否为空提示
			if (pw.val() != repw.val()){
				repw.next().html("两次密码输入不相同");
				return false;
			}
			sw[repwid] = true;
			return true;
			//$(this).next().html("正确");
		}
		return false;
	}

	/*
		检查姓名格式是否正确
	*/
	function checkName(id){
		var elem = $("#"+id);
		if(checkNull(elem)){//是否为空提示
			var ret = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/
			if(!ret.test(elem.val())){
				elem.next().html("姓名输入有误");
				return false;
			}
			sw[id] = true;
			return true;
		}
		return false;
	}

	/*
		判断日期是否8位YYYYMMDD格式
	*/
	function isDate(strDate){
		var ret = /^[0-9]{8}$/;
		if(!ret.test(strDate)){
			return false;
		}
		var year, month, day;
		year = strDate.substring(0, 4);
		month = strDate.substring(4, 6);
		day = strDate.substring(6, 8);
		var monthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
		if(year < 1700 || year > 2019) return false;//判断年份
		if(month < 1 || month > 12) return false;//判断月份
		if(((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) monthDays[1] = 29;//判断闰年、二月日期
		if(day < 1 || day > monthDays[month - 1]) return false;//判读天数
		return true;
	}

	/*
		判断日期是否6位YYYYMM格式
	*/
	function isDate6(strDate){
		var ret = /^[0-9]{6}$/;
		if(!ret.test(strDate)){
			return false;
		}
		var year, month;
		year = strDate.substring(0, 4);
		month = strDate.substring(4, 6);
		if(year < 1700 || year > 2019) return false;//判断年份
		if(month < 1 || month > 12) return false;//判断月份
		return true;
	}

	/*
		检查身份证格式
	*/	
	function checkCard(id){
		var elem = $("#"+id);
		var num = elem.val();
		if(checkNull(elem)){//是否为空提示
			var factorArr = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1];
			var paritBit =  ["1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"];
			var varArray = new Array();
			var intValue;
			var lngProduct = 0;
			var intCheckDigit;
			var intStrLen = num.length;//身份证位数
			var idNumber = num;//身份证内容
			//init
			if((intStrLen != 18) && (intStrLen != 15)){//判断身份证号长度是否18位或15位
				elem.next().html("身份证号长度应18位或15位");
				return false;
			}
			for(i = 0; i < intStrLen; i++){
				varArray[i] = idNumber.charAt(i);
				if((varArray[i] < '0' || varArray[i] > '9') && (i != 17)){
					elem.next().html("身份证填写有误");
					return false;
				}else if(i < 17){
					varArray[i] = varArray[i] * factorArr[i];
				}
			}
			//18位身份证
			if(intStrLen == 18){
				var date8 = idNumber.substring(6, 14);
				//检查日期
				if(!isDate(date8)){//检查身份证第7到14位日期是否正确
					elem.next().html("身份证填写有误");
					return false;
				}
				// calculate the sum of the products 计算乘积的总和
				for (var i = 0; i < 17; i++) {
					lngProduct = lngProduct + varArray[i];
				}
				// calculate the check digit 计算校验位 
				intCheckDigit = paritBit[lngProduct % 11];
				// check last digit 检查最后一个数字
				if(varArray[17] != intCheckDigit){
					elem.next().html("身份证填写有误");
					return false;
				}

			}
			//15位身份证
			else{
				//检查日期
				var date6 = idNumber.substring(6, 12);
				if(!isDate6(date6)){
					elem.next().html("身份证填写有误");
					return false;
				}
			}
			sw[id] = true;
			return true;
		}
		return false;
	}

	/*
		检查邮箱格式
	*/
	function checkEm(id){
		var elem = $("#"+id);
		if(checkNull(elem)){//是否为空提示
			var ret = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
			if(!ret.test(elem.val())){
				elem.next().html("邮箱格式错误");
				return false;
			}
			sw[id] = true;
			return true;
		}
		return false;
	}

	/*
		发送短信验证请求
	*/
	function sendcode(){
		var obj = $("#getcode");
		if(checkPhone("phone")){
			var phone = $("#phone").serialize();
			console.log("send:");
			console.log(phone);
			$.ajax({
				url:"recode.action",
				type:"post",
				dataType:"json",//返回的类型必须为json
				data:phone,//数据
				success:function(data){
					if(data.meg=="success"){//成功
						settime(obj);//倒数
						console.log("success:"+data);
						obj.prev().html("已发送");
						/* window.location.href="/strutsweb/success.jsp"; */
					}//如果失败就在当前页面显示
				}
			})
		}else{
			obj.prev().html("请先正确填写手机号");
		}
	}

	/*
		重新发送计时
	*/
	function settime(obj) { //发送验证码倒计时
		if (countdown == 0) { 
			obj.removeClass("lock");
			obj.attr('disabled',false); 
			//obj.removeattr("disabled"); 
			obj.html("免费获取验证码");
			countdown = 60;
			return false;
		} else { 
			obj.addClass("lock");
			obj.attr('disabled',true);
			obj.html("重新发送(" + countdown + ")");
			countdown--;
		} 
		setTimeout(function() {settime(obj)},1000);
	}

	function checkAgree(){
		if(!agree.checked){
			$("#ab").html("请勾选");
			return false;
		}else{
			$("#ab").html("");
			return true;
		}
	}

	/*
		列表校验
	*/
	function checkForm(){
		// if($agree.checked){
		// 	console.log("send");
		// 	return true;
		// }
		console.log("check");
		if(checkPhone("phone")&&checkCode("code")&&checkPw("pw")&&checkRePw("pw","repw")&&checkName("name")&&checkCard("card")&&checkEm("email")&&checkAgree()){
			console.log("send");
			$form.submit();
		}else{
			console.log("wor");
		}
		return false;
	}

	/*
		列表发送
	*/
	var form = $form.serialize();
	// console.log("数据序列化:"+form);

	function regiseter(){
		$.ajax({
			url:"test.action",
			type:"post",
			dataType:"json",//返回的类型必须为json
			data:form,//数据为序列化之后的。
			success:function(data){
				//data.mess是返回的json对象里面的值(在action里面设置)
				console.log("data:");
				console.log(data);
				if(data.meg=="success"){
					//成功后页面跳转到登陆成功界面
					console.log("success:"+data);
				}else{
					//如果失败就在当前页面显示
					this.next().html("验证码输入错误");
				}
			}
		})
	}
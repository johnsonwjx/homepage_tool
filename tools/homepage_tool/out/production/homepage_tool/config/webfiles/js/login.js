function userlogin(){
		var loginId = document.getElementById("username").value;
		if(loginId==""){
		   alert("用户名为空！");
		   return;
		}
		var psw = document.getElementById("password").value;
		var validata = document.getElementById("validata").value;
		if(document.getElementById("validataTD").style.visibility=="hidden"){
			validata = "";
		}else{
			if(validata==""){
				alert("请输入验证码！");
				return;
			}
		}
		bjztblogin(loginId,psw,validata,"");
	}

	function bjztblogin(loginId,psw,validata,keynum){

		var url = rootpath+"/system/login/jsp/login.do?";
		var xml = "";

		loginId = encodeURIComponent(encodeURIComponent(loginId));
		psw = encodeURIComponent(encodeURIComponent(psw));
		validata = encodeURIComponent(encodeURIComponent(validata));
		var param = "action2=ajaxLogin&loginId="+loginId + "&psw="+psw+"&validata="+validata+"&keyNum="+keynum;;
		var myAjax = new Ajax.Request(url,
			{method:"post",
			parameters:param,
			asynchronous: false,
			onSuccess:function (request){
				xml = request.responseText;
			},
			onFailure:function (request) {
				alert("调用登录失败！");
			}
		});

		var doc = createXMLDOMDocument();
		try{
			doc.loadXML(xml);
		}catch(e){
			alert("解析登录返回数据失败："+xml);
			return;
		}

		var msg = doc.selectSingleNode("root/login/msg").text;
		var loginFlg = doc.selectSingleNode("root/login/loginFlg").text;
		var toUrl = doc.selectSingleNode("root/login/toUrl").text;
		var needShowVaildata = doc.selectSingleNode("root/login/needShowVaildata").text;
		var msg = doc.selectSingleNode("root/login/msg").text;
		if(loginFlg=="1"){
			var url = rootpath+"/bjztb/bjztbaction.do?";
			xml = "";
			var param = "action=userlogin";
			var myAjax = new Ajax.Request(url,
				{method:"post",
				parameters:param,
				asynchronous: false,
				onSuccess:function (request){
					xml = request.responseText;
				},
				onFailure:function (request) {
					alert("判断是否系统用户失败！");
				}
			});


			var doc = createXMLDOMDocument();
			try{
				doc.loadXML(xml);
			}catch(e){
				alert("解析判断是否系统用户返回数据失败："+xml);
				return;
			}
			var _userloginflag = doc.selectSingleNode("root/flag").text;
			if(_userloginflag){
				window.location = "mainframe.jsp?flag="+_userloginflag;
			}else{
				alert("不是系统用户！");
			}

		}else{
		    alert(msg);
			if(!document.getElementById("validataTD"))
			   return;
			if(needShowVaildata=="true"){
				document.getElementById("images").src=rootpath + "/system/login/jsp/login.do?action2=validateimage&flg="+loginFlg;
				document.getElementById("validataTD").style.visibility = "visible";
			}else{
				document.getElementById("validataTD").style.visibility = "hidden";
			}
		}
	}
function gotoBusiness(){
			if(_userloginflag)
				window.location = "mainframe.jsp?flag="+_userloginflag;
			else
			    logout();
}

	//更新验证码
function updateImg(){
	var timestamp=new Date().getTime();
	document.getElementById("images").src = rootpath + "/system/login/jsp/login.do?action2=validateimage&flg=x&timestamp=" + timestamp;
}

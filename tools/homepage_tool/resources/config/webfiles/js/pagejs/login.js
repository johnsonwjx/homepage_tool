(function ($, _) {
	var rootpath = window.rootpath;

	$(function () {
		$('#submit').click(submit);

		var loginName = getCookie('YFLoginName');
		$('#username').val(loginName);

		$('.loginFields').keydown(function (e) {
			if(e.which == 13){
				submit();
			}
		});

		$('#images').click(function () {
			var timestamp = new Date().getTime();
			$(this).attr('src', rootpath + "/system/login/jsp/login.do?action2=validateimage&flg=x&timestamp=" + timestamp);
		});
	});

	function submit() {
		var loginId = $('#username').val();
		if (!loginId) {
			alert('用户名为空!');
			return;
		}

		var password = $('#password').val();
		var validata = $("#validata").val();
		if ($("#validataTD").css('visibility') == 'visible') {
			if (validata == "") {
				alert("请输入验证码！");
				return;
			}
		} else {
			validata = "";
		}

		onceLogin(loginId, password, validata, '');
	}

	function login(loginId, password, validata, keynum) {
		var url = rootpath + '/system/login/jsp/login.do';

		var param = {
			action2: 'ajaxLogin',
			loginId: loginId,
			psw: password,
			validata: validata,
			keynum: keynum
		};

		$.ajax({
			url: url,
			data: param,
			method: 'POST',
			async: false,
			success: function (resultXml) {
				var doc = $(resultXml);
				var loginFlg = doc.find('loginFlg').text();
				if (loginFlg == '1') {
					location.reload();
				} else {
					var message = doc.find('msg').text();
					alert(message);
					var needShowVaildata = doc.find('needShowVaildata').text();

					if (needShowVaildata) {
						$("#images").attr('src', rootpath + "/system/login/jsp/login.do?action2=validateimage&flg=" + loginFlg);
						$("#validataTD").css('visibility', 'visible');
					} else {
						$("#validataTD").css('visibility', 'hidden');
					}
					// 失败了才需要再次可以点击提交
					onceLogin = _.once(login);
				}
			},
			error: function () {
				alert('登录失败!');
				// 失败了才需要再次可以点击提交
				onceLogin = _.once(login);
			}
		});
	}

	function getCookie(name) {
		var cookies = document.cookie;
		var start = cookies.indexOf(" " + name + "=");
		if (start == -1) {
			start = cookies.indexOf(name + "=");
		}
		if (start == -1) {
			cookies = null;
		} else {
			start = cookies.indexOf("=", start) + 1;
			var end = cookies.indexOf(";", start);
			if (end == -1) {
				end = cookies.length;
			}
			cookies = decodeURIComponent(cookies.substring(start, end));
		}
		return cookies;
	}


	// 防止多次点击提交
	var onceLogin = _.once(login);
})(jQuery, _);

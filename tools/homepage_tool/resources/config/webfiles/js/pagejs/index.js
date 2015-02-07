$(function () {
	var rootPath = _getRootPath();

	var url = rootPath + '/wkmh/loadData.do';

    $.ajax({
        url: url,
        data: {
            action: 'loadMenu'
        },
        method: 'GET',
        success: function (menu) {

            if (menu && menu != "") {

                $(".loginTx").empty();
                $(".loginTx").append(menu);
            }
        }
    });


    $.ajax({
		url: url,
		data: {
			action: 'loadAllNews'
		},
		method:'POST',
		success: loadAllNews,
		complete: function () {
			scrollMessage();
//			AutoPlay();// pic_pro.js
		}
	});

	function loadAllNews(datas) {
		var newsType;
		for (newsType in datas) {
			if (!datas.hasOwnProperty(newsType)) {
				continue;
			}
			var data = datas[newsType];

			if (!data) {
				continue;
			}

			var $news = $('#' + newsType);
			
			//期刊
			if(newsType == '60'){
			
				$.each(data, function (i, line) {
				
					var newsTitle = line['newstitle'];
					var fileName = line['file_name'];
					fileName = fileName.substring(0, fileName.lastIndexOf('.'));
																		
					var $bookImage = $(document.createElement('div'));
					$bookImage.attr('style','float:left;border:2px solid;width:102px;height:120px;color:lightgray;margin-left:10px;margin-bottom:10px;');
					
					var $a = $(document.createElement('a'));
					$a.attr('href', 'flexpaper.jsp?fileName='+fileName+'&title='+encodeURIComponent(encodeURIComponent(newsTitle)));
					//$a.attr('onclick', 'window.open("flexpaper.jsp?fileName='+fileName+'&title='+encodeURIComponent(encodeURIComponent(newsTitle))+'");');
					$a.attr('title', newsTitle);
					
					$img = $(document.createElement('img'));
					$img.attr('src', 'doc/'+fileName+'.jpg');
					$img.attr('style', 'width:102px;height:120px;');
					$a.append($img);
					
					$bookImage.append($a);
					
					
					$news.append($bookImage);
				});
				
			}else{
				
				var $ul = $("<ul>");

				var maxTextLength = 20;

				if (newsType == '00') {
					$ul.addClass('mulitline');
					$ul.css('height', '230px');
				}
				
				$.each(data, function (i, line) {
					
					var newsTitle = line['newstitle'];
					var issue_date = line['issue_date'];
					issue_date = issue_date.length > 10 ? issue_date.substring(0,10) : issue_date;
					
					var issue_username = line['issue_username'];
					var affix_fileid = line['affix_fileid'];
					
					var $a = $(document.createElement('a'));
					$a.attr('href', '#');
					$a.attr('title', newsTitle);
					$a.attr('style', 'margin-left:10px;');

					if(newsType == '90' || newsType == '50'){
						$a.text(truncate(newsTitle, 34, true));
					}else if(newsType == '00'){
						$a.text(newsTitle);
						$a.attr('style', 'margin-left:10px;font-family:"微软雅黑";color:red;');
					}else{
						$a.text(truncate(newsTitle, 36, true));
					}
						
					// 点击事件
					$a.click(function (e) {
						var fileId = line['file_id'];
						var newsType = line['newstype'];

						//下载
						if(newsType == '90'){

							var url = rootPath + '/common/updownfile/jsp/updownfile.do?action=DownFile&fileId='+fileId+'&downWay=2&thumbnail=';
							window.open(url,"_blank","toolbar=no,menubar=no,scrollbars=no,center=yes,alwaysLowered=yes,resizable=no,location=no,status=no,width=100,height=100");
						
						}else if(newsType == '00'){
						
							var billid = line['newsurl'];
							var url = rootPath + '/system/form2/jsp/formData.do?action=view&workData='+billid;
							window.open(url,"_blank","toolbar=no,menubar=no,scrollbars=no,center=yes,alwaysLowered=yes,resizable=no,location=no,status=no,width=100,height=100");
							
						}else {
							
							newsDetail(fileId, newsType, issue_username, affix_fileid,issue_date);
						}
						
						e.preventDefault();
						return false;
					});

					var $li = $(document.createElement('li'));
					
					$img = $(document.createElement('img'));
					$img.attr('src', 'images/arr.png');
					$img.attr('style', 'float:left;margin-top:8px;');
					$li.append($img);

					if(newsType == '00'){
						
						$li.attr('style','font-weight:bold;');	
						var titleLength = String(newsTitle).length;
						var rowLength = ~~20;
						if( titleLength > rowLength){

							var rownum = Math.ceil(titleLength/rowLength);
							rownum = rownum > 3 ? 3 : rownum;
							$li.attr('style','height:'+(rownum * 26)+'px;line-height:26px;font-weight:bold;');	
						}	
					}
					
					$li.append($a);	

					if(newsType != '00' && newsType != '90' && newsType != '50'){
						
						var $span = $(document.createElement('span'));
						$span.attr('style','float:right;margin-right:8px;');
						$span.text(issue_date);
						$li.append($span);
					}
										
					$ul.append($li);
				});
				
				$news.append($ul);
			}				
		}
	}

	function getLength(str){
		var chineseRegex = /[^\x00-\xff]/g; 
		var strLength = str.replace(chineseRegex,"**").length; 
		return strLength;
	}
	
	function truncate(str, len, hasDot) { 
		var newLength = 0; 
		var newStr = ""; 
		var chineseRegex = /[^\x00-\xff]/g; 
		var singleChar = ""; 
		var strLength = str.replace(chineseRegex,"**").length; 
		for(var i = 0;i < strLength;i++) 
		{ 
			singleChar = str.charAt(i).toString(); 
			if(singleChar.match(chineseRegex) != null) { 
				newLength += 2; 
			} else { 
				newLength++; 
			}
			
			if(newLength > len) { 
				break; 
			} 
			newStr += singleChar; 
		} 
		 
		if(hasDot && strLength > len) { 
			newStr += "..."; 
		} 
		
		return newStr; 
	} 
	
	function truncate_1(str, length, truncateStr) {
		if (str == null) return '';
		str = String(str);
		truncateStr = truncateStr || '...';
		length = ~~length;
		return str.length > length ? str.slice(0, length) + truncateStr : str;
	}

	function scrollMessage() {
		var _wrap = $('ul.mulitline');//定义滚动区域
		var _interval = 3000;//定义滚动间隙时间
		var _moving;//需要清除的动画
		_wrap.hover(function () {
			clearInterval(_moving);//当鼠标在滚动区域中时,停止滚动
		},function () {
			_moving = setInterval(function () {
				var _field = _wrap.find('li:first');//此变量不可放置于函数起始处,li:first取值是变化的
				var _h = _field.height();//取得每次滚动高度
				_field.animate({marginTop: -_h + 'px'}, 600, function () {//通过取负margin值,隐藏第一行
					_field.css('marginTop', 0).appendTo(_wrap);//隐藏后,将该行的margin值置零,并插入到最后,实现无缝滚动
				})
			}, _interval);//滚动间隔时间取决于_interval
		}).trigger('mouseleave');//函数载入时,模拟执行mouseleave,即自动滚动
	}

	function newsDetail(fileId, newsType, issue_username, affix_fileid, issue_date) {
		if (!fileId) {
			return;
		}

		var param = {
			newsType:newsType,
			fileId : fileId,
			issue_username:encodeURIComponent(issue_username),
			affix_fileid:affix_fileid,
			issue_date:encodeURIComponent(issue_date)
		};
        var projectName=window.location.href.replace(rootPath,"").replace("/index.jsp","")
        var url=null;
        if(projectName&&projectName.length>0){
            url=rootPath+projectName+"/news_detail.jsp?"+ $.param(param);
        }else{
            url=rootPath+"news_detail.jsp?"+ $.param(param);
        }
        window.location = url;
	}

});

function _getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName + "/");
}


(function ($, _) {
    var rootPath = window.rootpath;
    var types={	"00": "滚动信息",	"05": "滚动图片",	"10": "公司新闻",	"20": "业界资讯",	"30": "公司公告",	"40": "党团工会",	"50": "问卷调查",	"60": "期刊管理",	"70": "文科培训",	"90": "文件下载"};
    var newsType = window.newsType;
    var title = types[newsType];
	$(function () {
		$('.title').text(title);
		loadNews(newsType, 1);

		$('.search-news').click(function () {
			gotoPage(1);
		});

		// pages event
		$('#first-page').click(function () {
			gotoPage(1);
		});
		$('#pre-page').click(function () {
			var page = $('.page.hover').text();
			page = parseInt(page, 10) - 1;
			if (!page || page < 1) {
				page = 1;
			}
			gotoPage(page)
		});
		$('#next-page').click(function () {
			var total = $('.page').length;
			var page = $('.page.hover').text();
			page = parseInt(page, 10) + 1;
			if (!page || page > total) {
				page = total;
			}
			gotoPage(page)
		});
		$('#last-page').click(function () {
			var total = $('.page').length;
			gotoPage(total);
		});

		$('#gotoPage').click(function () {
			var page = parseInt($('#pageindex_id').val());
			var total = $('.page.hover').length;

			if (!page) {
				page = 1;
			}
			if (page > total) {
				page = total;
			}

			gotoPage(page);
		});
	});

	function loadNews(newsType, page, filter) {
		var url = rootPath + '/wkmh/loadData.do';
		$.ajax({
			url: url,
			method: 'POST',
			data: {
				action: 'loadNewsByPage',
				newsType: newsType,
				page: page,
				filter: filter ? filter : ''
			},
			success: handleData
		});
	}

	function handleData(result) {
		if (!result) {
			return;
		}

		var total = result.total;
		var datas = result.datas;
		var count = datas.length;
		
		var $newslist = $('#newslist').empty();
		var $_newslist = $('#_newslist').empty();
				
		$.each(datas, function (i, data) {

			var fileId = data.file_id;
			
			//期刊
			if(newsType == '60'){
			
				var divHeight = Math.ceil(count/4) * 260 + 'px';
				$_newslist.attr('style', 'height:' + divHeight + ';margin-left:15px;');
			
				var newsTitle = data.newstitle;
				var fileName = data.file_name;
				fileName = fileName.substring(0, fileName.lastIndexOf('.'));
				
				var $book = $(document.createElement('div'));
				$book.attr('style','float:left;width:160px;height:250px;color:lightgray;margin-left:20px;margin-bottom:15px;');
				
				var $bookImage = $(document.createElement('div'));
				$bookImage.attr('style','float:left;border:2px solid;width:160px;height:200px;');
					
				var $a = $(document.createElement('a'));
				$a.attr('href', 'flexpaper.jsp?fileName='+fileName+'&title='+encodeURIComponent(encodeURIComponent(newsTitle)));
				//$a.attr('onclick', 'window.open("flexpaper.jsp?fileName='+fileName+'&title='+encodeURIComponent(encodeURIComponent(newsTitle))+'");');
				$a.attr('title', newsTitle);
					
				$img = $(document.createElement('img'));
				$img.attr('src', 'doc/'+fileName+'.jpg');
				$img.attr('style', 'width:160px;height:200px;');
				$a.append($img);
					
				$bookImage.append($a);
				$book.append($bookImage);
				
				$bookName = $(document.createElement('div'));
				$bookName.attr('style','float:left;width:160px;height:40px;font-family:"微软雅黑";word-wrap:break-word;word-break:break-all;');
				var $title = $(document.createElement('a'));
				$title.attr('href', 'flexpaper.jsp?fileName='+fileName+'&title='+encodeURIComponent(encodeURIComponent(newsTitle)));
				//$title.attr('onclick', 'window.open("flexpaper.jsp?fileName='+fileName+'&title='+encodeURIComponent(encodeURIComponent(newsTitle))+'");');
				$title.attr('title', newsTitle);
				$title.text(newsTitle);
				$bookName.append($title);
				$book.append($bookName);
				
				$_newslist.append($book);
				
			}else{
			
				var $li = $('<li>');
				var $a = $('<a>');
				var $span = $('<span>');

				$a.text(data.newstitle);
				var url;
				if (!newsType || !fileId) {
					url = '#';
				} else {

					if(newsType == '90'){
						
						url = '../common/updownfile/jsp/updownfile.do?action=DownFile&fileId='+fileId+'&downWay=2&thumbnail=';
						
						$a.attr('onclick', 'window.open("'+url+'","_blank","toolbar=no,menubar=no,scrollbars=no,center=yes,alwaysLowered=yes,resizable=no,location=no,status=no,width=100,height=100")');
						$a.attr('href', 'javascript:void(0);');
					}else if(newsType == '00'){
						
						var billid = data.newsurl;
						url = '../system/form2/jsp/formData.do?action=view&workData='+billid;
						
						$a.attr('onclick', 'window.open("'+url+'","_blank","toolbar=no,menubar=no,scrollbars=no,center=yes,alwaysLowered=yes,resizable=no,location=no,status=no,width=100,height=100")');
						$a.attr('href', 'javascript:void(0);');
					}else{
					
						//if(newsType=='10'){
							
							var issue_username = data.issue_username;
							var affix_fileid = data.affix_fileid;
							var issue_date = data.issue_date;
							issue_date = issue_date.length > 10 ? issue_date.substring(0,10) : issue_date;
							
							issue_username = encodeURIComponent(encodeURIComponent(issue_username));
							issue_date = encodeURIComponent(encodeURIComponent(issue_date));
							affix_fileid = encodeURIComponent(affix_fileid);
							url = 'news_detail.jsp?newsType=' + newsType + '&fileId=' + fileId + '&issue_username='+issue_username + '&affix_fileid='+affix_fileid+'&issue_date='+issue_date;
						//}else{
						//	url = 'news_detail.jsp?newsType=' + newsType + '&fileId=' + fileId;
						//}
						
						$a.attr('href', url);
					}	
				}

				$span.text(data.issue_date);

				$li.append($a).append($span);
				$newslist.append($li);
			}				
		});
		
		$('#total').text(total);

		var page = result.page;
		page = page <= 0 ? 1 : page;

		var $pages = $('#pages').empty();
		var pagecount = parseInt(result.pagecount, 10);
		_.chain(0).range(pagecount).each(function (pageNum) {
			pageNum = pageNum + 1;

			var $a = $('<a></a>');
			$a.text(pageNum);
			$a.addClass('page');

			// current page
			if (pageNum == page) {
				// clear other hover;
				$a.addClass('hover');
			}
			$pages.append($a);
		});

		// add default 1 page
		if (pagecount == 0) {
			$pages.append($('<a class="hover">1</a>'));
		}

		$('.page').click(function (e) {
			var page = $(this).text();
			gotoPage(page);
		});
	}

	function gotoPage(page) {
		var filter = $('#search-input').val();
		if (filter == '请输入关键字…') {
			filter = ''
		}
		loadNews(newsType, page, filter)
	}
})(jQuery, _);

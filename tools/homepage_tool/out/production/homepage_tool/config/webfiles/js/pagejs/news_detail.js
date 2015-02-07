$(function () {
    var types={	"00": "滚动信息",	"05": "滚动图片",	"10": "公司新闻",	"20": "业界资讯",	"30": "公司公告",	"40": "党团工会",	"50": "问卷调查",	"60": "期刊管理",	"70": "文科培训",	"90": "文件下载"};
    var newsType = window.newsType;
    var title = types[newsType];
    var fileId = window.fileId;
	$('.title').text(title);
	if (fileId) {
		if(newsType == '90'){
			var url = '../common/updownfile/jsp/updownfile.do?action=DownFile&fileId='+fileId+'&downWay=2&thumbnail=';
			window.open(url,"_blank","toolbar=no,menubar=no,scrollbars=no,center=yes,alwaysLowered=yes,resizable=no,location=no,status=no,width=100,height=100");
		}else{
		
			var fileUrl = '../common/updownfile/jsp/updownfile.do?action=ConvertDocToHtmlandDownFile&downWay=1&fileId=' + fileId;
			$('#frame_content').attr('src', fileUrl);
		}
	}
});

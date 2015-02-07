<%@ page language="java" isELIgnored="false" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html>
<html>
<head>
	<meta  charset=UTF-8"/>
	<title>详细内容</title>
	<link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function iFrameHeight() {
            var ifm= document.getElementById("frame_content");
            var subWeb = document.frames ? document.frames["frame_content"].document : ifm.contentDocument;
            if(ifm != null && subWeb != null) {
                ifm.height = subWeb.body.scrollHeight;
            }
        }
    </script>
</head>
<body>
<%@include file="header.html"%>
<div class="mianbaox"><span>您现在所在的位置：<a href="index.jsp">首页</a>-<span class="title"></span></span></div>
<div id="zbdetail_id" class="newMain">
	<span class="top"></span>

	<div class="main_gu">
		<div class="main_gu_left">
			<h4>【<strong class="title"></strong>】</h4>
			<ul>
				<li><a href="news.jsp?newsType=${param.newsType}" class="menu title"></a></li>
			</ul>
		</div>
		<div class="main_gu_right">
			<h4 class="title"></h4>

			<div class="txt">
				<iframe id="frame_content" name="frame_content" frameBorder=0 scrolling=no width="100%" height="100%" onLoad="iFrameHeight()"></iframe>
			</div>
			<div id="issue_user" style="height:30px;margin-bottom:3px;float:right;margin-right:65px;font-family:'微软雅黑';font-size:14px;"></div>
			<div class="clear"></div>
			<div id="fujian" style="display:none;">
				<div style="height:25px;float:left;font-weight:bold;font-family:'微软雅黑';font-size:15px;color:green;">附件 :</div>
				<div class="clear"></div>
				<div id="_document" style="border: solid 2px lightgray;min-height:150px;font-family:'微软雅黑';font-size:14px;"></div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div style="clear: both"></div>
</div>
<%@include file="foot.html"%>
<script type="text/javascript" src="js/lib/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/lib/underscore.js"></script>
<script type="text/javascript">
    var rootpath = '${pageContext.request.contextPath}';
    var fileId = '${param.fileId}';
    var newsType = '${param.newsType}';
    var issue_username = '${param.issue_username}';
    if(issue_username){
        issue_username=decodeURIComponent(issue_username);
    }
    var issue_date = '${param.issue_date}';
    var affix_fileid = '${param.affix_fileid}';
    $(function(){
        $("#issue_user").html('（发布人 ：' + issue_username + '&nbsp;&nbsp;' + issue_date+'）');

        var fileObj;
        var url = rootpath + '/wkmh/loadData.do';
        var param = {
            action: 'getFileName',
            affix_fileid: affix_fileid
        };

        $.ajax({
            url: url,
            data: param,
            method:'POST',
            async: false,
            success: function(returnXml){

                if(returnXml !=null && returnXml != ""){

                    fileObj = eval("(" + returnXml + ")");
                    var _length = fileObj.fileinfo.length;
                    if(_length>0){

                        $("#fujian").css("display","block");
                        for(var i = 0; i < _length; i++){

                            var obj = fileObj.fileinfo[i];
                            if(obj){

                                var fileid = obj.fileid;
                                var filename = obj.filename;

                                var $span = $(document.createElement('span'));
                                $span.text((i + 1) + ' 、');
                                $span.attr('style', 'float:left;margin-left:10px;margin-top:5px;');
                                $("#_document").append($span);

                                var $a = $(document.createElement('a'));
                                $a.attr('href', 'javascript:void(0);');
                                $a.attr('title', filename);
                                $a.attr('style', 'float:left;margin-top:5px;width:350px;');
                                $a.attr('id', fileid);

                                $a.text(filename);

                                $a.click(function (e){

                                    var _fileid = $(this).attr('id');
                                    var url = rootpath + '/common/updownfile/jsp/updownfile.do?action=DownFile&fileId='+_fileid+'&downWay=2&thumbnail=';
                                    window.open(url,"_blank","toolbar=no,menubar=no,scrollbars=no,center=yes,alwaysLowered=yes,resizable=no,location=no,status=no,width=100,height=100");

                                    e.preventDefault();
                                    return false;
                                });

                                $("#_document").append($a);
                            }
                        }
                    }
                }
            },
            error: function (e) {
                alert(e);
            }
        });

    });
</script>
<script type="text/javascript" src="js/pagejs/news_detail.js"></script>
</body>
</html>

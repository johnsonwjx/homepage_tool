<%@ page language="java"  isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset=UTF-8"/>
	<title>内容列表</title>
	<link href="css/style.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
		var rootpath = '${pageContext.request.contextPath}';
		var newsType = '${param.newsType}';
	</script>
</head>
<body>
<!--头部 -->
<%@ include file="header.html"%>
<!--推广焦点图 -->
<div class="mianbaox">您现在所在的位置：<a href="index.jsp">首页</a>-<span class="title"></span></div>
<div class="newMain">
	<span class="top"></span>

	<div class="main_gu">
		<div class="main_gu_left">
			<ul>
				<li class="hover"><a href="news.jsp?newsType=${param.newsType}" class="menu title"></a></li>
			</ul>
		</div>
		<div id="gu_right_id" class="main_gu_right">
			<div class="div-title">
				<div class="div-search-left">
					
				</div>
				<div class="div-search-right search-news"><a><label for="search-input">搜索</label></a>
				</div>
				<div class="div-search">
					<div class="search-icon search-news"></div>
					<div class="div-input">
						<input class="search-input no-bootstrap" id="search-input"
						       onfocus="if (value=='请输入关键字…') {value='';this.style.color='#000000';}"
						       onblur="if(!value){value='请输入关键字…';this.style.color='#999999';}" type="text"
						       value="请输入关键字…">
					</div>
				</div>
			</div>
			<div class="txt">
				<div id="_newslist" style="height:0px;"></div>
				<ul id="newslist">
				</ul>
				<div class="fanye">
					<div class="tj">
						<span>共<strong id="total"></strong>条信息</span>
					</div>
					<div class="search">
                        <span style="float:left;">
                            <input id="pageindex_id" name="" type="text"
                                   style="width:30px;height:14px;line-height:14px;margin-right:3px;">
                        </span>
						<span id="gotoPage" style="float:left;height:18px;display:block;">
							<img alt="" src="images/search_btn.jpg" width="27" height="18">
						</span>
					</div>
					<div class="fanyeTx">
						<a id="first-page">&lt;&lt;</a>
						<a id="pre-page">&lt;</a>
						<span id="pages"></span>
						<a id="next-page">&gt;</a>
						<a id="last-page">&gt;&gt;</a>
					</div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
<%@include file="foot.html"%>
<script type="text/javascript" src="js/lib/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/lib/underscore.js"></script>
<script type="text/javascript" src="js/pagejs/news.js"></script>
</body>
</html>

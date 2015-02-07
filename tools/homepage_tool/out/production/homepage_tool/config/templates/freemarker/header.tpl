<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8"/>
</head>
<body>
<div class="header">
	<div class="logo">
		<div class="logoTx">
			<a href="index.jsp"><img src="${image_path}${header.logopath}"  alt="logo"/></a>
		</div>
		<div style="width:20%;float:left;margin-top: 20px;margin-left: 10px;line-height:18px;font-size:14px;font-family:'微软雅黑';color:#179D49;font-weight:bold;">
			<#list header.txt1 as o>
             <div>${o}</div>
            </#list>
		</div>
		<div class="qqtx1">
			<div class="qqtxxb">
                <#list header.txt2 as o>
                <div><label style="font-size:14px;color:#179D49;font-weight:bold; font-family:'微软雅黑';">${o}</label></div>
                </#list>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="nav">
		<div>
			<ul>
                <#list header.navs as o>
                    <li><a href="${o.url}" class="white">${o.title}</a></li>
                </#list>

			</ul>
		</div>
	</div>
</div>
<div id="adv" style="width: 1000px; margin: auto auto 0px;" >
<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,11" width="1000">
    <embed src="${image_path}${header.flashpath}"  width="1000" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" wmode="transparent"></embed>
</object>
</div>
        </body>
</html>
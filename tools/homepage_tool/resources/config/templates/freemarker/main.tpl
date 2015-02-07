<!--中间内容区 -->
<div class="main">
    <div class="mainLeft">
        <div class="login">
            <h5>
                <span id="welcome" style="margin-left:40px;margin-top:7px;font-size:13px;font-family:'微软雅黑';text-align: left; float: left;"></span>
                <a href="javascript:void(0);" onclick="closeWindow();" style="margin-right:5px;margin-top:7px;text-align: right; float: right;">
                    <img src="images/logout.png" alt="" style="width: 90px;height: 24px"/>
                </a>
            </h5>
            <div class="loginTx">
            </div>
        </div>
        <#list main.left_tabs as item>
        <div class="tongzhi" style="height:${item.height}px;color: #ffffff;cursor:auto;">
            <h5 title="${item.title}" style="background:url(${image_path}${ item.title_logo}) no-repeat;" ></h5>
            <div id="${item.newType}" style="height:${item.height-40}px;" class="tzTx" ></div>
        </div>
    </#list>
</div>
<div class="mainRight">
    <#list main.right_tabs as item>
        <#if item_index%2==0>
            <div class="newsBox">
            <div class="over">
        </#if>
<div class="news">
    <div class="news-title">
        <div><label class="green large">${item.title}</label><a href="news.jsp?newsType=${item.newType}" class="green more">更多>></a></div>
        <div class="hr"><span class="hr-hightlight"></span><span class="hr-gray"></span></div>
    </div>
    <div id="${item.newType}" class="zonghezixunTx">
    </div>
    </div>
        <#if item_index%2!=0||!item_has_next>
            </div>
            </div>
        </#if>
 </#list>
 </div>
    <div class="clear"></div>
 </div>
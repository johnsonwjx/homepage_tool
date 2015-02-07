
<div class="foot">
    <div class="container" style="width:700px; margin:auto;">
        <div>
            <div class="weixin" style="float: left; margin-right: 5px">
                <img src="${image_path}${foot.image_winxin}" alt="微信" style="width: 70px;height: 70px"/>
            </div>
            <#list foot.txt1 as o>
             <div class="address" style="width:55%;float: left;text-align: left;font-family:'微软雅黑';font-size: 13px;">
                    <p>${o}</p>
              </div>
            </#list>
            <#list foot.txt2 as o>
                <div  class="address" style="width:26%;float: left;text-align: left;font-family:'微软雅黑';font-size: 13px;">
                  <p>${o}</p>
              </div>
            </#list>
            <div style="clear: both;"></div>
        </div>
    </div>
</div>

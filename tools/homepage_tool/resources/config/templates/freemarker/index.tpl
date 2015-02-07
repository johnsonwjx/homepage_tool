<%@ page language="java"  isELIgnored="false"%>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8"/>
    <title>${main.title}</title>
    <#noparse>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/lib/jquery-1.10.2.min.js"></script>
    <!--[if lt IE 7]>
    <script type="text/javascript" src="js/DD_belatedPNG_0.0.8a.js"></script>
    <script type="text/javascript">
    DD_belatedPNG.fix('*');
    </script>
    <![endif]-->
    <!--[if lte IE 6]>
    <style type="text/css">
        body {
            behavior: url("css/csshover.htc");
        }
    </style>
    <![endif]-->
</head>
<body>
<%@ include file="header.html" %>
</#noparse>
<#include "main.tpl">
<#noparse>
<%@ include file="foot.html"%>
<script type="text/javascript">
    window.rootpath = '${pageContext.request.contextPath}';
    window.accessid = "${sessionScope.sysAccessID}";
</script>
<script type="text/javascript" src="js/pagejs/index.js"></script>
<script type="text/javascript" src="js/pagejs/common.js"></script>
</#noparse>
</body>
</html>

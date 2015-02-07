<?xml version="1.0" encoding="UTF-8"?>
<root>
    <#list menus as o>
    <menu>
     <groupid>${o.groupid}</groupid>
     <name>${o.name}</name>
     <url>${o.url}</url>
    </menu>
    </#list>
</root>
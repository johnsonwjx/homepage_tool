<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:output method="xml" indent="yes"/>

   <xsl:template match="root">      
	<root>
	  <xsl:apply-templates select="table"/>
	</root>
   </xsl:template>

   <xsl:template match="table">
    <classes>
	<id><xsl:value-of select="@id"/></id>
	<fatherid><xsl:value-of select="@fatherid"/></fatherid>
	<name><xsl:value-of select="@name"/></name>
	<catalogid><xsl:value-of select="@catalogid"/></catalogid>
	<activeversion><xsl:value-of select="@activeversion"/></activeversion>
	<componentid><xsl:value-of select="@componentid"/></componentid>
	<codecontinuous><xsl:value-of select="@codecontinuous"/></codecontinuous>
	<billtablename><xsl:value-of select="@billtablename"/></billtablename>	
	<status><xsl:value-of select="@status"/></status>
	<canrepeat><xsl:value-of select="@canrepeat"/></canrepeat>
	<description><xsl:value-of select="@description"/></description>
	<alias><xsl:value-of select="@alias"/></alias>	
    <xsl:copy-of select="formclassusemode"/>	 
    <xsl:copy-of select="classmodules"/>
    <xsl:copy-of select="classcustommodules"/>    
    <xsl:apply-templates select="fields"/>
    </classes>
   </xsl:template>

   <xsl:template match="fields">
    <classitems>
     <xsl:apply-templates select="field"/>	
    </classitems>
   </xsl:template>

    <xsl:template match="field">
    <classitem>	
        <id><xsl:value-of select="@id"/></id>
        <version><xsl:value-of select="@version"/></version>
        <itemno><xsl:value-of select="@no"/></itemno>
        <itemname><xsl:value-of select="@name"/></itemname>
        <itemlabel><xsl:value-of select="@label"/></itemlabel>
        <itemtype><xsl:value-of select="@type"/></itemtype>
        <itemlength><xsl:value-of select="@length"/></itemlength>
        <itemdec><xsl:value-of select="@dec"/></itemdec>
        <itemdefault><xsl:value-of select="@default"/></itemdefault>
        <itemdescription><xsl:value-of select="@description"/></itemdescription>
    <!--     <recflag><xsl:value-of select="@recflag"/></recflag> -->
        <classid><xsl:value-of select="@classid"/></classid>
        <itemprop><xsl:value-of select="@itemprop"/></itemprop>        	
        <valuecondition><xsl:value-of select="@valuecondition"/></valuecondition>        	
          <xsl:copy-of select="itemmodules"/>	 
    </classitem>
   </xsl:template>

</xsl:stylesheet>
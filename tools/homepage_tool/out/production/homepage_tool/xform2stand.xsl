<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:output method="xml" indent="yes"/>

   <xsl:template match="root">      
	<root>
	  <xsl:apply-templates select="Classes"/>
	</root>
   </xsl:template>

   <xsl:template match="Classes">
	<xsl:element name="table">
	      <xsl:attribute name="id"><xsl:value-of select="id"/></xsl:attribute>
	      <xsl:attribute name="fatherid"><xsl:value-of select="fatherid"/></xsl:attribute>
	      <xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
	      <xsl:attribute name="description"><xsl:value-of select="description"/></xsl:attribute>
	      <xsl:attribute name="alias"><xsl:value-of select="alias"/></xsl:attribute>
	      <xsl:attribute name="codecontinuous"><xsl:value-of select="codecontinuous"/></xsl:attribute>
	      <xsl:attribute name="catalogid"><xsl:value-of select="catalogid"/></xsl:attribute>
	      <xsl:attribute name="billtablename"><xsl:value-of select="billtablename"/></xsl:attribute>
	      <xsl:attribute name="codefmt"></xsl:attribute>
	      <xsl:attribute name="componentid"><xsl:value-of select="componentid"/></xsl:attribute>
	      <xsl:attribute name="cname"></xsl:attribute>
	      <xsl:attribute name="fathertablename"></xsl:attribute>
	      <xsl:attribute name="mode"></xsl:attribute>
	      <xsl:attribute name="keyfieldname"></xsl:attribute>
	      <xsl:attribute name="code"></xsl:attribute>
          <xsl:attribute name="activeversion"><xsl:value-of select="activeversion"/></xsl:attribute>
          <xsl:attribute name="status"><xsl:value-of select="status"/></xsl:attribute>
          <xsl:attribute name="canrepeat"><xsl:value-of select="canrepeat"/></xsl:attribute>
	      <xsl:apply-templates select="indexs"/>
          <xsl:apply-templates select="classitems"/>
          <xsl:copy-of select="formclassusemode"/>	 
          <xsl:copy-of select="classmodules"/>
          <xsl:copy-of select="classcustommodules"/>
	</xsl:element>
   </xsl:template>

   <xsl:template match="indexs">
    <indexs>
    </indexs>
   </xsl:template>


   <xsl:template match="classitems">
    <fields>
     <xsl:apply-templates select="ClassItem"/>	
    </fields>
   </xsl:template>

   <xsl:template match="ClassItem">
	<xsl:element name="field">
	      <xsl:attribute name="name"><xsl:value-of select="itemname"/></xsl:attribute>
	      <xsl:attribute name="label"><xsl:value-of select="itemlabel"/></xsl:attribute>
	      <xsl:attribute name="type"><xsl:value-of select="itemtype"/></xsl:attribute>
	      <xsl:attribute name="length"><xsl:value-of select="itemlength"/></xsl:attribute>
	      <xsl:attribute name="dec"><xsl:value-of select="itemdec"/></xsl:attribute>
	      <xsl:attribute name="description"><xsl:value-of select="itemdescription"/></xsl:attribute>
	      <xsl:attribute name="default"><xsl:value-of select="default"/></xsl:attribute>
	      <xsl:attribute name="required"><xsl:value-of select="required"/></xsl:attribute>
	      <xsl:attribute name="isshow"></xsl:attribute>
	      <xsl:attribute name="null"></xsl:attribute>
	      <xsl:attribute name="isdept"></xsl:attribute>
	      <xsl:attribute name="ischeckrepeat"></xsl:attribute>
	      <xsl:attribute name="foreignkey"></xsl:attribute>
	      <xsl:attribute name="classid"><xsl:value-of select="classid"/></xsl:attribute>
	      <xsl:attribute name="worktype"></xsl:attribute>
	      
	      <xsl:attribute name="id"><xsl:value-of select="id"/></xsl:attribute>	      
	      <xsl:attribute name="version"><xsl:value-of select="version"/></xsl:attribute>
	      <xsl:attribute name="no"><xsl:value-of select="itemno"/></xsl:attribute>
	 <!--      <xsl:attribute name="recflag"><xsl:value-of select="recflag"/></xsl:attribute> -->   
	      <xsl:attribute name="itemprop"><xsl:value-of select="itemprop"/></xsl:attribute>
	      <xsl:attribute name="valuecondition"><xsl:value-of select="valuecondition"/></xsl:attribute>	      

          <xsl:copy-of select="itemmodules"/>

   
	</xsl:element>
   </xsl:template>


</xsl:stylesheet>